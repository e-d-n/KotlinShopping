package com.androidstackoverflow.kotlinshopping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }// end onCreate

    fun onEnterDeptData(view: View){
        val intent = Intent(this,EnterDeptActivity::class.java)
        intent.putExtra("FROM", "N")
        startActivity(intent)
    }

    fun onViewDeptData(view: View){
        val intent = Intent(this,ViewDeptActivity::class.java)
        intent.putExtra("FROM", "N")
        startActivity(intent)
    }

    fun onEnterItemData(view: View){
        val intent = Intent(this,EnterItemActivity::class.java)
        startActivity(intent)
    }

    fun onViewItemData(view: View){
        val intent = Intent(this,ViewItemActivity::class.java)
        startActivity(intent)

    }

}// end Class
