package wiki.scene.base.net

import com.blankj.utilcode.util.NetworkUtils
import com.casad.common.resources.toXmlString
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.TimeoutCancellationException
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.exception.ParseException
import wiki.scene.base.R
import wiki.scene.base.toast.showToast
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 *
 * @Description:
 * @Author:         scene
 * @CreateDate:     2022/2/22 10:26
 * @UpdateUser:
 * @UpdateDate:     2022/2/22 10:26
 * @UpdateRemark:
 * @Version:        1.0.0
 */
fun Throwable.show() {
    errorMsg.show()
}

fun String.show() {
    showToast(this)
}

val Throwable.errorCode: Int
    get() {
        val errorCode = when (this) {
            is HttpStatusCodeException -> this.statusCode //Http状态码异常
            is ParseException -> this.errorCode     //业务code异常
            else -> "-1"
        }
        return try {
            errorCode as Int
        } catch (e: Exception) {
            -1
        }
    }

val Throwable.errorMsg: String
    get() {
        return if (this is UnknownHostException) { //网络异常
            if (!NetworkUtils.isConnected()) {
                R.string.base_network_error.toXmlString()
            } else
                R.string.base_internet_connection_unavailable.toXmlString()
        } else if (
            this is SocketTimeoutException  //okhttp全局设置超时
            || this is TimeoutException     //rxjava中的timeout方法超时
            || this is TimeoutCancellationException  //协程超时
        ) {
            R.string.base_connection_timed_out.toXmlString()
        } else if (this is ConnectException) {
            R.string.base_network_is_weak.toXmlString()
        } else if (this is HttpStatusCodeException) {               //请求失败异常
            R.string.base_http_status_code_exception.toXmlString()
        } else if (this is JsonSyntaxException) {  //请求成功，但Json语法异常,导致解析失败
            R.string.base_data_parsing_failed.toXmlString()
        } else if (this is ParseException) {       // ParseException异常表明请求成功，但是数据不正确
            this.message ?: errorCode   //msg为空，显示code
        } else {
            message ?: this.toString()
        }
    }