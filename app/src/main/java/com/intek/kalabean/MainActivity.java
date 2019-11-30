package com.intek.kalabean;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.Init;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.intek.kalabean.Definition_Store.DefinitionFragment.PERMISSION_UPLOAD_REQUEST_CODE;
import static com.intek.kalabean.Edit_User0.EditUserFragment.PERMISSION_REQUEST;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable;
    public static int requestCodeCheck = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kalaBeanDataSource = new KalaBeanRepository();
        compositeDisposable = new CompositeDisposable();
        kalaBeanDataSource.getInit().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Init>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Init init) {
                        getInit(init);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.frm_MainActivity_mainLayout, new MainFragment());
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
            case PERMISSION_UPLOAD_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(this);
                } else {
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
//                        if (requestCodeCheck == 1) {
//                            Picasso.get().load(result.getUri()).into((ImageView) findViewById(R.id.img_fragmentDefinition_storePic));
                    }
                    if (requestCodeCheck == 2) {
                        Picasso.get().load(result.getUri()).into((ImageView) findViewById(R.id.img_fragmentEditUser_profile));
                    } else if (requestCodeCheck == 3) {
                        Picasso.get().load(result.getUri()).into((ImageView) findViewById(R.id.img_fragmentAddProduct_image));
                    } else if (requestCodeCheck == 4) {
                        Picasso.get().load(result.getUri()).into((ImageView) findViewById(R.id.img_fragmentDefinition_srcInner));


                    }else if(requestCodeCheck == 5){
                        Picasso.get().load(result.getUri()).into((ImageView) findViewById(R.id.img_fragmentDefinition_srcOuter));
                    }else if(requestCodeCheck == 6){
                        Picasso.get().load(result.getUri()).into((ImageView) findViewById(R.id.img_fragmentRequestProduct_srcProduct));
                    }
                    //}
                    else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                        Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
                    }
                    break;


            }
        }
    }

    private void getInit(Init init) {
        Toast.makeText(this, init.getCompany(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }
}

//Get App Version


//        try{
//                PackageInfo packageInfo = this.getPackageManager().getPackageInfo(getPackageName(),0);
//                String versionName = packageInfo.versionName;
//                int versionCode = packageInfo.versionCode;
//                Toast.makeText(this, versionName + "/" + versionCode, Toast.LENGTH_SHORT).show();
//                }catch (PackageManager.NameNotFoundException e){
//                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
//                }
