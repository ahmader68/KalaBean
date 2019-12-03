package com.intek.kalabean.Edit_User;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.User;

import io.reactivex.disposables.CompositeDisposable;

public class EditUserPresenter implements EditUserContract.Presenter {
    private final int databaseFlag = 14;
    private static EditUserContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private KalaBeanDataSource kalaBeanDataSource;

    public EditUserPresenter(KalaBeanDataSource kalaBeanDataSource) {
        this.kalaBeanDataSource = kalaBeanDataSource;
        DatabaseMethods.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void attachView(EditUserContract.View view) {
        EditUserPresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void userInfo(int userId) {
        DatabaseMethods.getUserInfo(userId);
    }

    @Override
    public void editUser(int uid, String mobile, String email) {
        DatabaseMethods.editUser(uid, mobile, email);
    }

    @Override
    public void onSuccessEditUser(User user) {
        EditUserPresenter.view.showEditSuccess(user.getResult());
    }

    @Override
    public void onSuccessGetUserInfo(User user) {
        EditUserPresenter.view.showUserInfo(user);
    }

    @Override
    public void onError(String msg) {
        EditUserPresenter.view.showMessage(msg);
    }
}
