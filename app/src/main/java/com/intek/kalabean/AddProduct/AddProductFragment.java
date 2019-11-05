package com.intek.kalabean.AddProduct;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.MainActivity;
import com.intek.kalabean.Main_Page.MainFragment;
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


    private ImageView imgQuestionMark,imgProductImage;

    private ConstraintLayout conImage, conVideo;

    private MaterialSpinner spProductCat, spProductSubCat;

    private TextInputLayout
            tilProductNameFa,
            tilProductNameEn,
            tilProductNameAr,
            tilPrice,
            tilSales;

    private TextInputEditText
            edtProductNameFa,
            edtProductNameEn,
            edtProductNameAr,
            edtPrice,
            edtSales;
    private EditText edtDescription;

    private MaterialCheckBox chkAddtoSales;

    private Button btnSave;

    private AddProduct product;


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
        spProductCat.setAdapter(activityAdapter);
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
        spProductSubCat.setAdapter(positionAdapter);

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_addproduct;
    }

    @Override
    public void setupViews() {
        id = sharedPreferences.getInt("userid", 0);
        shopidentify = sharedPreferences.getInt("ShopId", 0);
        presenter.activityKind();

        conMainLayout = rootView.findViewById(R.id.con_fragmentAddProduct_mainLayout);
        conImage = rootView.findViewById(R.id.con_fragmentAddProduct_image);
        conVideo = rootView.findViewById(R.id.con_fragmentAddProduct_video);

        chkAddtoSales = rootView.findViewById(R.id.chk_fragmentAddProduct_sales);

        btnSave = rootView.findViewById(R.id.btn_fragmentAddProduct_save);

        imgQuestionMark = rootView.findViewById(R.id.img_fragmentAddProduct_question);
        imgProductImage = rootView.findViewById(R.id.img_fragmentAddProduct_image);


        tilProductNameFa = rootView.findViewById(R.id.til_fragmentAddProduct_productFarsiName);
        edtProductNameFa = rootView.findViewById(R.id.edt_fragmentAddProduct_productFaName);

        tilProductNameEn = rootView.findViewById(R.id.til_fragmentAddProduct_productEnglishName);
        edtProductNameEn = rootView.findViewById(R.id.edt_fragmentAddProduct_productEnglishName);

        tilProductNameAr = rootView.findViewById(R.id.til_fragmentAddProduct_productArabicName);
        edtProductNameAr = rootView.findViewById(R.id.edt_fragmentAddProduct_productArabicName);

        tilPrice = rootView.findViewById(R.id.til_fragmentAddProduct_price);
        edtPrice = rootView.findViewById(R.id.edt_fragmentAddProduct_price);

        tilSales = rootView.findViewById(R.id.til_fragmentAddProduct_percentSales);
        edtSales = rootView.findViewById(R.id.edt_fragmentAddProduct_percentSales);


        edtDescription = rootView.findViewById(R.id.edt_fragmentAddProduct_description);


        spProductCat = rootView.findViewById(R.id.sp_fragmentAddProduct_productCat);
        spProductSubCat = rootView.findViewById(R.id.sp_fragmentAddProduct_subCatProduct);


        conImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                if (Build.VERSION.SDK_INT >= 23) {
                    checkPermissions();
                    imgProductImage.setVisibility(View.VISIBLE);

                } else {
                    takePicture();
                    imgProductImage.setVisibility(View.VISIBLE);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatePrice() || !validateProductName()) {
                    return;
                } else {

                    int price = Integer.parseInt(edtPrice.getText().toString());
                    product.setTitleFA(edtProductNameFa.getText().toString());
                    product.setCategoryId(activityId);
                    product.setPrice(price);
                    product.setSubCategoryId(subCatId);
                    product.setProducer(shopidentify);
                    // product.setUsrid(id);
                    product.setUsrid(2185);
                    product.setAutolang("fa");
                    presenter.insertProduct(product);
                }
            }
        });

        spProductCat.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, View view, int position, long l) {
                activityId = spActivityKind.getItems().get(position).getId();
                Toast.makeText(getViewContext(), activityId + "", Toast.LENGTH_SHORT).show();
                spProductSubCat.setSelection(-1);
                presenter.subCatid();
            }

            @Override
            public void onNothingSelected(MaterialSpinner materialSpinner) {

            }
        });

        spProductSubCat.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, View view, int position, long l) {
                subCatId = cutPostions.getSubCategory().get(position).getIdSubCategory();
                Toast.makeText(getViewContext(), subCatId + "", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onResume() {
        super.onResume();
        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_MainActivity_mainLayout,new MainFragment()).commit();
                    return true;
                }
                return false;
            }
        });
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
            tilProductNameFa.requestFocus();
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
            tilPrice.requestFocus();
            return false;
        } else {
            tilPrice.setError(null);
            return true;
        }
    }
}
