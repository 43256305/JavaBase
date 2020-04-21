package multithreading;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-01 13:57
 **/
public class AdvancedThreadPool {
    public static void main(String[] args) {
//        threadPoolExecutor();
        factory();
//        exception();
//        exception1();
//        exception2();
//        exception3();
    }

    /**
    * @Description:ThreadPoolExecutor学习
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/4/1
    */
    public static void threadPoolExecutor(){
        //ThreadPool中的四种线程池都是用这个类生成的
        //以下的语句executor在空闲状态时就有5个线程（因为核心线程会一直存活，即使空闲）
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

    //自定义线程类，便于管理线程
    static class WorkThread extends Thread {
        private Runnable target;   //线程执行目标
        private AtomicInteger counter;

        public WorkThread(Runnable target, AtomicInteger counter) {
            this.target = target;
            this.counter = counter;
        }
        @Override
        public void run() {
            try {
                target.run();
            } finally {
                int c = counter.getAndDecrement();
                System.out.println("terminate no " + c + " Threads");
            }
        }
    }

    
    /**
    * @Description: 用于设置创建线程的工厂，可以给创建的线程设置有意义的名字，可方便排查问题。
     * 作用：在线程开始之前执行一些操作，如设置名字，设置异常处理handler等
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/15
    */
    public static void factory(){
        ExecutorService service=Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger atomicInteger = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                int c = atomicInteger.incrementAndGet();
                System.out.println("create no " + c + " Threads");
                return new WorkThread(r, atomicInteger);//通过计数器，可以更好的管理线程
            }
        });

        service.execute(()->{
            System.out.println("complete a task");
        });
        service.execute(()->{
            System.out.println("complete a task");
        });
        service.shutdown();
    }

    /**
     * @Description: //如果线程池内部线程出现异常怎么办？
     *     //发生异常后，虽然没有结果输出，但是没有抛出异常，所以我们无法感知任务出现了异常，这时有四种异常捕获方法
     *     1.手动在可能发生异常处try catch
     *     2.把执行结果给future，用try catch捕获future的get方法
     *     3.为线程设置异常处理handler
     *     4.继承ThreadPoolExecutor，重写after方法
     * @Param:
     * @return:
     * @Author: xjh
     * @Date: 2020/4/14
     */
    public static void exception(){
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            threadPool.submit(new Thread(){
                @Override
                public void run() {
                    System.out.println("current thread name" + Thread.currentThread().getName());
                    Object object = null;
//                    System.out.print("result## "+object.toString());

                    //加了try catch块后会抛出异常
                    try{
                        System.out.print("result## "+object.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();  //线程池需要手动关闭
    }

    /**
     * @Description: 通过future的get方法也可以捕获异常
     * @Param:
     * @return:
     * @Author: xjh
     * @Date: 2020/4/15
     */
    public static void exception1(){
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Future f=threadPool.submit(new Thread(){
                @Override
                public void run() {
                    System.out.println("current thread name" + Thread.currentThread().getName());
                    Object object = null;
                    System.out.print("result## "+object.toString());

                }
            });
            try{
                f.get();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        threadPool.shutdown();  //线程池需要手动关闭
    }

    public static void exception2(){
        //newFixedThreadPool的第二个参数是threadFactory
        ExecutorService threadPool = Executors.newFixedThreadPool(1, r -> {
            Thread t = new Thread(r);  //r为runable接口
            //为线程设置抛出异常的处理handler
            t.setUncaughtExceptionHandler(
                    (t1, e) -> {
                        System.out.println(t1.getName() + "线程抛出的异常:"+e);
                    });
            return t;
        });
        threadPool.execute(()->{
            Object object = null;
            System.out.print("result## " + object.toString());
        });
        threadPool.shutdown();

        Thread t1=new Thread(){};
        t1.setUncaughtExceptionHandler((t2,e)->{});   //可以这样设置handler
    }

    public static void exception3(){
        ExtendedExecutor executor=new ExtendedExecutor(5,5,5,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3));
        executor.execute(()->{
            Object o=null;
            System.out.println(o.toString());
        });
    }

    //重写afterExecute来输出异常
    static class ExtendedExecutor extends ThreadPoolExecutor{

        public ExtendedExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            if (t == null && r instanceof Future<?>) {
                try {
                    Object result = ((Future<?>) r).get();
                } catch (CancellationException ce) {
                    t = ce;
                } catch (ExecutionException ee) {
                    t = ee.getCause();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // ignore/reset
                }
            }
            if (t != null)
                System.out.println(t);
        }
    }
}
