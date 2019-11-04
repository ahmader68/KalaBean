package com.intek.kalabean.Request_Product;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Classes.GetProvinceAndCity;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Definition_Store.DefinitionFragment;
import com.intek.kalabean.MainActivity;
import com.intek.kalabean.Model.ActivityKind;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.tiper.MaterialSpinner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.intek.kalabean.Definition_Store.DefinitionFragment.PERMISSION_UPLOAD_REQUEST_CODE;

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

    private List<String> activityKindName;
    private List<String> provinces;
    private List<String> cities;

    private ActivityKindList spActivityKindList;

    private GetProvinceAndCity getProvinceAndCity;

    private String state;
    private String city;







    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RequestPresenter(new KalaBeanRepository());
        presenter.activityKind();
        getProvinceAndCity = new GetProvinceAndCity();

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

        cities = new ArrayList<>();
        provinces = new ArrayList<>();
        provinces = getProvinceAndCity.getProvince();

        provinceAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,provinces);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFragmentRequestProductProvince.setAdapter(provinceAdapter);

        spFragmentRequestProductProvince.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(@NotNull MaterialSpinner materialSpinner, @org.jetbrains.annotations.Nullable View view, int i, long l) {
                state = spFragmentRequestProductProvince.getSelectedItem().toString();
                cities = getProvinceAndCity.getCity(state);
                cityAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,cities);
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spFragmentRequestProductCity.setAdapter(cityAdapter);
                spFragmentRequestProductCity.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(@NotNull MaterialSpinner materialSpinner, @org.jetbrains.annotations.Nullable View view, int i, long l) {
                        city = spFragmentRequestProductCity.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(@NotNull MaterialSpinner materialSpinner) {

                    }
                });
            }

            @Override
            public void onNothingSelected(@NotNull MaterialSpinner materialSpinner) {

            }
        });

        linearFragmentRequestProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMyPermission(6);
                imgFragmentRequestProductSrc.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getActivityKindRequest(ActivityKindList activityKindList) {
        activityKindName = new ArrayList<>();
        spActivityKindList = activityKindList;

        for(ActivityKind activityKind : activityKindList.getItems()){
            activityKindName.add(activityKind.getName());
        }
        activityKindAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,activityKindName);
        activityKindAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFragmentRequestProductActivityKind.setAdapter(activityKindAdapter);
    }


    private void checkMyPermission(int imgId) {
        if (ContextCompat.checkSelfPermission(getViewContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_UPLOAD_REQUEST_CODE);
        } else {
            takePicture(imgId);
        }
    }

    private void takePicture(int id) {
        MainActivity.requestCodeCheck = id;
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(getActivity());
    }

    @Override
    public Context getViewContext() {
        return getContext();
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
