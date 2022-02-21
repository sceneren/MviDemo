package wiki.scene.base.net.config

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import rxhttp.wrapper.annotation.DefaultDomain
import wiki.scene.base.BuildConfig
import java.util.concurrent.TimeUnit

object ApiConfig {
    private const val TIME_OUT = 30
    const val SUCCESS_CODE = 0

    @DefaultDomain
    const val BASE_URL = "https://www.wanandroid.com"

    const val ARTICLE = "/banner/json"

    fun getOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        return logging
    }
}