package com.xhu;

public class Result {
    /**
     * 业务状态
     */
    private Integer status;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 响应数据
     */
    private Object data;

    public static Result ok(){
        return new Result(ResponseStatusEnum.SUCCESS);
    }

    public static Result error(){
        return new Result(ResponseStatusEnum.FAILED);
    }

    public Result(Object data){

    }
    public Result(ResponseStatusEnum responseStatusEnum,Object data){
        this.status=responseStatusEnum.getI();
        this.msg=responseStatusEnum.getStr();
        this.success=responseStatusEnum.isB();
        this.data=data;
    }
    public Result(ResponseStatusEnum responseStatusEnum){
        this.status=responseStatusEnum.getI();
        this.msg=responseStatusEnum.getStr();
        this.success=responseStatusEnum.isB();
    }

    public Result(){}

    public Result(Integer status, String msg, Boolean success, Object data) {
        this.status = status;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
