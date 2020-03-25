package basic;

public class OverlAndOver {
    public static void main(String[] args) {
        Dog dog=new Hound();
        dog.bark();

        Dog1 dog1=new Dog1();
        dog1.bark();
        dog1.bark(3);
        dog1.bark("This is overloadind");
    }
}

class Dog{
    public void bark(){
        System.out.println("woof");
    }
}
class Hound extends Dog{
//overriding  重写，方法重新写过，旧方法不用了（在多态中用子孙重写的方法）
    public void bark(){
        System.out.println("bowl");
    }
}

class Dog1{
//overloading  重载，如构造器重载，重新加载，这几个方法都可以用，只是加载的方法不同
    public void bark(){
        System.out.println("woof");
    }
    public void bark(int num){
        for (int i=0;i<num;i++){
            System.out.println("woof");
        }
    }
//重载中参数，返回值都可以不同
    public String bark(String s){
        System.out.println(s);
        return s;
    }
}
