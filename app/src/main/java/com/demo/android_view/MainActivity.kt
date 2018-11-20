package com.demo.android_view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.demo.android_view.activity.LineProgressBarActivity
import com.demo.android_view.activity.RingProgressBarActivity
import com.demo.android_view.activity.VerticalBarViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btn1 -> startActivity(Intent(this,LineProgressBarActivity::class.java))
            R.id.btn2 -> startActivity(Intent(this,RingProgressBarActivity::class.java))
            R.id.btn3 -> startActivity(Intent(this,VerticalBarViewActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
    }
}
