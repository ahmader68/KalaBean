package com.intek.kalabean.Register;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.MainActivity;
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
}
