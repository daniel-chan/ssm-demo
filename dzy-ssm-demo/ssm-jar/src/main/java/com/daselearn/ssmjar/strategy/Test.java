package com.daselearn.ssmjar.strategy;

import com.daselearn.ssmjar.strategy.enums.HandlerTypeEnum;
import com.daselearn.ssmjar.strategy.handler.OrderBaseHandler;
import com.daselearn.ssmjar.strategy.handler.PayBaseHandler;
import com.daselearn.ssmjar.util.SpringCtxHelper;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 下载ClassScanner代码
 * https://gitee.com/centy/class-scanner
 * <repositories>
 <repository>
 <id>cent-repo</id>
 <url>https://gitee.com/centy/maven/raw/master</url>
 </repository>
 </repositories>
 *<dependency>
 <groupId>org.cent</groupId>
 <artifactId>scanner-core</artifactId>
 <version>1.0.1-SNAPSHOT</version>
 </dependency>
 * @author daniel
 * @date 2019/9/9
 */
public class Test {
    public static void main(String[] args) {
        SpringCtxHelper.getCtx();
        PayBaseHandler instance = (PayBaseHandler) HandlerContext.getInstance(HandlerTypeEnum.ALIPAY_PAY);
        instance.execute("");
        PayBaseHandler instance1 = (PayBaseHandler) HandlerContext.getInstance(HandlerTypeEnum.WECHAT_PAY);
        instance1.execute("");
        OrderBaseHandler instance2 = (OrderBaseHandler) HandlerContext.getInstance(HandlerTypeEnum.ALIPAY_ORDER);
        instance2.execute("");
        OrderBaseHandler instance3 = (OrderBaseHandler) HandlerContext.getInstance(HandlerTypeEnum.WECHAT_ORDER);
        instance3.execute("");


    }
}
