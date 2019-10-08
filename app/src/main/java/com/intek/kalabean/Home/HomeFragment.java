package com.intek.kalabean.Home;

import android.content.Context;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerOrderAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Model.Order;
import com.intek.kalabean.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements HomeContract.View  {

    private ConstraintLayout con_fragmentHome_orders;
    ConstraintLayout con_fragmentHome_slideShow;
    ConstraintLayout con_fragmentHome_malls;
    ConstraintLayout con_fragmentHome_search;
    ConstraintLayout con_fragmentHome_chainStore;
    ConstraintLayout con_fragmentHome_gallery;
    ConstraintLayout con_fragmentHome_brands;

    private RecyclerView rv_fragmentHome_orders;
    private RecyclerOrderAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setupViews() {
        con_fragmentHome_orders = rootView.findViewById(R.id.con_fragmentHome_orders);
        con_fragmentHome_slideShow = rootView.findViewById(R.id.con_fragmentHome_kalaBeanLook);
        con_fragmentHome_malls = rootView.findViewById(R.id.con_fragmentHome_malls);
        con_fragmentHome_search = rootView.findViewById(R.id.con_fragmentHome_search);
        con_fragmentHome_chainStore = rootView.findViewById(R.id.con_fragmentHome_chainStore);
        con_fragmentHome_gallery = rootView.findViewById(R.id.con_fragmentHome_gallery);
        con_fragmentHome_brands = rootView.findViewById(R.id.con_fragmentHome_brands);

        rv_fragmentHome_orders = rootView.findViewById(R.id.rv_fragmentHome_orders);


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
}
