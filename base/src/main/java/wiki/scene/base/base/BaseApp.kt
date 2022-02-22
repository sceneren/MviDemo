package wiki.scene.base.base

import android.app.Application
import android.content.Context
import android.os.Build.VERSION.SDK_INT
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.decode.VideoFrameDecoder
import coil.imageLoader
import coil.util.CoilUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.hjq.language.MultiLanguages
import com.kingja.loadsir.core.LoadSir
import com.kongzue.dialogx.DialogX
import com.kongzue.dialogx.style.IOSStyle
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import wiki.scene.base.coil.ByteArrayFetcher
import wiki.scene.base.loadsir.ErrorCallback
import wiki.scene.base.loadsir.LoadingCallback
import wiki.scene.base.mojito.MojitoUtils
import wiki.scene.base.mojito.coil.ProgressSupport
import wiki.scene.base.net.config.ApiConfig
import wiki.scene.base.toast.ToastUtil


/**
 *
 * @Description:    基类的Application
 * @Author:         scene
 * @CreateDate:     2021/11/10 09:45
 * @UpdateUser:
 * @UpdateDate:     2021/11/10 09:45
 * @UpdateRemark:
 * @Version:        1.0.0
 */
open class BaseApp : Application(), ImageLoaderFactory {
    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            MaterialHeader(context)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(MultiLanguages.attach(base))
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())
        // 初始化语种切换框架
        MultiLanguages.init(this)

        initToast()
        initDialogX()
        initLoadSir()
        initMojito()
    }

    private fun initToast() {
        ToastUtil.init(this)
    }

    /**
     * 配置DialogX
     */
    private fun initDialogX() {
        DialogX.init(this)
        DialogX.globalStyle = IOSStyle()
        DialogX.globalTheme = DialogX.THEME.LIGHT
        DialogX.dialogMaxWidth = ScreenUtils.getAppScreenWidth()
        DialogX.autoShowInputKeyboard = false
    }

    /**
     * 初始化多状态布局
     */
    private fun initLoadSir() {
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())
            .addCallback(ErrorCallback())
            .commit()
    }

    companion object {
        lateinit var instance: BaseApp
            private set
    }


    private inner class ApplicationLifecycleObserver : DefaultLifecycleObserver {

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            LogUtils.e("AppLifecycleObserver", "onStart")
        }

        override fun onStop(owner: LifecycleOwner) {
            super.onStop(owner)
            LogUtils.e("AppLifecycleObserver", "onStop")
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .crossfade(true)
            .okHttpClient {
                ApiConfig.getOkHttpClientBuilder()
                    .cache(CoilUtils.createDefaultCache(this))
                    .addInterceptor { chain ->
                        val request = chain.request()
                        val response = chain.proceed(request)
                        response.newBuilder()
                            .body(
                                ProgressSupport.OkHttpProgressResponseBody(
                                    request.url, response.body,
                                    ProgressSupport.DispatchingProgressListener()
                                )
                            )
                            .build()
                    }
                    .build()
            }
            .componentRegistry {
                // GIFs
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(applicationContext))
                } else {
                    add(GifDecoder())
                }
                // SVGs
                add(SvgDecoder(applicationContext))
                // Video frames
                add(VideoFrameDecoder(applicationContext))
                //Base64 Support
                add(ByteArrayFetcher())
            }
            .placeholder(com.luck.picture.lib.R.drawable.picture_anim_progress)
            .error(com.luck.picture.lib.R.drawable.picture_image_placeholder)
            .build()
    }

    /**
     * 查看大图
     */
    private fun initMojito() {
        MojitoUtils.init(applicationContext, applicationContext.imageLoader)
    }
}