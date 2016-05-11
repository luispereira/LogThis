package com.lib.logthisannotations.aspect;

import android.text.TextUtils;

import com.lib.logthisannotations.annotation.LogThis;
import com.lib.logthisannotations.internal.LoggerLevel;
import com.lib.logthisannotations.internal.Strings;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.FieldSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author lpereira on 04/01/2016.
 */
@Aspect
public class LogAspect {
    private static final String VOID_TYPE = "void";

    private static final String POINTCUT_METHOD =
            "execution(@com.lib.logthisannotations.annotation.LogThis * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.lib.logthisannotations.annotation.LogThis *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedDebugTrace() {
    }

    @Around("methodAnnotatedWithDebugTrace()")
    public Object weaveAroundLogThisJoinPoint(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Object returnValue;
        Logger logger = Logger.getInstance();
        if(logger != null && logger.isLoggerEnabled()) {
            MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
            String className = methodSignature.getDeclaringType().getSimpleName();
            String methodName = methodSignature.getName();
            String[] parameterNames = methodSignature.getParameterNames();
            Object[] parameterValues = thisJoinPoint.getArgs();

            Method method = methodSignature.getMethod();
            LogThis annotation = method.getAnnotation(com.lib.logthisannotations.annotation.LogThis.class);
            LoggerLevel loggerLevel = annotation.logger();

            Type type = method.getGenericReturnType();

            StringBuilder builder = Strings.getStringMethodBuilder(methodName, parameterNames, parameterValues);

            logger.getLoggerInstance().log(className, "Method " + builder.toString() + " called", loggerLevel, annotation.write());

            returnValue = thisJoinPoint.proceed();

            if (!TextUtils.equals(type.toString(), VOID_TYPE)) {
                logger.getLoggerInstance().log(className, "Method " + builder.toString() + " returned logger ⇢ [" + returnValue + "]", loggerLevel, annotation.write());
            }
        }else{
            returnValue = thisJoinPoint.proceed();
        }
        return returnValue;
    }

    @Before("constructorAnnotatedDebugTrace()")
    public void weaveBeforeLogThisJoinPoint(JoinPoint joinPoint) {
        Logger logger = Logger.getInstance();
        if(logger != null && logger.isLoggerEnabled()) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String className = methodSignature.getDeclaringType().getSimpleName();
            String methodName = methodSignature.getName();
            String[] parameterNames = methodSignature.getParameterNames();
            Object[] parameterValues = joinPoint.getArgs();

            Method method = methodSignature.getMethod();
            LogThis annotation = method.getAnnotation(com.lib.logthisannotations.annotation.LogThis.class);
            LoggerLevel loggerLevel = annotation.logger();

            StringBuilder builder = Strings.getStringMethodBuilder(methodName, parameterNames, parameterValues);

            logger.getLoggerInstance().log(className, "Method " + builder.toString() + " called", loggerLevel, annotation.write());
        }
    }

    @Around("set(@com.lib.logthisannotations.annotation.LogThis * *) && args(newVal) && target(t)")
    public void weaveBeforeFieldLogThisJoinPoint(ProceedingJoinPoint joinPoint, Object t, Object newVal) throws Throwable {
        Logger logger = Logger.getInstance();
        if(logger != null && logger.isLoggerEnabled()) {
            FieldSignature fs = (FieldSignature) joinPoint.getSignature();
            String fieldName = fs.getName();
            Field field = fs.getField();
            field.setAccessible(true);
            Object oldVal = field.get(t);
            LogThis annotation = field.getAnnotation(com.lib.logthisannotations.annotation.LogThis.class);
            LoggerLevel loggerLevel = annotation.logger();
            StringBuilder builder = Strings.getStringFieldBuilder(fieldName, String.valueOf(oldVal), String.valueOf(newVal));

            logger.getLoggerInstance().log(t.getClass().getName(), "Field " + builder.toString(), loggerLevel, annotation.write());
            joinPoint.proceed();
        }
    }
}

