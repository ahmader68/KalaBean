package com.intek.kalabean.Main_Page;


import android.content.Context;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.intek.kalabean.Adapters.ViewPagerAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Chain_Store.ChainFragment;
import com.intek.kalabean.Complex.ComplexFragment;
import com.intek.kalabean.Definition_Store.DefinitionFragment;
import com.intek.kalabean.Edit_User.EditUserFragment;
import com.intek.kalabean.Home.HomeFragment;
import com.intek.kalabean.Login.LoginFragment;
import com.intek.kalabean.Markets.MarketsFragment;
import com.intek.kalabean.R;
import com.intek.kalabean.Register.RegisterFragment;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Objects;

import static com.intek.kalabean.Edit_User.EditUserFragment.PERMISSION_REQUEST;

public class MainFragment extends BaseFragment implements MainContract.View {
    private DrawerLayout drawer;
    private Fragment fragment;

    @Override
    public int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void setupViews() {
        NavigationView navigationView = rootView.findViewById(R.id.navigation);
        TabLayout tabLayout = rootView.findViewById(R.id.tabLayout);
        ViewPager viewPager = rootView.findViewById(R.id.viewPager);
        drawer = rootView.findViewById(R.id.drawer);
        ImageView hamburgMenu = rootView.findViewById(R.id.hamburgMenu);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
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

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MarketsFragment(), "بازارها");
        viewPagerAdapter.addFragment(new ComplexFragment(), "مجتمع تجاری");
        viewPagerAdapter.addFragment(new ChainFragment(), "فروشگاه زنجیره ای");
        viewPagerAdapter.addFragment(new HomeFragment(), "خانه");
        viewPagerAdapter.addFragment(new DefinitionFragment(), "ثبت فروشگاه");

        viewPager.setRotationY(180);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


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
                }else if(id == R.id.editInfo){
                    drawer.closeDrawer(GravityCompat.START);
                    fragment = new EditUserFragment();
                }
                FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frm_MainActivity_mainLayout,fragment);
                transaction.commit();
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
}
