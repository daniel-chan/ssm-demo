package com.daselearn.ssmjar.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring上下文
 *
 * @author daniel
 * @date 2019/8/21
 */
public class SpringCtxHelper {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getCtx() {
        if (applicationContext==null) {
            applicationContext = new ClassPathXmlApplicationContext(new String[]{
                    "/spring/spring-context.xml"
            });
        }
        return applicationContext;
    }

}
