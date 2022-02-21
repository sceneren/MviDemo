package wiki.scene.mvidemo

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.navigation.DefaultNavigationViewModelDelegateFactory
import rxhttp.RxHttpPlugins
import wiki.scene.base.net.config.ApiConfig

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(
            this,
            viewModelDelegateFactory = DefaultNavigationViewModelDelegateFactory()
        )
        RxHttpPlugins.init(ApiConfig.getOkHttpClientBuilder().build())
    }
}