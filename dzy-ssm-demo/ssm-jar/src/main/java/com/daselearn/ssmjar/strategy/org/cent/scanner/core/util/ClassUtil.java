package com.daselearn.ssmjar.strategy.org.cent.scanner.core.util;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.daselearn.ssmjar.strategy.org.cent.scanner.core.consts.CommonConsts.PACKAGE_SEPARATOR;
import static com.daselearn.ssmjar.strategy.org.cent.scanner.core.consts.CommonConsts.PATH_SEPARATOR;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/7.
 * @description: 类相关工具方法
 */
public enum ClassUtil {
    ;

    /**
     * 匿名内部类匹配表达式
     */
    public static final Pattern ANONYMOUS_INNER_CLASS_PATTERN = Pattern.compile("^[\\s\\S]*\\${1}\\d+\\.class$");

    /**
     * 包名转换为路径名
     *
     * @param packageName
     * @return
     */
    public static String package2Path(String packageName) {
        return packageName.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }

    /**
     * 路径名转换为包名
     *
     * @param pathName
     * @return
     */
    public static String path2Package(String pathName) {
        return pathName.replaceAll(PATH_SEPARATOR, PACKAGE_SEPARATOR);
    }

    /**
     * 根据文件后缀名判断是否Class文件
     *
     * @param fileName
     * @return
     */
    public static boolean isClass(String fileName) {
        if (EmptyUtil.isEmpty(fileName)) {
            return false;
        }
        return fileName.endsWith(".class");
    }

    /**
     * Class文件名转为类名（即去除后缀名）
     *
     * @param classFileName
     * @return
     */
    public static String classFile2SimpleClass(String classFileName) {
        return classFileName.replace(".class", "");
    }

    /**
     * 根据类名判断是否匿名内部类
     *
     * @param className
     * @return
     */
    public static boolean isAnonymousInnerClass(String className) {
        return ANONYMOUS_INNER_CLASS_PATTERN.matcher(className).matches();
    }

    /**
     * 去除重复包
     *
     * @param packages
     * @return
     */
    public static List<String> distinctPackages(List<String> packages) {
        List<String> distinctPackages = new LinkedList<>();

        //去除完全重复包
        List<String> minPackages = packages.stream()
                .distinct()
                .collect(Collectors.toList());

        //去除父子包
        minPackages.forEach(srcPkg -> {
            long count = minPackages.stream()
                    .filter(comparePkg -> !comparePkg.equals(srcPkg) && srcPkg.startsWith(comparePkg))
                    .count();
            if (count == 0) {
                distinctPackages.add(srcPkg);
            }
        });


        return distinctPackages;
    }
}
