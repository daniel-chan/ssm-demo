package com.daselearn.ssmjar.strategy.handler;

/**
 * 订单策略类接口
 *
 * @author daniel
 * @date 2019/9/9
 */
public interface OrderBaseHandler extends BaseHandler{

    Object execute(Object param);

}
