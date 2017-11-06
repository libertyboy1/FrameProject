package www.crionline.cn.crilibrary.utils

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import com.su.base.application.BaseApplication
import org.jetbrains.anko.backgroundColor
import java.security.MessageDigest
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 *@date 创建时间 2017/4/12-下午2:47
 *@author 苏奥博
 *@description 弹出框
 *@company 国际在线
 **/
fun Context.alertMessage(rootView: CoordinatorLayout, message: String, color: Int = Color.BLACK) {
    var snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
    var alertView = snackbar.view
    alertView.backgroundColor = color
    snackbar.show()
}

/**
 *@date 创建时间 2017/4/12-下午2:47
 *@author 苏奥博
 *@description 判断是否有网络
 *@company 国际在线
 **/
fun Context.validateNetWorkState(context: Context): Boolean {
    val mNetworkInfo = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
    if (mNetworkInfo != null) {
        return mNetworkInfo.isAvailable
    }
    return false
}

/**
 *@date 创建时间 2017/4/12-下午2:47
 *@author 苏奥博
 *@description 验证邮箱格式是否正确
 *@company 国际在线
 **/
fun Context.validateIsEmail(email: String): Boolean {
    val p: Pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")//复杂匹配
    val m: Matcher = p.matcher(email)
    return m.matches()
}

/**
 *@date 创建时间 2017/4/12-下午2:47
 *@author 苏奥博
 *@description 验证手机号格式是否正确
 *@company 国际在线
 **/
fun Context.validateIsPhoneNum(phoneNum: String): Boolean {
    val p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[7,0-9]))\\d{8}$")
    val m: Matcher = p.matcher(phoneNum)
    return m.matches()
}

/**
 *@date 创建时间 2017/4/12-下午2:47
 *@author 苏奥博
 *@description MD5加密
 *@company 国际在线
 **/
fun Context.getMd5Value(sSecret: String): String {
    try {
        val bmd5: MessageDigest = MessageDigest.getInstance("MD5")
        bmd5.update(sSecret.toByteArray())
        var i: Int
        val buf: StringBuffer = StringBuffer()
        val b: ByteArray = bmd5.digest()// 加密

        for (offset: Int in 0..b.size - 1) {
            i = b[offset].toInt()
            if (i < 0)
                i += 256
            if (i < 16)
                buf.append("0")
            buf.append(Integer.toHexString(i))
        }

        return buf.toString()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""

}

/**
 *@date 创建时间 2017/4/12-下午2:47
 *@author 苏奥博
 *@description 判断网络是否可用
 *@company 国际在线
 **/
fun Any.validateNetWork(): String {
    var netType: String? = null
    val networkInfo = (BaseApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
    if (networkInfo != null) {
        val nType = networkInfo.type
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            netType = "0"
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = "1"
        }
    }
    return netType!!

}
