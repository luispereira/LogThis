package com.lib.logthisannotations.aspect;

import com.lib.logthisannotations.annotation.LogThisClassFields;
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
        Logger logger = Logger.getInstance();
        if(logger != null) {
            Signature signature = jp.getSignature();
            String fieldName = signature.getName();
            Field field = t.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object oldVal = field.get(t);
            LogThisClassFields annotation = t.getClass().getAnnotation(LogThisClassFields.class);
            LoggerLevel loggerLevel = annotation.logger();
            StringBuilder builder = Strings.getStringFieldBuilder(fieldName, String.valueOf(oldVal), String.valueOf(newVal));

            logger.getLoggerInstance().log(t.getClass().getName(), "ClassField " + builder.toString(), loggerLevel, annotation.write());
            jp.proceed();
        }
    }
}
