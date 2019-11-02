package com.intek.kalabean.Classes;

import java.util.ArrayList;
import java.util.List;

public class GetProvinceAndCity {
    public List<String> getProvince(){
        List<String> province;
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

        return province;
    }

    public List<String> getCity(String province){
        List<String> cities = new ArrayList<>();
        switch (province) {
            case "آذربایجان شرقی":
                cities.clear();
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
                cities.add("آشخانه");
                cities.add("اسفراین");
                cities.add("بجنورد");
                cities.add("جاجرم");
                cities.add("شیروان");
                cities.add("فاروج");
                break;


            case "خوزستان":
                cities.clear();
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
                cities.add("آبیك");
                cities.add("شهرک البرز");
                cities.add("بوئین زهرا");
                cities.add("تاكستان");
                cities.add("قزوین");
                cities.add("محمود آباد نمونه");
                break;

            case "قم":
                cities.clear();
                cities.add("دستجرد");
                cities.add("سلفچگان");
                cities.add("قنوات");
                cities.add("قم");
                cities.add("کهک");
                break;

            case "كردستان":
                cities.clear();
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

        return cities;
    }
}
