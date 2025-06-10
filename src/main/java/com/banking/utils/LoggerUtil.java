package com.banking.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for managing Log4j2 loggers with enhanced features:
 * - Thread-safe logger instances
 * - Automatic caller class detection
 * - Contextual logging (MDC)
 * - Performance optimization
 */
public class LoggerUtil {
    private static final Map<String, Logger> loggerCache = new HashMap<>();
    private static final ThreadLocal<Logger> threadLocalLogger = new ThreadLocal<>();

    private LoggerUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Gets logger for the calling class with thread-safe caching
     */
    public static Logger getLogger() {
        // Check if thread already has a logger
        if (threadLocalLogger.get() == null) {
            String className = getCallerClassName();
            threadLocalLogger.set(createLogger(className));
        }
        return threadLocalLogger.get();
    }

    /**
     * Creates or retrieves cached logger instance
     */
    private static synchronized Logger createLogger(String className) {
        return loggerCache.computeIfAbsent(className, LogManager::getLogger);
    }

    /**
     * Dynamically detects the calling class name
     */
    private static String getCallerClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // Element 0: getStackTrace()
        // Element 1: getCallerClassName()
        // Element 2: getLogger()
        // Element 3: Calling class
        return stackTrace[3].getClassName();
    }

    /**
     * Adds contextual information to logs (MDC)
     */
    public static void putContext(String key, String value) {
        ThreadContext.put(key, value);
    }

    /**
     * Removes contextual information
     */
    public static void removeContext(String key) {
        ThreadContext.remove(key);
    }

    /**
     * Clears all contextual information
     */
    public static void clearContext() {
        ThreadContext.clearAll();
    }

    /**
     * Logs entry to a method with parameters
     */
    public static void logMethodEntry(Object... params) {
        String methodName = getCallerMethodName();
        getLogger().debug("Entering {} with params: {}", methodName, params);
    }

    /**
     * Logs exit from a method with return value
     */
    public static void logMethodExit(Object returnValue) {
        String methodName = getCallerMethodName();
        getLogger().debug("Exiting {} with return: {}", methodName, returnValue);
    }

    /**
     * Gets the calling method name
     */
    private static String getCallerMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return stackTrace[3].getMethodName();
    }
}