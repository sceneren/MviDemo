package wiki.scene.base.net.response

class BaseResponse<T>(
    var errorCode: Int = 0,
    var errorMsg: String? = null,
    var data: T
)
