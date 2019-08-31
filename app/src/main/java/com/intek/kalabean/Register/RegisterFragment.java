package com.intek.kalabean.Register;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.MainActivity;
import com.intek.kalabean.Model.User;
import com.intek.kalabean.R;
import com.tiper.MaterialSpinner;

import java.util.ArrayList;

public class RegisterFragment extends BaseFragment implements RegisterContract.View {

    ConstraintLayout conRegister;
    RadioGroup rg_fragmentRegistry_regKind;
    RadioGroup rg_fragmentRegistry_gender;
    RadioButton rb_fragmentRegister_spacial;
    RadioButton rb_fragmentRegister_regular;
    RadioButton rb_fragmentRegister_man;
    RadioButton rb_fragmentRegister_woman;
    TextInputLayout til_fragmentRegister_name;
    TextInputLayout til_fragmentRegister_family;
    TextInputLayout til_fragmentRegister_email;
    TextInputLayout til_fragmentRegister_mobile;
    TextInputLayout til_fragmentRegister_phone;
    TextInputLayout til_fragmentRegister_address;
    TextInputLayout til_fragmentRegister_password;
    TextInputLayout til_fragmentRegister_confirmPassword;
    TextInputEditText edt_fragmentRegister_name;
    TextInputEditText edt_fragmentRegister_family;
    TextInputEditText edt_fragmentRegister_email;
    TextInputEditText edt_fragmentRegister_mobile;
    TextInputEditText edt_fragmentRegister_phone;
    TextInputEditText edt_fragmentRegister_address;
    TextInputEditText edt_fragmentRegister_password;
    TextInputEditText edt_fragmentRegister_confirmPassword;
    MaterialSpinner sp_fragmentRegister_locationState;
    MaterialSpinner sp_fragmentRegister_locationCity;
    Button btn_fragmentRegister_save;

    ArrayList<String> items;
    ArrayAdapter<String> arrayAdapter;
    private RegisterContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RegisterPresentr(new KalaBeanRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void setupViews() {

        conRegister = rootView.findViewById(R.id.con_fragmentRegister_mainLayout);
        conRegister.setRotationY(180);

        rg_fragmentRegistry_regKind = rootView.findViewById(R.id.rg_fragmentRegistry_regKind);
        rg_fragmentRegistry_gender = rootView.findViewById(R.id.rg_fragmentRegistry_gender);
        rb_fragmentRegister_spacial = rootView.findViewById(R.id.rb_fragmentRegister_spacial);
        rb_fragmentRegister_regular = rootView.findViewById(R.id.rb_fragmentRegister_regular);
        rb_fragmentRegister_man = rootView.findViewById(R.id.rb_fragmentRegister_man);
        rb_fragmentRegister_woman = rootView.findViewById(R.id.rb_fragmentRegister_woman);
        til_fragmentRegister_name = rootView.findViewById(R.id.til_fragmentRegister_name);
        til_fragmentRegister_family = rootView.findViewById(R.id.til_fragmentRegister_family);
        til_fragmentRegister_email = rootView.findViewById(R.id.til_fragmentRegister_email);
        til_fragmentRegister_mobile = rootView.findViewById(R.id.til_fragmentRegister_mobile);
        til_fragmentRegister_phone = rootView.findViewById(R.id.til_fragmentRegister_phone);
        til_fragmentRegister_address = rootView.findViewById(R.id.til_fragmentRegister_address);
        til_fragmentRegister_password = rootView.findViewById(R.id.til_fragmentRegister_password);
        til_fragmentRegister_confirmPassword = rootView.findViewById(R.id.til_fragmentRegister_confirmPassword);
        edt_fragmentRegister_name = rootView.findViewById(R.id.edt_fragmentRegister_name);
        edt_fragmentRegister_family = rootView.findViewById(R.id.edt_fragmentRegister_family);
        edt_fragmentRegister_email = rootView.findViewById(R.id.edt_fragmentRegister_email);
        edt_fragmentRegister_mobile = rootView.findViewById(R.id.edt_fragmentRegister_mobile);
        edt_fragmentRegister_phone = rootView.findViewById(R.id.edt_fragmentRegister_phone);
        edt_fragmentRegister_address = rootView.findViewById(R.id.edt_fragmentRegister_address);
        edt_fragmentRegister_password = rootView.findViewById(R.id.edt_fragmentRegister_password);
        edt_fragmentRegister_confirmPassword = rootView.findViewById(R.id.edt_fragmentRegister_confirm);
        sp_fragmentRegister_locationState = rootView.findViewById(R.id.sp_fragmentRegister_locationState);
        sp_fragmentRegister_locationCity = rootView.findViewById(R.id.sp_fragmentRegister_locationCity);
        btn_fragmentRegister_save = rootView.findViewById(R.id.btn_fragmentRegister_save);

        btn_fragmentRegister_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rg_fragmentRegistry_regKind.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getViewContext(), "لطفا نوع عضویت خود را مشخص کنید", Toast.LENGTH_SHORT).show();

                } else if (rg_fragmentRegistry_gender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getViewContext(), "لطفا جنسیت خود را مشخص کنید", Toast.LENGTH_SHORT).show();
                } else if (!validateName() || !validateFamily() || !validateEmail() || !validateMobile() || !validatePhone()
                        || !validatePassword() || !validateConpass() || !validateAddress()) {
                    return;
                } else if (!edt_fragmentRegister_password.getText().toString().equals(edt_fragmentRegister_confirmPassword.getText().toString())) {
                    til_fragmentRegister_confirmPassword.setError("کلمه عبور و تایید کلمه عبور با هم برابر نیستند");
                    edt_fragmentRegister_confirmPassword.requestFocus();
                } else if (sp_fragmentRegister_locationState.getSelectedItemId() == -1) {
                    Toast.makeText(getViewContext(), "لطفا استان خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
                }else if(sp_fragmentRegister_locationCity.getSelectedItemId() == -1){
                    Toast.makeText(getViewContext(), "لطفا شهر خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getViewContext(), "Continue", Toast.LENGTH_SHORT).show();
                }
            }
        });

        items = new ArrayList<>();


        items.add("دلار آمریکا");
        items.add("یورو");
        items.add("پوند انگلیس");
        items.add("مارک آلمان");
        items.add("ریال عربستان");
        items.add("تومان ایران ");
        //android.R.layout.simple_spinner_item

        arrayAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_fragmentRegister_locationState.setAdapter(arrayAdapter);
        sp_fragmentRegister_locationCity.setAdapter(arrayAdapter);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    private boolean validateName() {
        String name = edt_fragmentRegister_name.getText().toString().trim();
        if (name.isEmpty()) {
            til_fragmentRegister_name.setError("فیلد نام خالی است");
            edt_fragmentRegister_name.requestFocus();
            return false;
        } else {
            til_fragmentRegister_name.setError(null);
            return true;
        }
    }

    private boolean validateFamily() {
        String family = edt_fragmentRegister_family.getText().toString().trim();
        if (family.isEmpty()) {
            til_fragmentRegister_family.setError("فیلد نام خانوادگی خالی است");
            edt_fragmentRegister_family.requestFocus();
            return false;
        } else {
            til_fragmentRegister_family.setError(null);
            return true;
        }
    }

    private boolean validateMobile() {
        String mobile = edt_fragmentRegister_mobile.getText().toString().trim();
        if (mobile.isEmpty()) {
            til_fragmentRegister_mobile.setError("فیلد شماره همراه خالی است");
            edt_fragmentRegister_mobile.requestFocus();
            return false;
        } else {
            til_fragmentRegister_mobile.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = edt_fragmentRegister_phone.getText().toString().trim();
        if (phone.isEmpty()) {
            til_fragmentRegister_phone.setError("فیلد تلفن خالی است");
            edt_fragmentRegister_phone.requestFocus();
            return false;
        } else {
            til_fragmentRegister_phone.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String email = edt_fragmentRegister_email.getText().toString().trim();
        if (email.isEmpty()) {
            til_fragmentRegister_email.setError("فیلد ایمیل خالی است");
            edt_fragmentRegister_email.requestFocus();
            return false;
        } else {
            til_fragmentRegister_email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = edt_fragmentRegister_password.getText().toString().trim();
        if (password.isEmpty()) {
            til_fragmentRegister_password.setError("فیلد کلمه عبور خالی است");
            edt_fragmentRegister_password.requestFocus();
            return false;
        } else {
            til_fragmentRegister_password.setError(null);
            return true;
        }
    }

    private boolean validateConpass() {
        String conPass = edt_fragmentRegister_confirmPassword.getText().toString().trim();
        if (conPass.isEmpty()) {
            til_fragmentRegister_confirmPassword.setError("فیلد تایید کلمه عبور خالی است");
            edt_fragmentRegister_confirmPassword.requestFocus();
            return false;
        } else {
            til_fragmentRegister_confirmPassword.setError(null);
            return true;
        }
    }

    private boolean validateAddress() {
        String address = edt_fragmentRegister_address.getText().toString().trim();
        if (address.isEmpty()) {
            til_fragmentRegister_address.setError("فیلد آدرس خالی است");
            edt_fragmentRegister_address.requestFocus();
            return false;
        } else {
            til_fragmentRegister_address.setError(null);
            return true;
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(User user) {
        if (user.getName().isEmpty()){
            showMessage("ثبت نام با موفقیت انجام شد");
        }
    }
}
