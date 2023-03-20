package hu.zsoltkiss.myproducts.persistence.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class Product {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Long = 0
    var name: String = ""
    var description: String = ""
    var quantity: Int = 0


    override fun toString(): String {
        return "Product { $id, $name, $quantity }"
    }
}