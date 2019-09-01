package com.intek.kalabean.Edit_User;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Classes.G;
import com.intek.kalabean.Classes.Upload;
import com.intek.kalabean.MainActivity;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;
import com.tiper.MaterialSpinner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static android.app.Activity.RESULT_OK;

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

    ConstraintLayout conEditUser;

    MaterialSpinner spFragmentEditUserState;
    MaterialSpinner spFragmentEditUserCity;
    //Test

    ArrayList<String> items;

    public static final int PERMISSION_REQUEST_CODE = 10;
    public static final int PERMISSION_REQUEST = 20;
    public static final int OPEN_GALLERY_REQUEST_CODE = 100;
    public static final int TAKE_CODE = 400;
    Long name;

    String img;

    @Override
    public int getLayout() {
        return R.layout.fragment_edit_user;
    }

    @Override
    public void setupViews() {

        conEditUser = rootView.findViewById(R.id.con_fragmentEditUser_mainLayout);
        conEditUser.setRotationY(180);

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
                if (!validateName() || !validateFamily() || !validateEmail() || !validateMobile() || !validatePhone() || !validatePassword() || !validateConPass()) {
                    return;
                } else if (!edtFragmentEditUserPassword.getText().toString().equals(edtFragmentEditUserConfPass.getText().toString())) {
                    tilFragmentEditUserConfPass.setError("کلمه عبور و تایید کلمه عبور با هم برابر نیستند");
                    edtFragmentEditUserConfPass.requestFocus();
                } else if (spFragmentEditUserState.getSelectedItemId() == -1) {
                    Toast.makeText(getViewContext(), "لطفا استان خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
                } else if (spFragmentEditUserCity.getSelectedItemId() == -1) {
                    Toast.makeText(getViewContext(), "لطفا شهر خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getViewContext(), "continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnFragmentEditUserUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getViewContext());
                // ...Irrelevant code for customizing the buttons and title
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.open_camera_dialog, null);
                dialogBuilder.setView(dialogView);

                ImageView imgGallery = dialogView.findViewById(R.id.img_fragmentEditUser_dialogGallery);
                ImageView imgCamera = dialogView.findViewById(R.id.img_fragmentEditUser_dialogCamera);

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();

                imgGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ContextCompat.checkSelfPermission(getViewContext(),
                                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    PERMISSION_REQUEST_CODE);
                        } else {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, OPEN_GALLERY_REQUEST_CODE);
                        }
                    }
                });

                imgCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());
                        if (Build.VERSION.SDK_INT >= 23) {
                            chechMyPermissions();
                        } else {
                            takePicture();
                        }
                    }
                });

                //imgFragmentEditUserProfile.setImageResource(R.drawable.ic_launcher_background);
                Picasso.get().load(img).into(imgFragmentEditUserProfile);
                imgFragmentEditUserProfile.setVisibility(View.VISIBLE);
            }
        });

        /////Test

        items = new ArrayList<>();
        items.add("دلار آمریکا");
        items.add("یورو");
        items.add("پوند انگلیس");
        items.add("مارک آلمان");
        items.add("ریال عربستان");
        items.add("تومان ایران ");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFragmentEditUserState.setAdapter(arrayAdapter);
        spFragmentEditUserCity.setAdapter(arrayAdapter);
    }

    private void chechMyPermissions() {
        if (ContextCompat.checkSelfPermission(getViewContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            takePicture();
        }
    }

    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        name = System.currentTimeMillis();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(G.AppAddress + "/" + name + ".jpg")));
        startActivityForResult(intent, TAKE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(getViewContext(), "You must accept permission", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, OPEN_GALLERY_REQUEST_CODE);
                }
                break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_CODE:
                Bitmap bitmap = BitmapFactory.decodeFile(G.AppAddress + "/" + name + ".jpg");
                imgFragmentEditUserProfile.setImageBitmap(bitmap);
                break;
            case OPEN_GALLERY_REQUEST_CODE:
                if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                    Upload upload = new Upload();
                    img = upload.pickFile(data, getActivity());
                }
                break;
        }
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    private boolean validateName() {
        String nameInput = edtFragmentEditUserName.getText().toString().trim();
        if (nameInput.isEmpty()) {
            tilFragmentEditUserName.setError("فیلد نام خالی است");
            edtFragmentEditUserName.requestFocus();
            return false;
        } else {
            tilFragmentEditUserName.setError(null);
            return true;
        }
    }

    private boolean validateFamily() {
        String familyInput = edtFragmentEditUserFamily.getText().toString().trim();
        if (familyInput.isEmpty()) {
            tilFragmentEditUserFamily.setError("فیلد نام خانوادگی خالی است");
            edtFragmentEditUserFamily.requestFocus();
            return false;
        } else {
            tilFragmentEditUserFamily.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String emailInput = edtFragmentEditUserEmail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            tilFragmentEditUserEmail.setError("فیلد ایمیل خالی است");
            edtFragmentEditUserEmail.requestFocus();
            return false;
        } else {
            tilFragmentEditUserEmail.setError(null);
            return true;
        }
    }

    private boolean validateMobile() {
        String mobileInput = edtFragmentEditUserMobile.getText().toString().trim();
        if (mobileInput.isEmpty()) {
            tilFragmentEditUserMobile.setError("فیلد موبایل خالی است");
            edtFragmentEditUserMobile.requestFocus();
            return false;
        } else {
            tilFragmentEditUserMobile.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phoneInput = edtFragmentEditUserPhone.getText().toString().trim();
        if (phoneInput.isEmpty()) {
            tilFragmentEditUserPhone.setError("فیلد تلفن خالی است");
            edtFragmentEditUserPhone.requestFocus();
            return false;
        } else {
            tilFragmentEditUserPhone.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = edtFragmentEditUserPassword.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            tilFragmentEditUserPassword.setError("فیلد کلمه عبور خالی است");
            edtFragmentEditUserPassword.requestFocus();
            return false;
        } else {
            tilFragmentEditUserPassword.setError(null);
            return true;
        }
    }

    private boolean validateConPass() {
        String conPassInput = edtFragmentEditUserConfPass.getText().toString().trim();
        if (conPassInput.isEmpty()) {
            tilFragmentEditUserConfPass.setError("فیلد تکرار کلمه عبور خالی است");
            edtFragmentEditUserConfPass.requestFocus();
            return false;
        } else {
            tilFragmentEditUserConfPass.setError(null);
            return true;
        }
    }
}
