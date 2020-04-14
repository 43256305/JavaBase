package multithreading;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-12 11:41
 **/
//通常情况下，我们创建的变量是可以被任何一个线程访问并修改的。而使用ThreadLocal创建的变量只能被当前线程访问，其他线程则无法访问
// 和修改，每个线程都有一个自己独立的一个变量（注意一个ThreadLocal对象就只有一个value（如果要存储多个value，就要new多个threadLocal对象））
public class AboutThreadLocal {
    public static void main(String[] args) {
        testThreadLocal();
//        dateFormat();
//        solveDateFormat();
    }

    public static void testThreadLocal(){
        new Thread(){
            //ThreadLocal最好声明一个独立的类变量修饰为：private static final，而不宜放在线程中new，这样消耗了多余的资源
            //如果ThreadLocal不加static，则每次其所在类实例化时，都会有重复ThreadLocal创建。这样即使线程在访问时不出现错误也有资源浪费。
            ThreadLocal<String> mStringThreadLocal = new ThreadLocal<String>(){
                //为ThreadLocal设置默认的get初始值
                @Override
                protected String initialValue() {
                    return "xie";
                }
            };
            @Override
            public void run() {
                mStringThreadLocal.remove();
                System.out.println(mStringThreadLocal.get());  //:xie
                //实现原理：每个线程都维护一个ThreadLocals的map对象，此map对象的key值为ThreadLocal对象，所以每次取值都用当前ThreadLocal去取
                //map由ThreadLocal的静态内部类Entry生成，而Entry对键key（即ThreadLocal）弱引用，对值value强引用
                mStringThreadLocal.set("test");
                System.out.println(mStringThreadLocal.get());  //:test
                mStringThreadLocal.remove();
                System.out.println(mStringThreadLocal.get());  //:xie
//ThreadLocal导致内存泄漏：如果应用使用了线程池，那么之前的线程实例处理完之后出于复用的目的依然存活，所以，ThreadLocal设定的值被持有，导致内存泄露。
//弱引用：长期存在的线程（ThreadLocalMap）持有短生命周期的对象（ThreadLocal），会导致内存泄漏，而用了弱引用后，GC就会及时回收生命周期已经
//结束的ThreadLocal，一定程度防止了内存泄漏。但是，回收Threanlocal对象后，value是却强引用，这会导致存在key为null，value有效的entry，
//所以，为了防止内存泄漏，在ThreadLocal使用前后都调用remove清理，同时对异常情况也要在finally中清理。

            }
        }.start();
    }

    //SimpleDateFormat存在线程安全问题
    //why?
    //下面会报错：NumberFormatException  并且只会输出一个线程的时间
    public static void dateFormat(){
        final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] strs = new String[] {"2016-01-01 10:24:00", "2016-01-02 20:48:00", "2016-01-11 12:24:00"};
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < 10; i++){
                        try {
                            System.out.println(Thread.currentThread().getName()+ "\t" + dateFormat.parse(strs[i % strs.length]));
                            Thread.sleep(100);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        latch.countDown();
    }

    //既然多线程会导致SimpleDatefomate报错，可以用在ThreadLocal中，确保每个线程一个对象（事实上上面把new SimpleDateFormat放在run方法
    // 上面也没有报错。那为什么不每个run方法声明一个对象Simple呢？因为那个类创建代价高昂且每次调用都需要创建不同的实例所以不值得
    // 在局部范围使用它，如果为每个线程提供一个自己独有的变量拷贝，将大大提高效率）
    public static void solveDateFormat(){
        //ThreadLocal放在外面比较好
        ThreadLocal<SimpleDateFormat> local=new ThreadLocal<SimpleDateFormat>(){
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        };
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] strs = new String[] {"2016-01-01 10:24:00", "2016-01-02 20:48:00", "2016-01-11 12:24:00"};
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(local.hashCode());
                    for (int i = 0; i < 10; i++){
                        try {
                            System.out.println(Thread.currentThread().getName()+ "\t" + local.get().parse(strs[i % strs.length]));
                            Thread.sleep(100);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        latch.countDown();
    }
}
