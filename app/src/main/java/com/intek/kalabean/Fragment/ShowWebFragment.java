package com.intek.kalabean.Fragment;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.R;

public class ShowWebFragment extends BaseFragment {

    private Bundle bundle;
    private WebView wvSite;
    private String webAddress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        webAddress = "http://www.kalabean.com";

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_show_web;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void setupViews() {
        wvSite = rootView.findViewById(R.id.wv_fragmentShowWeb);
        wvSite.getSettings().setJavaScriptEnabled(true);
        wvSite.setWebViewClient(new WebViewClient());
        wvSite.setWebChromeClient(new WebChromeClient());
        wvSite.getSettings().setDomStorageEnabled(true);
        wvSite.getSettings().setLoadWithOverviewMode(true);
        wvSite.loadUrl(webAddress);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(wvSite.canGoBack()){
            wvSite.goBack();
        }else {
            if (getView() == null) {
                return;
            }
            getView().setFocusableInTouchMode(true);
            getView().requestFocus();
            getView().setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_MainActivity_mainLayout, new MainFragment()).commit();
                    return true;
                }
                return false;
            });
        }
    }


}
