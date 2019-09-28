package com.intek.kalabean.Classes;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class GetSharedPrefrences {
    private static SharedPreferences preferences;
    private GetSharedPrefrences(){

    }
    public static SharedPreferences getInstance(Context context){
        if(preferences == null){
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return preferences;
    }
}
