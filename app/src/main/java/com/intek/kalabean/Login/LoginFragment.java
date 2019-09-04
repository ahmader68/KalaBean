package com.intek.kalabean.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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
    SignInButton btnFragmentLogingLogin;
    Button btnFragmentLoginLogin;
    ConstraintLayout conLogin;
    User user;
    LoginContract.Presenter presenter;
    private final int GOOGLE_LOGIN_REQUEST = 101;
    private static final String TAG = "AndroidClarified";
    GoogleSignInOptions gso;
    GoogleSignInClient googleSignInClient;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User();
        presenter = new LoginPresenter(new KalaBeanRepository());
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getViewContext(),gso);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void setupViews() {

        conLogin = rootView.findViewById(R.id.con_fragmentLogin_mainLayout);
        conLogin.setRotationY(180);
        btnFragmentLogingLogin = rootView.findViewById(R.id.btn_fragmentLogin_gLogin);
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
        btnFragmentLogingLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,GOOGLE_LOGIN_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case GOOGLE_LOGIN_REQUEST:
                    try{
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        onLoggedIn(account);
                    }catch (ApiException e){
                        Toast.makeText(getViewContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
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
    private void onLoggedIn(GoogleSignInAccount googleSignInAccount){
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

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
