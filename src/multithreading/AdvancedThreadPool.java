package multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-01 13:57
 **/
public class AdvancedThreadPool {
    public static void main(String[] args) {
        threadPoolExecutor();
    }

    /**
    * @Description:ThreadPoolExecutor学习
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/4/1
    */
    public static void threadPoolExecutor(){
        /**
         * @param corePoolSize 线程池基本大小，核心线程池大小，活动线程小于corePoolSize则直接创建，大于等于则先加到workQueue中，
         * 队列满了才创建新的线程。当提交一个任务到线程池时，线程池会创建一个线程来执行任务，即使其他空闲的基本线程能够执行新任务也会创建线程，
         * 等到需要执行的任务数大于线程池基本大小时就不再创建。如果调用了线程池的prestartAllCoreThreads()方法，
         * 线程池会提前创建并启动所有基本线程。
         * @param maximumPoolSize 最大线程数，超过就reject；线程池允许创建的最大线程数。如果队列满了，
         * 并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务
         * @param keepAliveTime
         * 线程池的工作线程空闲后，保持存活的时间。所以，如果任务很多，并且每个任务执行的时间比较短，可以调大时间，提高线程的利用率
         * @param unit  线程活动保持时间的单位）：可选的单位有天（DAYS）、小时（HOURS）、分钟（MINUTES）、
         * 毫秒（MILLISECONDS）、微秒（MICROSECONDS，千分之一毫秒）和纳秒（NANOSECONDS，千分之一微秒）
         * @param workQueue 工作队列，线程池中的工作线程都是从这个工作队列源源不断的获取任务进行执行
         */
        //ThreadPool中的四种线程池都是用这个类生成的
        //以下的语句executor在空闲状态时就有5个线程
        ThreadPoolExecutor executor=new ThreadPoolExecutor(5,10,15, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i=0;i<11;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(10000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
