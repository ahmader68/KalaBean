package com.intek.kalabean.Classes;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

public class G extends Application {
    public Context context;
    public static String AppAddress= Environment.getExternalStorageDirectory().getAbsolutePath()+"/captured";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        File file = new File(AppAddress);
        file.mkdirs();
    }
}
