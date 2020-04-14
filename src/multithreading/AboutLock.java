package multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-05 22:01
 **/
//lock与syn的用法区别：1.lock在获取锁的过程可以被中断  2.lock可以尝试获取锁，如果锁被其他线程持有，则返回 false，不会使当前线程休眠
    //3.lock在尝试获取锁的时候，传入一个时间参数，如果在这个时间范围内，没有获得锁，那么就是终止请求  4.lock需要手动释放锁
public class AboutLock {
    //Lock是一个接口  ReentrantLock()可重入锁
    private Lock lock=new ReentrantLock();
    //可重入锁（syn也是可重入锁）：同一个线程，外层函数获得锁之后，内层函数仍有获得该锁的代码，但是不受影响，但是获得几次就要释放几次。
    //比如方法a调用方法b，a要获取锁，b也要获取锁，此时b永远获得不了锁，则会永远阻塞，可重入锁可解决这个问题

    //syn和synchronized的主要区别是
    //ReentrantLock是在java层面上实现的，基于AQS(AbstractQueuedSynchronized)框架下使用自旋CAS机制实现，另外ReentrantLock扩展了很多额外的
    // 同步方法，比如公平锁，非公平锁，可中断锁，非阻塞锁。而synchronized是基于JVM层面实现的，使用计数监视锁来做同步。

    public static void main(String[] args) throws Exception{
//        lock();
        condition();
    }

    public static void lock(){
        final AboutLock lockTest = new AboutLock();
        //线程1
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
//                try{
//                    lockTest.method2(Thread.currentThread());
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
//                try{
//                    lockTest.method2(Thread.currentThread());
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
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
            thread.sleep(2000);
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

    //syn中的wait()和notify()实现了等待通知模型，lock同样可以用condition实现，且有更好的灵活性
    //await()和signal()之前，必须要先lock()获得锁，使用完毕在finally中unlock()释放锁
    //1.一个Lock里面可以创建多个Condition实例，实现多路通知
    //2.notify()方法进行通知时，被通知的线程时Java虚拟机随机选择的，但是ReentrantLock结合Condition可以实现有选择性地通知，这是非常重要的
    public static void condition(){
        final ThreadDomain td = new ThreadDomain();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                td.awaitMethod1();
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                td.awaitMethod2();
            }
        });
        t1.start();
        t2.start();
        td.signalMethod();
    }

    static class ThreadDomain {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private Condition condition1 = lock.newCondition();

        //下面三个方法，每次只能有一个方法获得锁
        public void awaitMethod1() {
            try {
                lock.lock();
                System.out.println("method1获得锁");
                condition.await();  //释放上面lock获得的锁，阻塞，等待condition的signal()方法
                System.out.println("method1等待结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void awaitMethod2() {
            try {
                lock.lock(); //method1调用await()释放锁后method2才能获得锁
                System.out.println("method2获得锁");
                condition1.await();
                System.out.println("method2等待结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        //signal运行lock.unlock()后才能继续运行前面的两个方法，而如果没有signal，则此方法释放锁后前面的方法会继续阻塞
        public void signalMethod() {
            try {
                Thread.sleep(6000);
                lock.lock();
                System.out.println("signal获得锁");
                condition.signal();
                System.out.println("使method1退出阻塞队列");
                condition1.signal();    //这里没有让method2进入就绪队列，则lock.unlock()后，method2还会继续阻塞（还在阻塞队列中）
                System.out.println("使method2退出阻塞队列");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("signal:lock.unlock()");
            }
        }


        //阻塞队列实现（现实中我们不用写这个类，只需要用ArrayBlockingQueue就行）
        //下面为什么要用await()而不用wait()呢？因为condition可以通知指定线程，所以消费者只会通知生产者，生产者只会通知消费者，而用wait()方法，
        //系统的通知完全是随机的，如果消费者线程通知了另一个消费者，则死锁发生了（用notifyAll()也会造成通知所有线程，但只有一个线程能正常运行，造成
        // 了没必要的线程切换）
        static class BoundedBuffer {
            private final Lock lock = new ReentrantLock();
            private final Condition notFull = lock.newCondition();
            private final Condition notEmpty = lock.newCondition();

            final Object[] items = new Object[10];
            int putptr, takeptr, count;   //三个变量初始化都为0

            public void put(Object x) throws InterruptedException {
                lock.lock();
                try {
                    while (count == items.length)
                        notFull.await();  //释放锁
                    items[putptr] = x;
                    //如果put指针指向最后一格，让put指针指向0（因为这是循环数组实现）
                    if (++putptr == items.length) putptr = 0;
                    ++count;
                    notEmpty.signal();
                } finally {
                    lock.unlock();
                }
            }

            public Object take() throws InterruptedException {
                lock.lock();
                try {
                    while (count == 0)
                        notEmpty.await();
                    Object x = items[takeptr];
                    //如果take指针指向最后一个，让take指针指向0
                    if (++takeptr == items.length) takeptr = 0;
                    --count;
                    notFull.signal();
                    return x;
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
