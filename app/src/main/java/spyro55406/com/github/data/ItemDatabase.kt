package spyro55406.com.github.data

import androidx.room.Database
import androidx.room.RoomDatabase
import spyro55406.com.github.model.ItemModel


@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}