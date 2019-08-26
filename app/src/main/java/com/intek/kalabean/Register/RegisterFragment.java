package com.intek.kalabean.Register;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.ArrayAdapter;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.MainActivity;
import com.intek.kalabean.R;
import com.tiper.MaterialSpinner;

import java.util.ArrayList;

public class RegisterFragment extends BaseFragment implements RegisterContract.View {


    MaterialSpinner spinnerState;
    MaterialSpinner spinnerCity;
    ConstraintLayout conRegister;
    @Override
    public int getLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void setupViews() {

        conRegister = rootView.findViewById(R.id.con_fragmentRegister_mainLayout);
        conRegister.setRotationY(180);

        ArrayList<String> items;
        spinnerState = rootView.findViewById(R.id.sp_fragmentRegister_locationState);
        spinnerCity = rootView.findViewById(R.id.sp_fragmentRegister_locationCity);

        items = new ArrayList<>();


        items.add("دلار آمریکا");
        items.add("یورو");
        items.add("پوند انگلیس");
        items.add("مارک آلمان");
        items.add("ریال عربستان");
        items.add("تومان ایران ");
        //android.R.layout.simple_spinner_item

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getViewContext(), android.R.layout.simple_spinner_item, items);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(arrayAdapter);
        spinnerCity.setAdapter(arrayAdapter);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
