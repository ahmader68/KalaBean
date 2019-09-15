package com.intek.kalabean;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.intek.kalabean.Login.LoginFragment;
import com.intek.kalabean.Main_Page.MainFragment;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import static com.intek.kalabean.Definition_Store.DefinitionFragment.PERMISSION_UPLOAD_REQUEST_CODE;
import static com.intek.kalabean.Edit_User.EditUserFragment.PERMISSION_REQUEST;
import static com.intek.kalabean.Login.LoginFragment.GOOGLE_LOGIN_REQUEST;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    SharedPreferences sharedPreferences;

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
            case PERMISSION_UPLOAD_REQUEST_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(this);
                }else{
                    Toast.makeText(this, "اجازه دسترسی داده نشد.", Toast.LENGTH_SHORT).show();
                }
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
//                case GOOGLE_LOGIN_REQUEST:
//                    try{
//                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//                        GoogleSignInAccount account = task.getResult(ApiException.class);
//                        onLoggedIn(account);
//                    }catch (ApiException e){
//                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                    break;

            }
        }
    }
//    private void onLoggedIn(GoogleSignInAccount googleSignInAccount){
//        String username = googleSignInAccount.getEmail();
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("username",username);
//        editor.apply();
//        editor.commit();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.frm_MainActivity_mainLayout,new MainFragment());
//        transaction.commit();
//
//    }
}
