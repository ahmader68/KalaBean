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
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.KeyEvent;
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
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.tiper.MaterialSpinner;

import java.io.File;
import java.util.ArrayList;

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

    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> cityArrayAdapter;

    ArrayList<String> province;
    ArrayList<String> cities;
    public static final int PERMISSION_REQUEST_CODE = 10;
    public static final int PERMISSION_REQUEST = 20;
    public static final int OPEN_GALLERY_REQUEST_CODE = 100;
    public static final int TAKE_CODE = 400;
    Long name;

    String img;

    String state;

    String city;
    @Override
    public int getLayout() {
        return R.layout.fragment_edit_user;
    }

    @Override
    public void setupViews() {

        conEditUser = rootView.findViewById(R.id.con_fragmentEditUser_mainLayout);

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
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                if (Build.VERSION.SDK_INT >= 23) {
                    chechMyPermissions();
                } else {
                    takePicture();
                }
                imgFragmentEditUserProfile.setVisibility(View.VISIBLE);
            }
        });

        cities = new ArrayList<>();
        province = new ArrayList<>();

        province.add("آذربایجان شرقی");
        province.add("آذربایجان غربی");
        province.add("اردبیل");
        province.add("اصفهان");
        province.add("البرز");
        province.add("ایلام");
        province.add("بوشهر");
        province.add("تهران");
        province.add("چهارمحال بختیاری");
        province.add("خراسان جنوبی");
        province.add("خراسان رضوی");
        province.add("خراسان شمالی");
        province.add("خوزستان");
        province.add("زنجان");
        province.add("سمنان");
        province.add("سیستان و بلوچستان");
        province.add("فارس");
        province.add("قزوین");
        province.add("قم");
        province.add("كردستان");
        province.add("كرمان");
        province.add("كرمانشاه");
        province.add("كهكیلویه و بویراحمد");
        province.add("گلستان");
        province.add("گیلان");
        province.add("لرستان");
        province.add("مازندران");
        province.add("مركزی");
        province.add("هرمزگان");
        province.add("همدان");
        province.add("یزد");

        arrayAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, province);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFragmentEditUserState.setAdapter(arrayAdapter);
        cityArrayAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, cities);
        cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spFragmentEditUserState.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, View view, int i, long l) {
                state = spFragmentEditUserState.getSelectedItem().toString();
                switch (state) {
                    case "آذربایجان شرقی":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آذر شهر");
                        cities.add("اسكو");
                        cities.add("اهر");
                        cities.add("بستان آباد");
                        cities.add("بناب");
                        cities.add("بندر شرفخانه");
                        cities.add("تبریز");
                        cities.add("تسوج");
                        cities.add("جلفا");
                        cities.add("سراب");
                        cities.add("شبستر");
                        cities.add("صوفیان");
                        cities.add("عجبشیر");
                        cities.add("قره آغاج");
                        cities.add("كلیبر");
                        cities.add("كندوان");
                        cities.add("مراغه");
                        cities.add("مرند");
                        cities.add("ملكان");
                        cities.add("میانه");
                        cities.add("ورزقان");
                        cities.add("هادیشهر");
                        cities.add("هریس");
                        cities.add("هشترود");
                        cities.add("ممقان");
                        break;
                    case "آذربایجان غربی":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("ارومیه");
                        cities.add("اشنویه");
                        cities.add("بازرگان");
                        cities.add("بوكان");
                        cities.add("پیرانشهر");
                        cities.add("تكاب");
                        cities.add("چالدران");
                        cities.add("خوی");
                        cities.add("سر دشت");
                        cities.add("سلماس");
                        cities.add("سیه چشمه");
                        cities.add("شاهین دژ");
                        cities.add("ماكو");
                        cities.add("مهاباد");
                        cities.add("میاندوآب");
                        cities.add("نقده");
                        break;
                    case "اردبیل":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("اردبیل");
                        cities.add("بیله سوار");
                        cities.add("پارس آباد");
                        cities.add("خلخال");
                        cities.add("سرعین");
                        cities.add("گیوی(کوثر)");
                        cities.add("گرمی");
                        cities.add("مشگین شهر");
                        cities.add("نمین");
                        cities.add("نیر");
                        break;
                    case "اصفهان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آران و بیدگل");
                        cities.add("اردستان");
                        cities.add("اصفهان");
                        cities.add("باغ بهادران");
                        cities.add("تیران");
                        cities.add("چادگان");
                        cities.add("خمینی شهر");
                        cities.add("خوانسار");
                        cities.add("دولت آباد");
                        cities.add("دهاقان");
                        cities.add("زرین شهر");
                        cities.add("زیبا شهر");
                        cities.add("سمیرم");
                        cities.add("سپاهان شهر");
                        cities.add("شاهین شهر");
                        cities.add("شهرضا");
                        cities.add("فریدن");
                        cities.add("فریدون شهر");
                        cities.add("فلاورجان");
                        cities.add("فولاد شهر");
                        cities.add("قهدریجان");
                        cities.add("كاشان");
                        cities.add("گلدشت");
                        cities.add("گلپایگان");
                        cities.add("مباركه");
                        cities.add("ملک شهر");
                        cities.add("نایین");
                        cities.add("نجف آباد");
                        cities.add("نطنز");
                        cities.add("هرند");
                        break;
                    case "البرز":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آسارا و شهرستانک");
                        cities.add("اشتهارد");
                        cities.add("کرج");
                        cities.add("هشتگرد");
                        cities.add("ساوجبلاغ");
                        cities.add("طالقان");
                        cities.add("گرمدره");
                        cities.add("مشکین دشت و ولدآباد");
                        cities.add("نظرآباد");
                        break;
                    case "ایلام":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آبدانان");
                        cities.add("ایلام");
                        cities.add("ایوان");
                        cities.add("دره شهر");
                        cities.add("دهلران");
                        cities.add("سرابله");
                        cities.add("مهران");
                        break;

                    case "بوشهر":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("اهرم");
                        cities.add("برازجان");
                        cities.add("آبپخش");
                        cities.add("بوشهر");
                        cities.add("تنگستان");
                        cities.add("جم");
                        cities.add("خارك");
                        cities.add("خورموج");
                        cities.add("دشتستان");
                        cities.add("دشتی");
                        cities.add("دلوار");
                        cities.add("دیر");
                        cities.add("دیلم");
                        cities.add("عسلویه");
                        cities.add("كنگان");
                        cities.add("گناوه");
                        break;
                    case "تهران":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آسارا");
                        cities.add("اسلامشهر");
                        cities.add("اشتهارد");
                        cities.add("بومهن");
                        cities.add("پاكدشت");
                        cities.add("تجریش");
                        cities.add("تهران");
                        cities.add("چهاردانگه");
                        cities.add("دماوند");
                        cities.add("رباط كریم");
                        cities.add("رودهن");
                        cities.add("ری");
                        cities.add("شریف آباد");
                        cities.add("شهریار");
                        cities.add("طالقان");
                        cities.add("فشم");
                        cities.add("فیروزكوه");
                        cities.add("قدس");
                        cities.add("قرچك");
                        cities.add("كرج");
                        cities.add("كن");
                        cities.add("كهریزك");
                        cities.add("گلستان");
                        cities.add("لواسان");
                        cities.add("ملارد");
                        cities.add("نظرآباد");
                        cities.add("ورامین");
                        cities.add("هشتگرد");
                        break;

                    case "چهارمحال بختیاری":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("اردل");
                        cities.add("بروجن");
                        cities.add("چلگرد");
                        cities.add("سامان");
                        cities.add("شهركرد");
                        cities.add("فارسان");
                        cities.add("فرخ شهر");
                        cities.add("لردگان");
                        cities.add("هفشجان");
                        break;

                    case "خراسان جنوبی":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("بشرویه");
                        cities.add("بیرجند");
                        cities.add("خضری");
                        cities.add("سرایان");
                        cities.add("سربیشه");
                        cities.add("سرابله");
                        cities.add("فردوس");
                        cities.add("قائن");
                        cities.add("نهبندان");
                        break;

                    case "خراسان رضوی":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("بردسكن");
                        cities.add("بجستان");
                        cities.add("تایباد");
                        cities.add("تربت جام");
                        cities.add("تربت حیدریه");
                        cities.add("جغتای");
                        cities.add("جوین");
                        cities.add("چناران");
                        cities.add("خواف");
                        cities.add("خلیل آباد");
                        cities.add("درگز");
                        cities.add("رشتخوار");
                        cities.add("سبزوار");
                        cities.add("سرخس");
                        cities.add("طوس");
                        cities.add("طرقبه");
                        cities.add("فریمان");
                        cities.add("قوچان");
                        cities.add("كاشمر");
                        cities.add("كلات");
                        cities.add("گناباد");
                        cities.add("مشهد");
                        cities.add("نیشابور");
                        break;

                    case "خراسان شمالی":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آشخانه");
                        cities.add("اسفراین");
                        cities.add("بجنورد");
                        cities.add("جاجرم");
                        cities.add("شیروان");
                        cities.add("فاروج");
                        break;

                    case "خوزستان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آبادان");
                        cities.add("امیدیه");
                        cities.add("اندیمشك");
                        cities.add("اهواز");
                        cities.add("ایذه");
                        cities.add("گتوند");
                        cities.add("باغ ملك");
                        cities.add("بندرامام خمینی");
                        cities.add("بندر ماهشهر");
                        cities.add("بهبهان");
                        cities.add("خرمشهر");
                        cities.add("دزفول");
                        cities.add("رامهرمز");
                        cities.add("رامشیر");
                        cities.add("سوسنگرد");
                        cities.add("شادگان");
                        cities.add("شوشتر");
                        cities.add("شوش");
                        cities.add("لالی");
                        cities.add("مسجد سلیمان");
                        cities.add("هندیجان");
                        cities.add("هویزه");
                        break;

                    case "زنجان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آب بر");
                        cities.add("ابهر");
                        cities.add("ایجرود");
                        cities.add("خرمدره");
                        cities.add("زرین آباد");
                        cities.add("زنجان");
                        cities.add("قیدار");
                        cities.add("ماهنشان");
                        break;

                    case "سمنان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("ایوانكی");
                        cities.add("بسطام");
                        cities.add("دامغان");
                        cities.add("سمنان");
                        cities.add("سرخه");
                        cities.add("شاهرود");
                        cities.add("شهمیرزاد");
                        cities.add("گرمسار");
                        cities.add("مهدیشهر");
                        break;

                    case "سیستان و بلوچستان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("ایرانشهر");
                        cities.add("چابهار");
                        cities.add("خاش");
                        cities.add("راسك");
                        cities.add("زابل");
                        cities.add("زاهدان");
                        cities.add("سراوان");
                        cities.add("سرباز");
                        cities.add("فنوج");
                        cities.add("کنارک");
                        cities.add("میرجاوه");
                        cities.add("نیكشهر");
                        break;

                    case "فارس":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آباده");
                        cities.add("اردكان");
                        cities.add("ارسنجان");
                        cities.add("استهبان");
                        cities.add("اقلید");
                        cities.add("ایزد خواست");
                        cities.add("بوانات");
                        cities.add("جهرم");
                        cities.add("حاجی آباد");
                        cities.add("خرم بید");
                        cities.add("خنج");
                        cities.add("خشت");
                        cities.add("داراب");
                        cities.add("شیراز");
                        cities.add("فراشبند");
                        cities.add("فسا");
                        cities.add("فیروز آباد");
                        cities.add("قایمیه");
                        cities.add("قیرو کارزین");
                        cities.add("كازرون");
                        cities.add("لار");
                        cities.add("لامرد");
                        cities.add("مرودشت");
                        cities.add("مهر");
                        cities.add("نورآباد");
                        cities.add("نی ریز");
                        break;

                    case "قزوین":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آبیك");
                        cities.add("شهرک البرز");
                        cities.add("بوئین زهرا");
                        cities.add("تاكستان");
                        cities.add("قزوین");
                        cities.add("محمود آباد نمونه");
                        break;

                    case "قم":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("دستجرد");
                        cities.add("سلفچگان");
                        cities.add("قنوات");
                        cities.add("قم");
                        cities.add("کهک");
                        break;

                    case "كردستان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("بانه");
                        cities.add("بیجار");
                        cities.add("دیواندره");
                        cities.add("دهگلان");
                        cities.add("سقز");
                        cities.add("سنندج");
                        cities.add("قروه");
                        cities.add("كامیاران");
                        cities.add("مریوان");
                        break;

                    case "كرمان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("شهر بابك");
                        cities.add("بافت");
                        cities.add("بردسیر");
                        cities.add("بم");
                        cities.add("جیرفت");
                        cities.add("سرچشمه");
                        cities.add("راور");
                        cities.add("رفسنجان");
                        cities.add("زرند");
                        cities.add("سیرجان");
                        cities.add("كرمان");
                        cities.add("كهنوج");
                        break;

                    case "كرمانشاه":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("اسلام آباد غرب");
                        cities.add("پاوه");
                        cities.add("ثلاث باباجانی");
                        cities.add("جوانرود");
                        cities.add("خسروی");
                        cities.add("سر پل ذهاب");
                        cities.add("سنقر");
                        cities.add("صحنه");
                        cities.add("قصر شیرین");
                        cities.add("كرمانشاه");
                        cities.add("كنگاور");
                        cities.add("گیلان غرب");
                        cities.add("هرسین");
                        break;

                    case "كهكیلویه و بویراحمد":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("دنا");
                        cities.add("دوگنبدان");
                        cities.add("دهدشت");
                        cities.add("سی سخت");
                        cities.add("گچساران");
                        cities.add("لیکک");
                        cities.add("یاسوج");
                        break;

                    case "گلستان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آزاد شهر");
                        cities.add("آق قلا");
                        cities.add("بندر گز");
                        cities.add("تركمن");
                        cities.add("جلین");
                        cities.add("رامیان");
                        cities.add("علی آباد كتول");
                        cities.add("كردكوی");
                        cities.add("كلاله");
                        cities.add("گالیکش");
                        cities.add("گرگان");
                        cities.add("گنبد كاووس");
                        cities.add("مراوه تپه");
                        cities.add("مینو دشت");
                        break;

                    case "گیلان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آستارا");
                        cities.add("آستانه اشرفیه");
                        cities.add("املش");
                        cities.add("بندرانزلی");
                        cities.add("تالش");
                        cities.add("خمام");
                        cities.add("رودبار");
                        cities.add("رود سر");
                        cities.add("رستم آباد");
                        cities.add("رشت");
                        cities.add("رضوان شهر");
                        cities.add("سیاهكل");
                        cities.add("شفت");
                        cities.add("صومعه سرا");
                        cities.add("فومن");
                        cities.add("كلاچای");
                        cities.add("لاهیجان");
                        cities.add("لنگرود");
                        cities.add("لوشان");
                        cities.add("ماسال");
                        cities.add("ماسوله");
                        cities.add("منجیل");
                        break;

                    case "لرستان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("ازنا");
                        cities.add("الشتر");
                        cities.add("الیگودرز");
                        cities.add("بروجرد");
                        cities.add("پلدختر");
                        cities.add("خرم آباد");
                        cities.add("دورود");
                        cities.add("سراب دوره");
                        cities.add("سپید دشت");
                        cities.add("شول آباد");
                        cities.add("كوهدشت");
                        cities.add("نور آباد");
                        break;

                    case "مازندران":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آمل");
                        cities.add("بلده");
                        cities.add("بهشهر");
                        cities.add("بابل");
                        cities.add("بابلسر");
                        cities.add("پل سفید");
                        cities.add("تنكابن");
                        cities.add("جویبار");
                        cities.add("چالوس");
                        cities.add("رامسر");
                        cities.add("ساری");
                        cities.add("سلمانشهر");
                        cities.add("سواد كوه");
                        cities.add("فریدون كنار");
                        cities.add("کلاردشت");
                        cities.add("قائم شهر");
                        cities.add("گلوگاه");
                        cities.add("محمود آباد");
                        cities.add("مرزن آباد");
                        cities.add("نكا");
                        cities.add("نور");
                        cities.add("نوشهر");
                        break;

                    case "مركزی":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("آشتیان");
                        cities.add("اراك");
                        cities.add("تفرش");
                        cities.add("خمین");
                        cities.add("خنداب");
                        cities.add("دلیجان");
                        cities.add("زرندیه");
                        cities.add("ساوه");
                        cities.add("شازند");
                        cities.add("کمیجان");
                        cities.add("محلات");
                        break;

                    case "هرمزگان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("ابوموسی");
                        cities.add("انگهران");
                        cities.add("بندر جاسك");
                        cities.add("بندر خمیر");
                        cities.add("بندرعباس");
                        cities.add("بندر لنگه");
                        cities.add("بستك");
                        cities.add("پارسیان");
                        cities.add("تنب بزرگ");
                        cities.add("حاجی آباد");
                        cities.add("دهبارز");
                        cities.add("قشم");
                        cities.add("كیش");
                        cities.add("میناب");
                        break;

                    case "همدان":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("اسدآباد");
                        cities.add("بهار");
                        cities.add("تویسركان");
                        cities.add("رزن");
                        cities.add("كبودر اهنگ");
                        cities.add("ملایر");
                        cities.add("نهاوند");
                        cities.add("همدان");
                        break;

                    case "یزد":
                        cities.clear();
                        spFragmentEditUserCity.setSelection(-1);
                        cities.add("ابركوه");
                        cities.add("اردكان");
                        cities.add("اشكذر");
                        cities.add("بافق");
                        cities.add("تفت");
                        cities.add("طبس");
                        cities.add("مهریز");
                        cities.add("میبد");
                        cities.add("هرات");
                        cities.add("یزد");
                        break;
                }

                spFragmentEditUserCity.setAdapter(cityArrayAdapter);
                spFragmentEditUserCity.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner materialSpinner, View view, int i, long l) {
                        city = spFragmentEditUserCity.getSelectedItem().toString();
                        Toast.makeText(getViewContext(), city, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(MaterialSpinner materialSpinner) {

                    }
                });
            }

            @Override
            public void onNothingSelected(MaterialSpinner materialSpinner) {

            }
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
    private void chechMyPermissions() {
        if (ContextCompat.checkSelfPermission(getViewContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            takePicture();
        }
    }

    public void takePicture() {
        MainActivity.requestCodeCheck = 2;
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(getActivity());
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
