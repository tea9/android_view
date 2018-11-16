package com.demo.android_view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.demo.android_view.R;
import com.demo.android_view.mview.LineProgressBarView;
import com.demo.android_view.view.LineProgressView2;
import com.demo.android_view.view.MyLinearProgressView;

/**
 * created by tea9 at 2018/11/14
 */
public class TestActivity extends AppCompatActivity {

    MyLinearProgressView mlpv;
    Button bt;
    LineProgressView2 lpv2;
    LineProgressBarView lll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mlpv = findViewById(R.id.mlpv);
        bt = findViewById(R.id.bt);
        lpv2 = findViewById(R.id.lpv2);
        mlpv.setProgress(0);
        lll = findViewById(R.id.lll);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lpv2.setProgress(50);
                mlpv.setProgress(50);
                lll.setNumAProgress(40);
                lll.setNumBProgress(60);
//                /lpv2.setType(2);
            }
        });
    }
}
