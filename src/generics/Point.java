package generics;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @program: type_
 * @description: 泛型类使用
 * @author: xjh
 * @create: 2020-03-12 20:09
 **/

//多个范型时用'，'隔开
public class Point<T> {
    //在类中使用，T的使用和其他类型语法相同，只是在类声明的时候需要加上<>符号
    //虚拟机编译的时候后就会摸出范型，以普通类替换
    private T t;


    public Point(T t) {
        this.t = t;
    }

    //返回范型
    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

//报错：satic与范型类结合使用时，必须单独声明出来，如下面范型方法
//    public static void desc(T t){
//
//    }
}

interface Info<T>{
    T getVar();
    void setVar(T t);
}

//非泛型类实现范型接口  可以看到下面都没有出先符号：<>
class InfoIm implements Info<String>{
    private String var;

    @Override
    public String getVar() {
        return var;
    }

    @Override
    public void setVar(String s) {
        this.var=s;
    }
}

//实现接口
class InfoImGene<T,K> implements Info<T>{
    private T var;
    private K var1;

    @Override
    public T getVar() {
        return var;
    }

    @Override
    public void setVar(T s) {
        this.var=s;
    }

    public K getVar1() {
        return var1;
    }

    public void setVar1(K var1) {
        this.var1 = var1;
    }
}

class StaticGene{
    //如和在方法上单独使用范类：只需要在返回的地方写<>就行，函数内还是可以把他当成普通类型
    public static <T> void desc(T a){
        System.out.println("静态范型函数:"+a.toString());
    }

    public <T> T desc1(T t){
        //范型在单独的方法中传入与传出
        return t;
    }

    //确保T实现了Comparable接口用extends，可以限制让T实现多个接口，接口之间用&隔开。找出数组中最小的数
    public static <T extends Comparable & Serializable> T getMin(T[] t){
        if (t==null||t.length==0) return null;
        T smallest=t[0];
        for (int i=1;i<t.length;i++){
            if (t[i].compareTo(smallest)<0){
                smallest=t[i];
            }
        }
        return smallest;
    }

}

//Class<T>的使用:Class类的传递，比如反射判断是什么类时，比如String的类：class java.lang.String
class ClassT<T>{
    public void descClass(Class<T> object){
        System.out.println(object);
        try{
            //用获得类生成一个实例
            System.out.println(object.newInstance());
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    public T[] getGeneArray(T ... args){
        return args;
    }

}

class Food<T>{
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

class Fruit<T>{
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

class Apple extends Fruit{

}
class Babana extends Fruit{

}


class Test{
    public static void main(String[] args) {
        Point<String> p=new Point<String>("xie");
        //不用类型转换
        System.out.println(p.getT());  //：xie

        //可以不用<String>，直接调用(不建议这么做)，下面的desc1方法也是
        StaticGene.<String>desc("<T>");
        StaticGene.desc("另一种调用方法：<T>");

        StaticGene staticGene=new StaticGene();
        System.out.println(staticGene.<String>desc1("范型方法调用"));

        ClassT<String> classT=new ClassT();
        classT.descClass(String.class);  //：class java.lang.String
        System.out.println(Arrays.toString(classT.getGeneArray("12","23")));  //:[12,23]

        //String实现了Comparable接口
        String[] s={"1","2","3","0"};
        System.out.println(StaticGene.getMin(s));  //：0

        //?上界通配符的使用一般与extends或者super结合使用，且不用在范类的声明中，只在使用中使用
        ClassT<? super Comparable> classT1=new ClassT<>();
        System.out.println(Arrays.toString(classT1.getGeneArray("12","23")));  //:[12,23]
        //可以看到，如果不用?，直接用String，那么传入的只能是String，而如果是? super Comparable，则只要实现了Comparable接口就可以传入
        System.out.println(Arrays.toString(classT1.getGeneArray(1,2,3,4)));  //:[1,2,3,4]

        //super可以set，get到的东西只能存放到objec里面   super可以存放Fruit和Food的对象(根据多态也可以存放Fruit的子类)
        //extends不能set，只能get（编译器不知道他是Apple还是Banana）   extends可以存放Fruit和所有继承了Fruit的对象
        Fruit<? super Fruit> fruit=new Fruit<>();
        fruit.setT(new Apple());
//        Apple apple=fruit.getT();  //报错，只能存放到Object中
        Object apple1=fruit.getT();
        System.out.println(fruit.getT().getClass());  //:class generics.Apple
        fruit.setT(new Babana());

        Fruit<? extends Food> fruit1=new Fruit<>();
//        fruit1.setT(new Apple());  //报错，不能set

//        频繁往外读取内容的，适合用上界Extends。
//        经常往里插入的，适合用下界Super。

    }
}
