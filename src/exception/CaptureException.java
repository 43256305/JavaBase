package exception;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: type_
 * @description: 捕获异常
 * @author: xjh
 * @create: 2020-03-25 20:58
 **/
public class CaptureException {
    public static void main(String[] args) throws IOException{
        //如果程序try块中某行代码抛出一个异常，则try中下面的代码都不会执行，会直接执行catch中的代码
        //如果程序没有抛出任何异常，则catch块中的不会执行
        try{
            Thread.sleep(100);
            //PrintWriter printWriter=new PrintWriter("");   //加上这一句后，下面catch块不会执行，因为catch中没有它的异常类型，所以捕捉不到
            throw new CusException("抛出异常");
//            System.out.println("try block");   //可以看到，抛出异常后，这里就不会执行了
        }catch (InterruptedException e){
            //这里没有选择抛出异常，而是直接打印
            System.out.println("抛出InterruptedException异常");
            e.printStackTrace();
        }catch (CusException e){  //抛出多个异常
            System.out.println("抛出自定义异常");   //可以看到，虽然try抛出了异常，但还是执行了这一行（所以try块中抛出的异常可以是自己抛的，
            // 也可以是try块中的代码调用的函数抛出的异常，反正抛出异常后就会执行响应的catch块中的语句，然后推出函数）
            throw e;
        }catch (Exception e){
            System.out.println("执行多个catch");  //上面的抛出异常后，这里就不会执行了
        }
        //可以看到，抛出异常后这里的代码就没有执行了，抛出异常相当于return
        System.out.println("抛出异常后");
    }
}
