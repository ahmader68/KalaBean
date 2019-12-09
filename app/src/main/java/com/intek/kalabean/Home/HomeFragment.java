package com.intek.kalabean.Home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.DefaultSliderView;
import com.intek.kalabean.Adapters.RecyclerHomeProductAdapter;
import com.intek.kalabean.Adapters.RecyclerNewJobAdapter;
import com.intek.kalabean.Adapters.RecyclerOrderAdapter;
import com.intek.kalabean.Adapters.RecyclerProductAdapter;
import com.intek.kalabean.Adapters.RecyclerReductedProductAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Best.BestFragment;
import com.intek.kalabean.Brands.BrandsFragment;
import com.intek.kalabean.Chain_Store.ChainFragment;
import com.intek.kalabean.Complex.ComplexFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Markets.MarketsFragment;
import com.intek.kalabean.Model.Order;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseFragment implements HomeContract.View  {

    private ConstraintLayout con_fragmentHome_orders;
    private ConstraintLayout con_fragmentHome_slideShow;
    private ConstraintLayout con_fragmentHome_cat;
    private HomeContract.Presenter presenter;
    private RecyclerView rv_fragmentHome_orders;
    private RecyclerView rv_fragmentHome_newProduct;
    private RecyclerView rv_fragmentHome_reductedProduct;
    private RecyclerView rv_fragmentHome_newJob;
    private RecyclerOrderAdapter adapter;
    private CardView cv_fragmentHome_complex;
    private CardView cv_fragmentHome_malls;
    private CardView cv_fragmentHome_chainStore;
    private CardView cv_fragmentHome_best;
    private Fragment fragment;

    private SliderLayout slider;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(new KalaBeanRepository());

        presenter.getProductList(5599);
        presenter.getReductedProductList(5599);
        presenter.getShopList(1074 , -1);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setupViews() {
        con_fragmentHome_orders = rootView.findViewById(R.id.con_fragmentHome_orders);
        con_fragmentHome_slideShow = rootView.findViewById(R.id.con_fragmentHome_slideShow);
        con_fragmentHome_cat = rootView.findViewById(R.id.con_fragmentHome_cat);
        rv_fragmentHome_orders = rootView.findViewById(R.id.rv_fragmentHome_orders);
        rv_fragmentHome_newProduct = rootView.findViewById(R.id.rv_fragmentHome_newProduct);
        rv_fragmentHome_reductedProduct = rootView.findViewById(R.id.rv_fragmentHome_reductedProduct);
        rv_fragmentHome_newJob = rootView.findViewById(R.id.rv_fragmentHome_newJob);
        cv_fragmentHome_complex = rootView.findViewById(R.id.cv_fragmentHome_complex);
        cv_fragmentHome_malls = rootView.findViewById(R.id.cv_fragmentHome_malls);
        cv_fragmentHome_chainStore = rootView.findViewById(R.id.cv_fragmentHome_chainStore);
        cv_fragmentHome_best = rootView.findViewById(R.id.cv_fragmentHome_best);
        slider = rootView.findViewById(R.id.slider);

        List<String> linkList;

        linkList = new ArrayList<>();
        linkList.add("https://food.fnr.sndimg.com/content/dam/images/food/fullset/2017/8/11/0/FND_HE-Inflammation-Opener_s4x3.jpg.rend.hgtvcom.966.725.suffix/1502480247120.jpeg");
        linkList.add("https://www.time4diamonds.com/pub/media/wysiwyg/luxury-question.jpg");
        linkList.add("https://www.emmajohnson.co.uk/wp-content/uploads/2016/07/muzakpic.png");

        //slider.setCustomIndicator(indicator);
        for (int i = 0; i < linkList.size(); i++) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
            defaultSliderView.image(linkList.get(i));
            slider.addSlider(defaultSliderView);
        }



        List<Order> orderList = new ArrayList<>();

        Order order = new Order();
        order.setId(1);
        order.setImage("https://img.pngio.com/male-profile-picture-icon-png-profile-png-512_512.png");
        order.setName("علی");
        order.setTitle("کفش");
        order.setTime("یک ساعت پیش");
        order.setCity("مشهد");

        orderList.add(order);

        order = new Order();
        order.setId(2);
        order.setImage("https://img.pngio.com/male-profile-picture-icon-png-profile-png-512_512.png");
        order.setName("رامین");
        order.setTitle("لپتاپ");
        order.setTime("یک ساعت پیش");
        order.setCity("تهران");

        orderList.add(order);

        order = new Order();
        order.setId(3);
        order.setImage("https://img.pngio.com/male-profile-picture-icon-png-profile-png-512_512.png");
        order.setName("رها");
        order.setTitle("مانتو");
        order.setTime("یک ساعت پیش");
        order.setCity("شیراز");

        orderList.add(order);

        order = new Order();
        order.setId(4);
        order.setImage("https://img.pngio.com/male-profile-picture-icon-png-profile-png-512_512.png");
        order.setName("فرید");
        order.setTitle("ساعت");
        order.setTime("یک ساعت پیش");
        order.setCity("دبی");

        orderList.add(order);

        order = new Order();
        order.setId(5);
        order.setImage("https://img.pngio.com/male-profile-picture-icon-png-profile-png-512_512.png");
        order.setName("مهناز");
        order.setTitle("عینک");
        order.setTime("یک ساعت پیش");
        order.setCity("کرج");

        orderList.add(order);

        showOrderList(orderList);


        //////////////////////////////////////////////////////////////////

        cv_fragmentHome_complex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ComplexFragment();
                FragmentManager managers = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction transactions = managers.beginTransaction();
                transactions.replace(R.id.frm_fragmentMain_mainLayout, fragment);
                transactions.commit();
            }
        });

        cv_fragmentHome_malls.setOnClickListener(view -> {
            fragment = new MarketsFragment();
            FragmentManager managers = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction transactions = managers.beginTransaction();
            transactions.replace(R.id.frm_fragmentMain_mainLayout, fragment);
            transactions.commit();
        });

        cv_fragmentHome_chainStore.setOnClickListener(view -> {
            fragment = new ChainFragment();
            FragmentManager managers = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction transactions = managers.beginTransaction();
            transactions.replace(R.id.frm_fragmentMain_mainLayout, fragment);
            transactions.commit();
        });

        cv_fragmentHome_best.setOnClickListener(view -> {
            fragment = new BrandsFragment();
            FragmentManager managers = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction transactions = managers.beginTransaction();
            transactions.replace(R.id.frm_fragmentMain_mainLayout, fragment);
            transactions.commit();
        });

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOrderList(List<Order> orderList) {
        adapter = new RecyclerOrderAdapter(getViewContext() , orderList);
        rv_fragmentHome_orders.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.HORIZONTAL , false));
        rv_fragmentHome_orders.setAdapter(adapter);
    }

    @Override
    public void showProductList(ProductList productList) {
        RecyclerHomeProductAdapter adapter = new RecyclerHomeProductAdapter(getViewContext() , productList);
        rv_fragmentHome_newProduct.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.HORIZONTAL , false));
        rv_fragmentHome_newProduct.setAdapter(adapter);
    }

    @Override
    public void showReductedProductList(ProductList productList) {
        RecyclerReductedProductAdapter adapter = new RecyclerReductedProductAdapter(getViewContext() , productList);
        rv_fragmentHome_reductedProduct.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.HORIZONTAL , false));
        rv_fragmentHome_reductedProduct.setAdapter(adapter);
    }

    @Override
    public void showNewJob(ShopsList shopsList) {
        RecyclerNewJobAdapter adapter = new RecyclerNewJobAdapter(getViewContext() , shopsList);
        rv_fragmentHome_newJob.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.HORIZONTAL , false));
        rv_fragmentHome_newJob.setAdapter(adapter);
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
}
