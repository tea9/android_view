package com.demo.android_view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.demo.android_view.R;
import com.demo.android_view.mview.LineProgressOView;

/**
 * created by tea9 at 2018/11/18
 */
public class LineProgressOActivity extends AppCompatActivity {

    private LineProgressOView lll;
    private Button switch_type_btn;
    private int type = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_progress_o);
        switch_type_btn = findViewById(R.id.switch_type_btn);
        lll = findViewById(R.id.lll);
        lll.setNumProgress(40);
        switch_type_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lll.setType(type==0?1:0);
                lll.setNumProgress(40);
                if (type==0) type = 1; else type = 0;
            }
        });
    }
}
