package designPattern.singleton;

/**
 * @program: type_
 * @description: 懒汉式变种，属于懒汉式中最好的写法，保证了：延迟加载和线程安全（推荐使用）
 * @author: xjh
 * @create: 2020-04-18 17:36
 **/
public class Singleton2 {

    private static Singleton2 instance=null;

    private Singleton2() {};

    public static Singleton2 getInstance(){
        if (instance == null) {  //双检锁
            synchronized (Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
