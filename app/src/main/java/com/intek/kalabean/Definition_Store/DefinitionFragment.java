package com.intek.kalabean.Definition_Store;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.MainActivity;
import com.intek.kalabean.Model.ActivityKind;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.Floor;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.MallKindList;
import com.intek.kalabean.Model.ShopCenter;
import com.intek.kalabean.Model.ShopCenterList;
import com.intek.kalabean.Model.StoreDif;
import com.intek.kalabean.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.tiper.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class DefinitionFragment extends BaseFragment implements DefinitionContract.View {
    private DefinitionContract.Presenter presenter;


    private TextInputLayout tilFragmentDefinitionStoreName;
    private TextInputEditText edtFragmentDefinitionStoreName;



    private TextInputLayout tilFragmentDefinitionTelegram;
    private TextInputEditText edtFragmentDefinitionTelegram;


    private TextInputLayout tilFragmentDefinitionPhone;
    private TextInputEditText edtFragmentDefinitionPhone;


    private TextInputLayout tilFragmentDefinitionSite;
    private TextInputEditText edtFragmentDefinitionSite;


    private TextInputLayout tilFragmentDefinitionWorkHour;
    private TextInputEditText edtFragmentDefinitionWorkHour;



    private TextInputLayout tilFragmentDefinitionAddressFa;
    private TextInputEditText edtFragmentDefinitionAddressFa;




    private MaterialSpinner spFragmentDefinitionStoreKind;
    private MaterialSpinner spFragmentDefinitionComplexName;
    private MaterialSpinner spFragmentDefinitionFloor;
    private MaterialSpinner spFragmentDefinitionPrivince;
    private MaterialSpinner getSpFragmentDefinitionCity;


    private Button btnFragmentDefinitionAccept;



    private ImageView imgFragmentDefinitionInnerImage;
    private ImageView ImgFragmentDefinitionOuterImage;

    private ConstraintLayout conFragmentDefinitionMainLayout;
    private ConstraintLayout conFragmentDefinitionSpinner;
    private ConstraintLayout conFragmentDefinitionActivityKind;
    private ConstraintLayout conFragmentDefinitionPhone;
    private ConstraintLayout conFragmentDefinitionAddress;

    private ArrayAdapter<String> malKindArrayAdapter;
    private ArrayAdapter<String> shopCenterListAdapter;
    private ArrayAdapter<String> floorListAdapter;
    private ArrayAdapter<String> activityKindAdapter;

    private List<String> malkind;
    private MallKindList spMalKindList;

    private List<String> shopCenterNames;
    private ShopCenterList spShopCenter;

    private List<String> floorNames;
    private FloorList spFloorList;

    private List<String> activityName;
    private ActivityKindList spActivityKind;

    private int mallId;
    private int shopCenterId;
    private int floorId;
    private int activityId;
    private int cityId;


    public static final int PERMISSION_UPLOAD_REQUEST_CODE = 200;

    private List<String> mkindsName;
    private List<String> akindName;
    private List<MallKindList.MallKind> mkinds;
    private List<ActivityKind> akinds;
    private ArrayAdapter<String> activityKindsArrayAdapter;
    private MaterialSpinner spMallKind;
   // private MaterialSpinner spActivityKind;
    private ArrayAdapter<String> storeKindArrayAdapter;
    public ConstraintLayout con_fragmentDefinition_mainLayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new DefinitionPresenter(new KalaBeanRepository());
        mkindsName = new ArrayList<>();
        akindName = new ArrayList<>();
        presenter.storeKind();
        presenter.activityKind();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_definition;
    }

    @Override
    public void setupViews() {
        conFragmentDefinitionMainLayout = rootView.findViewById(R.id.con_fragmentDefinition_mainLayout);
        conFragmentDefinitionMainLayout.setRotationY(180);


        tilFragmentDefinitionStoreName = rootView.findViewById(R.id.til_fragmentDefinition_storeName);
        edtFragmentDefinitionStoreName = rootView.findViewById(R.id.edt_fragmentDefinition_storeName);



        tilFragmentDefinitionTelegram = rootView.findViewById(R.id.til_fragmentDefinition_telegram);
        edtFragmentDefinitionTelegram = rootView.findViewById(R.id.edt_fragmentDefinition_telegram);


        tilFragmentDefinitionPhone = rootView.findViewById(R.id.til_fragmentDefinition_phone);
        edtFragmentDefinitionPhone = rootView.findViewById(R.id.edt_fragmentDefinition_phone);


        tilFragmentDefinitionSite = rootView.findViewById(R.id.til_fragmentDefinition_site);
        edtFragmentDefinitionSite = rootView.findViewById(R.id.edt_fragmentDefinition_site);

        tilFragmentDefinitionWorkHour = rootView.findViewById(R.id.til_fragmentDefinition_workHour);
        edtFragmentDefinitionWorkHour = rootView.findViewById(R.id.edt_fragmentDefinition_workHour);


        tilFragmentDefinitionAddressFa = rootView.findViewById(R.id.til_fragmentDefinition_address);
        edtFragmentDefinitionAddressFa = rootView.findViewById(R.id.edt_fragmentDefinition_address);



        spFragmentDefinitionComplexName = rootView.findViewById(R.id.sp_fragmentDefinition_storeName);
        spFragmentDefinitionFloor = rootView.findViewById(R.id.sp_fragmentDefinition_floor);
        spFragmentDefinitionStoreKind = rootView.findViewById(R.id.sp_fragmentDefinition_storeKind);


        btnFragmentDefinitionAccept = rootView.findViewById(R.id.btn_fragmentDefinition_save);

        imgFragmentDefinitionInnerImage = rootView.findViewById(R.id.img_fragmentDefinition_innerImage);
        ImgFragmentDefinitionOuterImage = rootView.findViewById(R.id.img_fragmentDefinition_outerImage);

        spFragmentDefinitionStoreKind.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, View view, int position, long l) {
                mallId = spMalKindList.getItems().get(position).getId();
                presenter.ShopCenterList(mallId);


            }

            @Override
            public void onNothingSelected(MaterialSpinner materialSpinner) {

            }
        });
        spFragmentDefinitionComplexName.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, View view, int position, long l) {
                shopCenterId = spShopCenter.getItems().get(position).getId();
                cityId = spShopCenter.getItems().get(position).getCityid();
                presenter.floorList(shopCenterId);
            }

            @Override
            public void onNothingSelected(MaterialSpinner materialSpinner) {

            }
        });
        spFragmentDefinitionFloor.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, View view, int position, long l) {
                floorId = spFloorList.getFloorList().get(position).getId();

            }

            @Override
            public void onNothingSelected(MaterialSpinner materialSpinner) {

            }
        });




        btnFragmentDefinitionAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreDif storeDif = new StoreDif();
                if (!validateStoreKind() || !validateComplexName() || !validateFloor() || !validateStoreName()  || !validatePhone() || !validAddress()) {
                    return;
                } else {
                    int storeKind;
                    int complexName;
                    int floor;
                    String fName;
                    String phone;
                    String fAddress;
                    int jobCatid;


                    storeKind = mallId;
                    complexName = shopCenterId;
                    floor = floorId;
                    fName = edtFragmentDefinitionStoreName.getText().toString();
                    phone = edtFragmentDefinitionPhone.getText().toString();
                    fAddress = edtFragmentDefinitionAddressFa.getText().toString();
                    jobCatid = activityId;
                    storeDif.setShopCenterKind(storeKind);
                    storeDif.setShopCenterName(complexName);
                    storeDif.setStoreFloor(floor);
                    storeDif.setFstoreName(fName);
                    storeDif.setPhone(phone);
                    storeDif.setFaddress(fAddress);
                    storeDif.setCityid(cityId);
                    storeDif.setJobcatid(jobCatid);
                    presenter.storeDefinition(storeDif);
                }
            }
        });

    }

    private void checkMyPermission() {
        if (ContextCompat.checkSelfPermission(getViewContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_UPLOAD_REQUEST_CODE);
        } else {
            takePicture();
        }
    }

    private void takePicture() {
        MainActivity.requestCodeCheck = 1;
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(getActivity());
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getStoreKind(MallKindList mallKinds) {
        malkind = new ArrayList<>();
        spMalKindList = mallKinds;
        for (MallKindList.MallKind kind : mallKinds.getItems()) {
            malkind.add(kind.getName());
        }
        malKindArrayAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, malkind);
        malKindArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFragmentDefinitionStoreKind.setAdapter(malKindArrayAdapter);
    }

    @Override
    public void getActivityKind(ActivityKindList activityKindList) {
        activityName = new ArrayList<>();
        spActivityKind = activityKindList;
        for (ActivityKind activityKind : activityKindList.getItems()) {
            activityName.add(activityKind.getName());
        }
        activityKindAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, activityName);
        activityKindAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }


    @Override
    public void getShopCenterList(ShopCenterList shopCenterList) {
        shopCenterNames = new ArrayList<>();
        spShopCenter = shopCenterList;
        for (ShopCenter shopCenter : shopCenterList.getItems()) {
            shopCenterNames.add(shopCenter.getTitleFA() + "");
        }
        shopCenterListAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, shopCenterNames);
        shopCenterListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFragmentDefinitionComplexName.setAdapter(shopCenterListAdapter);
    }

    @Override
    public void getFloorList(FloorList floorList) {
        floorNames = new ArrayList<>();
        spFloorList = floorList;
        for (Floor floor : floorList.getFloorList()) {
            floorNames.add(floor.getTitleFA());
        }
        floorListAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, floorNames);
        floorListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFragmentDefinitionFloor.setAdapter(floorListAdapter);
    }

    @Override
    public void getStoreId(StoreDif storeDif) {
        int storeId = storeDif.getResult();
        if(storeId > 0){
            showMessage("فروشگاه با موفقیت ثبت شد");
        }
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

    private boolean validateStoreKind() {
        String selectedItem = (String) spFragmentDefinitionStoreKind.getSelectedItem();
        if (selectedItem == null) {
            spFragmentDefinitionStoreKind.setError("لطفا یکی از گزینه ها را انتخاب کنید");
            conFragmentDefinitionSpinner.requestFocus();
            return false;
        } else {
            spFragmentDefinitionStoreKind.setError(null);
            return true;
        }
    }

    private boolean validateComplexName() {
        String selectedItem = (String) spFragmentDefinitionComplexName.getSelectedItem();
        if (selectedItem == null) {
            spFragmentDefinitionComplexName.setError("لطفا یکی از گزینه ها را انتخاب کنید");
            conFragmentDefinitionSpinner.requestFocus();
            return false;
        } else {
            spFragmentDefinitionComplexName.setError(null);
            return true;
        }
    }

    private boolean validateFloor() {
        String selectedItem = (String) spFragmentDefinitionFloor.getSelectedItem();
        if (selectedItem == null) {
            spFragmentDefinitionFloor.setError("لطفا یکی از گزینه ها را انتخاب کنید");
            conFragmentDefinitionSpinner.requestFocus();
            return false;
        } else {
            spFragmentDefinitionFloor.setError(null);
            return true;
        }
    }

    private boolean validateStoreName() {
        String selectedItem = edtFragmentDefinitionStoreName.getText().toString().trim();
        if (selectedItem.isEmpty()) {
            tilFragmentDefinitionStoreName.setError("فیلد نام فروشگاه خالی است");
            conFragmentDefinitionSpinner.requestFocus();
            return false;
        } else {
            tilFragmentDefinitionStoreName.setError(null);
            return true;
        }
    }



    private boolean validatePhone() {
        String selectedItem = edtFragmentDefinitionPhone.getText().toString().trim();
        if (selectedItem.isEmpty()) {
            tilFragmentDefinitionPhone.setError("فیلد تلفن خالی است");
            conFragmentDefinitionPhone.requestFocus();
            return false;
        } else {
            tilFragmentDefinitionPhone.setError(null);
            return true;
        }

    }

    private boolean validAddress() {
        String selectedItem = edtFragmentDefinitionAddressFa.getText().toString().trim();
        if (selectedItem.isEmpty()) {
            tilFragmentDefinitionAddressFa.setError("فیلد آدرس فارسی فروشگاه خالی است");
            conFragmentDefinitionAddress.requestFocus();
            return false;
        } else {
            tilFragmentDefinitionAddressFa.setError(null);
            return true;
        }
    }
}
