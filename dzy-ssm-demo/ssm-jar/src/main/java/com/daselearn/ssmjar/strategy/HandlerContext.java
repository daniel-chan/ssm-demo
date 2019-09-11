package com.daselearn.ssmjar.strategy;

import com.daselearn.ssmjar.strategy.enums.HandlerTypeEnum;
import com.daselearn.ssmjar.strategy.handler.BaseHandler;
import com.daselearn.ssmjar.util.SpringCtxHelper;

import java.util.Map;
import java.util.Objects;

/**
 * 策略上下文
 *
 * @author daniel
 * @date 2019/9/9
 */
public class HandlerContext {
    private static Map<HandlerTypeEnum,Class> handlerMap;

    public HandlerContext(Map<HandlerTypeEnum, Class> handlerMap) {
        HandlerContext.handlerMap = handlerMap;
    }

    public static BaseHandler getInstance(HandlerTypeEnum type){
        Class clazz = handlerMap.get(type);
        if(Objects.isNull(clazz)){
            throw new IllegalArgumentException("not found one initialized by handlerType");
        }
        return (BaseHandler) SpringCtxHelper.getCtx().getBean(clazz);
    }
}
