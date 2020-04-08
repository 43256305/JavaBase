package generics.base;

import java.io.Serializable;

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

//实现接口，InfoimGene上也要写T
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

        //String实现了Comparable接口
        String[] s={"1","2","3","0"};
        System.out.println(StaticGene.getMin(s));  //：0

        //可以看到下面报错，因为Point没有继承Comparable接口
//        Point[] points=new Point[2];
//        StaticGene.getMin(points);

    }
}
