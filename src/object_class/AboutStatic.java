package object_class;

/**
 * @program:
 * @description: 关于static的知识点和用法
 * @author: xjh
 * @create: 2020-03-09 15:56
 **/
public class AboutStatic {
    //static属于类，一个类只有一个，不能用this操作，没有静态类
    private static int i=0;
    private int i1=0;

    //static代码块  可以看到，下面代码块中输出比下面main方法中的输出早
    //JVM加载类时会执行这些静态的代码块，如果有些代码必须在项目启动时就执行，就需要使用静态代码块（主动执行），参考单例模式
    static{
//            this.i1;  //同样不能访问非静态变量或方法
        System.out.println("this is static valuable:"+i);  //:0

    }

    //初始化块（主动执行）   当下面的main方法没有new这个类时，此代码块不会执行。 初始化块先于构造器执行。
    {
        System.out.println("this is init block");
    }

    //静态方法：需要在项目启动的时候就初始化但是不执行，在不创建对象的情况下,可以供其他程序调用,而在调用的时候才执行（被动执行）
    public static void main(String[] args) {
        //静态变量(方法中不能存在静态变量,报错)
        System.out.println(Math.PI);
//        static int i=0;

        //静态方法
        System.out.println(Math.pow(2,3));

        //AboutStatic aboutStatic=new AboutStatic();

//        StaticTest s=new StaticTest();

        //访问静态方法或静态变量也会导致类被初始化
        StaticTest.getCount();
        System.out.println(StaticTest.s);
        System.out.println(StaticTest.STRING);
        System.out.println(StaticTest.i);

        StaticInter.print();  //：kkkkk


        //静态代码块不能放置在方法中
//        static{
////            this.i1;  //同样不能访问非静态变量或方法
//
//        }

        //先初始化，再使用，static代码块在初始化时就输出了，而变量i要到使用时才输出
        System.out.println(InitAndUse.i);  //: B  A
        try{
            Class.forName("object_class.InitAndUse");  //可以看到，上面的B已经输出了，这里就没有输出（因为只类加载一次），如果上面注释，这里会输出B
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getStatic(){
//        return i1;  报错，静态方法只能访问静态变量
        return  i;
    }

    //https://blog.csdn.net/eff666/article/details/52203406  java类加载顺序
    //编译->加载->链接（验证+准备+解析）->初始化（使用前的准备  new（或反射,调用static方法等）的时候初始化）->使用->卸载  下面的所有加载步骤都是在初始化时加载的
    //（静态变量、静态初始化块）–>（变量、初始化块）–> 构造器；如果有父类，则顺序是：父类static方法 –> 子类static方法 –> 父类构造方法- -> 子类构造方法
}

class StaticTest{
    //当上面的main访问此类的静态变量或者非法时，下面的静态代码块也会执行
    public static String s="1";

    //注意：当上面的main访问此静态常量时，下面的静态代码块不会执行，因为常量在编译时就被全部替换成了实际的数值，就不会初始化此类
    public static final int i=0;

    //如果final修饰的类变量不能在编译时确定下来，则必须等到运行时才能确定该类变量的值，如果通过该类来访问它的类变量，则会导致该类被初始化。
    static final String STRING=""+System.currentTimeMillis();

    static{
        //当上面的main方法new了此类时，下面这一句才会执行，可知，static块也是在初始化时执行的
        System.out.println("static test");
    }

    public static void getCount(){
        System.out.println("2");;
    }

    //初始化时机：
    //new，静态变量方法（如果静态变量为常量不会加载类，参考Constant的Test2类），反射，初始化子类，jvm表明的启动类
}

interface StaticInter{
//    static{  //报错，static代码块不能用在接口中
//
//    }

    //static方法可以写在接口中，不过必须要实现
    public static void print(){
        System.out.println("kkkkk");
    };

    public void print1();

}

class InitAndUse{
    public static String i="A";
    static{
        System.out.println("B");
    }
}
