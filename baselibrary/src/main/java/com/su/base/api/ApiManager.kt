package www.crionline.cn.crilibrary

import android.util.Log
import com.google.gson.GsonBuilder
import com.su.base.application.BaseApplication
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.*
import www.crionline.cn.crilibrary.api.converter.GsonConverterFactory
import www.crionline.cn.crilibrary.utils.validateNetWorkState
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * @项目名称：FrameProject
 * @包名：com.su.base.api.converter
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 9:50
 * @修改人：
 * @修改时间：
 * @类描述: 网络请求初始化
 */
class ApiManager private constructor(){
    private val DEFAULT_TIMEOUT :Long = 30
    companion object{
        fun getInstance():ApiManager{
            return instanceHelper.instance
        }
    }
    private object instanceHelper{
        val instance :ApiManager= ApiManager()
    }

    private val RewriteInterceptor = Interceptor { chain ->
        val originalResponse = chain.proceed(chain.request())

        if (BaseApplication.instance.validateNetWorkState(BaseApplication.instance) ) {
            val maxAge = 60 * 5 // 在线缓存在10分钟内可读取
            originalResponse.newBuilder()
                    .addHeader("token", "xieqiongyu")
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build()
            originalResponse
        } else {
            val maxStale = 60 * 60 * 24 * 1 // 离线时缓存保存1天
            originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build()
        }
    }
    private val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
       Log.e("---ApiManager---",message) })
    private val httpCacheDirectory = File(BaseApplication.instance.cacheDir, "[缓存目录]")
    private val cacheSize :Long = 30 * 1024 * 1024 // 20 MiB 缓存大小
    private val cache = Cache(httpCacheDirectory, cacheSize)//创建缓存
    private val client = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时的时间
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(RewriteInterceptor)//添加网络拦截器
            .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .cache(cache)//添加缓存
            .build()

    /**
     * 处理时间
     */
    private val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()


    /**
     * 获取Retrofit

     * @return
     */
    fun getRetrofit(baseUrl: String): Retrofit {

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory(gson))
                .client(client)
                .build()


        return retrofit

    }

}