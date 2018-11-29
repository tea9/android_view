package com.demo.android_view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.demo.android_view.R;
import com.demo.android_view.view.Polygon;

/**
 * created by tea9 at 2018/11/27
 */
public class PolygonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPolygon();
    }

    private void initPolygon() {
        setContentView(R.layout.activity_polygon);
        Polygon poly = (Polygon) findViewById(R.id.poly);
        poly.setRealData(new int[]{1, 2, 3, 4, 5, 4});
    }
}
