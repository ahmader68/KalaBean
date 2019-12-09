package com.intek.kalabean.Category;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerCategoryAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.R;

import java.util.ArrayList;
import java.util.List;

public class CatFragment extends BaseFragment implements CatContract.View {

    private CatContract.Presenter presenter;

    private ConstraintLayout
            conFurniture,
            conCloth,
            conJewellery,
            conBeauty,
            conIndustry,
            conFood,
            conDigital,
            conSport,
            conArt,
            conKid,
            conWedding;

    private ImageView
            imgArrowFurniture,
            imgArrowCloth,
            imgArrowJewellery,
            imgArrowBeauty,
            imgArrowIndustry,
            imgArrowFood,
            imgArrowDigital,
            imgArrowSport,
            imgArrowArt,
            imgArrowKid,
            imgArrowWedding;

    private View
            view1,
            view2,
            view3,
            view4,
            view5,
            view6;

    private RecyclerView
            rvClothFurniture,
            rvJewelleryBeauty,
            rvIndustryFood,
            rvDigitalSport,
            rvArtKid,
            rvWedding;


    private List<String> items;

    private List<Integer> id;

    private int[][] viewId;


    private RecyclerCategoryAdapter categoryAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CatPresenter(new KalaBeanRepository());
        items = new ArrayList<>();
        id = new ArrayList<>();
        viewId = new int[11][4];


    }

    @Override
    public int getLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void setupViews() {
        conArt = rootView.findViewById(R.id.con_fragmentCategory_art);
        conBeauty = rootView.findViewById(R.id.con_fragmentCategory_beauty);
        conCloth = rootView.findViewById(R.id.con_fragmentCategory_cloth);
        conDigital = rootView.findViewById(R.id.con_fragmentCategory_digital);
        conFood = rootView.findViewById(R.id.con_fragmentCategory_food);
        conFurniture = rootView.findViewById(R.id.con_fragmentCategory_furniture);
        conIndustry = rootView.findViewById(R.id.con_fragmentCategory_industry);
        conJewellery = rootView.findViewById(R.id.con_fragmentCategory_jewellery);
        conKid = rootView.findViewById(R.id.con_fragmentCategory_kid);
        conSport = rootView.findViewById(R.id.con_fragmentCategory_sport);
        conWedding = rootView.findViewById(R.id.con_fragmentCategory_wedding);

        imgArrowArt = rootView.findViewById(R.id.img_fragmentCategory_arrowArt);
        imgArrowBeauty = rootView.findViewById(R.id.img_fragmentCategory_arrowBeauty);
        imgArrowCloth = rootView.findViewById(R.id.img_fragmentCategory_arrowCloth);
        imgArrowDigital = rootView.findViewById(R.id.img_fragmentCategory_arrowDigital);
        imgArrowFood = rootView.findViewById(R.id.img_fragmentCategory_arrowFood);
        imgArrowFurniture = rootView.findViewById(R.id.img_fragmentCategory_arrowFurniture);
        imgArrowIndustry = rootView.findViewById(R.id.img_fragmentCategory_arrowIndustry);
        imgArrowJewellery = rootView.findViewById(R.id.img_fragmentCategory_arrowJewellery);
        imgArrowKid = rootView.findViewById(R.id.img_fragmentCategory_arrowKid);
        imgArrowSport = rootView.findViewById(R.id.img_fragmentCategory_arrowSport);
        imgArrowWedding = rootView.findViewById(R.id.img_fragmentCategory_arrowWedding);

        view1 = rootView.findViewById(R.id.view_fragmentCategory_line1);
        view2 = rootView.findViewById(R.id.view_fragmentCategory_line2);
        view3 = rootView.findViewById(R.id.view_fragmentCategory_line3);
        view4 = rootView.findViewById(R.id.view_fragmentCategory_line4);
        view5 = rootView.findViewById(R.id.view_fragmentCategory_line5);
        view6 = rootView.findViewById(R.id.view_fragmentCategory_line6);

        rvClothFurniture = rootView.findViewById(R.id.rv_fragmentCategory_cloth_furniture);
        rvArtKid = rootView.findViewById(R.id.rv_fragmentCategory_kid_art);
        rvDigitalSport = rootView.findViewById(R.id.rv_fragmentCategory_sport_digital);
        rvIndustryFood = rootView.findViewById(R.id.rv_fragmentCategory_food_industry);
        rvJewelleryBeauty = rootView.findViewById(R.id.rv_fragmentCategory_beauty_jewellery);
        rvWedding = rootView.findViewById(R.id.rv_fragmentCategory_wedding);

        rvClothFurniture.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
        rvArtKid.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
        rvDigitalSport.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
        rvIndustryFood.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
        rvJewelleryBeauty.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
        rvWedding.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));


        viewId = setViewId();





        conFurniture.setOnClickListener(v -> {
            dataEntryList(2);
            imgArrowFurniture.setVisibility(View.VISIBLE);
            setVisiblity(conFurniture.getId());
            imgArrowCloth.setVisibility(View.INVISIBLE);
            view1.setVisibility(View.VISIBLE);
            rvClothFurniture.setVisibility(View.VISIBLE);
            runAnimation(rvClothFurniture, 1);
        });

        conCloth.setOnClickListener(v -> {
            dataEntryList(1);
            imgArrowCloth.setVisibility(View.VISIBLE);
            setVisiblity(conCloth.getId());
            view1.setVisibility(View.VISIBLE);
            imgArrowFurniture.setVisibility(View.INVISIBLE);
            rvClothFurniture.setVisibility(View.VISIBLE);
            runAnimation(rvClothFurniture, 0);
        });

        conJewellery.setOnClickListener(v -> {
            dataEntryList(4);
            imgArrowJewellery.setVisibility(View.VISIBLE);
            setVisiblity(conJewellery.getId());
            view2.setVisibility(View.VISIBLE);
            imgArrowBeauty.setVisibility(View.INVISIBLE);
            rvJewelleryBeauty.setVisibility(View.VISIBLE);
            runAnimation(rvJewelleryBeauty,1);
        });

        conBeauty.setOnClickListener(v -> {
            dataEntryList(3);
            imgArrowBeauty.setVisibility(View.VISIBLE);
            setVisiblity(conBeauty.getId());
            view2.setVisibility(View.VISIBLE);
            imgArrowJewellery.setVisibility(View.INVISIBLE);
            rvJewelleryBeauty.setVisibility(View.VISIBLE);
            runAnimation(rvJewelleryBeauty,0);
        });

        conIndustry.setOnClickListener(v -> {
            dataEntryList(7);
            imgArrowIndustry.setVisibility(View.VISIBLE);
            setVisiblity(conIndustry.getId());
            view3.setVisibility(View.VISIBLE);
            imgArrowFood.setVisibility(View.INVISIBLE);
            rvIndustryFood.setVisibility(View.VISIBLE);
            runAnimation(rvIndustryFood,1);
        });

        conFood.setOnClickListener(v -> {
            dataEntryList(6);
            imgArrowFood.setVisibility(View.VISIBLE);
            setVisiblity(conFood.getId());
            view3.setVisibility(View.VISIBLE);
            imgArrowIndustry.setVisibility(View.INVISIBLE);
            rvIndustryFood.setVisibility(View.VISIBLE);
            runAnimation(rvIndustryFood,0);
        });

        conDigital.setOnClickListener(v -> {
            dataEntryList(10);
            imgArrowDigital.setVisibility(View.VISIBLE);
            setVisiblity(conDigital.getId());
            view4.setVisibility(View.VISIBLE);
            imgArrowSport.setVisibility(View.INVISIBLE);
            rvDigitalSport.setVisibility(View.VISIBLE);
            runAnimation(rvDigitalSport,1);
        });

        conSport.setOnClickListener(v -> {
            dataEntryList(11);
            imgArrowSport.setVisibility(View.VISIBLE);
            setVisiblity(conSport.getId());
            view4.setVisibility(View.VISIBLE);
            imgArrowDigital.setVisibility(View.INVISIBLE);
            rvDigitalSport.setVisibility(View.VISIBLE);
            runAnimation(rvDigitalSport,0);
        });

        conArt.setOnClickListener(v -> {
            dataEntryList(8);
            imgArrowArt.setVisibility(View.VISIBLE);
            setVisiblity(conArt.getId());
            view5.setVisibility(View.VISIBLE);
            imgArrowKid.setVisibility(View.INVISIBLE);
            rvArtKid.setVisibility(View.VISIBLE);
            runAnimation(rvArtKid,1);
        });

        conKid.setOnClickListener(v -> {
            dataEntryList(5);
            imgArrowKid.setVisibility(View.VISIBLE);
            setVisiblity(conKid.getId());
            view5.setVisibility(View.VISIBLE);
            imgArrowArt.setVisibility(View.INVISIBLE);
            rvArtKid.setVisibility(View.VISIBLE);
            runAnimation(rvArtKid,0);
        });

        conWedding.setOnClickListener(v -> {
            dataEntryList(9);
            imgArrowWedding.setVisibility(View.VISIBLE);
            setVisiblity(conWedding.getId());
            view6.setVisibility(View.VISIBLE);
            rvArtKid.setVisibility(View.GONE);
            rvWedding.setVisibility(View.VISIBLE);
            runAnimation(rvWedding,0);
        });

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    private void runAnimation(RecyclerView recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if (type == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_from_right);
        } else if (type == 1) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_from_left);
        }
        categoryAdapter = new RecyclerCategoryAdapter(getViewContext(), items);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }

    private void setVisiblity(int targetId) {
        for (int i = 0; i < 11; i++) {
            int conId = viewId[i][0];

            if (conId != targetId) {
                ImageView arrowImage = rootView.findViewById(viewId[i][1]);
                View view = rootView.findViewById(viewId[i][2]);
                RecyclerView recyclerView = rootView.findViewById(viewId[i][3]);
                arrowImage.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }
        }
    }


    private int[][] setViewId() {
        int[][] viewId = new int[11][4];

        viewId[0][0] = conFurniture.getId();
        viewId[0][1] = imgArrowFurniture.getId();
        viewId[0][2] = view1.getId();
        viewId[0][3] = rvClothFurniture.getId();

        viewId[1][0] = conCloth.getId();
        viewId[1][1] = imgArrowCloth.getId();
        viewId[1][2] = view1.getId();
        viewId[1][3] = rvClothFurniture.getId();

        viewId[2][0] = conJewellery.getId();
        viewId[2][1] = imgArrowJewellery.getId();
        viewId[2][2] = view2.getId();
        viewId[2][3] = rvJewelleryBeauty.getId();

        viewId[3][0] = conBeauty.getId();
        viewId[3][1] = imgArrowBeauty.getId();
        viewId[3][2] = view2.getId();
        viewId[3][3] = rvJewelleryBeauty.getId();

        viewId[4][0] = conIndustry.getId();
        viewId[4][1] = imgArrowIndustry.getId();
        viewId[4][2] = view3.getId();
        viewId[4][3] = rvIndustryFood.getId();

        viewId[5][0] = conFood.getId();
        viewId[5][1] = imgArrowFood.getId();
        viewId[5][2] = view3.getId();
        viewId[5][3] = rvIndustryFood.getId();

        viewId[6][0] = conDigital.getId();
        viewId[6][1] = imgArrowDigital.getId();
        viewId[6][2] = view4.getId();
        viewId[6][3] = rvDigitalSport.getId();

        viewId[7][0] = conSport.getId();
        viewId[7][1] = imgArrowSport.getId();
        viewId[7][2] = view4.getId();
        viewId[7][3] = rvDigitalSport.getId();

        viewId[8][0] = conArt.getId();
        viewId[8][1] = imgArrowArt.getId();
        viewId[8][2] = view5.getId();
        viewId[8][3] = rvArtKid.getId();

        viewId[9][0] = conKid.getId();
        viewId[9][1] = imgArrowKid.getId();
        viewId[9][2] = view5.getId();
        viewId[9][3] = rvArtKid.getId();

        viewId[10][0] = conWedding.getId();
        viewId[10][1] = imgArrowWedding.getId();
        viewId[10][2] = view6.getId();
        viewId[10][3] = rvWedding.getId();


        return viewId;
    }

    private void dataEntryList(int id){
        switch (id){
            case 1:
                items.clear();
                items.add("پوشاک مردانه");
                items.add("پوشاک زنانه");
                items.add("پوشاک کودک");
                items.add("پوشاک چرم");
                items.add("پوشاک ورزشی");
                items.add("شال و روسری");
                items.add("لباس زیر");
                items.add("پوشاک حجاب");
                items.add("کیف و کفش");
                break;
            case 2:
                items.clear();
                items.add("فرش و پارچه و پرده");
                items.add("کاغذ دیوازی");
                items.add("لوازم تزئینی منزل");
                items.add("تجهیزات آشپزخانه");
                items.add("کادویی لوکس");
                items.add("شوینده و بهداشتی");
                items.add("گل");
                items.add("کالای خواب");
                items.add("سرویس چوب");
                items.add("مبلمان");
                items.add("لوازم تولد");
                items.add("لوازم برقی");
                items.add("صنایع دستی");
                break;
            case 3:
                items.clear();
                items.add("سالن زیبایی");
                items.add("محصولات مراقبتی پوست");
                items.add("لوازم آرایشی");
                items.add("لوازم ورزشی");
                items.add("باشگاه ورزشی");
                items.add("مکمل ورزشی");
                break;
            case 4:
                items.clear();
                items.add("ساعت");
                items.add("عینک");
                items.add("طلا و جواهر");
                items.add("نقره و زیورآلات");
                items.add("عطر");
                break;
            case 5:
                items.clear();
                items.add("پوشاک کودک");
                items.add("سیسمونی");
                items.add("اسباب بازی");
                items.add("کلوپ ورزشی");
                items.add("شهربازی");
                break;
            case 6:
                items.clear();
                items.add("کافی شاپ و رستوران");
                items.add("کیک و شیرینی");
                items.add("سالن و تالار پذیرایی");
                items.add("آجیل");
                items.add("چاشنی های خوراکی");
                items.add("لوازم قنادی");
                items.add("مکمل ورزشی");
                items.add("غرفه غذایی فروشگاه های زنجیره ای");
                break;
            case 7:
                items.clear();
                items.add("لوازم خودرو");
                items.add("لوازم ساختمان");
                items.add("موتور و دوچرخه");
                items.add("تاسیسات");
                items.add("ابزارآلات");
                items.add("ادوات کشاورزی");
                items.add("شیرآلات و تصفیه آب");
                items.add("الکتریکی و روشنایی");
                break;
            case 8:
                items.clear();
                items.add("کتاب");
                items.add("لوازم تحریر");
                items.add("آتلیه عکاسی");
                items.add("موسیقی و سازها");
                break;
            case 9:
                items.clear();
                items.add("پوشاک عروس");
                items.add("سالن زیبایی");
                items.add("آتلیه عکاسی");
                items.add("گل");
                break;
            case 10:
                items.clear();
                items.add("موبایل");
                items.add("کامپیوتر");
                items.add("خدمات چاپ");
                break;
            case 11:
                items.clear();
                items.add("کلوپ بازی");
                items.add("باشگاه ورزشی");
                items.add("سینما");
                items.add("لوازم ورزشی");
                items.add("شهربازی");
                items.add("دامپزشکی");
                break;

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
        getView().setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_MainActivity_mainLayout,new MainFragment()).commit();
                return true;
            }
            return false;
        });
    }
}
