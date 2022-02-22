package wiki.scene.base.mojito.coil

import android.content.ContentResolver
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import coil.request.Disposable
import coil.request.ImageRequest
import coil.util.CoilUtils
import net.mikaelzero.mojito.loader.ImageLoader
import okhttp3.Cache
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.io.File
import kotlin.collections.set


/**
 *
 * @Description:    查看大图的Coil引擎，Copy自mojito，修改使其共用一个imageLoader
 * @Author:         scene
 * @CreateDate:     2021/12/8 14:52
 * @UpdateUser:
 * @UpdateDate:     2021/12/8 14:52
 * @UpdateRemark:
 * @Version:        1.0.0
 */
class CoilImageLoader private constructor(
    val context: Context,
    private val imageLoader: coil.ImageLoader
) :
    ImageLoader {

    private val mFlyingRequestTargets: MutableMap<Int, Disposable> = HashMap(3)

    override fun loadImage(
        requestId: Int,
        uri: Uri?,
        onlyRetrieveFromCache: Boolean,
        callback: ImageLoader.Callback?
    ) {
        val localCache = uri.toString().getCoilCacheFile()
        if (onlyRetrieveFromCache && (localCache == null || !localCache.exists())) {
            callback?.onFail(Exception(""))
            return
        }
        val request = ImageRequest.Builder(context)
            .data(uri)
            .memoryCacheKey(uri.toString())
            .target(object : ImageDownloadTarget(uri?.toString().orEmpty()) {
                override fun onSuccess(result: Drawable) {
                    super.onSuccess(result)
                    val resource = uri.getCoilCacheFile()
                    if (resource?.exists() == true) {
                        callback?.onSuccess(resource)
                    } else {
                        callback?.onFail(CoilLoaderException(null))
                    }
                }

                override fun onError(error: Drawable?) {
                    super.onError(error)
                    callback?.onFail(CoilLoaderException(error))
                }

                override fun onDownloadStart() {
                    callback?.onStart()
                }

                override fun onProgress(progress: Int) {
                    callback?.onProgress(progress)
                }

                override fun onDownloadFinish() {
                    callback?.onFinish()
                }

            })
            .build()
        rememberTarget(requestId, imageLoader.enqueue(request))
    }

    private fun String?.toFile(): File? {
        if (this == null) {
            return null
        }
        val f = File(this)
        return if (f.exists()) f else null
    }

    fun Uri?.getCoilCacheFile(): File? {
        if (SCHEMES.contains(this?.scheme)) {
            if (this?.path == null) {
                return null
            }
            return File(this.path!!)
        } else {
            return this.toString().getCoilCacheFile()
        }
    }

    private fun String?.getCoilCacheFile(): File? {
        return this?.toFile() ?: this?.toHttpUrlOrNull()?.let { u ->
            CoilUtils.createDefaultCache(context).directory.listFiles()
                ?.lastOrNull { it.name.endsWith(".1") && it.name.contains(Cache.key(u)) }
        }
    }

    override fun prefetch(uri: Uri?) {
        val request = ImageRequest.Builder(context)
            .data(uri)
            .build()

        imageLoader.enqueue(request)
    }

    override fun cancel(requestId: Int) {
        clearTarget(mFlyingRequestTargets.remove(requestId))
    }

    private fun clearTarget(target: Disposable?) {
        target?.dispose()
    }

    @Synchronized
    private fun rememberTarget(requestId: Int, target: Disposable) {
        mFlyingRequestTargets[requestId] = target
    }

    override fun cancelAll() {
        val targets: List<Disposable> = ArrayList(mFlyingRequestTargets.values)
        for (target in targets) {
            clearTarget(target)
        }
    }

    override fun cleanCache() {
        imageLoader.memoryCache.clear()
        CoilUtils.createDefaultCache(context).delete()
    }


    companion object {
        private val SCHEMES = setOf(
            ContentResolver.SCHEME_FILE,
            ContentResolver.SCHEME_ANDROID_RESOURCE,
            ContentResolver.SCHEME_CONTENT
        )

        fun with(context: Context, imageLoader: coil.ImageLoader): CoilImageLoader {
            return CoilImageLoader(context, imageLoader)
        }
    }
}
