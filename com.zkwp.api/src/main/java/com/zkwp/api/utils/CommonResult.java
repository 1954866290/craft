package com.zkwp.api.utils;



/**
 * @auther zhangkun
 * @date 2020/3/22 9:37
 **/
public class CommonResult<T> {


    private String status;//返回状态码
    private String message;//返回信息
    private T data;//返回数据，将要给客户端的数据


    protected CommonResult(String status, String message, T data){
        this.status=status;
        this.message=message;
        this.data=data;
    }

    public CommonResult(){

    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

	/*以下为静态方法，作用是利用本类的构造方法将三个元素组装在一起
	生成一个本类对象并返回，根据不同的响应状态自行调用不同的方法，可以利用
	上面写好的枚举类型填充返回状态码和返回信息，也可以自行传入返回状态码和
	返回信息*/
    /**
     * 成功返回的结果
     * @param data  获取的数据
     */
    public static <T> CommonResult success(T data){
        return new CommonResult(ResultStatus.SUCCESS.getstatus(),ResultStatus.SUCCESS.getMessage(),data);
    }

    /**
     *
     * @param message   自定义提示信息
     * @param data  获取的数据
     */
    public static <T> CommonResult success(String message, T data){
        return new CommonResult(ResultStatus.SUCCESS.getstatus(),message,data);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult failed(){
        return new CommonResult(ResultStatus.FAILED.getstatus(),ResultStatus.FAILED.getMessage(),null);
    }

    /**
     * 失败返回结果
     * @param message   自定义信息
     */
    public static <T> CommonResult failed(String message){
        return new CommonResult(ResultStatus.FAILED.getstatus(),message,null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult validateFailed(){
        return new CommonResult(ResultStatus.VALIDATE_FAILED.getstatus(),ResultStatus.VALIDATE_FAILED.getMessage(),null);
    }

    /**
     * 参数验证失败返回结果
     * @param message   自定义提示信息
     */
    public static <T> CommonResult validateFailed(String message){
        return new CommonResult(ResultStatus.VALIDATE_FAILED.getstatus(),message,null);
    }

    /**
     * 未授权返回结果
     *
     */
    public static <T> CommonResult forbidden(T data){
        return new CommonResult(ResultStatus.FORBIDDEN.getstatus(),ResultStatus.FORBIDDEN.getMessage(),data);
    }

    /**
     * 未登录返回结果
     * @param data  返回的数据
     */
    public static <T> CommonResult unauthorized(T data){
        return new CommonResult(ResultStatus.UNAUTHORIZED.getstatus(),ResultStatus.UNAUTHORIZED.getMessage(),null);
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
