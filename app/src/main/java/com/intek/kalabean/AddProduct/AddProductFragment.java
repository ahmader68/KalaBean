package com.intek.kalabean.AddProduct;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Classes.GetSharedPrefrences;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.MainActivity;
import com.intek.kalabean.Model.ActivityKind;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.AddProduct;
import com.intek.kalabean.Model.Positions;
import com.intek.kalabean.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.tiper.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class AddProductFragment extends BaseFragment implements AddProductContract.View {

    private AddProductContract.Presenter presenter;
    private Button btnUploadPic;
    private Button btnUploadVid;
    private Button btnSave;
    private Button btnClear;

    private MaterialSpinner spCat;
    private MaterialSpinner spSubCat;

    private TextInputLayout tilProductNameFa;
    private TextInputEditText edtProductNameFa;

    private TextInputLayout tilProductNameEn;
    private TextInputEditText edtProductNameEn;

    private TextInputLayout tilProductNameAr;
    private TextInputEditText edtProductNameAr;

    private TextInputLayout tilPrice;
    private TextInputEditText edtPrice;

    private TextInputLayout tilDiscount;
    private TextInputEditText edtDiscount;

    private TextInputLayout tilOrderby;
    private TextInputEditText edtOrderby;

    private TextInputLayout tilCount;
    private TextInputEditText edtCount;

    private TextInputLayout tilKeywordFa;
    private TextInputEditText edtKeywordFa;

    private TextInputLayout tilKeywordEn;
    private TextInputEditText edtKeywordEn;

    private TextInputLayout tilKeywordAr;
    private TextInputEditText edtKeywordAr;

    private TextInputLayout tilDescFa;
    private TextInputEditText edtDescFa;

    private TextInputLayout tilDescAr;
    private TextInputEditText edtDescAr;

    private TextInputLayout tilDescEn;
    private TextInputEditText edtDescEn;

    private CheckBox chkNew;

    private AddProduct product;

    private ImageView imgProfile;

    private ConstraintLayout conProductName;
    private ConstraintLayout conPrice;
    private ConstraintLayout conMainLayout;

    private int catId;

    private SharedPreferences sharedPreferences;

    private int id;

    private int shopidentify;

    private ActivityKindList spActivityKind;

    private List<String> activityName;

    private ArrayAdapter<String> activityAdapter;

    private int activityId;

    private int subCatId;

    private Positions.Category cutPostions;

    private List<String> positionsName;

    private ArrayAdapter<String> positionAdapter;

    private final int PERMISSION_REQUEST_CODE_ADDPRODUCT = 203;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new AddProductPresenter(new KalaBeanRepository());
        product = new AddProduct();
        //preference = GetSharedPrefrences.getInstance(getViewContext());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());

    }

    @Override
    public void showMesssage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(int id) {
        if (id > 0) {
            showMesssage("محصول با موفقیت ثبت گردید");
        }
    }

    @Override
    public void getActivityKind(ActivityKindList activityKindList) {
        activityName = new ArrayList<>();
        spActivityKind = activityKindList;
        for (ActivityKind activityKind : activityKindList.getItems()) {
            activityName.add(activityKind.getName());
        }
        activityAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, activityName);
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCat.setAdapter(activityAdapter);
    }

    @Override
    public void getSubCatId(Positions positions) {
        for (int i = 0; i < positions.getItems().size(); i++) {
            if (positions.getItems().get(i).getId() == activityId) {
                cutPostions = positions.getItems().get(i);
            }
        }
        positionsName = new ArrayList<>();
        for (int j = 0; j < cutPostions.getSubCategory().size(); j++) {
            positionsName.add(cutPostions.getSubCategory().get(j).getNameSubCategory());
        }
        positionAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, positionsName);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSubCat.setAdapter(positionAdapter);

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_addproduct;
    }

    @Override
    public void setupViews() {
        id = sharedPreferences.getInt("userid", 0);
        shopidentify = sharedPreferences.getInt("ShopId",0);
        conMainLayout = rootView.findViewById(R.id.con_fragmentAddProduct_mainLayout);
        conMainLayout.setRotationY(180);
        presenter.activityKind();
        btnUploadPic = rootView.findViewById(R.id.btn_fragmentAddProduct_uploadPhoto);
        btnUploadVid = rootView.findViewById(R.id.btn_fragmentAddProduct_uploadVideo);
        btnSave = rootView.findViewById(R.id.btn_fragmentAddProduct_save);
        btnClear = rootView.findViewById(R.id.btn_fragmentAddProduct_removeForm);

        conProductName = rootView.findViewById(R.id.con_fragmentAddProduct_productName);
        conPrice = rootView.findViewById(R.id.con_fragmentAddProduct_price);

        chkNew = rootView.findViewById(R.id.chkNew);

        tilProductNameFa = rootView.findViewById(R.id.til_fragmentAddProduct_faName);
        edtProductNameFa = rootView.findViewById(R.id.edt_fragmentAddProduct_faName);

        tilProductNameEn = rootView.findViewById(R.id.til_fragmentAddProduct_enName);
        edtProductNameEn = rootView.findViewById(R.id.edt_fragmentAddProduct_enName);

        tilProductNameAr = rootView.findViewById(R.id.til_fragmentAddProduct_arName);
        edtProductNameAr = rootView.findViewById(R.id.edt_fragmentAddProduct_arName);

        tilPrice = rootView.findViewById(R.id.til_fragmentAddProduct_price);
        edtPrice = rootView.findViewById(R.id.edt_fragmentAddProduct_price);

        tilDiscount = rootView.findViewById(R.id.til_fragmentAddProduct_reduction);
        edtDiscount = rootView.findViewById(R.id.edt_fragmentAddProduct_reduction);

        tilOrderby = rootView.findViewById(R.id.til_fragmentAddProduct_order);
        edtOrderby = rootView.findViewById(R.id.edt_fragmentAddProduct_order);

        tilCount = rootView.findViewById(R.id.til_fragmentAddProduct_number);
        edtCount = rootView.findViewById(R.id.edt_fragmentAddProduct_number);

        tilKeywordFa = rootView.findViewById(R.id.til_fragmentAddProduct_faKeyWords);
        edtKeywordFa = rootView.findViewById(R.id.edt_fragmentAddProduct_faKeyWords);

        tilKeywordEn = rootView.findViewById(R.id.til_fragmentAddProduct_enKeyWords);
        edtKeywordEn = rootView.findViewById(R.id.edt_fragmentAddProduct_enKeyWords);

        tilKeywordAr = rootView.findViewById(R.id.til_fragmentAddProduct_arKeyWords);
        edtKeywordAr = rootView.findViewById(R.id.edt_fragmentAddProduct_arKeyWords);

        tilDescFa = rootView.findViewById(R.id.til_fragmentAddProduct_faDesc);
        edtDescFa = rootView.findViewById(R.id.edt_fragmentAddProduct_faDesc);

        tilDescEn = rootView.findViewById(R.id.til_fragmentAddProduct_enDesc);
        edtDescEn = rootView.findViewById(R.id.edt_fragmentAddProduct_enDesc);

        tilDescAr = rootView.findViewById(R.id.til_fragmentAddProduct_arDesc);
        edtDescAr = rootView.findViewById(R.id.edt_fragmentAddProduct_arDesc);

        spCat = rootView.findViewById(R.id.sp_fragmentAddProduct_cat);
        spSubCat = rootView.findViewById(R.id.sp_fragmentAddProduct_subCat);

        imgProfile = rootView.findViewById(R.id.img_fragmentAddProduct_image);


        btnUploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions();
                } else {
                    takePicture();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatePrice() || !validateProductName()) {
                    return;
                } else {
                    String stOrder = edtOrderby.getText().toString();
                    int order = 0;
                    if (!stOrder.isEmpty()) {
                        order = Integer.parseInt(edtOrderby.getText().toString());
                        product.setOrderNo(order);
                    }
                    int price = Integer.parseInt(edtPrice.getText().toString());
                    product.setTitleFA(edtProductNameFa.getText().toString());
                    product.setCategoryId(activityId);
                    product.setPrice(price);
                    product.setSubCategoryId(subCatId);
                    product.setProducer(shopidentify);
                    product.setUsrid(id);
                    product.setAutolang("fa");
                    presenter.insertProduct(product);
                }
            }
        });

        spCat.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, View view, int position, long l) {
                activityId = spActivityKind.getItems().get(position).getId();
                Toast.makeText(getViewContext(), activityId+"", Toast.LENGTH_SHORT).show();
                spSubCat.setSelection(-1);
                presenter.subCatid();
            }

            @Override
            public void onNothingSelected(MaterialSpinner materialSpinner) {

            }
        });

        spSubCat.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, View view, int position, long l) {
                subCatId = cutPostions.getSubCategory().get(position).getIdSubCategory();
                Toast.makeText(getViewContext(), subCatId+"", Toast.LENGTH_SHORT).show();
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

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(getViewContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE_ADDPRODUCT);
        } else {
            takePicture();
        }
    }

    private void takePicture() {
        MainActivity.requestCodeCheck = 3;
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(getActivity());
    }

    private boolean validateProductName() {
        String productName = edtProductNameFa.getText().toString().trim();
        if (productName.isEmpty()) {
            tilProductNameFa.setError("لطفا نام محصول را وارد کنید");
            conProductName.requestFocus();
            return false;
        } else {
            tilProductNameFa.setError(null);
            return true;
        }
    }

    private boolean validatePrice() {
        String price = edtPrice.getText().toString().trim();
        if (price.isEmpty()) {
            tilPrice.setError("لطفا قیمت محصول را وارد کنید");
            conPrice.requestFocus();
            return false;
        } else {
            tilPrice.setError(null);
            return true;
        }
    }
}
