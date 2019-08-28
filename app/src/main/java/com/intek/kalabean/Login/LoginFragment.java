package com.intek.kalabean.Login;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.R;

public class LoginFragment extends BaseFragment implements LoginContract.View {
    TextInputLayout tilFragmentLoginUsername;
    TextInputLayout tilFragmentLoginPassword;
    TextInputEditText edtFragmentLoginUsername;
    TextInputEditText edtFragmentLoginPassword;
    Button btnFragmentLoginLogin;
    ConstraintLayout conLogin;
    @Override
    public int getLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void setupViews() {

        conLogin = rootView.findViewById(R.id.con_fragmentLogin_mainLayout);
        conLogin.setRotationY(180);

        tilFragmentLoginUsername = rootView.findViewById(R.id.til_fragmentLogin_username);
        tilFragmentLoginPassword = rootView.findViewById(R.id.til_fragmentLogin_password);
        edtFragmentLoginUsername = rootView.findViewById(R.id.edt_fragmentLogin_username);
        edtFragmentLoginPassword = rootView.findViewById(R.id.edt_fragmentLogin_password);
        btnFragmentLoginLogin = rootView.findViewById(R.id.btn_fragmentLogin_login);
        btnFragmentLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUsername() || !validatePassword()){
                    return;
                }else{
                    Toast.makeText(getViewContext(), "Continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
    private boolean validateUsername(){
        String usernameInput = edtFragmentLoginUsername.getText().toString().trim();
        if(usernameInput.isEmpty()){
            tilFragmentLoginUsername.setError("فیلد نام کاربری خالی است");
            edtFragmentLoginUsername.requestFocus();
            return false;
        }else{
            tilFragmentLoginUsername.setError(null);
            return true;
        }
    }
    private boolean validatePassword(){
        String passwordInput = edtFragmentLoginPassword.getText().toString().trim();
        if(passwordInput.isEmpty()){
            tilFragmentLoginPassword.setError("فیلد کلمه عبور خالی است");
            edtFragmentLoginPassword.requestFocus();
            return false;
        }else {
            tilFragmentLoginPassword.setError(null);
            return true;
        }
    }
}
