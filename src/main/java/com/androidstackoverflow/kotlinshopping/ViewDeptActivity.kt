package com.androidstackoverflow.kotlinshopping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem

class ViewDeptActivity : AppCompatActivity() {

    private var RecyclerAdapter: DeptAdapter? = null
    private var recyclerView: RecyclerView? = null
    private val db = DBHelper(this)
    private var deptListDept:List<ModelDept> = ArrayList()
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_dept)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()

    }// end onCreate


    override fun onResume() {
        super.onResume()
        initDB()
    }

    // This is ONLY called when Activity is in onResume state
    private fun initDB() {
        deptListDept = db.queryDEPT()
        if(deptListDept.isEmpty()){
            title = "No Records in DB"
        }else{
            title = "Dept List"
        }

        RecyclerAdapter = DeptAdapter(deptListDept = deptListDept, context = applicationContext)
        (recyclerView as RecyclerView).adapter = RecyclerAdapter
    }

    private fun initViews() {

        recyclerView = this.findViewById(R.id.rvDeptView)
        RecyclerAdapter = DeptAdapter(deptListDept = deptListDept, context = applicationContext)
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
                    val intent = Intent(this, EnterDeptActivity::class.java)
                    intent.putExtra("FROM","N")// ADD NEW NOTE
                    startActivity(intent)
                }
            }
            // CODE below manages HOME Button
            val id = item.itemId
            if (id == android.R.id.home) {
                val intent = Intent(this, EnterDeptActivity::class.java)
                intent.putExtra("FROM","N")// ADD NEW NOTE
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}// end Class
