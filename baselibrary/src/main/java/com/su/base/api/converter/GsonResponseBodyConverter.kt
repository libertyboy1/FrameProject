package www.crionline.cn.crilibrary.api.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import okhttp3.internal.Util.UTF_8
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Converter
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStreamReader
/**
 * @项目名称：FrameProject
 * @包名：com.su.base.api.converter
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 9:50
 * @修改人：
 * @修改时间：
 * @类描述: 网络请求响应解析框架类
 */
class GsonResponseBodyConverter<T>(gson: Gson, adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {
    private var gson: Gson? = null
    private var adapter: TypeAdapter<T>? = null

    init {
        this.gson = gson
        this.adapter = adapter
    }


    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {

        val response = value.string()
        val jsonObject = JSONObject(response)

//        val httpState = gson!!.fromJson<ApiState>(o!!.toString(), ApiState::class.java)
//        if (httpState.ret != "1") {
//            value.close()
//            throw ApiException(httpState.ret, httpState.msg)
//        }

        val contentType = value.contentType()
        val charset = if (contentType != null) contentType.charset(UTF_8) else UTF_8

        val inputStream = ByteArrayInputStream(jsonObject.toString().toByteArray())
        val reader = InputStreamReader(inputStream, charset)
        val jsonReader = gson!!.newJsonReader(reader)

        try {
            return adapter!!.read(jsonReader)
        } finally {
            value.close()
        }


    }
}