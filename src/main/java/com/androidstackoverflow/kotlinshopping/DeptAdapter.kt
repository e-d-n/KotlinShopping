package com.androidstackoverflow.kotlinshopping

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

class DeptAdapter(deptListDept : List<ModelDept>, internal var context: Context):RecyclerView.Adapter<DeptAdapter.DeptViewHolder>() {

    private var deptListDept: List<ModelDept> = ArrayList()
    init { this.deptListDept = deptListDept }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeptViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_dept,parent,false)
        return DeptViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deptListDept.size
    }

    override fun onBindViewHolder(holder: DeptViewHolder, position: Int) {
        val items = deptListDept[position]
        holder.item.text = items.dept

        holder.editCLICK.setOnClickListener {
            val i = Intent(context, EnterDeptActivity::class.java)
            i.putExtra("FROM", "U")
            i.putExtra("MainActId",items.idD)
            i.putExtra("ET",items.dept)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }
    }

    inner class DeptViewHolder(view: View):RecyclerView.ViewHolder(view){
        var item: TextView = view.findViewById(R.id.tvDept) as TextView
        var editCLICK: RelativeLayout = view.findViewById(R.id.editCLICK) as RelativeLayout
    }

}