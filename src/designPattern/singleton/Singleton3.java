package designPattern.singleton;

/**
 * @program: type_
 * @description: 懒汉式：内部类方法，推荐使用
 * @author: xjh
 * @create: 2020-04-18 17:39
 **/
public class Singleton3 {

    private Singleton3 (){}

    //静态内部类
    private static class SingletonHolder {
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    //只有调用此方法，才会加载内部类
    public static final Singleton3 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
