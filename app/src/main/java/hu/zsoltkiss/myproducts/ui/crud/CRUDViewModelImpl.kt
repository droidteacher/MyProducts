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
import java.util.concurrent.TimeUnit

class CRUDViewModelImpl(private val dao: ProductDao): ViewModel(), CRUDViewModel, Disposables by DisposeBag() {
    override val selectedTab: MutableState<TabItem> = mutableStateOf(TabItem.Read)
    override val products: MutableState<List<Product>> = mutableStateOf(emptyList())

//    init {
//        createOrFetch()
//    }

    override fun onSelectTab(selected: TabItem) {
        selectedTab.value = selected
        debugPrint("::onSelectTab, $selected", step = 7777)

        if(selectedTab.value == TabItem.Read) {
            fetchAll()
        }
    }

    override fun add(name: String, desc: String, quantity: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }

    private fun createOrFetch() {
        Completable.create { emitter ->
            if (dao.productCount() == 0) {
                debugPrint("::createOrFetch, inserting test data", step = 7777)
                dao.insertProduct(
                    Product().also {
                        it.name = "Test product"
                        it.description = "Some description"
                        it.quantity = 5
                    }
                )
            }

            val items = dao.fetchAll()
            debugPrint("::createOrFetch, items count: ${items.size}", step = 7777)
            products.value = items
            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .delay(1, TimeUnit.SECONDS)
            .subscribe()
            .addTo(disposeBag)
    }

    private fun fetchAll() {
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


}