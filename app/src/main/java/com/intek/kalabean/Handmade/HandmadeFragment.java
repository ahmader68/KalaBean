package com.intek.kalabean.Handmade;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerCircleImageAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.R;

import java.util.ArrayList;
import java.util.List;

public class HandmadeFragment extends BaseFragment implements HandmadeContract.View {
    private HandmadeContract.Presenter presenter;
    private RecyclerCircleImageAdapter handmadeAdapter;
    private List<StoreList.Store> stores;
    private RecyclerView rvHandmade;
    private ConstraintLayout conHandmade;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stores = new ArrayList<>();
        presenter = new HandmadePresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_handmade;
    }

    @Override
    public void setupViews() {
        conHandmade = rootView.findViewById(R.id.con_fragmentHandmade_mainLayout);
        rvHandmade = rootView.findViewById(R.id.rv_fragmentHandmade_list);
        conHandmade.setRotationY(180);
        //handmadeAdapter = new RecyclerCircleImageAdapter(getViewContext(),stores);
        rvHandmade.setLayoutManager(new LinearLayoutManager(getViewContext(),RecyclerView.VERTICAL,false));
        rvHandmade.setAdapter(handmadeAdapter);
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
