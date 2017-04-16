package com.hipposa.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hipposa.lib.HippoManager;

public class MainActivity extends BaseActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private TextView gCodeInputView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!hasActivatedDevice()) {
            goToActivation();
        }

        gCodeInputView = (TextView) findViewById(R.id.gCodeInput);

        findViewById(R.id.activateLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivation();
            }
        });

        findViewById(R.id.generateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = getActivationResult();
                    if (result == null || result.isEmpty()) {
                        showAlertDialog(getString(R.string.error_title),
                                getString(R.string.error_not_yet_activated),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        goToActivation();
                                    }
                                }
                        );
                    }

                    String username = ((TextView) findViewById(R.id.usernameInputView))
                            .getText().toString();

                    String gCode = gCodeInputView.getText().toString();
                    if (gCode == null || gCode.isEmpty()) {
                        showAlertDialog(getString(R.string.waringing_title),
                                getString(R.string.warning_gcode_empty));
                        return;
                    }
                    HippoManager manager = new HippoManager(API_KEY);
                    String otp = manager.generateOTP(gCode, username, result, 0);
                    if (otp == null) {
                        showAlertDialog(getString(R.string.error_title),
                                getString(R.string.error_generate_otp_failed));
                    } else {
                        final Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        intent.putExtra(KEY_OTP, otp);
                        startActivity(intent);
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
        gCodeInputView.setText("");
    }
}
