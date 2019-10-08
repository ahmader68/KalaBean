package com.intek.kalabean.VIP_User_Profile;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;

public interface VUContract {
    interface View extends BaseView{
        void showMessage(String msg);
    }
    interface Presenter extends BasePresenter<View>{

    }
}
