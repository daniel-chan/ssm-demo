package com.daselearn.ssmjar.strategy.enums;


import lombok.Getter;

/**
 *  枚举类
 *
 * @author daniel
 * @date 2019/9/9
 */
@Getter
public enum HandlerTypeEnum {

     WECHAT_PAY(10001,"微信支付"),

     ALIPAY_PAY(10002,"支付宝支付"),

     WECHAT_ORDER(20001,"微信订单"),

     ALIPAY_ORDER(20002,"支付宝订单");


     HandlerTypeEnum(Integer code,String desc){
      this.code = code;
      this.desc = desc;
     }

     private Integer code;
     private String desc;

}
