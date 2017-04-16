package com.hipposa.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hipposa.lib.HippoManager;
import com.hipposa.util.DataUtil;

public class ActivationActivity extends BaseActivity {

    private static String TAG = ActivationActivity.class.getSimpleName();
    private TextView devicePinCodeInputView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);
        devicePinCodeInputView = (TextView) findViewById(R.id.devicePinCodeInput);
        devicePinCodeInputView.requestFocus();

        View cancelLinkView = findViewById(R.id.cancelLink);

        if (hasActivatedDevice()) {
            cancelLinkView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            cancelLinkView.setVisibility(View.GONE);
        }

        findViewById(R.id.generateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String username = ((TextView) findViewById(R.id.usernameInputView))
                            .getText().toString();

                    String devicePinCode = devicePinCodeInputView.getText().toString();
                    if (devicePinCode == null || devicePinCode.isEmpty()) {
                        showAlertDialog(getString(R.string.waringing_title),
                                getString(R.string.warning_device_pin_code_empty));
                        return;
                    }
                    HippoManager manager = new HippoManager(API_KEY);
                    String result = manager.activate(devicePinCode, username, "0");
                    if (result == null || result.isEmpty()) {
                        showAlertDialog(getString(R.string.error_title),
                                getString(R.string.error_activation_failed));
                    } else {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.message_activation_success),
                                Toast.LENGTH_SHORT).show();
                        new DataUtil(getApplicationContext()).saveActivationResult(result);
                        finish();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Failed onClick: " + e.toString());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
