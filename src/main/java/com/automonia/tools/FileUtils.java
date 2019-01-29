package com.automonia.tools;

import com.automonia.tools.delegate.FileUtilsDelegate;

import java.io.File;

/**
 * @作者 温腾
 * @创建时间 2019年01月26日 19:12
 */
public enum FileUtils {

    singleton;

    private FileUtilsDelegate delegate;

    public void setDelegate(FileUtilsDelegate delegate) {
        this.delegate = delegate;
    }

    ////////////////////////////////////////////////////////////////////////////////

    public File getFile(String filePath) {
        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 获取文件在互联网上的访问url信息,
     * 全路径形式返回
     *
     * @return 资源的访问url信息
     */
    public String getFileAccessPath(String relativePath) {
        if (StringUtils.singleton.isEmpty(relativePath)) {
            return null;
        }

        // 以http，https开头的都是全访问url信息了
        if (relativePath.startsWith("http")) {
            return relativePath;
        }

        if (delegate != null) {
            String prefixFilePath = appendFilePath(delegate.getFileSysDomain(), delegate.getFileSysResourcesPath());
            return appendFilePath(prefixFilePath, relativePath);
        }

        LogUtils.singleton.error("获取文件的访问路径，却没有配置代理对象");
        return relativePath;
    }


    /**
     * 获取相对filePath对应的文件在服务器的本地硬盘上的绝对路径信息
     *
     * @param filePath 文件的相对路径
     * @return 文件的绝对路径信息
     */
    public String getFileLocalResourcesPath(String filePath) {
        if (StringUtils.singleton.isEmpty(filePath)) {
            return null;
        }

        if (delegate != null) {
            return appendFilePath(delegate.getFileSysLocalPath(), filePath);
        }

        LogUtils.singleton.error("获取文件的绝对路径信息，却没有配置代理对象");
        return filePath;
    }


    /**
     * 路径的拼接
     *
     * @param baseFilePath 上一级路径信息
     * @param filePath     下一级路径信息
     * @return 拼接后的路径信息
     */
    public String appendFilePath(String baseFilePath, String filePath) {
        // 都是空的时候，都返回空
        if (StringUtils.singleton.isEmptyAnd(baseFilePath, filePath)) {
            return null;
        }

        //优先以上一级路径信息为准
        if (StringUtils.singleton.isEmpty(filePath)) {
            return baseFilePath;
        }

        if (StringUtils.singleton.isEmpty(baseFilePath)) {
            return filePath;
        }

        // 清除baseFilePath结尾的/， 清除filePath开头的/
        if (baseFilePath.endsWith("/")) {
            baseFilePath = baseFilePath.substring(0, baseFilePath.length() - 1);
        }
        if (filePath.startsWith("/")) {
            filePath = filePath.substring(1);
        }

        return baseFilePath + "/" + filePath;
    }

}



















