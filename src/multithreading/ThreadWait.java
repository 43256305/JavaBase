package multithreading;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: type_
 * @description: 线程等待与唤醒
 * @author: xjh
 * @create: 2020-03-21 14:49
 **/
//每个锁对象都有两个队列，就绪队列以及阻塞队列。就绪队列存储了将要获得锁的线程，阻塞队列存储了被阻塞的线程。
    //wait-> 阻塞队列   notify->就绪队列
public class ThreadWait {
    public static void main(String[] args) {
        Service s=new Service();
        //注意，要两个线程用一个锁，两个线程中一个wait，一个notify，wait和notify放在一个线程中没用
        new Thread(new TestWait(s)).start();
//        new Thread(new TestWait(s)).start();   //如果有两个线程正在wait()，而只有一个notify(),那么会随机选取一个
        new Thread(new TestNotify(s)).start();

        try{
            Thread.sleep(6000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("------------------------------------");
        //下面对一个ArrayList减两次，加一次，如果用if，会报错，而用while则不会
        //所以wait常常与while结合使用
        new ThreadSubtract(new Subtract(ValueObject.list)).start();
        new ThreadSubtract(new Subtract(ValueObject.list)).start();
        new ThreadAdd(new Add(ValueObject.list)).start();
    }
}

//wait/notify与锁有关，所以都是放在synchronized中
class Service{
    //用byte比object更好（前面的synchronized(this)和修饰方法是让相同的代码不同时执行，而这里是让不同的代码前后执行）
    //在java中object类都是带有一个内存锁的
    private byte[] lock=new byte[0];
    public void testWait(){
        synchronized (this.lock){
            System.out.println("begin wait() ThreadName="+ Thread.currentThread().getName());
            try{
                //释放获得的对象锁lock，并放弃CPU，进入等待队列。
                lock.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("end wait() ThreadName="+ Thread.currentThread().getName());
        }
    }

    public void testNotify(){
        //会唤醒ThreadA，但是此时它并不立即释放锁，接下来它睡眠了5秒钟(sleep()是不释放锁的，事实上sleep()也可以不在同步代码块中调用)，直到第28行
        // ，退出synchronized修饰的临界区时，才会把锁释放
        //运行notify后，并不是立刻让wait线程拥有锁，而是让wait线程进入就绪队列，等notify线程释放锁后（即退出syn区），就绪队列竞争锁
        synchronized (this.lock){
            System.out.println("begin notify() ThreadName="+ Thread.currentThread().getName() + " time="+ System.currentTimeMillis());
            lock.notify();
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("end notify() ThreadName="+ Thread.currentThread().getName() + " time="+ System.currentTimeMillis());
        }
    }
}

class TestWait implements Runnable{
    private Service s;
    public TestWait(Service s){
        this.s=s;
    }
    @Override
    public void run() {
        s.testWait();
    }
}

class TestNotify implements Runnable{
    private Service s;
    public TestNotify(Service s){
        this.s=s;
    }
    @Override
    public void run() {
        s.testNotify();
    }
}


//while与wait用法
class ValueObject {

    public static List list = new ArrayList();

}
class Subtract {

    List lock;

    public Subtract(List lock) {
        super();
        this.lock = lock;
    }

    public void subtract() {
        try {
            synchronized (lock) {
                //将这里的if改成while即可保证不出现越界异常!!!!
                //为什么while不会报错呢？if时，被唤醒后会直接从wait的下面执行，而用while时，会从wait下面执行，另外还会判断while中的条件，不符合则继续阻塞
                while (ValueObject.list.size() == 0) {
                    System.out.println("wait begin ThreadName="
                            + Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("wait   end ThreadName="
                            + Thread.currentThread().getName());
                }
                ValueObject.list.remove(0);
                System.out.println("list size=" + ValueObject.list.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadSubtract extends Thread {

    private Subtract r;

    public ThreadSubtract(Subtract r) {
        super();
        this.r = r;
    }

    @Override
    public void run() {
        r.subtract();
    }
}

class Add {
    private List lock;
    public Add(List lock) {
        super();
        this.lock = lock;
    }

    public void add() {
        synchronized (lock) {
            ValueObject.list.add("anyString");
            //唤醒其他所有线程
            lock.notifyAll();
        }
    }
}

class ThreadAdd extends Thread {

    private Add p;

    public ThreadAdd(Add p) {
        super();
        this.p = p;
    }

    @Override
    public void run() {
        p.add();
    }
}