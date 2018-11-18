package com.demo.android_view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.demo.android_view.R;
import com.demo.android_view.mview.RingProgressBarView;

/**
 * created by tea9 at 2018/11/18
 */
public class RingProgressBarActivity extends AppCompatActivity {

    private RingProgressBarView rpbv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_progress_bar);
        rpbv = findViewById(R.id.rpbv);
        rpbv.setProgress(100f);
    }
}
