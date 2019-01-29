package com.automonia.tools.delegate;

/**
 * 日志记录工作的代理对象
 *
 * @作者 温腾
 * @创建时间 2019年01月26日 16:59
 */
public interface LogUtilsDelegate {

    /**
     * info级别的日志信息打印
     *
     * @param message 日志信息
     */
    void info(String message);


    /**
     * error级别的日志信息打印
     *
     * @param message 日志信息
     */
    void error(String message);


    /**
     * debug级别的日志信息打印
     *
     * @param message 日志信息
     */
    void debug(String message);


    /**
     * 打印异常信息
     *
     * @param exception 异常对象
     */
    void exception(Exception exception);
}
