package wiki.scene.base.net.config

import rxhttp.wrapper.annotation.DefaultDomain

object ApiConfig {
    const val SUCCESS_CODE = 0

    @DefaultDomain
    const val BASE_URL = "https://www.wanandroid.com"

    const val ARTICLE = "/banner/json"


}