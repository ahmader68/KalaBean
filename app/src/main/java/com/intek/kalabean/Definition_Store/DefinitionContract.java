package com.intek.kalabean.Definition_Store;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ActivityKind;
import com.intek.kalabean.Model.MallKindList;

import java.util.List;

public interface DefinitionContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getStoreKind(MallKindList mallKindList);
        void getActivityKind(List<ActivityKind> activityKinds);
    }
    interface Presenter extends BasePresenter<View>{
        void storeKind();
        void activityKind();
        //void storeDenifition(Store store);
    }
}
