package hu.zsoltkiss.myproducts.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hu.zsoltkiss.myproducts.persistence.entity.Product

@Dao
interface ProductDao {
    @Insert
    fun insertProduct(product: Product): Long

    @Insert
    fun insertAll(vararg products: Product)

    @Query("select * from products")
    fun fetchAll(): List<Product>

    @Query("select count(*) from products")
    fun productCount(): Int

    @Delete
    fun deleteProduct(product: Product)

    @Update
    fun updateProduct(product: Product)
}