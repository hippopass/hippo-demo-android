package com.hipposa.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wyu on 3/31/17.
 */

public class DataUtil {

    private Context mContext = null;
    private static String KEY_SHARED_PREF = "app";
    private static String KEY_ACTIVATION_RESULT = "activationResult";

    public DataUtil(Context context) {
        mContext = context;
    }

    public void saveActivationResult(String activationResult) {
        if (mContext != null) {
            SharedPreferences sharedPref = mContext.getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(KEY_ACTIVATION_RESULT, activationResult);
            editor.commit();
        }
    }

    public String getActivationResult() {
        if (mContext != null) {
            SharedPreferences sharedPref = mContext.getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE);
            return sharedPref.getString(KEY_ACTIVATION_RESULT, null);
        }
        return null;
    }
}
