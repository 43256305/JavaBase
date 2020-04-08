package multithreading;

/**
 * @program: type_
 * @description: 线程的两种实现方法
 * @author: xjh
 * @create: 2020-03-21 13:15
 **/
public class ThreadImp {
    public static void main(String[] args) {
        Hello A=new Hello("A");
        Hello B=new Hello("B");
        //注意，启动线程是start()方法，因为启动线程需要系统支持，start启动线程会调用操作系统native函数来支持线程运行。
        A.start();
        B.start();
        Hello2 hello2=new Hello2();
        //注意，runable中没有start方法，要启动线程需要传入Thread，后面的A为线程名字（Thread类也实现了Runable接口）
        //一个实现了Runable接口的类的对象可以传入多个Thread里面，所以可以共享一个对象的资源
        Thread t1=new Thread(hello2,"hello2");
        t1.start();
    }
}

//继承Thread，重写run方法
class Hello extends Thread{
    private String s;
    public Hello(String s){
        this.s=s;
    }

    public void run(){
        System.out.println(this.getName());
        for (int i=0;i<5;i++){
            System.out.println(s+" is running "+i);
        }
    }
}

//实现Runable接口（此接口只有一个run方法）
//两种区别：1.Thread获取线程名字只需要用this.getName()，而Runable接口获取名字要先获取本线程
//2.Runable接口可以继承别的类  3.实现Runnable。可以多个线程共享一个Runnable target对象的资源，所以非常适合多个相同线程来处理同一份资源
//推荐Runable接口
class Hello2 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("线程的另一种实现方法！");
    }
}
