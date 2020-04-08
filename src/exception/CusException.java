package exception;

import java.io.IOException;

/**
 * @program: type_
 * @description: 自定义异常类
 * @author: xjh
 * @create: 2020-03-25 20:54
 **/
//自定义异常类都是继承Exception或者Exception的子类
public class CusException extends IOException {
    public CusException(){}

    public CusException(String message){   //message为new CusException时传入的值，打印报错信息时会自动打印message
        super(message);
    }
}
