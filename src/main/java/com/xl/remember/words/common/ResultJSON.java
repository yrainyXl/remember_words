package com.xl.remember.words.common;

public class ResultJSON {

    public int status;

    public String msg;

    public Object data;


    public ResultJSON() {
    }

    public ResultJSON(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static ResultJSON build(int status, String msg, Object data){
        return new ResultJSON(status,msg,data);
    }

    public static ResultJSON ok(){
        return build(0,"成功",null);
    }

    public static ResultJSON ok(Integer status){
        return build(status,"成功",null);
    }


    public static ResultJSON ok(Object data){
        return build(0,"成功",data);
    }

    public static ResultJSON ok(String msg,Object data){
        return build(0,msg,data);
    }



    public static ResultJSON error(){
        return build(1,"操作失败",null);
    }

    public static ResultJSON error(String msg){
        return build(1,msg,null);
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
