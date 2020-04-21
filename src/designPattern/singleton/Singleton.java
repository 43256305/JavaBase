package designPattern.singleton;

/**
 * @program: type_
 * @description: 饿汉式：可用   单例模式中构造器全部为private（因为全局只有一个对象，所以不可能让用户生产对象）
 * @author: xjh
 * @create: 2020-04-18 17:29
 **/
public class Singleton {

    //在静态初始化器中创建单例实例，这段代码保证了线程安全，也可以把new语句放到static代码块中
    private static Singleton uniqueInstance = new Singleton();

    //Singleton类只有一个构造方法并且是被private修饰的，所以用户无法通过new方法创建该对象实例
    private Singleton(){}

    public static Singleton getInstance(){
        return uniqueInstance;
    }
}
