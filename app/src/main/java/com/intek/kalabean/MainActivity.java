package com.intek.kalabean;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intek.kalabean.Adapters.ViewPagerAdapter;
import com.intek.kalabean.Classes.G;
import com.intek.kalabean.Classes.Upload;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Data.ServerDataSource;
import com.intek.kalabean.Definition_Store.DefinitionFragment;
import com.intek.kalabean.Edit_User.EditUserFragment;
import com.intek.kalabean.Home.HomeFragment;
import com.intek.kalabean.Login.LoginFragment;
import com.intek.kalabean.Markets.MarketsFragment;
import com.intek.kalabean.Model.User;
import com.intek.kalabean.Register.RegisterFragment;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.zip.Inflater;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.intek.kalabean.Edit_User.EditUserFragment.OPEN_GALLERY_REQUEST_CODE;
import static com.intek.kalabean.Edit_User.EditUserFragment.PERMISSION_REQUEST;
import static com.intek.kalabean.Edit_User.EditUserFragment.PERMISSION_REQUEST_CODE;
import static com.intek.kalabean.Edit_User.EditUserFragment.TAKE_CODE;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawer;
    ImageView hamburgMenu;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    Dialog dialogLogin;
    Button btnLoginDialogLogin;
    TextInputEditText edtLoginDialogUsername;
    TextInputEditText edtLoginDialogPassword;
    TextInputLayout tilLoginDialogUsername;
    TextInputLayout tilLoginDialogPassword;
    User user;
    KalaBeanDataSource kalaBeanDataSource;
    CompositeDisposable compositeDisposable;
    private final int GOOGLE_LOGIN_REQUEST = 101;
    private static final String TAG = "AndroidClarified";
//    GoogleSignInOptions gso;
//    SignInButton btnLoginDialogGoogle;
//    GoogleSignInClient googleSignInClient;
    String googleUsername;
    String googleEmail;
    private int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new User();
        compositeDisposable = new CompositeDisposable();
        kalaBeanDataSource = new KalaBeanRepository();
        navigationView = findViewById(R.id.navigation);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        drawer = findViewById(R.id.drawer);
        hamburgMenu = findViewById(R.id.hamburgMenu);

        hamburgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MarketsFragment(), "بازارها");
        viewPagerAdapter.addFragment(new HomeFragment(), "خانه");
        viewPagerAdapter.addFragment(new DefinitionFragment(),"ثبت فروشگاه");

        viewPager.setRotationY(180);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        dialogLogin = new Dialog(this);
        dialogLogin.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogLogin.setContentView(R.layout.fragment_login);
        btnLoginDialogLogin = dialogLogin.findViewById(R.id.btn_fragmentLogin_login);
        edtLoginDialogUsername = dialogLogin.findViewById(R.id.edt_fragmentLogin_username);
        edtLoginDialogPassword = dialogLogin.findViewById(R.id.edt_fragmentLogin_password);
        tilLoginDialogUsername = dialogLogin.findViewById(R.id.til_fragmentLogin_username);
        tilLoginDialogPassword = dialogLogin.findViewById(R.id.til_fragmentLogin_password);

        flag = getIntent().getIntExtra("flag",0);
        if(flag == 3){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.sabtenam).setVisible(false);
            menu.findItem(R.id.login).setVisible(false);
        }
        //btnLoginDialogGoogle = dialogLogin.findViewById(R.id.btn_fragmentLogin_gLogin);
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        googleSignInClient = GoogleSignIn.getClient(this, gso);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.login) {
                    drawer.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(MainActivity.this,SecondaryActivity.class);
                    intent.putExtra("flag",2);
                    startActivity(intent);
                }else if(id == R.id.sabtenam){
                    drawer.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(MainActivity.this,SecondaryActivity.class);
                    intent.putExtra("flag",1);
                    startActivity(intent);
                }
                return true;
            }
        });
//        btnLoginDialogLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!validateUsername() || !validatePassword()) {
//                    return;
//                } else {
//                    user.setMobile(edtLoginDialogUsername.getText().toString());
//                    user.setPassword(edtLoginDialogPassword.getText().toString());
//                    kalaBeanDataSource.login(user).subscribeOn(Schedulers.newThread())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new SingleObserver<User>() {
//                                @Override
//                                public void onSubscribe(Disposable d) {
//                                    compositeDisposable.add(d);
//                                }
//
//                                @Override
//                                public void onSuccess(User user) {
//                                    if (user.getResult() <= -1 && user.getResult() >= -3) {
//                                        switch (user.getResult()) {
//                                            case -1:
//                                                Toast.makeText(MainActivity.this, "نام کاربری یا کلمه عبور صحیح نمی باشد", Toast.LENGTH_SHORT).show();
//
//                                                break;
//                                            case -2:
//                                                Toast.makeText(MainActivity.this, "اکانت شما غیر فعال شده است", Toast.LENGTH_SHORT).show();
//
//                                                break;
//                                            case -3:
//                                                Toast.makeText(MainActivity.this, "کاربر با این مشخصات یافت نشد", Toast.LENGTH_SHORT).show();
//
//                                                break;
//                                        }
//                                    } else {
//                                        loginSuccess(user);
//                                    }
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                }
//            }
//        });
//        btnLoginDialogGoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent signInIntent = googleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, GOOGLE_LOGIN_REQUEST);
//            }
//        });
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
                            Picasso.get().load(result.getUri()).into((ImageView) findViewById(R.id.img_fragmentEditUser_profile));
                        } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                            Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
                        }
                    break;
            }
        }
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount){
        dialogLogin.dismiss();
        googleUsername = googleSignInAccount.getDisplayName();
        googleEmail = googleSignInAccount.getEmail();
        Toast.makeText(this, googleEmail, Toast.LENGTH_SHORT).show();

    }
    private boolean validatePassword() {
        String passwordInput = edtLoginDialogPassword.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            tilLoginDialogPassword.setError("فیلد کلمه عبور خالی است");
            edtLoginDialogPassword.requestFocus();
            return false;
        } else {
            tilLoginDialogPassword.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = edtLoginDialogUsername.getText().toString().trim();
        if (usernameInput.isEmpty()) {
            tilLoginDialogUsername.setError("فیلد نام کاربری خالی است");
            edtLoginDialogUsername.requestFocus();
            return false;
        } else {
            tilLoginDialogUsername.setError(null);
            return true;
        }
    }

    private void loginSuccess(User user) {
        Toast.makeText(this, user.getMobile(), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(alreadyloggedAccount != null){
            onLoggedIn(alreadyloggedAccount);
        }
    }
}
