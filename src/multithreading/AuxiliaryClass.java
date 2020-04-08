package multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: type_
 * @description: 辅助类CountDownLatch和CyclicBarrier
 * @author: xjh
 * @create: 2020-04-07 16:27
 **/

//CyclicBarrier 和 CountDownLatch 都可以用来让一组线程等待其它线程。与 CyclicBarrier 不同的是，CountdownLatch 不能重新使用。
public class AuxiliaryClass {
    public static void main(String[] args) {
        latch();
//        cyclicBarrier();
    }

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
}
