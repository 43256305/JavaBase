package object_class.extend;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-03-23 20:21
 **/
//父类static方法 –> 子类static方法 –> 父类构造方法（实例化的时候才会调用构造方法，只访问static变量还不会实例化）- -> 子类构造方法
//子类初始化，父类必定初始化
public class LoadOrder {
    // 程序入口
    public static void main(String[] args) {
        //明明是用子类调用的父类变量，为什么子类没有初始化呢？
        //很显然，子类初始化的条件（new，静态变量方法等）并不满足，这里调用的是父类的静态变量，而不是子类，所以子类没有初始化
        System.out.println(SubClass.p_StaticField);  //输出父类静态变量和初始化块
//        System.out.println(SubClass.s_StaticField);  //这里就对子类初始化了，同时也对父类初始化了
        //为什么调用子类独有的static变量，父类也会初始化呢？因为java中子类本就可以随意访问父类的public域，所以虽然我们写的subclass中没有super或者调用
        //父类变量或方法的语句，但还是会加载父类，子类不用父类，不代表用户不用父类啊，反正，子类初始化，父类必定初始化
        System.out.println("*************in main***************");
        new SubClass();
        System.out.println("*************second subClass***************");
        //这里没有加载静态变量和初始化块了，因为静态一个类只有一个，所以只加载一次
        new SubClass();
    }
}

class Parent {
    // 静态变量
    public static String p_StaticField = "父类--静态变量";
    // 变量(其实这用对象更好能体同这一点，如专门写一个类的实例)
    //如果这个变量放在初始化块的后面，是会报错的，因为你根本没有被初始化
    public String p_Field = "父类--变量";
    // 静态初始化块
    static {
        System.out.println(p_StaticField);
        System.out.println("父类--静态初始化块");
    }
    // 初始化块
    {
        System.out.println(p_Field);
        System.out.println("父类--初始化块");
    }
    // 构造器
    public Parent() {
        System.out.println("父类--构造器");
    }
}
class SubClass extends Parent {
    // 静态变量
    public static String s_StaticField = "子类--静态变量";
    // 变量
    public String s_Field = "子类--变量";
    // 静态初始化块
    static {
        System.out.println(s_StaticField);
        System.out.println("子类--静态初始化块");
    }
    // 初始化块
    {
        System.out.println(s_Field);
        System.out.println("子类--初始化块");
    }
    // 构造器
    public SubClass() {
        //super();
        System.out.println("子类--构造器");
    }

}