package com.intek.kalabean.Search;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Classes.GetProvinceAndCity;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.ActivityKind;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.Floor;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.MallKindList;
import com.intek.kalabean.Model.ShopCenter;
import com.intek.kalabean.Model.ShopCenterList;
import com.intek.kalabean.R;
import com.tiper.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    private Button
            btnStore,
            btnProduct,
            btnSeach;

    private Spinner
            spProvince,
            spCity,
            spStoreKind,
            spStoreName,
            spFloor,
            spActivityKind;

    private EditText edtName;

    private ArrayAdapter<String>
            provinceAdapter,
            cityAdapter,
            storeKindAdapter,
            storeNameAdapter,
            floorAdapter,
            activityKindAdapter;

    private List<String>
            provinces,
            cities,
            storeKinds,
            storeNames,
            floors,
            activityKinds;

    private MallKindList mallKindList;
    private ShopCenterList shopCenterList;
    private FloorList floorList;
    private ActivityKindList activityKindList;

    private int
            userId,
            cityId,
            storeKindId,
            activityId,
            floorId,
            shopCenterId;

    private String province,city;

    private GetProvinceAndCity getProvinceAndCity;

    SearchContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchPresenter(new KalaBeanRepository());
        getProvinceAndCity = new GetProvinceAndCity();
        provinces = new ArrayList<>();
        cities = new ArrayList<>();



    }

    @Override
    public int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void setupViews() {

        btnStore = rootView.findViewById(R.id.btn_fragmentSearch_store);
        btnProduct = rootView.findViewById(R.id.btn_fragmentSearch_product);
        btnSeach = rootView.findViewById(R.id.btn_fragmentSearch_search);

        spProvince = rootView.findViewById(R.id.sp_fragmentSearch_province);
        spCity = rootView.findViewById(R.id.sp_fragmentSearch_city);
        spActivityKind = rootView.findViewById(R.id.sp_fragmentSearch_activityKind);
        spFloor = rootView.findViewById(R.id.sp_fragmentSearch_floor);
        spStoreKind = rootView.findViewById(R.id.sp_fragmentSearch_storeKind);
        spStoreName = rootView.findViewById(R.id.sp_fragmentSearch_storeName);

        edtName = rootView.findViewById(R.id.edt_fragmentSearch_prodName);

        spActivityKind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                activityId = activityKindList.getItems().get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spStoreKind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                storeKindId = mallKindList.getItems().get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spStoreName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shopCenterId = shopCenterList.getItems().get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floorId = floorList.getFloorList().get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        provinces = getProvinceAndCity.getProvince();
        provinceAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,provinces);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProvince.setAdapter(provinceAdapter);

        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                province = spProvince.getSelectedItem().toString();
                cities = getProvinceAndCity.getCity(province);
                cityAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,cities);
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spCity.setAdapter(cityAdapter);

                spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        city = spCity.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getStoreKind(MallKindList mallKindList) {
        storeKinds = new ArrayList<>();
        this.mallKindList = mallKindList;
        for(MallKindList.MallKind kind : mallKindList.getItems()){
            storeKinds.add(kind.getName());
        }

        storeKindAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,storeKinds);
        storeKindAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStoreKind.setAdapter(storeKindAdapter);
    }

    @Override
    public void getActivityKind(ActivityKindList activityKindList) {
        activityKinds = new ArrayList<>();
        this.activityKindList = activityKindList;
        for(ActivityKind activityKind : activityKindList.getItems()){
            activityKinds.add(activityKind.getName());
        }
        activityKindAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,activityKinds);
        activityKindAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spActivityKind.setAdapter(activityKindAdapter);
    }

    @Override
    public void getShopCenterList(ShopCenterList shopCenterList) {
        storeNames = new ArrayList<>();
        this.shopCenterList = shopCenterList;
        for(ShopCenter shopCenter : shopCenterList.getItems()){
            storeNames.add(shopCenter.getTitleFA());
        }
        storeNameAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,storeNames);
        storeNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStoreName.setAdapter(storeNameAdapter);
    }

    @Override
    public void getFloorList(FloorList floorList) {
        floors = new ArrayList<>();
        this.floorList = floorList;
        for(Floor floor : floorList.getFloorList()){
            floors.add(floor.getTitleFA());
        }

        floorAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,floors);
        floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFloor.setAdapter(floorAdapter);
    }
}
