package com.intek.kalabean.Category;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.R;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CatPresenter(new KalaBeanRepository());
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

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
