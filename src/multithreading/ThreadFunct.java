package multithreading;

/**
 * @program: type_
 * @description: 线程常用方法
 * @author: xjh
 * @create: 2020-03-21 13:44
 **/
//线程的5中状态：new（新建） Runable（就绪：yield）  Running（运行）  Blocked（阻塞：sleep，wait，join）  Dead（死亡）
    //sleep与wait不同在于sleep不会释放锁
public class ThreadFunct {

    public static void main(String[] args) throws Exception{
        new Thread(new Test()).start();

        Thread thread=new Thread(new Test2());
        thread.start();
        try{
            //把运行此语句的线程（main）加入调用此方法的线程后面运行，如果1000毫秒内thread线程没有执行完，那么main线程进入就绪状态
            //通俗说就是调用的先执行
            thread.join(1000);
            //join，与yield，一个是立刻执行，一个是立刻暂停
        }catch (InterruptedException e){
            System.out.println(e.getStackTrace());
        }

        new Thread(new Test1("低级",1)).start();
        new Thread(new Test1("中级",5)).start();
        new Thread(new Test1("高级",10)).start();
        System.out.println("main Thread:"+Thread.currentThread().getName());  //:高级

        Thread.sleep(2000);
        new Thread(new Test3()).start();

        Thread thread1=new Thread(new Test4());
        thread1.start();
        thread1.join();
        //join()后，主线程一定要等thread1线程运行结束后才能运行（注意不能指定join中的数字，要不然主线程还是会在指定时间后运行）
        //因为运行join()后main线程就进入阻塞状态，虽然thread1线程进入就绪(yield())或者阻塞（sleep）状态，但是main线程必须要等到thread1线程
        //运行结束才能被唤醒（进入阻塞状态的线程一定要等待别人唤醒，比如可以指定时间（如join(100)）,要不然就算没有线程在运行也轮不到此线程）
        System.out.println("main()");

    }
}

class Test implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<3;i++){
            System.out.println("Thread sleep!");
            try{
                //进入阻塞状态
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println(e.getStackTrace());
            }
        }
    }
}

//sleep(),yield()都是静态方法
class Test1 implements Runnable{
    private String name;
    private int p;

    //设置name和优先级
    public Test1(String name,int p){
        //注意，到这里设置线程名字是没有用的！  这里设置的线程名字是为主线程设置
        Thread.currentThread().setName(name);
        this.name=name;
        this.p=p;
    }

    @Override
    public void run() {
        //线程优先级：1-10，优先级高的线程获取CPU资源的概率较大
        Thread.currentThread().setPriority(p);
        Thread.currentThread().setName(name);
        for (int i=0;i<11;i++){
            System.out.println(Thread.currentThread().getName()+"线程第"+i+"次运行");
            if (i%5==0){
                //从执行状态，变为就绪状态（就绪后并不是说优先级高的就运行，只是大概率优先级高的运行，所以次线程最主要的作用是当前的线程暂时让步给别的线程）
                Thread.yield();
            }
        }
    }
}

//join()
class Test2 implements Runnable{

    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            System.out.println("Thread run join()");
        }
    }
}

//线程终止
class Test3 implements Runnable{
    private int i=1;
    @Override
    public void run() {
        while (true) {
            System.out.println(i);
            //isInterrupted()判断此线程是否被打上中断标记（即中断状态码），是返回true，否则false
            System.out.println(Thread.currentThread().isInterrupted());
            //interrupted()静态方法（即返回执行此语句的线程的中断状态码），
            // 会返回当前线程的中断状态码，并且当中断状态码为true时会被重置为false，所以while循环不会停
//            System.out.println(Thread.interrupted());
            try {
                System.out.println("我马上去sleep了");
                Thread.sleep(2000);
                //给这个线程打上中断的标记，但实际上并没有被中断,Thread.isinterrepted()会变成true
                //贸然中断一个线程是非常不安全的中断一个线程只是为了引起该线程的注意，被中断线程可以决定如何应对中断
                //但是当线程被阻塞的时候，比如被Object.wait, Thread.join和Thread.sleep三种方法之一阻塞时。调用它的interrput()方法。
                //可想而知，没有占用CPU运行的线程是不可能给自己的中断状态置位的。这就会产生一个InterruptedException异常（用此机制和sleep结束一个正在循环的线程）
                Thread.currentThread().interrupt();
                //第一次循环给此线程打上标记后，第二次循环运行sleep时就会抛出异常
            } catch (InterruptedException e) {
                System.out.println("异常捕获了" + Thread.currentThread().isInterrupted());
                return;
            }
            i++;
        }
    }
}

class Test4 implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<10000;i++){
            i++;
            //可以看到，此线程运行join()后，再在线程内部循环运行yield（或者sleep），main线程还是必须要等Test4线程完全运行结束后才能运行
//            Thread.yield();
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Test4");
    }
}

