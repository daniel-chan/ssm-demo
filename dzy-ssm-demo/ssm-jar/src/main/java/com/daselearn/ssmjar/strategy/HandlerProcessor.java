package com.daselearn.ssmjar.strategy;

import com.daselearn.ssmjar.strategy.annotation.HandlerType;
import com.daselearn.ssmjar.strategy.enums.HandlerTypeEnum;
import com.daselearn.ssmjar.strategy.org.cent.scanner.core.callback.ScannerCallback;
import com.daselearn.ssmjar.strategy.org.cent.scanner.core.scanner.ClassScanner;
import com.daselearn.ssmjar.strategy.org.cent.scanner.core.scanner.impl.DefaultClassScanner;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *  BeanFactory后处理器
 *
 * @author daniel
 * @date 2019/9/9
 */
@Component
public class HandlerProcessor implements BeanFactoryPostProcessor {


    private static final String SCAN_PACKAGE = "com.daselearn.ssmjar.strategy" ;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        Map handlerMap = Maps.newHashMapWithExpectedSize(3);

        ClassScanner classScanner = new DefaultClassScanner();

        classScanner.scanAndCallbackByAnno(Arrays.asList(SCAN_PACKAGE),HandlerType.class, new ScannerCallback() {
            @Override
            public void callback(List<Class> list) {
                list.forEach(clazz -> {
                    HandlerType annotation = (HandlerType)clazz.getAnnotation(HandlerType.class);
                    HandlerTypeEnum value = annotation.value();
                    if(Objects.isNull(value)){
                        throw new IllegalArgumentException("HandlerType enmu value expect not null but null...");
                    }
                    handlerMap.put(value,clazz);
                });
            }
        });
        HandlerContext context = new HandlerContext(handlerMap);
        configurableListableBeanFactory.registerResolvableDependency(HandlerContext.class,context);
    }
}
