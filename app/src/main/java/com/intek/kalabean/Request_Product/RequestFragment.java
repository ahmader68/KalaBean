package com.intek.kalabean.Request_Product;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.R;
import com.tiper.MaterialSpinner;

public class RequestFragment extends BaseFragment implements RequestContract.View {

    private RequestContract.Presenter presenter;

    private MaterialSpinner spFragmentRequestProductCity;
    private MaterialSpinner spFragmentRequestProductProvince;
    private MaterialSpinner spFragmentRequestProductActivityKind;

    private ArrayAdapter<String> cityAdapter;
    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> activityKindAdapter;

    private TextInputLayout tilFragmentRequestProductProductName;
    private TextInputEditText edtFragmentRequestProductProductName;

    private EditText edtFragmentRequestProductColor;
    private EditText edtFragmentRequestProductSize;
    private EditText edtFragmentRequestProductFromPrice;
    private EditText edtFragmentRequestProductToPrice;

    private LinearLayout linearFragmentRequestProductImage;

    private ImageView imgFragmentRequestProductSrc;

    private Button btnFragmentRequestProductAccept;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RequestPresenter(new KalaBeanRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_request_product;
    }

    @Override
    public void setupViews() {

        spFragmentRequestProductActivityKind = rootView.findViewById(R.id.sp_fragmentRequestProduct_activityKind);
        spFragmentRequestProductCity = rootView.findViewById(R.id.sp_fragmentRequestProduct_city);
        spFragmentRequestProductProvince = rootView.findViewById(R.id.sp_fragmentRequestProduct_province);

        tilFragmentRequestProductProductName = rootView.findViewById(R.id.til_fragmentRequestProduct_productName);
        edtFragmentRequestProductProductName = rootView.findViewById(R.id.edt_fragmentRequestProduct_productName);

        edtFragmentRequestProductColor = rootView.findViewById(R.id.edt_fragmentRequestProduct_color);
        edtFragmentRequestProductSize = rootView.findViewById(R.id.edt_fragmentRequestProduct_size);
        edtFragmentRequestProductFromPrice = rootView.findViewById(R.id.edt_fragmentRequestProduct_fromPrice);
        edtFragmentRequestProductToPrice = rootView.findViewById(R.id.edt_fragmentRequestProduct_toPrice);

        linearFragmentRequestProductImage = rootView.findViewById(R.id.linear_fragmentRequestProduct_image);

        imgFragmentRequestProductSrc = rootView.findViewById(R.id.img_fragmentRequestProduct_srcProduct);

        btnFragmentRequestProductAccept = rootView.findViewById(R.id.btn_fragmentRequestProduct_accept);

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getActivityKind() {

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
