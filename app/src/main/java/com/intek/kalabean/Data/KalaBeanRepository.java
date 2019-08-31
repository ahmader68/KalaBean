package com.intek.kalabean.Data;

import com.intek.kalabean.Model.User;

import io.reactivex.Single;

public class KalaBeanRepository implements KalaBeanDataSource {
    private ServerDataSource serverDataSource = new ServerDataSource();
    @Override
    public Single<User> register(User user) {
        return serverDataSource.register(user);
    }
}
