package com.daselearn.ssmjar.strategy.org.cent.scanner.core.util;

import java.net.URL;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/8.
 * @description: URL相关工具方法
 */
public enum URLUtil {
    ;

    /**
     * url中文件类型前缀
     */
    public static final String JAR_URL_FILE_PREFIX = "file:";

    /**
     * 从url中获取jar文件真实路径
     * <p>
     * jar文件url示例如下：<p>
     * jar:file:/Users/cent/.gradle/caches/modules-2/files-2.1/org.projectlombok/lombok/1.18.4/7103ab519b1cdbb0642ad4eaf1db209d905d0f96/lombok-1.18.4.jar!/org
     *
     * @param url
     * @return
     */
    public static String getJarPathFormUrl(URL url) {
        String file = url.getFile();
        String jarRealPath = file.substring(0, file.lastIndexOf("!"))
                .replaceFirst(JAR_URL_FILE_PREFIX, "");
        return jarRealPath;
    }
}
