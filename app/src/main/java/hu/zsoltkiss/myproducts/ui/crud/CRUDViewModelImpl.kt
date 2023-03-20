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

    override fun onSelectTab(selected: TabItem) {
        selectedTab.value = selected
        debugPrint("::onSelectTab, $selected", step = 7777)

        if(selectedTab.value == TabItem.Read) {
//            fetchAll()
            createOrFetch()
        }
    }

    override fun onEditProduct(product: Product) {
        debugPrint("::onEditProduct, $product", step = 7777)
    }

    override fun onDeleteProduct(product: Product) {
        debugPrint("::onDeleteProduct, $product", step = 7777)
    }

    override fun createProduct(name: String, desc: String, quantity: Int) {
        debugPrint("::createProduct", step = 7777)
    }


    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }

    private fun createOrFetch() {
        Completable.create { emitter ->
            if (dao.productCount() == 0) {
                debugPrint("::createOrFetch, inserting test data", step = 7777)
                dao.insertAll(
                    Product().also {
                        it.name = "Kotlin in Action"
                        it.description = "Kotlin in Action teaches you the Kotlin programming language and how to use it to build applications running on the Java virtual machine and Android"
                        it.quantity = 5
                    },

                    Product().also {
                        it.name = "Professional React Native"
                        it.description = "The React Native framework offers a range of powerful features that make it possible to efficiently build high-quality, easy-to-maintain frontend applications across multiple platforms such as iOS, Android, Linux, Mac OS X, Windows, and the web, helping you save both time and money. And this book is your key to unlocking its capabilities."
                        it.quantity = 9
                    },

                    Product().also {
                        it.name = "Python Crash Course"
                        it.description = "If you've been thinking about learning how to code or picking up Python, this internationally bestselling guide to the most popular programming language is your quickest, easiest way to get started and go!"
                        it.quantity = 3
                    },
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