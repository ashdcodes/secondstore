package com.example.secondstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(private  val productList:ArrayList<Product>):RecyclerView.Adapter<DataAdapter.DataHolder>(){
    private lateinit var mListener : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.list_item,
        parent,false)
        return DataHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val currentItem=productList[position]
        holder.productN.text=currentItem.productName
        holder.productCat.text=currentItem.productCategory
        holder.productPr.text=currentItem.productPrice
        holder.productCon.text=currentItem.productCondition

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class DataHolder(itemView:View,listener:onItemClickListener):RecyclerView.ViewHolder(itemView){
        val productN:TextView=itemView.findViewById(R.id.product_Name)
        val productCat:TextView=itemView.findViewById(R.id.product_Category)
        val productPr:TextView=itemView.findViewById(R.id.product_Price)
        val productCon:TextView=itemView.findViewById(R.id.product_Condition)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }
}