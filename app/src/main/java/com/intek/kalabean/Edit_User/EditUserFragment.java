package com.intek.kalabean.Edit_User;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.MainActivity;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.User;
import com.intek.kalabean.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.tiper.MaterialSpinner;

import java.util.ArrayList;

public class EditUserFragment extends BaseFragment implements EditUserContract.View {

    private EditUserContract.Presenter presenter;


    EditText
            edtFragmentEditUserName,
            edtFragmentEditUserFamily,
            edtFragmentEditUserEmail,
            edtFragmentEditUserMobile;




    Button btnFragmentEditUserSupport,btnFragmentEditUserSave;


    ConstraintLayout conEditUser;

//    MaterialSpinner spFragmentEditUserState,spFragmentEditUserCity;


//    ArrayAdapter<String> arrayAdapter,cityArrayAdapter;


//    ArrayList<String> province;
//    ArrayList<String> cities;
    public static final int PERMISSION_REQUEST_CODE = 10;
    public static final int PERMISSION_REQUEST = 20;
    public static final int OPEN_GALLERY_REQUEST_CODE = 100;
    public static final int TAKE_CODE = 400;


//    String state;
//
//    String city;

    int userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        userId = sharedPreferences.getInt("userid",0);
        presenter = new EditUserPresenter(new KalaBeanRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_edit_user;
    }

    @Override
    public void setupViews() {

        conEditUser = rootView.findViewById(R.id.con_fragmentEditUser_mainLayout);

        edtFragmentEditUserName = rootView.findViewById(R.id.edt_fragmentEditUser_name);
        edtFragmentEditUserFamily = rootView.findViewById(R.id.edt_fragmentEditUser_family);
        edtFragmentEditUserEmail = rootView.findViewById(R.id.edt_fragmentEditUser_email);
        edtFragmentEditUserMobile = rootView.findViewById(R.id.edt_fragmentEditUser_mobile);
//        edtFragmentEditUserPhone = rootView.findViewById(R.id.edt_fragmentEditUser_phone);
//        edtFragmentEditUserPassword = rootView.findViewById(R.id.edt_fragmentEditUser_password);
//        edtFragmentEditUserConfPass = rootView.findViewById(R.id.edt_fragmentEditUser_confPass);
//        spFragmentEditUserCity = rootView.findViewById(R.id.sp_fragmentEditUser_city);
//        spFragmentEditUserState = rootView.findViewById(R.id.sp_fragmentEditUser_state);
        btnFragmentEditUserSupport = rootView.findViewById(R.id.btn_fragmentEditUser_support);
        btnFragmentEditUserSave = rootView.findViewById(R.id.btn_fragmentEditUser_save);

        presenter.userInfo(userId);

        btnFragmentEditUserSave.setOnClickListener(view -> {
            if (!validateName() || !validateFamily() || !validateEmail() || !validateMobile()) {
                return;
            }
//                else if (!edtFragmentEditUserPassword.getText().toString().equals(edtFragmentEditUserConfPass.getText().toString())) {
//                    edtFragmentEditUserConfPass.setHint("کلمه عبور و تایید کلمه عبور با هم برابر نیستند");
//                    edtFragmentEditUserConfPass.setHintTextColor(getResources().getColor(R.color.colorRed));
//                }
//                else if (spFragmentEditUserState.getSelectedItemId() == -1) {
//                    Toast.makeText(getViewContext(), "لطفا استان خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
//                }
//                else if (spFragmentEditUserCity.getSelectedItemId() == -1) {
//                    Toast.makeText(getViewContext(), "لطفا شهر خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
//                }
            else {
                int uid = userId;
                String mobile = edtFragmentEditUserMobile.getText().toString();
                String email = edtFragmentEditUserEmail.getText().toString();
                presenter.editUser(uid,mobile,email);

            }
        });
        btnFragmentEditUserSupport.setOnClickListener(view -> {

        });

    }
    @Override
    public void onResume() {
        super.onResume();
        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_MainActivity_mainLayout,new MainFragment()).commit();
                return true;
            }
            return false;
        });
    }


    @Override
    public Context getViewContext() {
        return getContext();
    }

    private boolean validateName() {
        String nameInput = edtFragmentEditUserName.getText().toString().trim();
        if (nameInput.isEmpty()) {
            edtFragmentEditUserName.setHint("فیلد نام خالی است");
            edtFragmentEditUserName.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateFamily() {
        String familyInput = edtFragmentEditUserFamily.getText().toString().trim();
        if (familyInput.isEmpty()) {
            edtFragmentEditUserFamily.setHint("فیلد نام خانوادگی خالی است");
            edtFragmentEditUserFamily.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateEmail() {
        String emailInput = edtFragmentEditUserEmail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            edtFragmentEditUserEmail.setHint("فیلد ایمیل خالی است");
            edtFragmentEditUserEmail.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateMobile() {
        String mobileInput = edtFragmentEditUserMobile.getText().toString().trim();
        if (mobileInput.isEmpty()) {
            edtFragmentEditUserMobile.setHint("فیلد موبایل خالی است");
            edtFragmentEditUserMobile.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {
            return true;
        }
    }



    @Override
    public void showUserInfo(User user) {
        edtFragmentEditUserName.setText(user.getFirstName());
        edtFragmentEditUserFamily.setText(user.getLastName());
        edtFragmentEditUserMobile.setText(user.getMobile());
        edtFragmentEditUserMobile.setEnabled(false);
        edtFragmentEditUserName.setEnabled(false);
        edtFragmentEditUserFamily.setEnabled(false);
        edtFragmentEditUserEmail.setText(user.getEmail());
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEditSuccess(int result) {
        if(result > 0){
            showMessage("اطلاعات با موفقیت ویرایش شد");
        }else {
            showMessage("اشکالی در ویرایش اطلاعات رخ داده است، لطفا مجدد تلاش کنید");
        }
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
