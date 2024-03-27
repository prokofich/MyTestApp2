package com.ddd.mytestapp2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.mytestapp2.model.database.FoodEntity
import com.ddd.mytestapp2.model.database.RoomRepository
import com.ddd.mytestapp2.model.modelFood.Food
import com.ddd.mytestapp2.model.modelFood.ModelAnswer
import com.ddd.mytestapp2.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MenuViewModel(application: Application):AndroidViewModel(application) {

    private val repository = Repository()
    private val roomRepository = RoomRepository(getApplication())

    val listFood: MutableLiveData<Response<ModelAnswer>> = MutableLiveData()
    val listFoodInMyDatabase: MutableLiveData<List<FoodEntity>> = MutableLiveData()

    fun getListFood(query:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getListFood(query)
            setListFoodInDatabase(response.body()?.results,query)
            withContext(Dispatchers.Main){
                listFood.value = response
            }
        }
    }

    fun getListFoodFromMyDatabase(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            val answer = roomRepository.database.databaseFoodDao().getListFoodByCatalog(query)
            withContext(Dispatchers.Main){
                listFoodInMyDatabase.value = answer
            }
        }
    }

    private fun setListFoodInDatabase(listFood:List<Food>?,itemCatalog:String){
        viewModelScope.launch(Dispatchers.IO) {
            if(listFood!=null){
                val list = mutableListOf<FoodEntity>()
                for (i in listFood){
                    list.add(FoodEntity(i.id.toLong(),i.title,i.image,itemCatalog))
                }
                roomRepository.database.databaseFoodDao().insertListFoods(list)
            }
        }
    }

}