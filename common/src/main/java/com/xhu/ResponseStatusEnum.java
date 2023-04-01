package com.xhu;

/**
 * 枚举反应状态和信息
 */
public enum ResponseStatusEnum {
    SUCCESS(0,true,"成功"),
    FAILED(1,false,"失败")

    ;

    private int i ;

    private boolean b;

    private String str;

    ResponseStatusEnum(int i, boolean b, String str) {
        this.i = i;
        this.b = b;
        this.str = str;
    }
    public static ResponseStatusEnum ok(){
        return ResponseStatusEnum.SUCCESS;
    }


}
