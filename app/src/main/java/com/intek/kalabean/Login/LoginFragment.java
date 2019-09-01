package com.intek.kalabean.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.User;
import com.intek.kalabean.R;

public class LoginFragment extends BaseFragment implements LoginContract.View {
    TextInputLayout tilFragmentLoginUsername;
    TextInputLayout tilFragmentLoginPassword;
    TextInputEditText edtFragmentLoginUsername;
    TextInputEditText edtFragmentLoginPassword;
    Button btnFragmentLoginLogin;
    ConstraintLayout conLogin;
    User user;
    LoginContract.Presenter presenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User();
        presenter = new LoginPresenter(new KalaBeanRepository());
    }

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
                    user.setMobile(edtFragmentLoginUsername.getText().toString());
                    user.setPassword(edtFragmentLoginPassword.getText().toString());
                    presenter.login(user);
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

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(User user) {
        Toast.makeText(getViewContext(), user.getMobile(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
