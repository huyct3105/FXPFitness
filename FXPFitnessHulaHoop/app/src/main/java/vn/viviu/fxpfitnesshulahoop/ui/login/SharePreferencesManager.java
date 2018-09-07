package vn.viviu.fxpfitnesshulahoop.ui.login;

import android.content.Context;
import android.content.SharedPreferences;


import vn.viviu.fxpfitnesshulahoop.util.AppKey;


public class SharePreferencesManager {

    private static SharedPreferences sharedPreferences;


    public SharePreferencesManager() {

    }


    public static SharedPreferences getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(AppKey.PREFERENCES_NAME, Context.MODE_PRIVATE);
        }

        return sharedPreferences;
    }




}
