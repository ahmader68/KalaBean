package com.intek.kalabean;

import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Login.LoginFragment;
import com.intek.kalabean.Register.RegisterFragment;

public class MainLoginRegisterFragment extends BaseFragment {

    private RadioGroup rgLoginRegister;

    private RadioButton rbLogin,rbRegister;

    private FrameLayout frmContent;

    private FragmentTransaction transaction;

    private Fragment loginFragment,registerFragment;

    @Override
    public int getLayout() {
        return R.layout.fragment_main_login_register;
    }

    @Override
    public void setupViews() {

        rgLoginRegister = rootView.findViewById(R.id.rg_fragmentMainLogin_radioGroup);

        rbLogin = rootView.findViewById(R.id.rb_fragmentMainLogin_login);

        rbRegister = rootView.findViewById(R.id.rb_fragmentMainLogin_register);

        frmContent = rootView.findViewById(R.id.frm_fragmentMainLogin_layout);

        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();

        rbLogin.setTextColor(getResources().getColor(R.color.colorWhite));

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_fragmentMainLogin_layout,loginFragment);
        transaction.commit();

        rgLoginRegister.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_fragmentMainLogin_login){
                    rbLogin.setTextColor(getResources().getColor(R.color.colorWhite));
                    rbRegister.setTextColor(getResources().getColor(R.color.black));
                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_from_left,R.anim.slide_out_to_right);
                    transaction.replace(R.id.frm_fragmentMainLogin_layout,loginFragment);
                    transaction.commit();
                }else if(checkedId == R.id.rb_fragmentMainLogin_register){
                    rbLogin.setTextColor(getResources().getColor(R.color.black));
                    rbRegister.setTextColor(getResources().getColor(R.color.colorWhite));
                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                    transaction.replace(R.id.frm_fragmentMainLogin_layout,registerFragment);
                    transaction.commit();
                }
            }
        });



    }
}
