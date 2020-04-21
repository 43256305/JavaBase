package designPattern.singleton;

/**
 * @program: type_
 * @description:懒汉式：所谓 “ 懒汉式” 就是说单例实例在第一次被使用时构建，而不是在JVM在加载这个类时就马上创建此唯一的单例实例。
 * 不能使用
 * @author: xjh
 * @create: 2020-04-18 17:32
 **/
public class Singleton1 {

    private static Singleton1 uniqueInstance;

    private Singleton1 (){
    }

    //没有加入synchronized关键字的版本是线程不安全的，加入syn后效率又太低（只有一个线程能获得锁）
    public static Singleton1 getInstance() {
        //判断当前单例是否已经存在，若存在则返回，不存在则再建立单例
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton1();
        }
        return uniqueInstance;
    }
}
