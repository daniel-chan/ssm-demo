package com.daselearn.ssmjar.strategy.handler;

import com.daselearn.ssmjar.strategy.annotation.HandlerType;
import com.daselearn.ssmjar.strategy.enums.HandlerTypeEnum;
import org.springframework.stereotype.Component;

/**
 * 微信支付策略类
 *
 * @author daniel
 * @date 2019/9/9
 */
@Component
@HandlerType(HandlerTypeEnum.WECHAT_PAY)
public class WeChatPayHandler implements PayBaseHandler {

    @Override
    public Object execute(Object param) {
        System.out.println("微信支付");
        return null;
    }

}
