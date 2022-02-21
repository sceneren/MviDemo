package wiki.scene.base.net.parser

import okio.IOException
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.TypeParser
import rxhttp.wrapper.utils.convertTo
import wiki.scene.base.net.config.ApiConfig
import wiki.scene.base.net.response.BaseResponse
import java.lang.reflect.Type

@Parser(name = "Response")
open class ResponseParser<T> : TypeParser<T> {

    protected constructor() : super()
    constructor(type: Type) : super(type)

    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T {
        val data: BaseResponse<T> = response.convertTo(BaseResponse::class, *types)
        val t = data.data     //获取data字段
        if (data.errorCode != ApiConfig.SUCCESS_CODE || t == null) { //code不等于200，说明数据不正确，抛出异常
            throw ParseException(data.errorCode.toString(), data.errorMsg, response)
        }
        return t  //最后返回data字段
    }
}
