package com.intek.kalabean.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Home.HomeFragment;
import com.intek.kalabean.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityHolder> {

    private Context context;
    private List<String> cities;

    public CityAdapter(Context context,List<String> cities) {
        this.context = context;
        this.cities = cities;
    }

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_city,parent,false);
        return new CityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityHolder holder, int position) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String city = cities.get(position);
        holder.txtCity.setText(cities.get(position));
        holder.txtCity.setOnClickListener(v -> {
            switch (city){
                case "مشهد":
                    editor.putInt("cityId",1202);
                    break;
                case "دبی (به زودی)":
                    editor.putInt("cityId",1204);
                    break;
                case "شیراز (به زودی)":
                    editor.putInt("cityId",1205);
                    break;
                case "قم (به زودی)":
                    editor.putInt("cityId",1248);
                    break;
                case "تهران":
                    editor.putInt("cityId",1298);
                    break;
                case "کرج":
                    editor.putInt("cityId",1930);
                    break;
            }
            editor.apply();
            editor.commit();
            Fragment fragment = new HomeFragment();
            FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frm_fragmentMain_mainLayout,fragment);
            transaction.commit();

        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    class CityHolder extends RecyclerView.ViewHolder{

        TextView txtCity;
        public CityHolder(@NonNull View itemView) {
            super(itemView);
            txtCity = itemView.findViewById(R.id.txt_rvCity_city);
        }
    }
}
