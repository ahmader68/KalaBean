package com.intek.kalabean.VIP_User_Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.intek.kalabean.AddProduct.AddProductFragment;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Definition_Store.DefinitionFragment;
import com.intek.kalabean.Edit_User.EditUserFragment;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.ShopCenter;
import com.intek.kalabean.Model.ShopCenterList;
import com.intek.kalabean.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class VUFragment extends BaseFragment implements VUContract.View {
    private VUContract.Presenter presenter;

    private CircularImageView cimgOuter;

    private ConstraintLayout conImage,conPBWM,conSetting;

    private ImageView
            imgEditProfile,
            imgBasket,
            imgWallet,
            imgMessage;

    private TextView txtStoreName;

    private CardView
            cvAddProduct,
            cvManageProduct,
            cvEditStore,
            cvSetMap,
            cvShowStore;



    private Button btnDefinition;

    private SharedPreferences sharedPreferences;

    private int userId,storeId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new VUPresenter(new KalaBeanRepository());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        userId = sharedPreferences.getInt("userid",0);
        storeId = sharedPreferences.getInt("storeId",0);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_vip_user_profile;
    }

    @Override
    public void setupViews() {
        cimgOuter = rootView.findViewById(R.id.cimg_fragmentVIPUser_outerImage);

        conImage = rootView.findViewById(R.id.con_fragmentVIPUser_image);

        conPBWM = rootView.findViewById(R.id.con_fragmentVIPUser_pbwm);
        conSetting = rootView.findViewById(R.id.con_fragmentVIPUser_setting);

        imgEditProfile = rootView.findViewById(R.id.img_fragmentVIPUser_profile);
        imgBasket = rootView.findViewById(R.id.img_fragmentVIPUser_basket);
        imgMessage = rootView.findViewById(R.id.img_fragmentVIPUser_message);
        imgWallet = rootView.findViewById(R.id.img_fragmentVIPUser_wallet);

        txtStoreName = rootView.findViewById(R.id.txt_fragmentVIPUser_name);

        cvAddProduct = rootView.findViewById(R.id.cv_fragmentVIPUser_addProd);
        cvEditStore = rootView.findViewById(R.id.cv_fragmentVIPUser_editStore);
        cvManageProduct = rootView.findViewById(R.id.cv_fragmentVIPUser_prodManage);
        cvSetMap = rootView.findViewById(R.id.cv_fragmentVIPUser_map);
        cvShowStore = rootView.findViewById(R.id.cv_fragmentVIPUser_showStore);

        btnDefinition = rootView.findViewById(R.id.btn_fragmentVIPUser_definitionStore);

        conPBWM.setBackgroundResource(0);
        conSetting.setBackgroundResource(0);

        if(storeId > 0){
            btnDefinition.setVisibility(View.GONE);
            presenter.getShopInfo(storeId,userId);
        }else{
            txtStoreName.setText("نام فروشگاه شما");
        }

        btnDefinition.setOnClickListener(v -> {
            Fragment fragment = new DefinitionFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frm_fragmentMain_mainLayout,fragment);
            transaction.commit();
        });

        imgEditProfile.setOnClickListener(v -> {
//                Bundle bundle = new Bundle();
//                bundle.putInt("userId",userId);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment editFragment = new EditUserFragment();
//                editFragment.setArguments(bundle);
            transaction.replace(R.id.frm_fragmentMain_mainLayout,editFragment);
            transaction.commit();

        });

        cvAddProduct.setOnClickListener(view -> {
            Fragment fragment = new AddProductFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frm_fragmentMain_mainLayout,fragment);
            transaction.commit();
        });

        cvEditStore.setOnClickListener(view -> Toast.makeText(getViewContext(), "Edit Store", Toast.LENGTH_SHORT).show());

        cvManageProduct.setOnClickListener(view -> Toast.makeText(getViewContext(), "Product Management", Toast.LENGTH_SHORT).show());

        cvSetMap.setOnClickListener(view -> Toast.makeText(getViewContext(), "Set on Map", Toast.LENGTH_SHORT).show());

        cvShowStore.setOnClickListener(view -> Toast.makeText(getViewContext(), "Show Store", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getShopInfo(ShopCenterList shopCenter) {
        Picasso.get().load(shopCenter.getItems().get(0).getIcon()).into(cimgOuter);
        txtStoreName.setText(shopCenter.getItems().get(0).getTitleFA());

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
}
