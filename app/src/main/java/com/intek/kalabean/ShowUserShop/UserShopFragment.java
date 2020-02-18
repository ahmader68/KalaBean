package com.intek.kalabean.ShowUserShop;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.intek.kalabean.Adapters.RecyclerProductAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Classes.Alert_Dialog;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.Model.UserShop;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserShopFragment extends BaseFragment implements UserShopContract.View {

    public UserShopContract.Presenter presenter;

    private RecyclerView rv_fragmentShops_list;
    private Bundle extras;
    public int SellCenterID;
    public String image;
    public String title;
    public int ShopId;
    private RecyclerProductAdapter adapter;
    private CircleImageView img_fragmentShops_Market;
    private TextView txt_fragmentShops_title;
    private TextView txt_fragmentShops_Null;

    private Alert_Dialog dialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Alert_Dialog(getViewContext());
        presenter = new UserShopPresenter(new KalaBeanRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_shops;
    }

    @Override
    public void setupViews() {


        Picasso.get().load(image).into(img_fragmentShops_Market);
        txt_fragmentShops_title.setText(title);

        dialog.showAlert();
        presenter.getUserShop(5599);
        presenter.getUserShop(5599);
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
    public void showUserShop(UserShop userShop) {

        String imgUrl = userShop.getItems().get(0).getIcon();
        final String[] separated = imgUrl.split("/");
        final String p00 = separated[0];
        final String p01 = separated[1];
        final String p02 = separated[2];
        final String p03 = separated[3];
        final String p04 = separated[4];
        final String p05 = separated[5];

        String url = "http://www.kalabean.com/Images/formModules/shop/images/" + p05 + "/image.png";

        Picasso.get().load(url).into(img_fragmentShops_Market);
        txt_fragmentShops_title.setText(userShop.getItems().get(0).getTitle());
        dialog.dismiss();
    }

    @Override
    public void getProductList(ProductList productList) {
        if (productList.getItems() == null){
            txt_fragmentShops_Null.setVisibility(View.VISIBLE);
        } else {
            adapter = new RecyclerProductAdapter(getActivity(), productList);
            rv_fragmentShops_list.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
            rv_fragmentShops_list.setAdapter(adapter);
        }
        dialog.dismiss();
    }
}
