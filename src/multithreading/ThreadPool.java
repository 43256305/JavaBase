package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-03-30 19:47
 **/
//为什么要用线程池？
//1.减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。2.可以根据系统的承受能力，调整线程池中工作线线程的数目，
// 防止因为消耗过多的内存，而把服务器累趴下(每个线程需要大约1MB内存，线程开的越多，消耗的内存也就越大，最后死机)
public class ThreadPool {
    public static void main(String[] args) throws InterruptedException{
//        newCached();
        newFixed();
//        newScheduled();
//        newSingle();
    }

    /**
    * @Description: 线程池为无限大，当执行第二个任务时第一个任务已经完成时，会复用执行第一个任务的线程，而不用每次新建线程。
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/30
    */
    public static void newCached(){
        ExecutorService  cachedThreadPool= Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            //index代表第i个产生的线程
            final int index=i;
            try {
                //当把这一句注释时，线程名字从1-10，有这一句时，只有线程1，这样减少了每次创建线程的消耗
                Thread.sleep( 2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index+":"+Thread.currentThread().getName());
                }
            });
        }
    }

    /**
    * @Description:创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/3/30
    */
    public static void newFixed(){
        ExecutorService fixedThreadPool=Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index+":"+Thread.currentThread().getName());
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
        fixedThreadPool.shutdown();   //线程池关闭
    }

    
    /**
    * @Description: 创建一个定长线程池，支持定时及周期性任务执行
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/30
    */
    public static void newScheduled(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        System.out.println("start execute thread");
        scheduledThreadPool.schedule(new Runnable() {

            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);  //延迟三秒后执行

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 1,3, TimeUnit.SECONDS);  //延迟1秒后没三秒执行一次
    }

    /**
    * @Description: 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/30
    */
    public static void newSingle(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(index+":"+Thread.currentThread().getName());
//                    try {
//                        Thread.sleep(2000);  //无论注释不注释这一句，线程名都是线程1
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
                }
            });
        }
    }
}
