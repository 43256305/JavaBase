package object_class.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-03-10 21:27
 **/
public class GetObject {

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        //如何用forName?  首先要用异常，其次里面的值是字符串（包括包名）
        //工厂模式中，字符串一般是从xml文件获取，这样再加上多态，可以只修改xml文件，不修改代码就达到扩展程序的目的
        //https://blog.csdn.net/Kaiwii/article/details/7405761
        try {
            //forName只是获得了类，newinstance才是实例化  注意要注明从src下面的完整路径
            //用此方法只能调用类的无参构造方法，调用有参构造参考下面
            Test t1=(Test)Class.forName("object_class.reflection.Test").newInstance();  //工厂模式常用
            t1.print();
        } catch (Exception e) {
            // TODO: handle exception
        }
        //思考：既然newInstance()才是生成新的对象，那加载数据库驱动的时候，为什么只需要forName()呢？
        //因为java里面任何class都要装载在虚拟机上才能运行，而静态代码是和class绑定的，class装载成功就表示执行了你的静态代码了，
        //而在数据库驱动中所有驱动都有根据加载的类生成对象的静态代码块（就像单例模式），自然不需要我们手动newInstance了

        //获取对象所属的类
        Test t2=new Test();
        System.out.println(t2.getClass());  //：class object_class.reflection.Test
        //基本数据类型只能用 .class  引用类型只能用.getClass()
        System.out.println(int.class);  //：int
        //只有实例才能用getClass()获取类目，类可以用.class
        System.out.println(Test.class);  //:class object_class.reflection.Test


        //判断是否是某个类的实例     在这里不能用t1，因为t1在try块中
        System.out.println(Test.class.isInstance(t2));  //:true
        System.out.println(t2.getClass().isInstance(t2));  //true

        //创建对象  2种方法
        Class t3=object_class.reflection.Test.class;   //这里也可以用实例.getClass()来获取类
        try {  //注意要抛出异常
            Constructor<?> constructor=t3.getConstructor(String.class);  //String.class是选择Test类中参数为String类型的构造方法
            //获得构造器
            System.out.println(Modifier.toString(constructor.getModifiers())+" "+constructor.getName()+"("
                    +constructor.getParameters()[0]+")");  //：public object_class.reflection.Test(java.lang.String arg0)
            Test t=(Test)constructor.newInstance("用反射选择构造器来创建对象");
        } catch (Exception e) {
            // TODO: handle exception
        }

        //创建数组
        try {
            Class c=Class.forName("java.lang.String");
            Object s=java.lang.reflect.Array.newInstance(c, 5);
            java.lang.reflect.Array.set(s, 0, "反射创建数组");
            System.out.println(java.lang.reflect.Array.get(s, 0));
        } catch (Exception e) {
            // TODO: handle exception
        }


    }

}

class Test {

    public Test(String s){
        System.out.println(s);
    }

    public Test(){

    }

    public void print(){
        System.out.println("运行时创建了对象t1");
    }

}


