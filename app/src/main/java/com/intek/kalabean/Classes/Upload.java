package com.intek.kalabean.Classes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

import com.intek.kalabean.Data.ServerDataSource;

import java.io.File;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Upload {

    private ServerDataSource serverDataSource;
    private Handler handler;
    private CompositeDisposable compositeDisposable;
    private ProgressDialog dialog;
    private Context context;
    private String url;
    private String img;

    public String pickFile(Intent data, Context context) {
        this.context = context;
        Uri uri = data.getData();
        String[] info = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri,info,null,null,null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(info[0]);
        String filePath = cursor.getString(columnIndex);
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"),file);
        final MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file",file.getName(),requestBody);
        final RequestBody fileName = RequestBody.create(MediaType.parse("plane/text"),file.getName());

        handler = new Handler();

        Thread upLoadThread = new Thread(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dialog.show();
                    }
                });
                img = uploadFile(fileToUpload, fileName);
            }
        });
        upLoadThread.start();
        return img;
    }

    private String  uploadFile(MultipartBody.Part file,RequestBody name){
        dialog = new ProgressDialog(context);
        dialog.setTitle("آپلود عکس...");

        serverDataSource.upload(file , name).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(String img) {
                        url = img;
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG:::",e.toString());
                    }
                });
        return url;
    }
}
