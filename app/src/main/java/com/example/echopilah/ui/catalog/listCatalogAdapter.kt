package com.example.echopilah.ui.catalog

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.echopilah.R
import com.example.echopilah.data.local.entity.Catalog

class listCatalogAdapter(private val listCatalog: ArrayList<Catalog>) : RecyclerView.Adapter<listCatalogAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listCatalog.size
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (image1,image2,title,description) = listCatalog[position]
        holder.cvTitle.text = title
        holder.cvImage.setImageResource(image1)

        val curentCatalog = listCatalog[position]
        holder.itemView.setOnClickListener{
            val intentToDetail = Intent(holder.itemView.context, DetailCatalog::class.java)
            intentToDetail.putExtra(DetailCatalog.KEY_CATALOG, curentCatalog)
            holder.itemView.context.startActivity(intentToDetail)
        }
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cvTitle: TextView = itemView.findViewById(R.id.title)
        val cvImage: ImageView = itemView.findViewById(R.id.img)

    }
}