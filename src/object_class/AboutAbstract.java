package object_class;

/**
 * @program:
 * @description: 抽象类
 * @author: xjh
 * @create: 2020-03-10 13:39
 **/
//包含一个或多个抽象方法的类必须声明为抽象类，没有抽象方法也可以声明为抽象类
//抽象类只是在普通类的基础上可以增加抽象方法，此外抽象类不能实例化（理解：如果可以实例化，用户调用没有方法体的方法怎么办呢？）
public abstract class AboutAbstract {
//    public final abstract class AboutAbstract {  //抽象类不能与final结合，如果抽象类没有子类，又不能new，那存在的意义是什么呢？

    private String description;

    public abstract String getDescription();

    //抽象类也有构造方法
    public AboutAbstract(){

    }

//    public static abstract void print();  //报错，抽象方法不能是静态方法，静态方法属于类，而抽象方法必须要子类去实现

//    private abstract void setDes();   //报错：private与abstract不能结合

}

class AboutAbstract1 extends AboutAbstract{
    @Override
    public String getDescription() {
        return "抽象类子类";
    }

    public static void main(String[] args) {
        AboutAbstract1 ab=new AboutAbstract1();
        System.out.println(ab.getDescription());;
    }
}
