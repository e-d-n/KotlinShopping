package com.androidstackoverflow.kotlinshopping

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView



class ItemAdapter(private val itemListItem:List<ModelItem>, internal var context: Context):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
       return itemListItem.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val items = itemListItem[position]
        holder.item.text = items.item
        holder.editCLICK.setOnClickListener {
            val i = Intent(context, EnterItemActivity::class.java)
            i.putExtra("FROM", "U")
            i.putExtra("MainActId",items.idI)
            i.putExtra("ET",items.item)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }
    }


    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        var item:TextView = view.findViewById(R.id.tvItem)as TextView
        var editCLICK: RelativeLayout = view.findViewById(R.id.editCLICK) as RelativeLayout
    }

}