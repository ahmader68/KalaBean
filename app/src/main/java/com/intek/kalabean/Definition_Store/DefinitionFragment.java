package com.intek.kalabean.Definition_Store;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Classes.GetProvinceAndCity;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Home.HomeFragment;
import com.intek.kalabean.MainActivity;
import com.intek.kalabean.Main_Page.MainFragment;
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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DefinitionFragment extends BaseFragment implements DefinitionContract.View {
    private DefinitionContract.Presenter presenter;


    private EditText
                edtFragmentDefinitionStoreName,
                edtFragmentDefinitionTelegram,
                edtFragmentDefinitionInstagram,
                edtFragmentDefinitionPhone,
                edtFragmentDefinitionSite,
                edtFragmentDefinitionWorkHour,
                edtFragmentDefinitionAddressFa;







    private Spinner
            spFragmentDefinitionStoreKind,
            spFragmentDefinitionComplexName,
            spFragmentDefinitionFloor,
            spFragmentDefinitionProvince,
            spFragmentDefinitionCity,
            spFragmentDefinitionActivityKind,
            spMallKind;




    private Button btnFragmentDefinitionAccept;


    private ImageView
            imgFragmentDefinitionInnerImage,
            imgFragmentDefinitionOuterImage,
            imgFragmentDefinitionsrcInner,
            imgFragmentDefinitionsrcOuter,
            imgFragmentDefinitionQuestionMark;


    private ConstraintLayout
            conFragmentDefinitionMainLayout,
            con_fragmentDefinition_mainLayout,
            conFragmentDefinitionSpinner,
            conFragmentDefinitionActivityKind,
            conFragmentDefinitionPhone,
            conFragmentDefinitionAddress;


    private ArrayAdapter<String>
            malKindArrayAdapter,
            shopCenterListAdapter,
            floorListAdapter,
            activityKindAdapter,
            adapterCity,
            adapterState,
            activityKindsArrayAdapter;

    private List<String>
            cities,
            province,
            malkind,
            shopCenterNames,
            floorNames,
            activityName,
            mkindsName,
            akindName;


    private MallKindList spMalKindList;


    private ShopCenterList spShopCenter;


    private FloorList spFloorList;

    private ActivityKindList spActivityKind;

    private int
            mallId,
            shopCenterId,
            floorId,
            activityId,
            cityId,
            userId;


    private String state,city;


    private GetProvinceAndCity getProvinceAndCity;

    private StoreDif storeDif;


    public static final int PERMISSION_UPLOAD_REQUEST_CODE = 200;

    private List<MallKindList.MallKind> mkinds;
    private List<ActivityKind> akinds;


    // private MaterialSpinner spActivityKind;
    private ArrayAdapter<String> storeKindArrayAdapter;

    private SharedPreferences sharedPreferences;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        userId = sharedPreferences.getInt("userid",0);
        getProvinceAndCity = new GetProvinceAndCity();
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

        edtFragmentDefinitionStoreName = rootView.findViewById(R.id.edt_fragmentDefinition_storeName);
        edtFragmentDefinitionTelegram = rootView.findViewById(R.id.edt_fragmentDefinition_telegram);
        edtFragmentDefinitionPhone = rootView.findViewById(R.id.edt_fragmentDefinition_phone);
        edtFragmentDefinitionSite = rootView.findViewById(R.id.edt_fragmentDefinition_site);
        edtFragmentDefinitionWorkHour = rootView.findViewById(R.id.edt_fragmentDefinition_workHour);
        edtFragmentDefinitionAddressFa = rootView.findViewById(R.id.edt_fragmentDefinition_address);
        edtFragmentDefinitionInstagram = rootView.findViewById(R.id.edt_fragmentDefinition_instagram);


        spFragmentDefinitionComplexName = rootView.findViewById(R.id.sp_fragmentDefinition_storeName);
        spFragmentDefinitionFloor = rootView.findViewById(R.id.sp_fragmentDefinition_floor);
        spFragmentDefinitionStoreKind = rootView.findViewById(R.id.sp_fragmentDefinition_storeKind);
        spFragmentDefinitionCity = rootView.findViewById(R.id.sp_fragmentDefinition_city);
        spFragmentDefinitionProvince = rootView.findViewById(R.id.sp_fragmentDefinition_province);
        spFragmentDefinitionActivityKind = rootView.findViewById(R.id.sp_fragmentDefinition_activityKind);


        btnFragmentDefinitionAccept = rootView.findViewById(R.id.btn_fragmentDefinition_save);

        imgFragmentDefinitionInnerImage = rootView.findViewById(R.id.img_fragmentDefinition_innerImage);
        imgFragmentDefinitionOuterImage = rootView.findViewById(R.id.img_fragmentDefinition_outerImage);
        imgFragmentDefinitionsrcInner = rootView.findViewById(R.id.img_fragmentDefinition_srcInner);
        imgFragmentDefinitionsrcOuter = rootView.findViewById(R.id.img_fragmentDefinition_srcOuter);
        imgFragmentDefinitionQuestionMark = rootView.findViewById(R.id.img_fragmentRegister_question);

        edtFragmentDefinitionSite.setText(R.string.http);
        Selection.setSelection(edtFragmentDefinitionSite.getText(), edtFragmentDefinitionSite.getText().length());
        edtFragmentDefinitionSite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("http://")) {
                    edtFragmentDefinitionSite.setText(R.string.http);
                    Selection.setSelection(edtFragmentDefinitionSite.getText(), edtFragmentDefinitionSite.getText().length());
                }
            }
        });

        edtFragmentDefinitionTelegram.setText("@");
        Selection.setSelection(edtFragmentDefinitionTelegram.getText(), edtFragmentDefinitionTelegram.getText().length());
        edtFragmentDefinitionTelegram.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("@")) {
                    edtFragmentDefinitionTelegram.setText("@");
                    Selection.setSelection(edtFragmentDefinitionTelegram.getText(), edtFragmentDefinitionTelegram.getText().length());
                }
            }
        });

        edtFragmentDefinitionInstagram.setText("@");
        Selection.setSelection(edtFragmentDefinitionInstagram.getText(), edtFragmentDefinitionInstagram.getText().length());
        edtFragmentDefinitionInstagram.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("@")) {
                    edtFragmentDefinitionInstagram.setText("@");
                    Selection.setSelection(edtFragmentDefinitionInstagram.getText(), edtFragmentDefinitionInstagram.getText().length());
                }
            }
        });


        imgFragmentDefinitionInnerImage.setOnClickListener(v -> {
            checkMyPermission(4);
            imgFragmentDefinitionsrcInner.setVisibility(View.VISIBLE);
            if(!imgFragmentDefinitionsrcOuter.isShown()){
                imgFragmentDefinitionsrcOuter.setVisibility(View.VISIBLE);
            }
        });



        imgFragmentDefinitionOuterImage.setOnClickListener(v -> {
            checkMyPermission(5);
            imgFragmentDefinitionsrcOuter.setVisibility(View.VISIBLE);

        });


        btnFragmentDefinitionAccept.setOnClickListener(v -> {
            storeDif = new StoreDif();

            storeDif.setJobcatid(activityId);
            storeDif.setCityid(cityId);
            storeDif.setFaddress(edtFragmentDefinitionAddressFa.getText().toString());
            storeDif.setEmail("");
            storeDif.setFax("");
            storeDif.setPhone(edtFragmentDefinitionPhone.getText().toString());
            storeDif.setFstoreName(edtFragmentDefinitionStoreName.getText().toString());
            storeDif.setShopCenterName(shopCenterId);
            storeDif.setStoreFloor(floorId);
            storeDif.setShopCenterKind(mallId);
            storeDif.setWorkHour(edtFragmentDefinitionWorkHour.getText().toString());


            presenter.storeDefinition(storeDif);

        });


        spFragmentDefinitionActivityKind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                activityId = spActivityKind.getItems().get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spFragmentDefinitionStoreKind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mallId = spMalKindList.getItems().get(position).getId();
                presenter.ShopCenterList(mallId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spFragmentDefinitionComplexName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shopCenterId = spShopCenter.getItems().get(position).getId();
                cityId = spShopCenter.getItems().get(position).getCityid();
                presenter.floorList(shopCenterId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spFragmentDefinitionFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floorId = spFloorList.getFloorList().get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cities = new ArrayList<>();
        province = new ArrayList<>();

        province = getProvinceAndCity.getProvince();

        adapterState = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, province);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFragmentDefinitionProvince.setAdapter(adapterState);


        spFragmentDefinitionProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = spFragmentDefinitionProvince.getSelectedItem().toString();
                cities = getProvinceAndCity.getCity(state);
                adapterCity = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, cities);
                adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spFragmentDefinitionCity.setAdapter(adapterCity);

                spFragmentDefinitionCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        city = spFragmentDefinitionCity.getSelectedItem().toString();
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


        btnFragmentDefinitionAccept.setOnClickListener(v -> {
            StoreDif storeDif = new StoreDif();
            if (!validateStoreKind() || !validateComplexName() || !validateFloor() || !validateStoreName() || !validatePhone() || !validAddress()) {
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
                storeDif.setUserid(userId);
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
        });

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
        spFragmentDefinitionActivityKind.setAdapter(activityKindAdapter);

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
        if (storeId > 0) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("storeId",storeId);
            editor.apply();
            editor.commit();
            showMessage("فروشگاه با موفقیت ثبت شد");
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment fragment = new MainFragment();
            transaction.replace(R.id.frm_fragmentMain_mainLayout,fragment);
            transaction.commit();
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

    private boolean validateStoreKind() {
        String selectedItem = (String) spFragmentDefinitionStoreKind.getSelectedItem();
        if (selectedItem == null) {
            Toast.makeText(getViewContext(), "لطفا نوع فروشگاه خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateComplexName() {
        String selectedItem = (String) spFragmentDefinitionComplexName.getSelectedItem();
        if (selectedItem == null) {
            Toast.makeText(getViewContext(), "لطفا مجتمع تجاری خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateFloor() {
        String selectedItem = (String) spFragmentDefinitionFloor.getSelectedItem();
        if (selectedItem == null) {
            Toast.makeText(getViewContext(), "لطفا طبقه خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
            return false;
        } else {

            return true;
        }
    }

    private boolean validateStoreName() {
        String selectedItem = edtFragmentDefinitionStoreName.getText().toString().trim();
        if (selectedItem.isEmpty()) {
            edtFragmentDefinitionStoreName.setHint("فیلد نام فروشگاه خالی است");
            edtFragmentDefinitionStoreName.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {
            return true;
        }
    }


    private boolean validatePhone() {
        String selectedItem = edtFragmentDefinitionPhone.getText().toString().trim();
        if (selectedItem.isEmpty()) {
            edtFragmentDefinitionPhone.setHint("فیلد تلفن خالی است");
            edtFragmentDefinitionPhone.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {

            return true;
        }

    }

    private boolean validAddress() {
        String selectedItem = edtFragmentDefinitionAddressFa.getText().toString().trim();
        if (selectedItem.isEmpty()) {
           edtFragmentDefinitionAddressFa.setHint("فیلد آدرس خالی است");
           edtFragmentDefinitionAddressFa.setHintTextColor(getResources().getColor(R.color.colorRed));
            return false;
        } else {

            return true;
        }
    }
}
