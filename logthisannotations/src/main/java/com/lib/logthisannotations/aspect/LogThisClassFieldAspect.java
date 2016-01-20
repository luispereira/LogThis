package com.lib.logthisannotations.aspect;

import com.lib.logthisannotations.internal.LogThis;
import com.lib.logthisannotations.internal.LoggerLevel;
import com.lib.logthisannotations.internal.Strings;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Field;

/**
 * @author lpereira on 19/01/2016.
 */
@Aspect
public class LogThisClassFieldAspect {


    @Around("set(* (@com.lib.logthisannotations.annotation.LogThisClassFields *) . *) && args(newVal) && target(t) ")
    public void aroundSetField(ProceedingJoinPoint jp, Object t, Object newVal) throws Throwable{
        Signature signature = jp.getSignature();
        String fieldName = signature.getName();
        Field field = t.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Object oldVal = field.get(t);
        LoggerLevel loggerLevel = t.getClass().getAnnotation(com.lib.logthisannotations.annotation.LogThisClassFields.class).value();
        StringBuilder builder = Strings.getStringFieldBuilder(fieldName, String.valueOf(oldVal), String.valueOf(newVal));

        LogThis.log(t.getClass().getName(), "Field " + builder.toString(), loggerLevel);
        //TODO compare oldVal with newVal and do sth.
        jp.proceed();
    }
}
