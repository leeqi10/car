package com.xhu.utils;

/**
 * 枚举反应状态和信息
 */
public enum ResponseStatusEnum {
    SUCCESS(200,true,"成功"),
    FAILED(1,false,"失败"),
    LOGIN(0,true,"登录成功"),
    REGISTER(0,true,"注册成功"),
    ACCEPT(0,true,"订单成功"),
    FAILEDLOGIN(1,false,"未登录")
    ;

    private int i ;

    private boolean b;

    private String str;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    ResponseStatusEnum(int i, boolean b, String str) {
        this.i = i;
        this.b = b;
        this.str = str;
    }
    public static ResponseStatusEnum ok(){
        return ResponseStatusEnum.SUCCESS;
    }


}
