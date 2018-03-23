package com.springboot.common.util;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/13 - 下午3:16
 * Created by IntelliJ IDEA.
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.FIELD})//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented//说明该注解将被包含在javadoc中
public @interface NullIgnore {

}
