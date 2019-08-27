package com.intek.kalabean.Search;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.R;
import com.tiper.MaterialSpinner;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    RadioGroup rg_fragmentSearch_searchRadioGroup;
    RadioButton rb_fragmentSearch_searchProduct;
    RadioButton rb_fragmentSearch_searchShop;
    MaterialSpinner sp_fragmentSearch_cityName;
    MaterialSpinner sp_fragmentSearch_shopKind;
    MaterialSpinner sp_fragmentSearch_shopName;
    MaterialSpinner sp_fragmentSearch_floor;
    MaterialSpinner sp_fragmentSearch_activity;
    TextInputLayout til_fragmentSearch_productName;
    TextInputEditText edt_fragmentSearch_productName;
    Button btn_fragmentSearch_search;
    TextView txt_fragmentSearch_resultNumber;

    @Override
    public int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void setupViews() {
        rg_fragmentSearch_searchRadioGroup = rootView.findViewById(R.id.rg_fragmentSearch_searchRadioGroup);
        rb_fragmentSearch_searchProduct = rootView.findViewById(R.id.rb_fragmentSearch_searchProduct);
        rb_fragmentSearch_searchShop = rootView.findViewById(R.id.rb_fragmentSearch_searchShop);
        sp_fragmentSearch_cityName = rootView.findViewById(R.id.sp_fragmentSearch_cityName);
        sp_fragmentSearch_shopKind = rootView.findViewById(R.id.sp_fragmentSearch_shopKind);
        sp_fragmentSearch_shopName = rootView.findViewById(R.id.sp_fragmentSearch_shopName);
        sp_fragmentSearch_floor = rootView.findViewById(R.id.sp_fragmentSearch_floor);
        sp_fragmentSearch_activity = rootView.findViewById(R.id.sp_fragmentSearch_activity);
        til_fragmentSearch_productName = rootView.findViewById(R.id.til_fragmentSearch_productName);
        edt_fragmentSearch_productName = rootView.findViewById(R.id.edt_fragmentSearch_productName);
        btn_fragmentSearch_search = rootView.findViewById(R.id.btn_fragmentSearch_search);
        txt_fragmentSearch_resultNumber = rootView.findViewById(R.id.txt_fragmentSearch_resultNumber);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
