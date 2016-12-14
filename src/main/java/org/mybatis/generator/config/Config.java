package org.mybatis.generator.config;

import java.io.Serializable;

/**
 * Created by pengpeng on 16-9-23.
 * mybatis 配置文件
 */

public class Config implements Serializable {


    /**
     * 添加get set 方法
     */
    public static boolean getSetMethodFlag;

    /**
     * 字段注释
     */
    public static boolean fieldCommentsFlag;

    /**
     * 字段注解
     */
    public static boolean fieldAnnotationFlag;

    /**
     * get set注释
     */
    public static boolean getSetCommentsFlag;

    /**
     * 引入lombok
     */
    public static boolean lombokImport;

    /**
     * 引入java注解
     */
    public static boolean javaImport;

    /**
     * 表明注释
     */
    public static boolean tableAnnotation;






}
