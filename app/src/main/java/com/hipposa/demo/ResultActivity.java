package com.hipposa.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ((TextView) findViewById(R.id.otpDisplayText)).setText(getIntent().getStringExtra(KEY_OTP));

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
