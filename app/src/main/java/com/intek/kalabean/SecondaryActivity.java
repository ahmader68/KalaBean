package com.intek.kalabean;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.intek.kalabean.Login.LoginFragment;
import com.intek.kalabean.Register.RegisterFragment;

public class SecondaryActivity extends AppCompatActivity {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ConstraintLayout conSecondaryActivityMainLayout;
    private int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        conSecondaryActivityMainLayout = findViewById(R.id.con_secondaryActivity_mainLayout);
        conSecondaryActivityMainLayout.setRotationY(180);
        flag = getIntent().getIntExtra("flag",0);
        if(flag == 1) {
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction().replace(R.id.frm_secondaryActivity_fragmentHolder, new RegisterFragment());
            transaction.commit();
        }else if(flag == 2){
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction().replace(R.id.frm_secondaryActivity_fragmentHolder,new LoginFragment());
            transaction.commit();
        }
    }
}
