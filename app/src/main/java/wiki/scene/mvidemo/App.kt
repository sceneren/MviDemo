package wiki.scene.mvidemo

import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.navigation.DefaultNavigationViewModelDelegateFactory
import rxhttp.RxHttpPlugins
import wiki.scene.base.base.BaseApp
import wiki.scene.base.net.config.ApiConfig

class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(
            this,
            viewModelDelegateFactory = DefaultNavigationViewModelDelegateFactory()
        )
        RxHttpPlugins.init(ApiConfig.getOkHttpClientBuilder().build())

    }
}