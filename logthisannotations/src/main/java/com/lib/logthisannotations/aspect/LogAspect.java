package com.lib.logthisannotations.aspect;

import com.lib.logthisannotations.internal.LogThis;
import com.lib.logthisannotations.internal.Strings;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author lpereira on 04/01/2016.
 */
@Aspect
public class LogAspect {
    private static final String POINTCUT_METHOD =
            "execution(@com.lib.logthisannotations.annotation.LogThis * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.lib.logthisannotations.annotation.LogThis *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedDebugTrace() {}

    @Before("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
    public void weaveJoinPoint(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        StringBuilder builder = new StringBuilder("\u21E2 ");
        builder.append(methodName).append('(');
        for (int i = 0; i < parameterValues.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(parameterNames[i]).append('=');
            builder.append(Strings.toString(parameterValues[i]));
        }
        builder.append(')');

        LogThis.log(className, "Method " + builder.toString() + " called");
    }
}


