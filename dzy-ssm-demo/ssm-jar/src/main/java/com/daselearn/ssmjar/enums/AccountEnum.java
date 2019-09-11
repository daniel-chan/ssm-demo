package com.daselearn.ssmjar.enums;

/**
 *  帐号枚举
 *
 * @author daniel
 * @date 2019/8/26
 */
public interface AccountEnum {

    enum UserStatus {
        NORMAL(0,"正常");

        private Integer code;
        private String msg;


        UserStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

    }

    enum UserSex {
        WOMAN(0, "女"), MAN(1, "男");
        private Integer code;
        private String msg;

        UserSex(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
    interface Delete {
        Integer YES = 1;
        Integer NO = 0;
    }
}
