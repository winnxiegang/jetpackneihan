package com.neihanjetpack.base.http;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xiangyun_liu
 * @date <p>
 * Rx线程执行控制器
 */

public class RxTransformer {

    /**
     * Observable io->main
     */
    public static <T> ObservableTransformer<T, T> io2Main() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 点击事件指定时间内只能触发一次
     */
    public static <T> ObservableTransformer<T, T> clickThrottle() {
        return upstream ->
                upstream.throttleFirst(300, TimeUnit.MILLISECONDS);
    }
}
