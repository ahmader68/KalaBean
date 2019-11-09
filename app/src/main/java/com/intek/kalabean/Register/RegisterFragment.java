package com.intek.kalabean.Register;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Classes.GetProvinceAndCity;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.User;
import com.intek.kalabean.R;
import com.tiper.MaterialSpinner;

import org.jetbrains.annotations.NotNull;


import java.util.List;

public class RegisterFragment extends BaseFragment implements RegisterContract.View {



    private TextView txtFragmentRegisterClickHere;

    private RadioGroup rgFragmentRegisterUserKind;

    private RadioButton rbFragmentRegisterOrdinaryUser;
    private RadioButton rbFragmentRegisterVIPUser;

    private TextInputLayout tilFragmentRegisterPassword;
    private TextInputEditText edtFragmentRegisterPassword;

    private TextInputLayout tilFragmentRegisterRepeatPassword;
    private TextInputEditText edtFragmentRegisterRepeatPassword;

    private TextInputLayout tilFragmentRegisterUsername;
    private TextInputEditText edtFragmentRegisterUsername;

    private MaterialSpinner spFragmentRegisterProvince;
    private MaterialSpinner spFragmentRegisterCity;

    private TextInputLayout tilFragmentRegisterEmail;
    private TextInputEditText edtFragmentRegisterEmail;

    private Button btnFragmentRegisterAccept;


    private String state;
    private String city;
    private String userLevel;

    private List<String> provinces;
    private List<String> cities;

    private ArrayAdapter<String> provinceArrayAdapter;
    private ArrayAdapter<String> cityArrayAdapter;

    private RegisterContract.Presenter presenter;

    private User user;

    private GetProvinceAndCity getProvinceAndCity;

    private SharedPreferences sharedPreferences;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RegisterPresentr(new KalaBeanRepository());
        user = new User();
        getProvinceAndCity = new GetProvinceAndCity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void setupViews() {

        tilFragmentRegisterPassword = rootView.findViewById(R.id.til_fragmentRegister_password);
        edtFragmentRegisterPassword = rootView.findViewById(R.id.edt_fragmentRegister_password);

        tilFragmentRegisterRepeatPassword = rootView.findViewById(R.id.til_fragmentRegister_repeatPassword);
        edtFragmentRegisterRepeatPassword = rootView.findViewById(R.id.edt_fragmentRegister_repeatPassword);

        tilFragmentRegisterUsername = rootView.findViewById(R.id.til_fragmentRegister_usernameDown);
        edtFragmentRegisterUsername = rootView.findViewById(R.id.edt_fragmentRegister_usernameDown);

        tilFragmentRegisterEmail = rootView.findViewById(R.id.til_fragmentRegister_email);
        edtFragmentRegisterEmail = rootView.findViewById(R.id.edt_fragmentRegister_email);

        rgFragmentRegisterUserKind = rootView.findViewById(R.id.rg_fragmentRegister_regKind);

        rbFragmentRegisterOrdinaryUser = rootView.findViewById(R.id.rb_fragmentRegister_regular);
        rbFragmentRegisterVIPUser = rootView.findViewById(R.id.rb_fragmentRegister_spacial);

        spFragmentRegisterCity = rootView.findViewById(R.id.sp_fragmentRegister_city);
        spFragmentRegisterProvince = rootView.findViewById(R.id.sp_fragmentRegister_province);

        txtFragmentRegisterClickHere = rootView.findViewById(R.id.txt_fragmentRegister_here);

        btnFragmentRegisterAccept = rootView.findViewById(R.id.btn_fragmentRegister_register);

        provinces = getProvinceAndCity.getProvince();
        provinceArrayAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,provinces);
        provinceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFragmentRegisterProvince.setAdapter(provinceArrayAdapter);

        spFragmentRegisterProvince.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(@NotNull MaterialSpinner materialSpinner, @org.jetbrains.annotations.Nullable View view, int i, long l) {
                state = spFragmentRegisterProvince.getSelectedItem().toString();
                cities = getProvinceAndCity.getCity(state);
                cityArrayAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,cities);
                cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spFragmentRegisterCity.setAdapter(cityArrayAdapter);

                spFragmentRegisterCity.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(@NotNull MaterialSpinner materialSpinner, @org.jetbrains.annotations.Nullable View view, int i, long l) {
                        city = spFragmentRegisterCity.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(@NotNull MaterialSpinner materialSpinner) {

                    }
                });
            }

            @Override
            public void onNothingSelected(@NotNull MaterialSpinner materialSpinner) {

            }
        });

        rgFragmentRegisterUserKind.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_fragmentRegister_regular){
                    userLevel = "0";
                }else{
                    userLevel = "1";
                }
            }
        });

        btnFragmentRegisterAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    user.setFirstName("hhh");
                    user.setLastName("gggg");
                    user.setEmail(edtFragmentRegisterEmail.getText().toString());
                    user.setCity(city);
                    user.setProvince(state);
                    user.setPassword(edtFragmentRegisterPassword.getText().toString());
                    user.setMobile(edtFragmentRegisterUsername.getText().toString());
                    user.setUsr(edtFragmentRegisterUsername.getText().toString());
                    //user.setUserLevel(userLevel);
                    presenter.register(user);

            }
        });

        txtFragmentRegisterClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getViewContext(), "Click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }


    private boolean validateName() {
        return true;
    }

    private boolean validateFamily() {
        return true;
    }

    private boolean validateMobile() {
        return true;
    }

    private boolean validatePhone() {
        return true;
    }

    private boolean validateEmail() {
        return true;
    }

    private boolean validatePassword() {
        return true;
    }

    private boolean validateConpass() {
        return true;
    }

    private boolean validateAddress() {
        return true;
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(int id) {
        if (id > 0) {
            Toast.makeText(getViewContext(), "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_MainActivity_mainLayout,new MainFragment()).commit();
        }
    }

    @Override
    public void successLogin(LoggedinUser user) {
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
