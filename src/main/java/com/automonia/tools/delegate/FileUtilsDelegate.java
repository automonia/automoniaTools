package com.automonia.tools.delegate;

/**
 * 定义文件操作的参数设置
 *
 * @作者 温腾
 * @创建时间 2019年01月26日 19:13
 */
public interface FileUtilsDelegate {

    /**
     * 获取文件服务器的域名对象
     * ip， host， projecName
     *
     * @return 可以是当前服务器, 当前项目
     */
    public String getFileSysDomain();


    /**
     * 获取文件服务器访问的资源文件映射信息
     * ${domain}/${fileDomain}/filePath
     *
     * @return 访问资源文件映射信息
     */
    public String getFileSysResourcesPath();


    /**
     * 获取文件服务器映射到的服务器本地硬盘上的路径信息
     *
     * @return 本地存放资源文件的路径信息
     */
    public String getFileSysLocalPath();
}
