package hu.zsoltkiss.myproducts.ui.crud

import androidx.compose.runtime.MutableState
import hu.zsoltkiss.myproducts.persistence.entity.Product

interface CRUDViewModel {
    val products: MutableState<List<Product>>

    fun add(name: String, desc: String, quantity: Int)
    fun deleteById(id: Int)

}