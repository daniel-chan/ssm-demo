package com.daselearn.ssmjar.strategy.handler;

import com.daselearn.ssmjar.strategy.annotation.HandlerType;
import com.daselearn.ssmjar.strategy.enums.HandlerTypeEnum;
import org.springframework.stereotype.Component;

/**
 * 支付宝订单策略类
 * @author daniel
 * @date 2019/9/9
 */
@Component
@HandlerType(HandlerTypeEnum.ALIPAY_ORDER)
public class AlipayOrderHandler implements OrderBaseHandler {

    @Override
    public Object execute(Object param) {
        System.out.println("支付宝订单");
        return null;
    }
}
