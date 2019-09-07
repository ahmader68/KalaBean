package com.intek.kalabean.Definition_Store;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.ActivityKind;
import com.intek.kalabean.Model.MallKind;
import com.intek.kalabean.R;
import com.tiper.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class DefinitionFragment extends BaseFragment implements DefinitionContract.View {
    private DefinitionContract.Presenter presenter;
    private List<String> mkindsName;
    private List<String> akindName;
    private List<MallKind> mkinds;
    private List<ActivityKind> akinds;
    private ArrayAdapter<String> activityKindsArrayAdapter;
    private MaterialSpinner spMallKind;
    private MaterialSpinner spActivityKind;
    private ArrayAdapter<String> storeKindArrayAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DefinitionPresenter(new KalaBeanRepository());
        mkindsName = new ArrayList<>();
        akindName = new ArrayList<>();
        presenter.storeKind();
       // presenter.activityKind();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_definition;
    }

    @Override
    public void setupViews() {
        spMallKind = rootView.findViewById(R.id.sp_fragmentDefinition_mallKind);
        spActivityKind = rootView.findViewById(R.id.sp_fragmentDefinition_activityKind);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getStoreKind(List<MallKind> mallKinds) {
        mkinds = mallKinds;
        for(MallKind mallKind : mallKinds){
            mkindsName.add(mallKind.getName());
        }
        storeKindArrayAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item, mkindsName);
        storeKindArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMallKind.setAdapter(storeKindArrayAdapter);
    }

    @Override
    public void getActivityKind(List<ActivityKind> activityKinds) {
//        akinds = activityKinds;
//        for (ActivityKind activityKind:activityKinds){
//            akindName.add(activityKind.getActivityName());
//        }
//        activityKindsArrayAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,akindName);
//        activityKindsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spActivityKind.setAdapter(activityKindsArrayAdapter);

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
}
