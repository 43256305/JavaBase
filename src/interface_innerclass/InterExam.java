package interface_innerclass;

//当接口没有public修饰时，只在包中可见
//接口扩展用extends，实现此接口的类必须要实现这两个接口了
public interface InterExam extends Comparable{
//    private int i1;  //报错   接口中不能存在私有
    //接口中可以有变量，但会被自动被设置成public static final(所以必须被初始化)
    int i1=1;

    //接口中的所有方法都默认为public
    void desc();
//    private void desc1();   //:报错   不能为私有

    //static方法可以存在与接口中，但必须实现
    static void desc3(){
        System.out.println("正在使用接口静态方法");
    };

    //接口中的default：实现此接口的类可以直接用默认实现，也可以重写
    default void desc4(){
        System.out.println("正在使用default关键字");
    }

}

class Employee implements InterExam{

    public static void main(String[] args) {
        InterExam.desc3();
        System.out.println("接口中的静态变量："+InterExam.i1);
    }

    @Override
    public void desc() {
        System.out.println("正在使用接口方法");
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
