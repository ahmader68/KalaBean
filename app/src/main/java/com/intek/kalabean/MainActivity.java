package com.intek.kalabean;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import com.google.android.material.tabs.TabLayout;

import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.intek.kalabean.Adapters.ViewPagerAdapter;
import com.intek.kalabean.Edit_User.EditUserFragment;
import com.intek.kalabean.Home.HomeFragment;
import com.intek.kalabean.Login.LoginFragment;
import com.intek.kalabean.Register.RegisterFragment;

import static com.intek.kalabean.Edit_User.EditUserFragment.OPEN_GALLERY_REQUEST_CODE;
import static com.intek.kalabean.Edit_User.EditUserFragment.PERMISSION_REQUEST_CODE;

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            /*
            case PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //takePicture();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    long name = System.currentTimeMillis();
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(G.AppAddress + "/" + name + ".jpg")));
                    startActivityForResult(intent, TAKE_CODE);
                } else {
                    Toast.makeText(MainActivity.this, "You must accept permission", Toast.LENGTH_SHORT).show();
                }
                break;
            }*/
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, OPEN_GALLERY_REQUEST_CODE);
                }
                break;
            }
        }
    }
}
