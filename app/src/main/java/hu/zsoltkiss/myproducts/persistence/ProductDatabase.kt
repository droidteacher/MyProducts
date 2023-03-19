package hu.zsoltkiss.myproducts.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.zsoltkiss.myproducts.persistence.dao.ProductDao
import hu.zsoltkiss.myproducts.persistence.entity.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private var dbInstance: ProductDatabase? = null

        internal fun getDatabase(context: Context): ProductDatabase? {
            if (dbInstance == null) {
                synchronized(ProductDatabase::class.java) {
                    if (dbInstance == null) {
                        dbInstance = Room.databaseBuilder(context.applicationContext, ProductDatabase::class.java, "myproducts_database").build()
                    }
                }
            }

            return dbInstance
        }
    }
}