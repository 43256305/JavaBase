package multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-05 21:05
 **/
public class Volatile {
    //https://www.cnblogs.com/dolphin0520/p/3920373.html
    //volatile:1.保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
    //2.禁止进行指令重排序。
    //volatile只能修饰成员变量
    private volatile static boolean stop=false;
    //如果没用volatile，那么在一个线程中，stop1改变了，只是在工作内存中改变了，还没写入主存中，那么别的线程就不能立刻收到改变的信息
    private static boolean stop1=false;

    //volatile实现原理，缓存一致协议，线程每次写入时，会导致其他线程的缓存失效，从而重新加载新写入的变量

    private volatile static int count=0;
    private volatile static AtomicInteger atoCount=new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (!stop){
                    i++;
                    System.out.println("volatile:"+i);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                stop=true;   //volatile保证了可见性，stop改变可以立刻让上一个线程知道(原理：1.修改立刻写入主存，2.使其他线程中的stop缓存无效
                //3.由于stop无效，上一个线程会立刻去主存中读取stop真实值 )
            }
        }).start();


        for (int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    count++;
                    atoCount.incrementAndGet();  //自增1
                    //AtomicInteger使用了cas（即循环（do while循环）检验current与预期值是否相同，不相同则重新取值自增）
                    //ABA问题：加入valueoffset，每次还要比较版本是否相同，相同则更新，不同重新取值
                }
            }).start();
        }

        while (atoCount.get()!=1000){ }   //确保1000个线程执行完毕
        //volatile不保证原子性（可以在run中放入syn关键字，或者变量类型变为AtomicInteger）
        System.out.println(count);  //：998  可以看到不是1000
        System.out.println(atoCount);

        //一定的有序性：1.当程序执行到volatile变量的读操作或者写操作时，在其前面的操作的更改肯定全部已经进行，且结果已经对后面的操作可见；在其后面
        //的操作肯定还没有进行 2.在进行指令优化时，不能将在对volatile变量访问的语句放在其后面执行，也不能把volatile变量后面的语句放到其前面执行。


        //volatile应用场景：
        //1.状态标记量，如前面的stop
        //2.单例模式
    }
}

class Singleton{
    //为什么不到这里就new呢？等用的时候再new，可以减少内存浪费
    private volatile static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if(instance==null) {  //此if确保在instance不会null时，线程不会一直去获取锁
            synchronized (Singleton.class) {
                if(instance==null)  //为什么这里还需要一个if呢？假如线程1进入第一个if，而线程2先获得了锁，显然，线程1阻塞直到线程2执行完毕
                    //然后线程1获得锁，然后（instance==null)不成立（volatile可见性），就确保线程1不会新new一个对象，如果没有第二个if
                    //因为if没有获得锁立刻检测循环条件是否符合的机制（while有，参见ThreadWait），线程1就会又新建一个对象
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
