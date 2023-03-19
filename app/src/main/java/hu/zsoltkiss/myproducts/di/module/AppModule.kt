package hu.zsoltkiss.myproducts.di.module

import hu.zsoltkiss.myproducts.persistence.ProductDatabase
import hu.zsoltkiss.myproducts.ui.crud.CRUDViewModel
import hu.zsoltkiss.myproducts.ui.crud.CRUDViewModelImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ProductDatabase.getDatabase(androidApplication())?.productDao() }

    viewModel { CRUDViewModelImpl(get()) }
}