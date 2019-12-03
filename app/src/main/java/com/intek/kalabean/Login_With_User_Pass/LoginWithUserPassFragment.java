package com.intek.kalabean.Login_With_User_Pass;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.User;
import com.intek.kalabean.R;

public class LoginWithUserPassFragment extends BaseFragment implements LoginWithUserPassContract.View {

    private LoginWithUserPassContract.Presenter presenter;


    private EditText edtUsername,edtPassword;

    private Button btnLogin;

    private SharedPreferences sharedPreferences;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginWithUserPassPresenter(new KalaBeanRepository());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_login_with_user_pass;
    }

    @Override
    public void setupViews() {


        edtUsername = rootView.findViewById(R.id.edt_fragmentLoginWithUserPass_username);
        edtPassword = rootView.findViewById(R.id.edt_fragmentLoginWithUserPass_password);

        btnLogin = rootView.findViewById(R.id.btn_fragmentLoginWithUserPass_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if(!validateUsername() || !validatePassword()){
                    return;
                }else{
                    user.setUsr(username);
                    user.setPassword(password);
                    presenter.login(user);
                }
            }
        });

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(LoggedinUser loggedinUser) {
        String username = loggedinUser.getItems().get(0).getMobile()+"";
        int userId = loggedinUser.getItems().get(0).getResult();
        int shop = loggedinUser.getItems().get(0).getShopId();
        String name = loggedinUser.getItems().get(0).getFirstName();
        String family = loggedinUser.getItems().get(0).getLastName();
        int storeId = loggedinUser.getItems().get(0).getShopId();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userid",userId);
        editor.putString("name",name);
        editor.putString("family",family);
        editor.putString("username",username);
        editor.putInt("ShopId",shop);
        editor.putInt("storeId",storeId);

        editor.apply();
        editor.commit();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frm_MainActivity_mainLayout,new MainFragment());
        transaction.commit();
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

    private boolean validateUsername(){
        String username = edtUsername.getText().toString().trim();
        if(username.isEmpty()){
            edtUsername.setHint("لطفا نام کاربری را وارد کنید");
            edtUsername.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        }else{
            return true;
        }
    }

    private boolean validatePassword(){
        String password = edtPassword.getText().toString().trim();
        if(password.isEmpty()){
            edtPassword.setHint("لطفا کلمه عبور را وارد کنید");
            edtPassword.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        }else{
            return true;
        }
    }
}
