package com.intek.kalabean.Definition_Store;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ActivityKind;
import com.intek.kalabean.Model.MallKind;

import java.util.List;

public interface DefinitionContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getStoreKind(MallKind mallKinds);
        void getActivityKind(List<ActivityKind> activityKinds);
    }
    interface Presenter extends BasePresenter<View>{
        void storeKind();
        void activityKind();
        //void storeDenifition(Store store);
    }
}
