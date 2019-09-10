package com.intek.kalabean.Main_Page;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intek.kalabean.Adapters.ViewPagerAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Definition_Store.DefinitionFragment;
import com.intek.kalabean.Home.HomeFragment;
import com.intek.kalabean.Login.LoginFragment;
import com.intek.kalabean.Markets.MarketsFragment;
import com.intek.kalabean.R;
import com.intek.kalabean.Register.RegisterFragment;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import static com.intek.kalabean.Edit_User.EditUserFragment.PERMISSION_REQUEST;

public class MainFragment extends BaseFragment implements MainContract.View {
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
    String googleUsername;
    String googleEmail;
    Fragment fragment;
    private int flag;
    SharedPreferences sharedPreferences;
    @Override
    public int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void setupViews() {
        navigationView = rootView.findViewById(R.id.navigation);
        tabLayout = rootView.findViewById(R.id.tabLayout);
        viewPager = rootView.findViewById(R.id.viewPager);
        drawer = rootView.findViewById(R.id.drawer);
        hamburgMenu = rootView.findViewById(R.id.hamburgMenu);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        String check = sharedPreferences.getString("username",null);
        if(check != null){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.login).setVisible(false);
            menu.findItem(R.id.sabtenam).setVisible(false);
        }
        hamburgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MarketsFragment(), "بازارها");
        viewPagerAdapter.addFragment(new HomeFragment(), "خانه");
        viewPagerAdapter.addFragment(new DefinitionFragment(), "ثبت فروشگاه");

        viewPager.setRotationY(180);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        dialogLogin = new Dialog(getViewContext());
        dialogLogin.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogLogin.setContentView(R.layout.fragment_login);
        btnLoginDialogLogin = dialogLogin.findViewById(R.id.btn_fragmentLogin_login);
        edtLoginDialogUsername = dialogLogin.findViewById(R.id.edt_fragmentLogin_username);
        edtLoginDialogPassword = dialogLogin.findViewById(R.id.edt_fragmentLogin_password);
        tilLoginDialogUsername = dialogLogin.findViewById(R.id.til_fragmentLogin_username);
        tilLoginDialogPassword = dialogLogin.findViewById(R.id.til_fragmentLogin_password);

//        flag = getIntent().getIntExtra("flag",0);
//        if(flag == 3){
//            Menu menu = navigationView.getMenu();
//            menu.findItem(R.id.sabtenam).setVisible(false);
//            menu.findItem(R.id.login).setVisible(false);
//        }
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
                    fragment = new LoginFragment();
                } else if (id == R.id.sabtenam) {
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new RegisterFragment();
                }
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frm_MainActivity_mainLayout,fragment);
                transaction.commit();
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
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(getActivity());
                } else {
                    Toast.makeText(getViewContext(), "اجازه دسترسی داده نشد.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


//    private void onLoggedIn(GoogleSignInAccount googleSignInAccount){
//        if(dialogLogin.isShowing()) {
//            dialogLogin.dismiss();
//        }
//        googleUsername = googleSignInAccount.getDisplayName();
//        googleEmail = googleSignInAccount.getEmail();
//        Toast.makeText(getViewContext(), googleEmail, Toast.LENGTH_SHORT).show();
//
//    }
//    private boolean validatePassword() {
//        String passwordInput = edtLoginDialogPassword.getText().toString().trim();
//        if (passwordInput.isEmpty()) {
//            tilLoginDialogPassword.setError("فیلد کلمه عبور خالی است");
//            edtLoginDialogPassword.requestFocus();
//            return false;
//        } else {
//            tilLoginDialogPassword.setError(null);
//            return true;
//        }
//    }
//
//    private boolean validateUsername() {
//        String usernameInput = edtLoginDialogUsername.getText().toString().trim();
//        if (usernameInput.isEmpty()) {
//            tilLoginDialogUsername.setError("فیلد نام کاربری خالی است");
//            edtLoginDialogUsername.requestFocus();
//            return false;
//        } else {
//            tilLoginDialogUsername.setError(null);
//            return true;
//        }
//    }
//
//    private void loginSuccess(User user) {
//        Toast.makeText(getViewContext(), user.getMobile(), Toast.LENGTH_SHORT).show();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        if(compositeDisposable != null && compositeDisposable.size() > 0){
//            compositeDisposable.clear();
//        }
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(getViewContext());
//        if(alreadyloggedAccount != null){
//            onLoggedIn(alreadyloggedAccount);
//        }
//    }
}
