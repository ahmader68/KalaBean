package com.intek.kalabean.City;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.CityAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Classes.Alert_Dialog;
import com.intek.kalabean.R;

import java.util.ArrayList;
import java.util.List;

public class CityFragment extends BaseFragment implements CityContract.View {

    private RecyclerView rvCity;
    private CityAdapter cityAdapter;
    private List<String> cities;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cities = new ArrayList<>();
        cities.add("مشهد");
        cities.add("دبی (به زودی)");
        cities.add("شیراز (به زودی)");
        cities.add("قم (به زودی)");
        cities.add("تهران");
        cities.add("کرج");
        cityAdapter = new CityAdapter(getViewContext(),cities);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_city;
    }

    @Override
    public void setupViews() {
        rvCity = rootView.findViewById(R.id.rv_fragmentCity_list);
        rvCity.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));
        rvCity.setAdapter(cityAdapter);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
