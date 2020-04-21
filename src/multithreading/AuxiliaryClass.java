package multithreading;

import java.util.concurrent.*;

/**
 * @program: type_
 * @description: 辅助类CountDownLatch和CyclicBarrier
 * @author: xjh
 * @create: 2020-04-07 16:27
 **/

//区别：1.CountDownLatch一个或者多个线程，等待其他多个线程完成某件事情之后才能执行  CyclicBarrier多个线程互相等待，直到到达同一个同步点，再继续一起执行。
// 2.与 CyclicBarrier 不同的是，CountdownLatch 不能重新使用。
public class AuxiliaryClass {
    public static void main(String[] args) throws InterruptedException{
//        latch();
//        cyclicBarrier();
        testSemaphore();
    }

    //CountDownLatch使用场景：一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
    public static void latch(){
        final CountDownLatch latch=new CountDownLatch(2);
        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();  //将count值减1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();  //将count值减1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        //下面是等待线程和main线程等待上面两个线程执行countDown后执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    latch.await();
                    System.out.println("等待线程--------");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }).start();

        try {
            System.out.println("等待2个子线程执行完毕...");
            latch.await();   //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行   await可以设置时间，到时间count不为0继续执行
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void cyclicBarrier(){
//        cyclic1();
//        cyclic2();
        cyclic3();
    }

    public static void cyclic1(){
        int N = 4;
        //CyclicBarrier通过它可以实现让一组线程等待至某个状态之后再全部同时执行
        CyclicBarrier barrier  = new CyclicBarrier(N);
        for(int i=0;i<N;i++)
            new Writer(barrier).start();
    }

    public static void cyclic2(){
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N,new Runnable() {
            //额外操作，当所有线程都执行到了await()时，选出一个线程来执行此操作，执行完后，4个线程同时开始执行await()后面的代码
            @Override
            public void run() {
                System.out.println("额外操作：当前线程"+Thread.currentThread().getName());
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        for(int i=0;i<N;i++)
            new Writer(barrier).start();
    }

    public static void cyclic3(){
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);

        for(int i=0;i<N;i++) {
            new Writer(barrier).start();
        }

        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");
//从执行结果可以看出，在初次的4个线程越过barrier状态后，又可以用来进行新一轮的使用。而CountDownLatch无法进行重复使用
        for(int i=0;i<N;i++) {
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep((long)(10000*Math.random()));      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();  //所有线程等待   也可以设置时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }

    // 请求的数量
    private static final int threadCount = 550;
    /**
    * @Description: synchronized 和 ReentrantLock 都是一次只允许一个线程访问某个资源，Semaphore(信号量)可以指定多个线程同时访问某个资源
     *作用：每次都只让固定数量的线程执行
     *
     *执行 acquire 方法阻塞，直到有一个许可证可以获得然后拿走一个许可证；每个 release 方法增加一个许可证，这可能会释放一个阻塞的
     * acquire 方法。然而，其实并没有实际的许可证这个对象，Semaphore 只是维持了一个可获得许可证的数量。 Semaphore 经常用于限制获取某种
     * 资源的线程数量。
     *
     * Semaphore 有两种模式，公平模式和非公平模式。new Semaphore(20,true)
     *
     * Semaphore与CountDownLatch一样，也是共享锁的一种实现。它默认构造AQS的state为permits（如设置permits=20时，初始state=20）。
     * 当执行任务的线程数量超出permits,那么多余的线程将会被放入阻塞队列Park,并自旋判断state是否大于0。只有当state大于0（如果等于0，
     * 说明减了20次，每次成功acquire一次，state-1）的时候，阻塞的线程才能继续执行,此时先前执行任务的线程继续执行release方法，release
     * 方法使得state的变量会加1，那么自旋的线程便会判断成功。 如此，每次只有最多不超过permits数量的线程能自旋成功，便限制了执行任务线
     * 程的数量。
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/4/17
    */
    public static void testSemaphore() throws InterruptedException{
        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        // 一次只能允许执行的线程数量。
        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {// Lambda 表达式的运用
                try {
                    semaphore.acquire();// 获取一个许可，所以可运行线程数量为20/1=20(如果指定为5，则线程数量为4)，还有tryAcquire()
                    test(threadnum);
                    semaphore.release();// 释放一个许可
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            });
        }
        threadPool.shutdown();
        System.out.println("finish");
    }

    private static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("threadnum:" + threadnum);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }
}
