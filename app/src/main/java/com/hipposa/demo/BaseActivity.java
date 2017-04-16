package com.hipposa.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.hipposa.util.DataUtil;

import java.util.Random;

public class BaseActivity extends AppCompatActivity {

    protected static final String KEY_OTP = "KEY_OTP";
    protected static final String API_KEY = "39643166cafe264ab576329aeefa9d1f";
    private static final String HEX_CHARS = "0123456789abcdef";
    private Random random = new Random();

    protected void goToActivation() {
        final Intent intent = new Intent(BaseActivity.this, ActivationActivity.class);
        startActivity(intent);
    }

    protected boolean hasActivatedDevice() {
        String result = getActivationResult();
        if (result != null && !result.isEmpty()) return true;
        return false;
    }

    protected String getActivationResult() {
        return new DataUtil(getApplicationContext()).getActivationResult();
    }

    private String getRandomHexString(int length, boolean toUpper) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(HEX_CHARS.charAt(random.nextInt(HEX_CHARS.length()) ) );
        if (toUpper) return sb.toString().toUpperCase();
        return sb.toString();
    }

    protected void showAlertDialog(String title, String message) {
        showAlertDialog(title, message, null);
    }
    protected void showAlertDialog(String title, String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.button_ok, listener)
                .show();
    }

}
