package hu.zsoltkiss.myproducts.ui.crud

import androidx.compose.runtime.MutableState
import hu.zsoltkiss.myproducts.data.TabItem
import hu.zsoltkiss.myproducts.persistence.entity.Product

interface CRUDViewModel {
    val selectedTab: MutableState<TabItem>
    val products: MutableState<List<Product>>

    fun onSelectTab(selected: TabItem)
    fun onEditProduct(product: Product)
    fun onDeleteProduct(product: Product)

    fun createProduct(name: String, desc: String, quantity: Int)
    fun fetchAll()

}