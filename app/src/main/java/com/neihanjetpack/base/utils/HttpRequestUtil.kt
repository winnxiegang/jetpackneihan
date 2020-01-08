package com.neihanjetpack.base.utils

import com.jiutong.base.annotation.Ignore
import com.jiutong.base.constant.HttpConstant
import com.jiutong.base.constant.HttpConstant.Header.APP_ID
import com.jiutong.base.constant.HttpConstant.Header.KEY
import com.jiutong.base.constant.HttpConstant.Header.MACHINE_NO
import com.jiutong.base.constant.HttpConstant.Header.PLATFORM_ID
import com.jiutong.base.constant.HttpConstant.Header.SECRET_KEY_DEBUG
import com.jiutong.base.constant.HttpConstant.Header.SECRET_KEY_RELEASE
import com.jiutong.base.constant.HttpConstant.Header.TOKEN
import com.jiutong.base.constant.HttpConstant.Header.VERSION
import com.jiutong.base.constant.HttpConstant.RequestParam.APP_ID_PARAM
import com.jiutong.base.constant.HttpConstant.RequestParam.PLATFORM_ID_PARAM
import com.neihanjetpack.BuildConfig
import com.neihanjetpack.base.entity.BaseEntity
import okhttp3.RequestBody

/**
 * @author: xiangyun_liu
 *
 * @date: 2018/8/21 14:34
 *
 * 接口请求定义：
 * http://wiki.9tong.com/pages/viewpage.action?pageId=1409143
 */
object HttpRequestUtil {

    /**
     * 公共的请求头,值不会动态改变
     * token 在登录之后传入
     */
    val publicHeader by lazy {
        mutableMapOf(
            APP_ID to APP_ID_PARAM,
            PLATFORM_ID to PLATFORM_ID_PARAM
        )
    }

    /**
     *  根据请求参数生成签名
     */
    fun <J : BaseEntity> generateSign(timestamp: Long, vararg jEntitys: J): String {
        val element = LinkedHashMap<String, String>()
        val t1 = System.currentTimeMillis()
        jEntitys.forEach { externalIt ->
            //取出当前类的所有的属性，不包含父类的
            externalIt.javaClass.declaredFields.filter {
                try {
                    it.isAccessible = true
                    val name = it.get(externalIt).toString()
                    //Kotlin默认有serialVersionUID字段，需要排除
                    //凡是用@Ignore注解标记过的字段也要排除
                    //实现Parcelable接口companion object CREATOR在java class中为字段也要排除
                    if (it.name == "serialVersionUID" || it.isAnnotationPresent(Ignore::class.java) || it.name == "CREATOR") {
                        false
                    } else {
                        name.isNotEmpty()
                    }
                } catch (e: Exception) {
                    false
                }
            }.forEach { element[it.name] = it.get(externalIt).toString() }
        }
        val t2 = System.currentTimeMillis()

//        经测试 java 反射耗时:1  kotlin 反射耗时:1627  ， 姑且认为KClass反射效率低，没仔细研究过
//        jEntity.javaClass.kotlin.declaredMemberProperties.filter {
//            try {
//                it.get(jEntity).toString().isNotEmpty()
//            } catch (e: Exception) {
//                false
//            }
//        }.forEach { element.put(it.name, it.get(jEntity).toString()) }
//        val t3 = System.currentTimeMillis()
//        Logger.d("kotlin 反射耗时:${t3 - t2}")
        return generateSign(timestamp, element)
    }

    /**
     * 根据请求参数生成签名
     *
     *  1、按照请求参数名的字母升序排列（参数名ASCII码从小到大排序）非空请求参数（包含公共请求头参数(appId、platformId、token、timstamp、version、machineNo)），
     *  使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA；
     *          注：参数名ASCII码从小到大排序（字典序）；
     *              如果参数的值为空不参与签名；
     *              参数名区分大小写
     *  2、在stringA最后拼接上secretKey得到字符串stringSignTemp（stringA+"&key=xxx"）；
     *  3、对stringSignTemp进行MD5运算，并将得到的字符串所有字符转换为大写，得到sign值，放到请求头
     *
     */
    fun generateSign(timestamp: Long, argument: Map<String, String>?): String {
        val element = LinkedHashMap<String, String>()
        publicHeader.filter {
            try {
                it.value.isNotEmpty()
            } catch (e: Exception) {
                false
            }
        }.forEach { element[it.key] = it.value }
        //添加时间戳
        element[HttpConstant.Header.TIME_STAMP] = timestamp.toString()
        argument?.filter {
            try {
                it.value.isNotEmpty()
            } catch (e: Exception) {
                false
            }
        }?.forEach { element[it.key] = it.value }
        var original = ""
        element.toSortedMap().forEach { original = "$original${it.key}=${it.value}&" }
        //秘钥
        original =
            "$original$KEY=${if (BuildConfig.DEBUG) SECRET_KEY_DEBUG else SECRET_KEY_RELEASE}"
        return MD5Util.encrypt(original).toUpperCase()
    }


    /**
     * 转换RequestBody
     */
    fun convertRequestBody(requestBody: String): RequestBody {
        return RequestBody.create(
            okhttp3.MediaType.parse("application/json; charset=utf-8"),
            requestBody
        )
    }

    /**
     * 转换RequestBody
     */
    fun convertRequestBody(jEntity: BaseEntity): RequestBody {
        return convertRequestBody(GsonUtil.mGson.toJson(jEntity))
    }
}