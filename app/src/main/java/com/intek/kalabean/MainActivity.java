package com.intek.kalabean;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import com.intek.kalabean.Main_Page.MainFragment;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import static com.intek.kalabean.Edit_User.EditUserFragment.PERMISSION_REQUEST;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    public static int requestCodeCheck = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.frm_MainActivity_mainLayout,new MainFragment());
        transaction.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(this);
                } else {
                    Toast.makeText(this, "اجازه دسترسی داده نشد.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    CropImage.ActivityResult result = CropImage.getActivityResult(data);
                    if (resultCode == RESULT_OK) {
                        if(requestCodeCheck == 1) {
                            Picasso.get().load(result.getUri()).into((ImageView) findViewById(R.id.img_fragmentDefinition_storePic));
                        }else if(requestCodeCheck == 2){
                            Picasso.get().load(result.getUri()).into((ImageView)findViewById(R.id.img_fragmentEditUser_profile));
                        }
                    } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                        Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
                    }
                    break;

            }
        }
    }


}
