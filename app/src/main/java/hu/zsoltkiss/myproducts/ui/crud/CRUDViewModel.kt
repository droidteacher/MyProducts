package hu.zsoltkiss.myproducts.ui.crud

import androidx.compose.runtime.MutableState
import hu.zsoltkiss.myproducts.data.TabItem
import hu.zsoltkiss.myproducts.persistence.entity.Product

interface CRUDViewModel {
    val selectedTab: MutableState<TabItem>
    val products: MutableState<List<Product>>
    val shouldDisplayConfirmDialog: MutableState<Boolean>

    val beingEdited: Product?

    fun onSelectTab(selected: TabItem)
    fun onEditRequest(product: Product)
    fun onDeleteRequest(product: Product)

    fun createProduct(name: String, desc: String, quantity: Int)
    fun fetchAll()
    fun confirmDelete()
    fun cancelDelete()
    fun confirmUpdate(name: String, desc: String, quantity: Int)
    fun cancelUpdate()

}