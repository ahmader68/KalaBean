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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

    private RadioButton rbFragmentRegisterOrdinaryUser, rbFragmentRegisterVIPUser;


    private EditText
            edtFragmentRegisterPassword,
            edtFragmentRegisterRepeatPassword,
            edtFragmentRegisterUsername,
            edtFragmentRegisterEmail,
            edtFragmentRegisterFirstname,
            edtFragmentRegisterLastname;


    private Spinner spFragmentRegisterProvince, spFragmentRegisterCity;


    private Button btnFragmentRegisterAccept;


    private String state, city, userLevel;


    private List<String> provinces, cities;


    private ArrayAdapter<String> provinceArrayAdapter, cityArrayAdapter;


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
        edtFragmentRegisterPassword = rootView.findViewById(R.id.edt_fragmentRegister_password);
        edtFragmentRegisterRepeatPassword = rootView.findViewById(R.id.edt_fragmentRegister_repeatPassword);
        edtFragmentRegisterUsername = rootView.findViewById(R.id.edt_fragmentRegister_usernameDown);
        edtFragmentRegisterEmail = rootView.findViewById(R.id.edt_fragmentRegister_email);
        edtFragmentRegisterFirstname = rootView.findViewById(R.id.edt_fragmentRegister_firstName);
        edtFragmentRegisterLastname = rootView.findViewById(R.id.edt_fragmentRegister_LastName);

        rgFragmentRegisterUserKind = rootView.findViewById(R.id.rg_fragmentRegister_regKind);

        rbFragmentRegisterOrdinaryUser = rootView.findViewById(R.id.rb_fragmentRegister_regular);
        rbFragmentRegisterVIPUser = rootView.findViewById(R.id.rb_fragmentRegister_spacial);

        spFragmentRegisterCity = rootView.findViewById(R.id.sp_fragmentRegister_city);
        spFragmentRegisterProvince = rootView.findViewById(R.id.sp_fragmentRegister_province);

        txtFragmentRegisterClickHere = rootView.findViewById(R.id.txt_fragmentRegister_here);

        btnFragmentRegisterAccept = rootView.findViewById(R.id.btn_fragmentRegister_register);

        provinces = getProvinceAndCity.getProvince();
        provinceArrayAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, provinces);
        provinceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFragmentRegisterProvince.setAdapter(provinceArrayAdapter);

        spFragmentRegisterProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = spFragmentRegisterProvince.getSelectedItem().toString();
                cities = getProvinceAndCity.getCity(state);
                cityArrayAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, cities);
                cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spFragmentRegisterCity.setAdapter(cityArrayAdapter);

                spFragmentRegisterCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        city = spFragmentRegisterCity.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rgFragmentRegisterUserKind.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_fragmentRegister_regular) {
                    userLevel = "0";
                } else {
                    userLevel = "1";
                }
            }
        });

        btnFragmentRegisterAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateName() || !validateFamily() || !validateUsername() || !validatePassword() ||  !validateConpass()  ||   !validateProvince() ||  !validateCity()   || !validateEmail()){
                    return;
                }else {
                    user.setFirstName(edtFragmentRegisterFirstname.getText().toString());
                    user.setLastName(edtFragmentRegisterLastname.getText().toString());
                    user.setEmail(edtFragmentRegisterEmail.getText().toString());
                    user.setCity(city);
                    user.setProvince(state);
                    user.setPassword(edtFragmentRegisterPassword.getText().toString());
                    user.setMobile(edtFragmentRegisterUsername.getText().toString());
                    user.setUsr(edtFragmentRegisterUsername.getText().toString());
                    //user.setUserLevel(userLevel);
                    presenter.register(user);
                }

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
        String name = edtFragmentRegisterFirstname.getText().toString().trim();
        if (name.isEmpty()) {
            edtFragmentRegisterFirstname.setHint("لطفا نام خود را وارد کنید");
            edtFragmentRegisterFirstname.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateFamily() {
        String family = edtFragmentRegisterLastname.getText().toString().trim();
        if (family.isEmpty()) {
            edtFragmentRegisterLastname.setHint("لطفا نام خانوادگی خود را وارد کنید");
            edtFragmentRegisterLastname.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateUsername() {
        String username = edtFragmentRegisterUsername.getText().toString().trim();
        if (username.isEmpty()) {
            edtFragmentRegisterUsername.setHint("لطفا شماره همراه خود را وارد کنید");
            edtFragmentRegisterUsername.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateProvince() {
        String province = spFragmentRegisterProvince.getSelectedItem().toString();
        if (province.isEmpty()) {
            Toast.makeText(getViewContext(), "لطفا استان خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateCity() {
        String city = spFragmentRegisterCity.getSelectedItem().toString();
        if (city.isEmpty()) {
            Toast.makeText(getViewContext(), "لطفا شهر خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    private boolean validateEmail() {
        String email = edtFragmentRegisterEmail.getText().toString().trim();
        if (email.isEmpty()) {
            edtFragmentRegisterEmail.setHint("لطفا ایمیل خود را وارد کنید");
            edtFragmentRegisterEmail.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword() {
        String password = edtFragmentRegisterPassword.getText().toString().trim();
        if (password.isEmpty()) {
            edtFragmentRegisterPassword.setHint("لطفا کلمه عبور خود را وارد کنید");
            edtFragmentRegisterPassword.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateConpass() {
        String conPass = edtFragmentRegisterRepeatPassword.getText().toString().trim();
        if (conPass.isEmpty()) {
            edtFragmentRegisterRepeatPassword.setHint("لطفا شماره همراه خود را وارد کنید");
            edtFragmentRegisterRepeatPassword.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else if (!edtFragmentRegisterPassword.getText().toString().equals(conPass)) {
            edtFragmentRegisterRepeatPassword.setHint("کلمه عبور با تکرار آن برابر نیست");
            edtFragmentRegisterRepeatPassword.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(int id) {
        if (id > 0) {
            Toast.makeText(getViewContext(), "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_MainActivity_mainLayout, new MainFragment()).commit();
        }
    }

    @Override
    public void successLogin(LoggedinUser user) {
        String username = user.getItems().get(0).getMobile() + "";
        int userId = user.getItems().get(0).getResult();
        int shop = user.getItems().get(0).getShopId();
        String name = user.getItems().get(0).getFirstName();
        String family = user.getItems().get(0).getLastName();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userid", userId);
        editor.putString("name", name);
        editor.putString("family", family);
        editor.putString("username", username);
        editor.putInt("ShopId", shop);

        editor.apply();
        editor.commit();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frm_MainActivity_mainLayout, new MainFragment());
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() == null) {
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_MainActivity_mainLayout, new MainFragment()).commit();
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
