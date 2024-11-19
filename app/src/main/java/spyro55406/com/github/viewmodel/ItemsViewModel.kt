package spyro55406.com.github.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.viewModelScope
import androidx.room.Room
import spyro55406.com.github.data.ItemDao
import spyro55406.com.github.data.ItemDatabase
import spyro55406.com.github.model.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ItemsViewModel(application: Application) : AndroidViewModel(application) {


    private val itemDao: ItemDao

    val itemsLiveData: LiveData<List<ItemModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            ItemDatabase::class.java,
            "items_database"
        ).build()


        itemDao = database.itemDao()

          itemsLiveData = itemDao.getAll()
    }


    fun addItem(title: String, desc: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val newItem = ItemModel(title = title, desc = desc)
            itemDao.insert(newItem)
        }
    }


    fun removeItem(item: ItemModel) {

        viewModelScope.launch(Dispatchers.IO) {
            itemDao.delete(item)
        }
    }
}