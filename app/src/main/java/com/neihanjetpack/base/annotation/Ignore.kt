package com.jiutong.base.annotation

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
/**
 * 遍历类时被此注解标记的字段不参与签名生成
 *
 * @author: xiangyun_liu
 * @date: 2018/12/18 16:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Ignore
