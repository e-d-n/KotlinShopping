package com.androidstackoverflow.kotlinshopping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem

class ViewItemActivity : AppCompatActivity() {

    private var RecyclerAdapter: ItemAdapter? = null
    private var recyclerView: RecyclerView? = null
    private val db = DBHelper(this)
    private var itemListItem:List<ModelItem> = ArrayList()
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        initViews()

    }// end onCreate

    override fun onResume() {
        super.onResume()
        initDB()
    }

    // This is ONLY called when Activity is in onResume state
    private fun initDB() {
        itemListItem = db.queryITEM()
        if(itemListItem.isEmpty()){
            title = "No Records in DB"
        }else{
            title = "Item List"
        }

        RecyclerAdapter = ItemAdapter(itemListItem = itemListItem, context = applicationContext)
        (recyclerView as RecyclerView).adapter = RecyclerAdapter
    }

    private fun initViews() {

        recyclerView = this.findViewById(R.id.rvItemView)
        RecyclerAdapter = ItemAdapter(itemListItem = itemListItem, context = applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        (recyclerView as RecyclerView).layoutManager = linearLayoutManager!!
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.addNote -> {
                    val intent = Intent(this, EnterItemActivity::class.java)
                    startActivity(intent)
                }
            }
            // CODE below manages HOME Button
            val id = item.itemId
            if (id == android.R.id.home) {
                val intent = Intent(this, EnterItemActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}// end Class
