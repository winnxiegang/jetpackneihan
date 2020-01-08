package com.neihanjetpack.base.http

import com.jiutong.base.http.HttpMyErrorException
import com.neihanjetpack.base.entity.BaseResult
import com.neihanjetpack.base.utils.LogUtils
import com.neihanjetpack.base.utils.ToastUtil
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException


/**
 * BaseObserver
 *
 * 接口开始请求时调用onHttpStart方法，
 * 请求成功（code正确）调用onHttpSuccess方法，
 * 请求失败（HttpException或者code 错误）时调用onHttpError方法（当code返回失败时不解析data,直接抛出HttpErrorException异常，详见 {@link com.jiutong.base.http.GsonResponseBodyConverter}）
 * 请求结束调用onHttpEnd 方法
 *
 *
 * @author: xiangyun_liu
 *
 * @date: 2018/8/20 14:29
 */
abstract class BaseObserver<T : BaseResult>() : DisposableObserver<T>() {

    /**
     * 请求开始
     *
     * 发起请求的时候调用，该方法一定会调用
     */
    open fun onHttpStart() {

    }

    /**
     * 请求成功
     *
     * http请求成功，并且服务端code返回正确的时候调用
     */
    abstract fun onHttpSuccess(t: T)

    /**
     * http失败或者code 错误的时候调用
     *
     * 默认打印Toast 错误信息
     */
    open fun onHttpError(e: HttpMyErrorException) {
    }

    /**
     * 请求结束
     *
     * 请求结束的时候调用，无论请求结果如何，该方法一定会调用
     */
    open fun onHttpEnd() {
    }

    /**
     * 订阅
     */
    override fun onStart() {
        super.onStart()

        try {
            onHttpStart()
        } catch (e: Exception) {
            showCodeException(e)
        }
    }

    /**
     * 成功
     */
    override fun onNext(t: T) {
        if (t.isSuccess()) {
            try {
                onHttpSuccess(t)
            } catch (e: Exception) {
                showCodeException(e)
            }
        }
        try {
            onHttpEnd()
        } catch (e: Exception) {
            showCodeException(e)
        }
    }

    /**
     * 请求失败
     * HttpException 时处理HttpException异常
     * HttpErrorException 时处理code 错误
     * 其他exception 不处理
     */
    override fun onError(e: Throwable) {
        try {
            //处理Http错误，如果是Http错误我们把Throwable向下转型成HttpException就可以获取Response信息
            if (e is HttpException) {
                try {
                    handleHttpExceptionCode(e)
                } catch (e: Exception) {
                    LogUtils.d(e.toString())
                }

                //处理后台返回的异常
            } else if (e is HttpMyErrorException) {
                handleExceptionCode(e)
                onHttpError(e)


                //处理代码exception
            } else {
                showCodeException(e)
                onHttpError(
                    HttpMyErrorException(
                        HttpMyErrorException.ERROR_SYSTEM,
                        if (e.message != null) e.message!! else e.toString()
                    )
                )
            }
        } catch (e: Exception) {
            showCodeException(e)
        }

        try {
            onHttpEnd()
        } catch (e: Exception) {
            showCodeException(e)
        }
    }

    /**
     * 结束
     */
    override fun onComplete() {
        try {
            onHttpEnd()
        } catch (e: Exception) {
            showCodeException(e)
        }
    }

    /**
     * 单独处理自定义异常
     */
    private fun handleExceptionCode(exceptionMy: HttpMyErrorException) {
        LogUtils.d(exceptionMy.toString())
        if (exceptionMy != null) {
            if (exitCodeHandle(exceptionMy.code)) {
                ToastUtil.toast("账号异常登录，重新登录")
            } else if (exceptionMy.code == 401 || exceptionMy.code == -1) {
                ToastUtil.toast(exceptionMy.message)
            } else {
                ToastUtil.toast(exceptionMy.message)
            }
            onHttpError(HttpMyErrorException(exceptionMy.code, exceptionMy.message))
        } else {
            ToastUtil.toast("系统异常")
            onHttpError(HttpMyErrorException(HttpMyErrorException.ERROR_SYSTEM, "系统异常"))
        }
    }

    /**
     * 单独处理http异常（非 200） 错误
     */
    private fun handleHttpExceptionCode(exception: HttpException) {
        LogUtils.d("HttpException" + exception.code().toString())
        when (exception.code()) {
            500, 501, 502, 503, 504 -> {
                ToastUtil.toast("服务器异常，稍后重试")
            }
            401 -> {
                ToastUtil.toast("无效的token,请重新登录")
            }
            404 -> {
                ToastUtil.toast("接口地址错误或者无效")
            }

        }

    }

    /**
     * 退出code处理
     */
    private fun exitCodeHandle(code: Int): Boolean {
        when (code) {
            //用户被封禁，无效的token,回到登录界面
            ResponseCodeConstant.ERROR_BANNED_CODE,
            ResponseCodeConstant.ERROR_NO_LOGIN_IN,
            ResponseCodeConstant.ERROR_LACK_TOKEN_CODE,
            ResponseCodeConstant.ERROR_INVALID_TOKEN_CODE -> {
                return true
            }
        }
        return false
    }

    /**
     * 显示代码导致的程序异常信息
     */
    private fun showCodeException(e: Throwable) {
        e.printStackTrace()
    }
}