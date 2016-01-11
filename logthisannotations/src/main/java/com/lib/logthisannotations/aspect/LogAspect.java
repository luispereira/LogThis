package com.lib.logthisannotations.aspect;

import android.text.TextUtils;

import com.lib.logthisannotations.Logger;
import com.lib.logthisannotations.internal.LogThis;
import com.lib.logthisannotations.internal.Strings;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

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
        if(Logger.getInstance() != null && Logger.getInstance().isLoggerEnabled()) {
            MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
            String className = methodSignature.getDeclaringType().getSimpleName();
            String methodName = methodSignature.getName();
            String[] parameterNames = methodSignature.getParameterNames();
            Object[] parameterValues = thisJoinPoint.getArgs();

            Type type = methodSignature.getMethod().getGenericReturnType();

            StringBuilder builder = Strings.getStringBuilder(methodName, parameterNames, parameterValues);

            LogThis.log(className, "Method " + builder.toString() + " called");

            returnValue = thisJoinPoint.proceed();

            if (!TextUtils.equals(type.toString(), VOID_TYPE)) {
                LogThis.log(className, "Method " + builder.toString() + " returned value ⇢ [" + returnValue + "]");
            }
        }else{
            returnValue = thisJoinPoint.proceed();
        }
        return returnValue;
    }

    @Before("constructorAnnotatedDebugTrace()")
    public void weaveBeforeLogThisJoinPoint(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        StringBuilder builder = Strings.getStringBuilder(methodName, parameterNames, parameterValues);

        LogThis.log(className, "Method " + builder.toString() + " called");
    }
}

