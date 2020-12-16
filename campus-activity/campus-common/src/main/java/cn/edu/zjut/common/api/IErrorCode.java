package cn.edu.zjut.common.api;

/**
 * 封装API的错误码
 * Created by iris on 2020/12/10.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
