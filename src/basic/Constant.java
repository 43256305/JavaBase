package basic;

public class Constant {
//与局部变量不同，成员变量声明final类型时就必须赋值
//    final private int A;  报错
    private int i=0;
    public static void main(String[] args) {
//基本类型时数值不能改变，对象时，引用不能改变，数值可以改变
        //常量大写
        final double PI=3.14;
//        PI=11;  报错
        final float F;
        F=3.33f;
//        F=4.44f;  报错

        final Constant constant=new Constant();
        Constant constant1=new Constant();
//        constant=constant1;  报错
        constant.i=100;
        System.out.println(constant.i);  //:100

        final String s="123";
//        s="23";  报错

//常量在编译时就已经确定（而变量d的访问就要在运行时通过连接来访问，运行时才能确定），也就是说在编译时，下面的b不是变量b，而是hello
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + "2";
        String e = d + "2";

        System.out.println(a == c);  //true
//字符串相加产生了新的String对象，所以为false
        System.out.println(a == e);  //false
        System.out.println(d==b);  //true

        //按照类加载机制，不是应该先初始化在执行吗（输出B A）？显示确是只输出了A，因为常量在编译的时候就确定了，相当于class文件中，常量已经变成了具体的值
        //所以这里根本没有加载Test2类
        //这种情形只使适用于直接赋值，static final String s=new String()不成立，还是要加载
        System.out.println(Test2.a);
    }
}
//final修饰类表示类不能被继承，方法表示方法不能被子类重写
final class Person{}
//class Man extends Person{}  报错

class Test2{
    public static final String a="A";

    static {
        System.out.print("B");
    }
}
