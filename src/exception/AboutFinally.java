package exception;

/**
 * @program: type_
 * @description: finally的用法
 * @author: xjh
 * @create: 2020-03-25 21:31
 **/
public class AboutFinally {
    public static void main(String[] args) {
        System.out.println(fun1());  //执行顺序：4
    }

    public static String fun1(){
        try{
            Thread.sleep(100);
            System.out.println("fun1 is executed");  //执行顺序：1
            //可以看出，一直到fun2执行完毕，finally才会执行，所以，try和catch块中的所有代码执行完毕后，finally才会执行
            //这里先执行fun2()在执行return，执行return就是完全推出fun1(),退出fun1（）之前会执行finally
            return fun2();
        }catch (InterruptedException e){
            e.printStackTrace();
            //finally语句不论抛不抛异常，都会执行，常常用于关闭资源
        }finally {
            System.out.println("finally statement is executed");  //执行顺序：3
        }
        return null;
    }

    public static String fun2(){
        System.out.println("fun2 is executed");  //执行顺序：2
        return "D";
    }
}
