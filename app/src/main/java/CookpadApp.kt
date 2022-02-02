
package com.example.cookpadapp
import android.app.Application
import com.example.cookpadapp.di.Modules.apiModule
import com.example.cookpadapp.di.Modules.viewModels
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class CookpadApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@CookpadApp)
            modules(listOf(viewModels, apiModule))
        }
    }
}