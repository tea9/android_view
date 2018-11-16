package com.demo.android_view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.demo.android_view.R;
import com.demo.android_view.mview.RingProgressBarView;

/**
 * created by tea9 at 2018/11/15
 */
public class MainActivity extends AppCompatActivity {
    private float mTotalProgress = 40f;
    private float mCurrentProgress = 0;
    //进度条
    private RingProgressBarView mTasksView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTasksView = (RingProgressBarView) findViewById(R.id.tasks_view);
        mTasksView.setProgress(43.9f);

//        new Thread(new ProgressRunable()).start();
    }

    class ProgressRunable implements Runnable {
        @Override
        public void run() {
            while (mCurrentProgress < mTotalProgress+1) {
                mCurrentProgress += 1;
                if (mCurrentProgress>mTotalProgress){
                    mCurrentProgress = mTotalProgress;
                }
                mTasksView.setProgress(mCurrentProgress);
                try {
                    Thread.sleep( 57);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
