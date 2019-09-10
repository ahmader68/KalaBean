package com.intek.kalabean.Register;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
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
    String state;
    String city;
    ArrayList<String> province;
    ArrayList<String> cities;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> cityArrayAdapter;
    private RegisterContract.Presenter presenter;
    User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RegisterPresentr(new KalaBeanRepository());
        user = new User();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void setupViews() {

        conRegister = rootView.findViewById(R.id.con_fragmentRegister_mainLayout);
        //conRegister.setRotationY(180);

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
                } else if (sp_fragmentRegister_locationCity.getSelectedItemId() == -1) {
                    Toast.makeText(getViewContext(), "لطفا شهر خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
                } else {
                    user.setFirstName(edt_fragmentRegister_name.getText().toString());
                    user.setLastName(edt_fragmentRegister_family.getText().toString());
                    user.setPassword(edt_fragmentRegister_password.getText().toString());
                    user.setProvince(state);
                    user.setCity(city);
                    user.setMobile(edt_fragmentRegister_mobile.getText().toString());
                    presenter.register(user);
                }
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
        sp_fragmentRegister_locationState.setAdapter(arrayAdapter);
       // sp_fragmentRegister_locationCity.setAdapter(arrayAdapter);
        cityArrayAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, cities);
        cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_fragmentRegister_locationState.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, View view, int i, long l) {
                state = sp_fragmentRegister_locationState.getSelectedItem().toString();
                switch (state) {
                    case "آذربایجان شرقی":
                        cities.clear();
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
                        cities.add("آشخانه");
                        cities.add("اسفراین");
                        cities.add("بجنورد");
                        cities.add("جاجرم");
                        cities.add("شیروان");
                        cities.add("فاروج");
                        break;

                    case "خوزستان":
                        cities.clear();
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
                        cities.add("آبیك");
                        cities.add("شهرک البرز");
                        cities.add("بوئین زهرا");
                        cities.add("تاكستان");
                        cities.add("قزوین");
                        cities.add("محمود آباد نمونه");
                        break;

                    case "قم":
                        cities.clear();
                        sp_fragmentRegister_locationCity.setSelection(-1);
                        cities.add("دستجرد");
                        cities.add("سلفچگان");
                        cities.add("قنوات");
                        cities.add("قم");
                        cities.add("کهک");
                        break;

                    case "كردستان":
                        cities.clear();
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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
                        sp_fragmentRegister_locationCity.setSelection(-1);
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

                sp_fragmentRegister_locationCity.setAdapter(cityArrayAdapter);
                sp_fragmentRegister_locationCity.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner materialSpinner, View view, int i, long l) {
                        city = sp_fragmentRegister_locationCity.getSelectedItem().toString();
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
    public void showSuccess(int id) {
        if (id > 0) {
            Toast.makeText(getViewContext(), "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_MainActivity_mainLayout,new MainFragment()).commit();
        }
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
