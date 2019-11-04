package com.intek.kalabean.Main_Page;


import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Definition_Store.DefinitionFragment;
import com.intek.kalabean.Home.HomeFragment;
import com.intek.kalabean.Oridinary_User_Profile.OUFragment;
import com.intek.kalabean.R;
import com.intek.kalabean.Register.RegisterFragment;
import com.intek.kalabean.Request_Product.RequestFragment;
import com.intek.kalabean.Shops.ShopsFragment;
import com.intek.kalabean.ShowShop.ShowShopFragment;
import com.intek.kalabean.ShowUserShop.UserShopFragment;
import com.intek.kalabean.Ticket.TicketFragment;
import com.intek.kalabean.VIP_User_Profile.VUFragment;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Objects;

import static com.intek.kalabean.Edit_User.EditUserFragment.PERMISSION_REQUEST;

public class MainFragment extends BaseFragment implements MainContract.View {
    private DrawerLayout drawer;
    private Fragment fragment;
    private boolean checkExit = false;
    private BottomNavigationView bottomNavigationView;
    private EditText edt_toolbar_search;
    private ImageView kalabeanIcon;

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

        bottomNavigationView = rootView.findViewById(R.id.bottomNavigationView);

        NavigationView navigationView = rootView.findViewById(R.id.navigation);
        drawer = rootView.findViewById(R.id.drawer);
        ImageView hamburgMenu = rootView.findViewById(R.id.hamburgMenu);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        String check = sharedPreferences.getString("username", null);
        String email = sharedPreferences.getString("email", null);

        ConstraintLayout searchToolbar = rootView.findViewById(R.id.search_toolbar);
        edt_toolbar_search = rootView.findViewById(R.id.edt_toolbar_search);
        kalabeanIcon = rootView.findViewById(R.id.kalabeanIcon);

        searchToolbar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (edt_toolbar_search.getVisibility() == View.INVISIBLE) {
                    edt_toolbar_search.setVisibility(View.VISIBLE);

                    //InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.showSoftInput(edt_toolbar_search, InputMethodManager.SHOW_IMPLICIT);

                    kalabeanIcon.setVisibility(View.INVISIBLE);
                } else if (edt_toolbar_search.getVisibility() == View.VISIBLE) {
                    edt_toolbar_search.setVisibility(View.INVISIBLE);
                    kalabeanIcon.setVisibility(View.VISIBLE);
                }
            }
        });

        navigationView.setItemIconTintList(null);
        if (check != null || email != null) {
            Menu menu = navigationView.getMenu();
            //menu.findItem(R.id.login).setVisible(false);
            //menu.findItem(R.id.sabtenam).setVisible(false);
        }
        hamburgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

//                if (id == R.id.login) {
//                    drawer.closeDrawer(GravityCompat.START);
//                    fragment = new LoginFragment();
//                }
                if (id == R.id.item_drawer_cities) {
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new RequestFragment();
                } else if (id == R.id.item_drawer_about) {
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new RegisterFragment();
                } else if (id == R.id.item_drawer_article) {
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new VUFragment();
                }
                FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frm_fragmentMain_mainLayout, fragment);
                transaction.commit();


                return true;
            }
        });

        fragment = new HomeFragment();
        FragmentManager managers = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction transactions = managers.beginTransaction();
        transactions.replace(R.id.frm_fragmentMain_mainLayout, fragment);
        transactions.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:

                        break;
                    case R.id.cat:
                        //fragment = new EditUserFragment();
                        break;
                    case R.id.cities:
                        //fragment = new EditUserFragment();
                        break;
                    case R.id.enter:
                        //fragment = new EditUserFragment();
                        break;
                }

                return true;
            }
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
                Toast.makeText(getViewContext(), "اجازه دسترسی داده نشد.", Toast.LENGTH_SHORT).show();
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
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
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
                            Toast.makeText(getViewContext(), "برای خروج دکمه بازگشت را مجدد کلیک کنید", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    checkExit = false;
                                }
                            }, 2000);
                            checkExit = true;
                        }
                    }
                }
                return false;
            }
        });

    }
}
