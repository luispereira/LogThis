package com.lib.logthisannotations.annotation;

import com.lib.logthisannotations.internal.LoggerLevel;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lpereira on 04/01/2016.
 */

/**
 * This annotation will log a method and its parameters whenever its called
 * Additionally it will log the returning logger if the method does not have a generic return type of void
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
public @interface LogThis {
    /**
     * Log level to show the log {@link  LoggerLevel}
     * @return which log level
     */
    LoggerLevel logger() default LoggerLevel.D;

    /**
     * If a log should be stored. This only works if the {@link com.lib.logthisannotations.aspect.Logger#storeLogs(File)} is called on initialization
     * @return if should be stored or not
     */
    boolean write() default true;
}
