package www.crionline.cn.crilibrary.api.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.IOException
import java.io.OutputStreamWriter
import java.nio.charset.Charset

/**
 * @项目名称：FrameProject
 * @包名：com.su.base.api.converter
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 9:50
 * @修改人：
 * @修改时间：
 * @类描述: 网络请求请求json格式封装框架类
 */
class GsonRequestBodyConverter<T>(gson: Gson, adapter: TypeAdapter<T>) : Converter<T, RequestBody> {
    private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
    private val UTF_8 = Charset.forName("UTF-8")

    private var gson: Gson?=null
    private var adapter: TypeAdapter<T>?=null
    init {
        this.gson = gson
        this.adapter = adapter
    }


    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson!!.newJsonWriter(writer)
        adapter!!.write(jsonWriter, value)
        jsonWriter.close()
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
    }
}