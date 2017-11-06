package www.crionline.cn.crilibrary.api.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import kotlin.properties.Delegates

/**
 * @项目名称：FrameProject
 * @包名：com.su.base.api.converter
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 9:50
 * @修改人：
 * @修改时间：
 * @类描述: 网络请求json工具
 */
class GsonConverterFactory constructor(private val gson: Gson?) : Converter.Factory() {
    init {
        if (gson == null) throw NullPointerException("gson == null")
    }

    override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?,
                                       retrofit: Retrofit?): Converter<ResponseBody, *> {
        val adapter = gson!!.getAdapter(TypeToken.get(type!!))
        return GsonResponseBodyConverter(gson, adapter)
    }

    override fun requestBodyConverter(type: Type?,
                                      parameterAnnotations: Array<Annotation>?, methodAnnotations: Array<Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody> {
        val adapter = gson!!.getAdapter(TypeToken.get(type!!))
        return GsonRequestBodyConverter(gson, adapter)
    }

    companion object {
        /**
         * Create an instance using a default [Gson] instance for conversion. Encoding to JSON and
         * decoding from JSON (when no charset is specified by a header) will use UTF-8.
         */

        fun create(): GsonConverterFactory {
            return create(Gson())
        }

        /**
         * Create an instance using `gson` for conversion. Encoding to JSON and
         * decoding from JSON (when no charset is specified by a header) will use UTF-8.
         */
        fun create(gson: Gson): GsonConverterFactory {
            return GsonConverterFactory(gson)
        }
    }
}