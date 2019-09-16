package com.intek.kalabean.ShowShop;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.intek.kalabean.Adapters.RecyclerProductAdapter;
import com.intek.kalabean.Adapters.RecyclerShopsAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.Product;
import com.intek.kalabean.R;
import com.intek.kalabean.Shops.ShopsContract;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowShopFragment extends BaseFragment implements ShowShopContract.View {

    private ShowShopContract.Presenter presenter;

    private RecyclerView rv_fragmentShops_list;
    private Bundle extras;
    public int SellCenterID;
    public String image;
    public String title;
    private RecyclerProductAdapter adapter;
    private CircleImageView img_fragmentShops_Market;
    private TextView txt_fragmentShops_title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ShowShopPresenter(new KalaBeanRepository());
        extras = getArguments();
        SellCenterID = extras.getInt("SellCenterID" , 1);
        image = extras.getString("image" , "");
        title = extras.getString("title" , "");
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_shops;
    }

    @Override
    public void setupViews() {
        rv_fragmentShops_list = rootView.findViewById(R.id.rv_fragmentShops_list);
        img_fragmentShops_Market = rootView.findViewById(R.id.img_fragmentShops_Market);
        txt_fragmentShops_title = rootView.findViewById(R.id.txt_fragmentShops_title);

        Picasso.get().load(image).into(img_fragmentShops_Market);
        txt_fragmentShops_title.setText(title);


        //presenter.getProduct(1025);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getProductList(List<Product> products) {
        adapter = new RecyclerProductAdapter(getViewContext() , products);
        rv_fragmentShops_list.setLayoutManager(new StaggeredGridLayoutManager(3 , RecyclerView.VERTICAL));
        rv_fragmentShops_list.setAdapter(adapter);
    }


    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }
}
