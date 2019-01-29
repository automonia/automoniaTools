package com.automonia.tools;

import com.automonia.tools.delegate.LogUtilsDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者 温腾
 * @创建时间 2019年01月26日 15:04
 */
public enum LogUtils {

    singleton;

    /*
    存放已创建过的Logger对象，key值使用类的simpleName
     */
    private Map<String, Logger> warehouse = new HashMap<String, Logger>();

    /*
    日志打印的代理对象
     */
    private LogUtilsDelegate delegate = null;

    public void setDelegate(LogUtilsDelegate delegate) {
        this.delegate = delegate;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    /**
     * error级别输出日志信息
     */
    public void error(String message) {
        getCallerLogger().error(message);

        if (delegate != null) {
            delegate.error(message);
        }
    }

    /**
     * info级别输出日志信息
     */
    public void info(String message) {
        getCallerLogger().info(message);

        if (delegate != null) {
            delegate.info(message);
        }
    }

    /**
     * debug级别输出日志信息
     */
    public void debug(String message) {
        getCallerLogger().debug(message);

        if (delegate != null) {
            delegate.debug(message);
        }
    }

    public void exception(Exception exception) {
        if (exception == null) {
            return;
        }
        exception.printStackTrace();

        if (delegate != null) {
            delegate.exception(exception);
        }
    }

    public Logger getCallerLogger(String simpleName) {
        if (StringUtils.singleton.isEmpty(simpleName)) {
            return null;
        }

        if (warehouse.containsKey(simpleName)) {
            warehouse.get(simpleName);
        }

        Logger logger = LoggerFactory.getLogger(simpleName);
        warehouse.put(simpleName, logger);

        return logger;
    }

    /**
     * 获取调用者所在对象的Logger对象
     *
     * @return Logger对象
     */
    public Logger getCallerLogger() {
        return getCallerLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    /**
     * 获取调用者所在对象的logger对象
     */
    public Logger getCallerLogger(Class classObject) {
        if (classObject == null) {
            return null;
        }
        return getCallerLogger(classObject.getSimpleName());
    }

}
