package hu.zsoltkiss.myproducts.app

import android.app.Application
import hu.zsoltkiss.myproducts.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TheApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TheApp)
            modules(appModule)
        }
    }
}