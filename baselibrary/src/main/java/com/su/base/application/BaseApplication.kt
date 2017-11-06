package com.su.base.application

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * @项目名称：FrameProject
 * @包名：com.su.base.application
 * @版本号：v0.1
 * @创建人：苏奥博
 * @创建时间：2017/7/7 9:50
 * @修改人：
 * @修改时间：
 * @类描述: application基础类，获取全局application上下文
 */
open class BaseApplication : Application(){

    companion object{
        var instance: BaseApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}
