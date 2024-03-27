package com.ddd.mytestapp2.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.ddd.mytestapp2.R
import com.ddd.mytestapp2.model.adapters.interfaceForAdapter.CatalogAdapterInterface
import com.ddd.mytestapp2.model.constant.listItemsInCatalog
import com.ddd.mytestapp2.model.constant.listItemsInCatalog_2

class CatalogAdapter(private val catalogInterface:CatalogAdapterInterface): RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    private var listCatalog = listItemsInCatalog
    private var listCatalog2 = listItemsInCatalog_2

    class CatalogViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_katalog,parent,false)
        return CatalogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCatalog.size
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val buttonName = holder.itemView.findViewById<Button>(R.id.id_item_katalog_button)
        buttonName.text = listCatalog[position]
    }

    override fun onViewAttachedToWindow(holder: CatalogViewHolder) {
        super.onViewAttachedToWindow(holder)

        val buttonName = holder.itemView.findViewById<Button>(R.id.id_item_katalog_button)
        buttonName.setOnClickListener {
            catalogInterface.showListFoodInRecyclerView(listCatalog2[holder.adapterPosition])
        }

    }

}