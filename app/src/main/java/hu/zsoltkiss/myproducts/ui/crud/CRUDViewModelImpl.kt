package hu.zsoltkiss.myproducts.ui.crud

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import hu.zsoltkiss.myproducts.data.TabItem
import hu.zsoltkiss.myproducts.helper.Disposables
import hu.zsoltkiss.myproducts.helper.DisposeBag
import hu.zsoltkiss.myproducts.helper.debugPrint
import hu.zsoltkiss.myproducts.persistence.dao.ProductDao
import hu.zsoltkiss.myproducts.persistence.entity.Product
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class CRUDViewModelImpl(private val dao: ProductDao): ViewModel(), CRUDViewModel, Disposables by DisposeBag() {
    override val selectedTab: MutableState<TabItem> = mutableStateOf(TabItem.Read)
    override val products: MutableState<List<Product>> = mutableStateOf(emptyList())
    override val shouldDisplayConfirmDialog: MutableState<Boolean> = mutableStateOf(false)

    private var productToRemove: Product? = null
    private var productToEdit: Product? = null

    override val beingEdited: Product?
        get() = productToEdit


    override fun onSelectTab(selected: TabItem) {
        selectedTab.value = selected
        debugPrint("::onSelectTab, $selected", step = 7777)
    }

    override fun onEditRequest(product: Product) {
        debugPrint("::onEditRequest, $product", step = 7777)
        productToEdit = product
        selectedTab.value = TabItem.Create
    }

    override fun onDeleteRequest(product: Product) {
        debugPrint("::onDeleteProduct, $product", step = 7777)
        productToRemove = product
        shouldDisplayConfirmDialog.value = true
    }

    override fun confirmDelete() {
        debugPrint("::confirmDelete", step = 7777)

        Completable.create { emitter ->
            productToRemove?.let {
                dao.deleteProduct(it)
                products.value = dao.fetchAll()
                emitter.onComplete()
            }
        }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    productToRemove = null
                    shouldDisplayConfirmDialog.value = false
                },
                {
                    it.printStackTrace()
                }
            )

    }

    override fun cancelDelete() {
        productToRemove = null
        shouldDisplayConfirmDialog.value = false
    }

    override fun confirmUpdate(name: String, desc: String, quantity: Int) {
        debugPrint("::confirmUpdate", step = 7777)

        Completable.create { emitter ->
            productToEdit?.let {
                it.name = name
                it.description = desc
                it.quantity = quantity

                dao.updateProduct(it)
                val items = dao.fetchAll()
                debugPrint("::confirmUpdate, REFRESHED, items count: ${items.size}", step = 7777)
                products.value = items
            }

            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    productToEdit = null
                    selectedTab.value = TabItem.Read
                },
                {
                    it.printStackTrace()
                }
            )
            .addTo(disposeBag)
    }

    override fun cancelUpdate() {
        productToEdit = null
        selectedTab.value = TabItem.Read
    }

    override fun createProduct(name: String, desc: String, quantity: Int) {
        debugPrint("::createProduct", step = 7777)

        Completable.create { emitter ->
            dao.insertProduct(
                Product().also {
                    it.name = name
                    it.description = desc
                    it.quantity = quantity
                }
            )

            val items = dao.fetchAll()
            debugPrint("::createProduct, items count: ${items.size}", step = 7777)
            products.value = items
            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    selectedTab.value = TabItem.Read
                },
                {
                    it.printStackTrace()
                }
            )
            .addTo(disposeBag)
    }

    override fun fetchAll() {
        debugPrint("::fetchAll")
        Observable.create<List<Product>> {
            it.onNext(dao.fetchAll())
            it.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .subscribe {
                products.value = it
            }.addTo(disposeBag)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }
}