package com.ddd.mytestapp2.model.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ddd.mytestapp2.R
import com.ddd.mytestapp2.model.adapters.interfaceForAdapter.FoodAdapterInterface
import com.ddd.mytestapp2.model.modelFood.Food

class FoodAdapter(private val foodInterface: FoodAdapterInterface): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private var listFood = emptyList<Food>()

    class FoodViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_for_menu,parent,false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val imageViewFood = holder.itemView.findViewById<ImageView>(R.id.id_item_food_iv)
        val textViewName = holder.itemView.findViewById<TextView>(R.id.id_item_food_tv_name)

        imageViewFood.load(listFood[position].image)
        textViewName.text = listFood[position].title
    }

    override fun onViewAttachedToWindow(holder: FoodViewHolder) {
        super.onViewAttachedToWindow(holder)

        val food = holder.itemView.findViewById<ConstraintLayout>(R.id.id_item_food_cs)

        food.setOnClickListener {
            foodInterface.showFullFoodInfo(listFood[holder.adapterPosition])
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<Food>?){
        if(list!=null){
            listFood = list
            notifyDataSetChanged()
        }
    }

}