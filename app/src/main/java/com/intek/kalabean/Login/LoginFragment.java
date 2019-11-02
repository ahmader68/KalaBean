package com.intek.kalabean.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.preference.PreferenceManager;

import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.User;
import com.intek.kalabean.R;

public class LoginFragment extends BaseFragment implements LoginContract.View {
    private TextInputLayout tilFragmentLoginUsername;
    private TextInputEditText edtFragmentLoginUsername;
    private TextInputEditText edtFragmentLoginPassword;
    private Button btnFragmentLoginLogin;
    private ConstraintLayout conLogin;
    private TextView txtSite;
    User user;
    LoginContract.Presenter presenter;
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User();
        presenter = new LoginPresenter(new KalaBeanRepository());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void setupViews() {

        conLogin = rootView.findViewById(R.id.con_fragmentLogin_mainLayout);
        //conLogin.setRotationY(180);
        tilFragmentLoginUsername = rootView.findViewById(R.id.til_fragmentLogin_username);
        edtFragmentLoginUsername = rootView.findViewById(R.id.edt_fragmentLogin_username);
        btnFragmentLoginLogin = rootView.findViewById(R.id.btn_fragmentLogin_login);
        txtSite = rootView.findViewById(R.id.txt_fragmentLogin_site);
        String htmlStyle = "<span><font color=\"black\">WWW.KALAB</font><font color =\"red\">EA</font><font color=\"black\">N.COM</font></span>";
        txtSite.setText(Html.fromHtml(htmlStyle));
        btnFragmentLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUsername()){
                    return;
                }else{
                    user.setMobile(edtFragmentLoginUsername.getText().toString());
                    presenter.login(user);
                }
            }
        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == Activity.RESULT_OK){
//            switch (requestCode){
//                case GOOGLE_LOGIN_REQUEST:
//                    try{
//                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//                        GoogleSignInAccount account = task.getResult(ApiException.class);
//                        onLoggedIn(account);
//                    }catch (ApiException e){
//                        Toast.makeText(getViewContext(), e.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//            }
//        }
//    }
//    private void onLoggedIn(GoogleSignInAccount googleSignInAccount){
//        String username = googleSignInAccount.getEmail();
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("email",username);
//        editor.apply();
//        editor.commit();
//        FragmentManager manager = getActivity().getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.frm_MainActivity_mainLayout,new MainFragment());
//        transaction.commit();
//
//    }
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


    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(LoggedinUser user) {
        String username = user.getItems().get(0).getMobile()+"";
        int userId = user.getItems().get(0).getResult();
        int shop = user.getItems().get(0).getShopId();
        String name = user.getItems().get(0).getFirstName();
        String family = user.getItems().get(0).getLastName();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userid",userId);
        editor.putString("name",name);
        editor.putString("family",family);
        editor.putString("username",username);
        editor.putInt("ShopId",shop);

        editor.apply();
        editor.commit();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frm_MainActivity_mainLayout,new MainFragment());
        transaction.commit();
    }
    @Override
    public void onResume() {
        super.onResume();
        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_MainActivity_mainLayout,new MainFragment()).commit();
                    return true;
                }
                return false;
            }
        });
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
