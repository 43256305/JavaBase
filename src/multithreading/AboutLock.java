package multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-05 22:01
 **/
//lock与syn的区别：1.lock在获取锁的过程可以被中断  2.lock可以尝试获取锁，如果锁被其他线程持有，则返回 false，不会使当前线程休眠
    //3.lock在尝试获取锁的时候，传入一个时间参数，如果在这个时间范围内，没有获得锁，那么就是终止请求
public class AboutLock {
    //Lock是一个接口  ReentrantLock()可重入锁
    private Lock lock=new ReentrantLock();
    //可重入锁：同一个线程，外层函数获得锁之后，内层函数仍有获得该锁的代码，但是不受影响。
    //比如方法a调用方法b，a要获取锁，b也要获取锁，此时b永远获得不了锁，则会永远阻塞，可重入锁可解决这个问题

    public static void main(String[] args){
        final AboutLock lockTest = new AboutLock();
        //线程1
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
//                lockTest.method(Thread.currentThread());
                try{
                    lockTest.method2(Thread.currentThread());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
//                lockTest.method(Thread.currentThread());
                try{
                    lockTest.method2(Thread.currentThread());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        t2.start();


    }

    //需要参与同步的方法
    private void method(Thread thread) {
        lock.lock();  //获得锁  如果已经被其他线程获取，则此线程等待，直到别的线程释放锁，此线程才继续执行lock()方法获得锁并继续执行
//        lock.tryLock();  //尝试获取锁，如果成功，返回true，否则false
//        tryLock(long time,TimeUnit unit)：在一定的时间内尝试获得锁，并且在这段时间直接可以被打断。成功，返回 true，否则，返回 false。
//        lock.lockInterruptibly()，线程A获取通过此语句获取锁后，线程B运行到这里时会等待，对线程B调用interrupt()能够中断线程B的等待过程。
        try {
            System.out.println("线程名" + thread.getName() + "获得了锁");
            thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程名" + thread.getName() + "释放了锁");
            lock.unlock();  //释放锁  lock不会自动释放锁，所以一定要手动释放锁
        }
    }

    public void method1(Thread thread){
        //可以看到，t1和t2只有一个能获得锁，因为tryLock只会运行一次，没有获得锁就返回false，然后方法结束
        while (lock.tryLock()){
            try {
                System.out.println("线程名" + thread.getName() + "获得了锁");
                thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("线程名" + thread.getName() + "释放了锁");
                lock.unlock();  //释放锁  lock不会自动释放锁，所以一定要手动释放锁
                return;
            }
        }
        System.out.println(thread.getName()+"没有获得锁");
    }

    public void method2(Thread thread) throws Exception{
        //如果把时间变为1，则只有一个能获得锁，把时间变大就两个都可以获得锁
        while (lock.tryLock(1, TimeUnit.SECONDS)){
            try {
                System.out.println("线程名" + thread.getName() + "获得了锁");
                thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("线程名" + thread.getName() + "释放了锁");
                lock.unlock();  //释放锁  lock不会自动释放锁，所以一定要手动释放锁
                return;
            }
        }
        System.out.println(thread.getName()+"没有获得锁");
    }

}
