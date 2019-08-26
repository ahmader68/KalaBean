package com.intek.kalabean.Edit_User;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.R;
import com.tiper.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class EditUserFragment extends BaseFragment implements EditUserContract.View {
    RadioGroup rgFragmentEditUserGender;
    RadioButton rbFragmetEditUserMan;
    RadioButton rbFragmetEditUserWoman;
    TextInputLayout tilFragmentEditUserName;
    TextInputLayout tilFragmentEditUserFamily;
    TextInputLayout tilFragmentEditUserEmail;
    TextInputLayout tilFragmentEditUserMobile;
    TextInputLayout tilFragmentEditUserPhone;
    TextInputLayout tilFragmentEditUserPassword;
    TextInputLayout tilFragmentEditUserConfPass;
    TextInputEditText edtFragmentEditUserName;
    TextInputEditText edtFragmentEditUserFamily;
    TextInputEditText edtFragmentEditUserEmail;
    TextInputEditText edtFragmentEditUserMobile;
    TextInputEditText edtFragmentEditUserPhone;
    TextInputEditText edtFragmentEditUserPassword;
    TextInputEditText edtFragmentEditUserConfPass;
    Button btnFragmentEditUserUpload;
    Button btnFragmentEditUserSave;
    ImageView imgFragmentEditUserProfile;
    MaterialSpinner spFragmentEditUserState;
    MaterialSpinner spFragmentEditUserCity;
    @Override
    public int getLayout() {
        return R.layout.fragment_edit_user;
    }

    @Override
    public void setupViews() {
        rgFragmentEditUserGender = rootView.findViewById(R.id.rg_fragmentEditUser_gender);
        rbFragmetEditUserMan = rootView.findViewById(R.id.rb_fragmentEditUser_man);
        rbFragmetEditUserWoman = rootView.findViewById(R.id.rb_fragmentEditUser_woman);
        tilFragmentEditUserName = rootView.findViewById(R.id.til_fragmentEditUser_name);
        tilFragmentEditUserFamily = rootView.findViewById(R.id.til_fragmentEditUser_family);
        tilFragmentEditUserEmail = rootView.findViewById(R.id.til_fragmentEditUser_email);
        tilFragmentEditUserMobile = rootView.findViewById(R.id.til_fragmentEditUser_mobile);
        tilFragmentEditUserPhone = rootView.findViewById(R.id.til_fragmentEditUser_phone);
        tilFragmentEditUserPassword = rootView.findViewById(R.id.til_fragmentEditUser_password);
        tilFragmentEditUserConfPass = rootView.findViewById(R.id.til_fragmentEditUser_confPass);
        edtFragmentEditUserName = rootView.findViewById(R.id.edt_fragmentEditUser_name);
        edtFragmentEditUserFamily = rootView.findViewById(R.id.edt_fragmentEditUser_family);
        edtFragmentEditUserEmail = rootView.findViewById(R.id.edt_fragmentEditUser_email);
        edtFragmentEditUserMobile = rootView.findViewById(R.id.edt_fragmentEditUser_mobile);
        edtFragmentEditUserPhone = rootView.findViewById(R.id.edt_fragmentEditUser_phone);
        edtFragmentEditUserPassword = rootView.findViewById(R.id.edt_fragmentEditUser_password);
        edtFragmentEditUserConfPass = rootView.findViewById(R.id.edt_fragmentEditUser_confPass);
        imgFragmentEditUserProfile = rootView.findViewById(R.id.img_fragmentEditUser_profile);
        spFragmentEditUserCity = rootView.findViewById(R.id.sp_fragmentEditUser_city);
        spFragmentEditUserState = rootView.findViewById(R.id.sp_fragmentEditUser_state);
        btnFragmentEditUserUpload = rootView.findViewById(R.id.btn_fragmentEditUser_upload);
        btnFragmentEditUserSave = rootView.findViewById(R.id.btn_fragmentEditUser_save);
        btnFragmentEditUserSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateName() || !validateFamily() || !validateEmail() || !validateMobile() || !validatePhone() || !validatePassword() || !validateConPass()){
                    return;
                }else if(!edtFragmentEditUserPassword.getText().toString().equals(edtFragmentEditUserConfPass.getText().toString())){
                    Toast.makeText(getViewContext(), "کلمه عبور و تایید آن باید باهم برابر باشند", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getViewContext(), "continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnFragmentEditUserUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgFragmentEditUserProfile.setImageResource(R.drawable.ic_launcher_background);
                imgFragmentEditUserProfile.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
    private boolean validateName(){
        String nameInput = edtFragmentEditUserName.getText().toString().trim();
        if(nameInput.isEmpty()){
            tilFragmentEditUserName.setError("فیلد نام خالی است");
            return false;
        }else{
            tilFragmentEditUserName.setError(null);
            return true;
        }
    }    private boolean validateFamily(){
        String familyInput = edtFragmentEditUserFamily.getText().toString().trim();
        if(familyInput.isEmpty()){
            tilFragmentEditUserFamily.setError("فیلد نام خانوادگی خالی است");
            return false;
        }else{
            tilFragmentEditUserFamily.setError(null);
            return true;
        }
    }    private boolean validateEmail(){
        String emailInput = edtFragmentEditUserEmail.getText().toString().trim();
        if(emailInput.isEmpty()){
            tilFragmentEditUserEmail.setError("فیلد ایمیل خالی است");
            return false;
        }else{
            tilFragmentEditUserEmail.setError(null);
            return true;
        }
    }    private boolean validateMobile(){
        String mobileInput = edtFragmentEditUserMobile.getText().toString().trim();
        if(mobileInput.isEmpty()){
            tilFragmentEditUserMobile.setError("فیلد موبایل خالی است");
            return false;
        }else{
            tilFragmentEditUserMobile.setError(null);
            return true;
        }
    }    private boolean validatePhone(){
        String phoneInput = edtFragmentEditUserPhone.getText().toString().trim();
        if(phoneInput.isEmpty()){
            tilFragmentEditUserPhone.setError("فیلد تلفن خالی است");
            return false;
        }else{
            tilFragmentEditUserPhone.setError(null);
            return true;
        }
    }    private boolean validatePassword(){
        String passwordInput = edtFragmentEditUserPassword.getText().toString().trim();
        if(passwordInput.isEmpty()){
            tilFragmentEditUserPassword.setError("فیلد کلمه عبور خالی است");
            return false;
        }else{
            tilFragmentEditUserPassword.setError(null);
            return true;
        }
    }    private boolean validateConPass(){
        String conPassInput = edtFragmentEditUserConfPass.getText().toString().trim();
        if(conPassInput.isEmpty()){
            tilFragmentEditUserConfPass.setError("فیلد تکرار کلمه عبور خالی است");
            return false;
        }else{
            tilFragmentEditUserConfPass.setError(null);
            return true;
        }
    }
}
