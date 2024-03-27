package com.ddd.mytestapp2.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.ddd.mytestapp2.R
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ddd.mytestapp2.databinding.FragmentMenuBinding
import com.ddd.mytestapp2.model.constant.MAIN
import com.ddd.mytestapp2.model.adapters.CatalogAdapter
import com.ddd.mytestapp2.model.adapters.FoodAdapter
import com.ddd.mytestapp2.model.adapters.ImageAdapter
import com.ddd.mytestapp2.model.adapters.interfaceForAdapter.CatalogAdapterInterface
import com.ddd.mytestapp2.model.adapters.interfaceForAdapter.FoodAdapterInterface
import com.ddd.mytestapp2.model.constant.listCities
import com.ddd.mytestapp2.model.modelFood.Food
import com.ddd.mytestapp2.model.repository.Repository
import com.ddd.mytestapp2.viewmodel.MenuViewModel

class MenuFragment : Fragment(),CatalogAdapterInterface,FoodAdapterInterface {

    private var binding: FragmentMenuBinding? = null

    private var recyclerViewFood: RecyclerView? = null
    private var recyclerViewImage: RecyclerView? = null
    private var recyclerViewCatalog: RecyclerView? = null

    private var foodAdapter: FoodAdapter? = null
    private var catalogAdapter: CatalogAdapter? = null
    private var imageAdapter: ImageAdapter? = null

    private var menuViewModel:MenuViewModel? = null

    private var repository:Repository? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuViewModel = ViewModelProvider(this)[MenuViewModel::class.java]

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listCities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.idMenuMenuSc1Spinner?.adapter = adapter
        binding?.idMenuMenuSc1Spinner?.setSelection(0)

        binding?.idMenuMenuSc1Spinner?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    binding?.idMenuMenuSc1Spinner?.setSelection(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        recyclerViewFood = binding?.idMenuMenuRvFood
        recyclerViewImage = binding?.idMenuMenuRvImage
        recyclerViewCatalog = binding?.idMenuMenuRvKatalog

        foodAdapter = FoodAdapter(this)
        catalogAdapter = CatalogAdapter(this)
        imageAdapter = ImageAdapter()

        recyclerViewFood?.adapter = foodAdapter
        recyclerViewCatalog?.adapter = catalogAdapter
        recyclerViewImage?.adapter = imageAdapter

        repository = Repository()

        if (repository?.checkInternet(requireContext()) == true) {
            menuViewModel?.getListFood("pizza") // запрос в сеть
        } else {
            menuViewModel?.getListFoodFromMyDatabase("pizza") // запрос в бд
        }

        menuViewModel?.listFood?.observe(viewLifecycleOwner) { data ->
            foodAdapter?.setList(data.body()?.results)
        }

        menuViewModel?.listFoodInMyDatabase?.observe(viewLifecycleOwner) { data ->
            val list = mutableListOf<Food>()
            for (i in data) {
                list.add(Food(i.id.toInt(), i.urlImage, "", i.name))
            }
            foodAdapter?.setList(list)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    // показ меню из выбранного каталога
    override fun showListFoodInRecyclerView(itemCatalog: String) {
        if(repository?.checkInternet(requireContext()) == true){
            menuViewModel?.getListFood(itemCatalog)
        }else{
            menuViewModel?.getListFoodFromMyDatabase(itemCatalog)
        }
    }

    // показ полной информации выбранной позиции
    override fun showFullFoodInfo(food: Food) {
        val bundle = Bundle()
        bundle.putParcelable("key1",food)
        MAIN?.navController?.navigate(R.id.action_menuFragment_to_detailFragment,bundle)
    }

}