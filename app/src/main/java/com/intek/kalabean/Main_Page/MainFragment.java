package com.intek.kalabean.Main_Page;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.intek.kalabean.AddProduct.AddProductFragment;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Brands.BrandsFragment;
import com.intek.kalabean.Category.CatFragment;
import com.intek.kalabean.Chain_Store.ChainFragment;
import com.intek.kalabean.City.CityFragment;
import com.intek.kalabean.Complex.ComplexFragment;
import com.intek.kalabean.Definition_Store.DefinitionFragment;
import com.intek.kalabean.Fragment.ShowWebFragment;
import com.intek.kalabean.Home.HomeFragment;
import com.intek.kalabean.Login_With_User_Pass.LoginWithUserPassFragment;
import com.intek.kalabean.Markets.MarketsFragment;
import com.intek.kalabean.Oridinary_User_Profile0.OUFragment;
import com.intek.kalabean.R;
import com.intek.kalabean.Register.RegisterFragment;
import com.intek.kalabean.Request_Product0.RequestFragment;
import com.intek.kalabean.Search.SearchFragment;
import com.intek.kalabean.VIP_User_Profile.VUFragment;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Objects;
import java.util.zip.Inflater;

import static com.intek.kalabean.Edit_User.EditUserFragment.PERMISSION_REQUEST;

public class MainFragment extends BaseFragment implements MainContract.View {
    private DrawerLayout drawer;
    private Fragment fragment;
    private boolean checkExit = false;
    private EditText edt_toolbar_search;
    private ImageView kalabeanIcon;
    private NavigationView navigationView;
    private FloatingActionButton fbtnPlus;
    private int storeId = 0, userId;
    private ImageView imgTelegram, imgInstagram, imgWeb;
    private String check, email;

    private ConstraintLayout
            con_drawerMenu_markets ,
            con_drawerMenu_Complex ,
            con_drawerMenu_chainStore ,
            con_drawerMenu_best,
            con_bottom_home,
            con_bottom_category,
            con_bottom_cities,
            con_bottom_login,
            searchToolbar;

    private TextView txtLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void setupViews() {
        con_bottom_home = rootView.findViewById(R.id.con_bottom_home);
        con_bottom_category = rootView.findViewById(R.id.con_bottom_category);
        con_bottom_cities = rootView.findViewById(R.id.con_bottom_city);
        con_bottom_login = rootView.findViewById(R.id.con_bottom_login);
        txtLogin = rootView.findViewById(R.id.txt_bottom_login);
        fbtnPlus = rootView.findViewById(R.id.floatingActionButton);
        navigationView = rootView.findViewById(R.id.navigation);
        drawer = rootView.findViewById(R.id.drawer);
        ImageView hamburgMenu = rootView.findViewById(R.id.hamburgMenu);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        check = sharedPreferences.getString("username", null);
        email = sharedPreferences.getString("email", null);
        userId = sharedPreferences.getInt("userid", 0);
        storeId = sharedPreferences.getInt("storeId", 0);




        if (userId > 0) {
            txtLogin.setText(R.string.profile);
        } else {
            txtLogin.setText(R.string.enter);

        }


        if (check != null) {
            hideItems(2);

        } else {
            hideItems(1);
        }
        searchToolbar = rootView.findViewById(R.id.search_toolbar);
        edt_toolbar_search = rootView.findViewById(R.id.edt_toolbar_search);
        kalabeanIcon = rootView.findViewById(R.id.kalabeanIcon);

        searchToolbar.setOnClickListener(v -> {
            if (edt_toolbar_search.getVisibility() == View.INVISIBLE) {
                edt_toolbar_search.setVisibility(View.VISIBLE);

                edt_toolbar_search.requestFocus();
                InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edt_toolbar_search, InputMethodManager.SHOW_IMPLICIT);

                kalabeanIcon.setVisibility(View.INVISIBLE);
            } else if (edt_toolbar_search.getVisibility() == View.VISIBLE) {
                edt_toolbar_search.setVisibility(View.INVISIBLE);
                InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edt_toolbar_search.getWindowToken(), 0);
                kalabeanIcon.setVisibility(View.VISIBLE);
            }
        });

        navigationView.setItemIconTintList(null);

        Menu menu = navigationView.getMenu();
        View view = menu.findItem(R.id.social_items).getActionView();
        imgInstagram = view.findViewById(R.id.img_drawerMenu_instagram);
        imgTelegram = view.findViewById(R.id.img_drawerMenu_telegram);
        imgWeb = view.findViewById(R.id.img_drawerMenu_website);
        imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instagram = new Intent(Intent.ACTION_VIEW);
                instagram.setData(Uri.parse("https://www.instagram.com/kalabean.co"));
                startActivity(instagram);

            }
        });

        imgTelegram.setOnClickListener(v -> {
            String account = "kalabean";
            Intent telegram = new Intent(Intent.ACTION_VIEW);
            telegram.setData(Uri.parse("http://telegram.me/" + account));
            startActivity(telegram);
        });

        imgWeb.setOnClickListener(v -> {
            Fragment webFragment = new ShowWebFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frm_MainActivity_mainLayout, webFragment);
            transaction.commit();
        });

        //menu.findItem(R.id.login).setVisible(false);
        //menu.findItem(R.id.sabtenam).setVisible(false);

        hamburgMenu.setOnClickListener(view1 -> drawer.openDrawer(GravityCompat.START));


        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();

//                if (id == R.id.login) {
//                    drawer.closeDrawer(GravityCompat.START);
//                    fragment = new LoginFragment();
//                }
            if (id == R.id.item_drawer_register) {
                drawer.closeDrawer(GravityCompat.START);
                fragment = new RegisterFragment();
            } else if (id == R.id.item_drawer_category) {
                drawer.closeDrawer(GravityCompat.START);
                fragment = new CatFragment();
//                     Toast.makeText(getViewContext(), "به زودی", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.item_drawer_cities) {

                drawer.closeDrawer(GravityCompat.START);


            } else if (id == R.id.item_drawer_language) {

                drawer.closeDrawer(GravityCompat.START);


            } else if (id == R.id.item_drawer_about) {

                drawer.closeDrawer(GravityCompat.START);


            } else if (id == R.id.item_drawer_article) {

                drawer.closeDrawer(GravityCompat.START);


            }  else if (id == R.id.item_drawer_guide) {

                drawer.closeDrawer(GravityCompat.START);


            } else if (id == R.id.item_drawer_support) {

                drawer.closeDrawer(GravityCompat.START);

            }else if(id == R.id.item_drawer_search){
                drawer.closeDrawer(GravityCompat.START);
                fragment = new SearchFragment();

            } else if (id == R.id.item_drawer_exit) {
                sharedPreferences.edit().clear().apply();
                txtLogin.setText(R.string.enter);
                hideItems(1);
                userId = 0;
                check =null;
                storeId = 0;
                email = null;
                showMessage(getResources().getString(R.string.toast_logout));
                fragment = new MainFragment();
                drawer.closeDrawer(GravityCompat.START);
            }
            FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frm_fragmentMain_mainLayout, fragment);
            transaction.commit();


            return true;
        });

        fragment = new HomeFragment();
        FragmentManager managers = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction transactions = managers.beginTransaction();
        transactions.replace(R.id.frm_fragmentMain_mainLayout, fragment);
        transactions.commit();

        con_bottom_home.setOnClickListener(v -> {
            fragment = new HomeFragment();
            changePage(fragment);
        });

        con_bottom_category.setOnClickListener(v ->{
            if (userId > 0) {
                fragment = new CatFragment();
            } else {
                showMessage(getResources().getString(R.string.toast_first_login));
                fragment = new LoginWithUserPassFragment();
            }
            changePage(fragment);
        });

        con_bottom_cities.setOnClickListener(v ->{
            fragment = new CityFragment();
            changePage(fragment);
        });

        con_bottom_login.setOnClickListener(v ->{
            if (userId <= 0) {
                fragment = new LoginWithUserPassFragment();
            } else {
                fragment = new VUFragment();
            }

            changePage(fragment);
        });


        fbtnPlus.setOnClickListener(v -> {
            if (userId > 0) {
                if (storeId > 0) {
                    fragment = new AddProductFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frm_fragmentMain_mainLayout, fragment);
                    transaction.commit();
                } else {
                    fragment = new DefinitionFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frm_fragmentMain_mainLayout, fragment);
                    transaction.commit();
                }
            } else {
                fragment = new LoginWithUserPassFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_fragmentMain_mainLayout, fragment);
                transaction.commit();
            }
        });

        View header = navigationView.getHeaderView(0);
        con_drawerMenu_markets = header.findViewById(R.id.con_drawerMenu_markets);
        con_drawerMenu_Complex = header.findViewById(R.id.con_drawerMenu_Complex);
        con_drawerMenu_chainStore = header.findViewById(R.id.con_drawerMenu_chainStore);
        con_drawerMenu_best = header.findViewById(R.id.con_drawerMenu_best);

        con_drawerMenu_markets.setOnClickListener(v -> {
            fragment = new MarketsFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frm_fragmentMain_mainLayout, fragment);
            transaction.commit();
            drawer.closeDrawer(GravityCompat.START);
        });

        con_drawerMenu_Complex.setOnClickListener(v -> {
            fragment = new ComplexFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frm_fragmentMain_mainLayout, fragment);
            transaction.commit();
            drawer.closeDrawer(GravityCompat.START);
        });

        con_drawerMenu_chainStore.setOnClickListener(v -> {
            fragment = new ChainFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frm_fragmentMain_mainLayout, fragment);
            transaction.commit();
            drawer.closeDrawer(GravityCompat.START);
        });

        con_drawerMenu_best.setOnClickListener(v -> {
            fragment = new BrandsFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frm_fragmentMain_mainLayout, fragment);
            transaction.commit();
            drawer.closeDrawer(GravityCompat.START);
        });
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(Objects.requireNonNull(getActivity()));
            } else {
                showMessage(getResources().getString(R.string.toast_not_allowed));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() == null) {
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((view, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    if (checkExit) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Objects.requireNonNull(getActivity()).finish();
                        System.exit(0);
                    } else if (!checkExit) {
                        showMessage(getResources().getString(R.string.toast_press_back_again));
                        new Handler().postDelayed(() -> checkExit = false, 2000);
                        checkExit = true;
                    }
                }
            }
            return false;
        });

    }

    private void hideItems(int id) {
        Menu navMenu = navigationView.getMenu();
        switch (id) {
            case 1:
                navMenu.findItem(R.id.item_drawer_register).setVisible(true);
                navMenu.findItem(R.id.item_drawer_category).setVisible(false);
                navMenu.findItem(R.id.item_drawer_exit).setVisible(false);
                break;
            case 2:
                navMenu.findItem(R.id.item_drawer_register).setVisible(false);
                navMenu.findItem(R.id.item_drawer_category).setVisible(true);
                navMenu.findItem(R.id.item_drawer_exit).setVisible(true);
                break;

        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void changePage(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_fragmentMain_mainLayout, fragment);
        transaction.commit();
    }
}
