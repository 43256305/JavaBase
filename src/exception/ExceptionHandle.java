package exception;

import java.io.IOException;

/**
 * @program: type_
 * @description: 异常分类
 * @author: xjh
 * @create: 2020-03-25 20:39
 **/
public class ExceptionHandle {
    //在方法中声明可能抛出的异常用 throws，用于去告诉调用方法的人，这个方法可能抛出异常
    //自己抛出异常尽量写IOExcepiton的异常，因为Error的异常我们解决不了，而RuntimeException的异常我们应尽量避免
    public static void main(String[] args) throws IOException {
        //所有异常类都是由Throwable继承而来，他又分为Error和Exception
        //Error错误描述了系统的错误
        //exception又分为IOException和RuntimeException(程序错误，一定是程序员的问题)
        Throwable throwable=new Throwable();

        //抛出异常，上面是声明这个方法可能抛出异常，这里是抛出异常
        int i=1;
        if (i==1){
            throw new IOException();
        }else {
            throw new CusException();  //抛出自定义异常类
        }
    }
}
