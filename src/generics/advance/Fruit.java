package generics.advance;

import java.util.Arrays;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-01 14:24
 **/
public class Fruit<T>{
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


class Test{

    public static void main(String[] args) {
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
        System.out.println(fruit.getT().getClass());  //:class generics.advance.Apple
        fruit.setT(new Babana());

        Fruit<? extends Food> fruit1=new Fruit<>();
//        fruit1.setT(new Apple());  //报错，不能set

//        频繁往外读取内容的，适合用上界Extends。
//        经常往里插入的，适合用下界Super。
    }

}
