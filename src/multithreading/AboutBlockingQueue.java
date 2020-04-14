package multithreading;


import java.util.Comparator;
import java.util.concurrent.*;

/**
 * @program: type_
 * @description: 阻塞队列
 * @author: xjh
 * @create: 2020-04-07 18:07
 **/
//线程间交换信息可以用共享对象或者阻塞队列
    //生产者消费者问题运用阻塞队列：当队列容器已满，生产者线程会被阻塞，直到队列未满；当队列容器为空时，消费者线程会被阻塞，直至队列非空时为止。
    //线程间通信：轮询volatile变量，使用wait()/notify()，使用await()/signal()
public class AboutBlockingQueue {
    public static void main(String[] args) {
//        array();
//        priority();
//        synchronous();
        transfer();
    }

    public static void array(){
        //数组实现的有界阻塞队列。该队列命令元素FIFO（先进先出），一旦创建，容量不可改变
        //不能保证线程访问队列的公平性，所谓公平性是指严格按照线程等待的绝对时间顺序，即最先等待的线程能够最先访问到ArrayBlockingQueue
        //要保证公平性需要创建时在容量后面传入true，如果保证公平性，通常会降低吞吐量
        BlockingQueue array=new ArrayBlockingQueue(3,true);
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i=0;i<20;i++){
                        System.out.print(array.take());  //队列为空时，阻塞
                        //poll(long timeout, TimeUnit unit)可以设置时间
                    }
                    System.out.println("");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i=0;i<10;i++){
                        array.put(i);   //队列已满时，阻塞
                        //offer(E e, long timeout, TimeUnit unit)方法，可以设定时间，到时间没有插入成功，则退出
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i=0;i<10;i++){
                        array.put((char)('A'+i));
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public static void linked(){
        //链表实现有界阻塞队列，FIFO,与ArrayBlockingQueue相比起来具有更高的吞吐量为了防止LinkedBlockingQueue容量迅速增，
        // 损耗大量内存。通常在创建LinkedBlockingQueue对象时，会指定其大小，如果未指定，容量等于Integer.MAX_VALUE
        BlockingQueue queue=new LinkedBlockingQueue(10);
    }

    public static void priority(){
        //是一个支持优先级的无界阻塞队列（优先级高的元素先被拿出）,默认情况下元素采用自然顺序进行排序，也可以通过自定义类实现compareTo()
        // 方法来指定元素排序规则，或者初始化时通过构造器参数Comparator来指定排序规则
        BlockingQueue queue=new PriorityBlockingQueue(10, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (int)o1-(int)o2;
            }
        });
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i=0;i<20;i++){
                        //可以看到，输出是从小到大
                        System.out.print(queue.take());
                    }
                    System.out.println("");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i=0;i<10;i++){
                        queue.put(i);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i=0;i<10;i++){
                        queue.put(i-10);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public static void synchronous(){
        //不能指定大小，可以指定公平性，SynchronousQueue每个插入操作必须等待另一个线程进行相应的删除操作，因此，SynchronousQueue
        // 实际上没有存储任何数据元素，因为只有线程在删除数据时，其他线程才能插入数据，同样的，如果当前有线程在插入数据时，线程才能删除数据。
        //总的来说，还是先进先出，因为进了之后再出去才能插入下一个
        BlockingQueue queue=new SynchronousQueue(true);
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //如果把20改为小于20，则下面的插入操作线程会阻塞(因为要插入时，这个线程已经结束了，没有删除操作了)
                    for (int i=0;i<10;i++){
                        System.out.print(queue.take());
                    }
                    System.out.println("");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i=0;i<10;i++){
                        queue.put(i);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i=0;i<10;i++){
                        queue.put((char)('A'+i));
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public static void transfer(){
        //由链表数据结构构成的无界阻塞队列
        TransferQueue queue=new LinkedTransferQueue();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i=0;i<20;i++){
                        System.out.print(queue.take());
                    }
                    System.out.println("");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i=0;i<10;i++){
                        queue.put(i);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i=0;i<10;i++){
//                        {
//                            queue.transfer((char)('A'+i));
//                            //1.如果有消费者正在take()或者poll()，则直接把数据交给消费者 2.如果没有，则插入队尾，直到有消费者消费刚才插入的元素才退出
//                            //所以可以看到，此字母线程总是在数字线程全部输出后才能输出
//                            try{
//                                Thread.sleep(1000);
//                            }catch (InterruptedException e){
//                                e.printStackTrace();
//                            }
//                        }
//                        {
//                            queue.tryTransfer((char)('A'+i));
//                            //1与上面相同，2.如果没有消费者，则直接返回false，并不插入，所以看到字母都没有插入队列中
//                        }
                        {
                            queue.tryTransfer((char)('A'+i),1,TimeUnit.MILLISECONDS);
                            //1与上面相同，2.如果没有消费者，则等待指定时间，还没有则退出并返回false
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public static void dequeue() {
        //有界双端队列，上面的queue先进先出，这个可以先进后出
        LinkedBlockingDeque deque=new LinkedBlockingDeque();
        try{
            deque.putLast(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void delay(){
        //存放实现Delayed接口的数据的无界阻塞队列，只有当数据对象的延时时间达到时才能插入到队列进行存储
        //如果当前所有的数据都还没有达到创建时所指定的延时期，则队列没有队头，并且线程通过poll等方法获取数据元素则返回null
        DelayQueue queue=new DelayQueue();
        queue.put(new Delay());
    }
    static class Delay implements Delayed{
        //所谓数据延时期满时，则是通过Delayed接口的getDelay(TimeUnit.NANOSECONDS)来进行判定，
        // 如果该方法返回的是小于等于0则说明该数据元素的延时期已满。
        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
