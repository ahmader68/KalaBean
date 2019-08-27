package com.intek.kalabean;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.intek.kalabean.Adapters.ViewPagerAdapter;
import com.intek.kalabean.Edit_User.EditUserFragment;
import com.intek.kalabean.Home.HomeFragment;
import com.intek.kalabean.Login.LoginFragment;
import com.intek.kalabean.Register.RegisterFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    ImageView hamburgMenu;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        viewPagerAdapter.addFragment(new LoginFragment(),"ورود");
        viewPagerAdapter.addFragment(new EditUserFragment(),"ویرایش کاربر");
        viewPagerAdapter.addFragment(new RegisterFragment(),"ثبت نام");
        viewPagerAdapter.addFragment(new HomeFragment(),"خانه");

        viewPager.setRotationY(180);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
