package wiki.scene.base.coil

import android.util.Base64
import android.widget.ImageView
import coil.loadAny
import coil.request.Disposable
import com.blankj.utilcode.util.LogUtils

@JvmSynthetic
fun ImageView.loadBase64(
    base64Str: String,
): Disposable {
    return loadAny(base64Str.toBase64())
}

fun String.toBase64(): ByteArray? {
    return try {
        val lastIndexOf = this.lastIndexOf(",")
        val realData = if (lastIndexOf >= 0) {
            this.substring(lastIndexOf + 1)
        } else {
            this
        }
        LogUtils.e(realData)
        Base64.decode(realData, Base64.DEFAULT)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}