package cn.crionline.www.chinanews.api



/**
 * @项目名称：FrameProject
 * @包名：com.su.base.api
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 9:50
 * @修改人：
 * @修改时间：
 * @类描述: 网络请求调用
                     例：
                     fun XXX(Observer: Observer<返回实体>, body: 请求实体) {
                            ApiManager.getInstance().getRetrofit(接口域名)
                              .create(ApiService::class.java)
                              .dynamicMenu(body)
                              .subscribeOn(Schedulers.computation())
                              .observeOn(AndroidSchedulers.mainThread())
                              .unsubscribeOn(Schedulers.io())
                              .subscribe(Observer)
                            }
                     }
 */
class ApiRequest private constructor() {
    private val CRI_URL = "http://192.168.200.51:8080/portal/"

    companion object {
        fun getInstance(): ApiRequest {
            return instanceHelper.instance
        }
    }

    private object instanceHelper {
        val instance: ApiRequest = ApiRequest()
    }

}