package com.intek.kalabean.ShowUserShop;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerProductAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new UserShopPresenter(new KalaBeanRepository());
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
        txt_fragmentShops_Null = rootView.findViewById(R.id.txt_fragmentShops_Null);

        Picasso.get().load(image).into(img_fragmentShops_Market);
        txt_fragmentShops_title.setText(title);


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


        if (userShop.getItems() == null){
            txt_fragmentShops_Null.setVisibility(View.VISIBLE);
        } else {
            //adapter = new RecyclerProductAdapter(getViewContext(), userShop);
            //rv_fragmentShops_list.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
            //rv_fragmentShops_list.setAdapter(adapter);
        }
    }
}
