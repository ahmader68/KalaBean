package com.intek.kalabean.VIP_User_Profile;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class VUFragment extends BaseFragment implements VUContract.View {
    private VUContract.Presenter presenter;

    CircularImageView cimgOuter;

    ConstraintLayout conImage;
    ConstraintLayout conProfile;
    ConstraintLayout conBasket;
    ConstraintLayout conWallet;
    ConstraintLayout conMessage;

    TextView txtStoreName;

    CardView cvAddProduct;
    CardView cvManageProduct;
    CardView cvEditStore;
    CardView cvSetMap;
    CardView cvShowStore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new VUPresenter(new KalaBeanRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_vip_user_profile;
    }

    @Override
    public void setupViews() {
        cimgOuter = rootView.findViewById(R.id.cimg_fragmentVIPUser_outerImage);

        conImage = rootView.findViewById(R.id.con_fragmentVIPUser_image);
//        conProfile = rootView.findViewById(R.id.con_fragmentVIPUser_profile);
//        conBasket = rootView.findViewById(R.id.con_fragmentVIPUser_basket);
//        conMessage = rootView.findViewById(R.id.con_fragmentVIPUser_message);
//        conWallet = rootView.findViewById(R.id.con_fragmentVIPUser_wallet);

        txtStoreName = rootView.findViewById(R.id.txt_fragmentVIPUser_name);

        cvAddProduct = rootView.findViewById(R.id.cv_fragmentVIPUser_addProd);
        cvEditStore = rootView.findViewById(R.id.cv_fragmentVIPUser_editStore);
        cvManageProduct = rootView.findViewById(R.id.cv_fragmentVIPUser_prodManage);
        cvSetMap = rootView.findViewById(R.id.cv_fragmentVIPUser_map);
        cvShowStore = rootView.findViewById(R.id.cv_fragmentVIPUser_showStore);

//        conProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getViewContext(), "Profile", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        conBasket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getViewContext(), "Basket", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        conMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getViewContext(), "Message", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        conWallet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getViewContext(), "Wallet", Toast.LENGTH_SHORT).show();
//            }
//        });

        cvAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getViewContext(), "Add Product", Toast.LENGTH_SHORT).show();
            }
        });

        cvEditStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getViewContext(), "Edit Store", Toast.LENGTH_SHORT).show();
            }
        });

        cvManageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getViewContext(), "Product Management", Toast.LENGTH_SHORT).show();
            }
        });

        cvSetMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getViewContext(), "Set on Map", Toast.LENGTH_SHORT).show();
            }
        });

        cvShowStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getViewContext(), "Show Store", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
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
}
