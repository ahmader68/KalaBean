package com.intek.kalabean.Data;

import com.intek.kalabean.Model.User;

import io.reactivex.Single;

public interface KalaBeanDataSource {
    Single<User> register(User user);
}
