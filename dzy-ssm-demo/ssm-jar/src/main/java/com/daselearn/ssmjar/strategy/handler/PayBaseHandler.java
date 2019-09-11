package com.daselearn.ssmjar.strategy.handler;

/**
 * 支付策略类接口
 *
 * @author daniel
 * @date 2019/9/9
 */
public interface PayBaseHandler extends BaseHandler{

    Object execute(Object param);
    
}
