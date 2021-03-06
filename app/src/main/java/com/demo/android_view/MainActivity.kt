package com.demo.android_view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.demo.android_view.activity.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btn1 -> startActivity(Intent(this,LineProgressBarActivity::class.java))
            R.id.btn2 -> startActivity(Intent(this,RingProgressBarActivity::class.java))
            R.id.btn3 -> startActivity(Intent(this,VerticalBarViewActivity::class.java))
            R.id.btn4 -> startActivity(Intent(this,LineProgressOActivity::class.java))
            R.id.btn5 -> startActivity(Intent(this,PolygonActivity::class.java))
            R.id.btn6 -> startActivity(Intent(this,PolygonViewActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
    }
}
