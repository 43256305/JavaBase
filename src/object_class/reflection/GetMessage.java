package object_class.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-03-10 21:23
 **/
public class GetMessage {

    public static void main(String[] args) {  //反射机制的作用，不用更改代码重新编译，只需更改配置文件
        //在运行时起作用,这种 动态的获取信息 以及 动态调用对象的方法 的功能称为 java 的反射机制,
        //其使得我们可以在程序运行时加载、探索以及使用编译期间完全未知的 .class 文件
        // TODO Auto-generated method stub
        GetMessage t=new GetMessage();
		t.printFieldMess();
        t.printMethodsMess();

    }

    //获取变量信息
    private static void printFieldMess(){
        Class mclass=SonClass.class;
        System.out.println("类的名称："+mclass);
        Field[] fields=mclass.getFields();  //获取所有public类型的变量信息（包括父类的）
//		Field[] fields1=mclass.getDeclaredFields();  //获取所有变量信息，除了继承的   也可以在方法中加入属性名字来获取指定属性
        for (Field f:fields){
            //print the modifier type and name of the value
            System.out.print(Modifier.toString(f.getModifiers())+" "+f.getType().getName()+" ");
            System.out.println(f.getName());
        }
    }

    //获取方法信息(方法的权限，返回值，名称，参数，异常)
    private static void printMethodsMess(){
        Class mclass=SonClass.class;
        System.out.println("类的名称："+mclass.getName());  //：object_class.reflection.SonClass
        Method[] methods=mclass.getMethods();  //获取所有public方法
        Method[] methods1=mclass.getDeclaredMethods();  //获取所有方法，除了继承的
        for (Method m:methods1){
            //private：2  public:1   这是用数字表示修饰符
            System.out.println(m.getModifiers());
            //Modifier.toString()把数字转换为private等
            System.out.print(Modifier.toString(m.getModifiers())+" "+m.getReturnType().getName()
                    +" "+m.getName()+"(");
            Parameter[] p=m.getParameters();
//			Class[] c=m.getParameterTypes();  //可以用这个获得参数类型，也可以用下面的方法
            for (Parameter pa:p){
                System.out.print(pa.getType().getName()+" "+pa.getName());
            }
            //获取异常
            Class[] exceptionType=m.getExceptionTypes();
            if (exceptionType.length==0){
                System.out.println(")");
            }else{
                System.out.println(")");
                for (Class c:exceptionType){
                    System.out.println(c.getName());
                }
            }

        }
    }


}

class FatherClass {

    public String mFatherName;
    public int mFatherAge;

    public void printFatherMsg() {
    }

}

class SonClass extends FatherClass {
    private String mSonName;
    protected int mSonAge;
    public String mSonBirthday;

    public void printSonMsg() { //print the message of SonClass
        System.out.println("Son Msg - name : " + mSonName + "; age : " + mSonAge);
    }

    private void setSonName(String name) {
        mSonName = name;
    }

    private void setSonAge(int age) {
        mSonAge = age;
    }

    private int getSonAge() {
        return mSonAge;
    }

    private String getSonName() {
        return mSonName;
    }

}



