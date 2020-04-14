package basic;

import java.util.concurrent.CountDownLatch;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-13 21:20
 **/
public class AboutHashCode {
    private static CountDownLatch latch=new CountDownLatch(4);
    public static void main(String[] args) throws Exception{
//        testNomalObject();
//        testWrap();

        //多线程中的hashCode
        testinnObject();
        latch.await();
        System.out.println("-----------------------");
        testoutObject();
    }

    public static void testNomalObject(){
        Object o=new Object();
        Object o1=new Object();
        //普通类对象：1.同一个对象的，hashCode值一定相同
        System.out.println(o.hashCode());  //：460141958
        System.out.println(o1.hashCode());  //：1163157884

        String a=new String("123");
        String b=new String("123");
        //java规定，equals返回为true，则hashCode也相同
        System.out.println(a.equals(b)); //:true
        //2.hashCode相同，不一定是同一个对象
        System.out.println(a.hashCode());  //:48690
        System.out.println(b.hashCode());  //:48690
        System.out.println(a==b);  //:false
    }

    public static void testWrap(){
        Integer integer=new Integer(1000);
        Integer integer1=new Integer(1000);
        //包装类：hashCode就是他们的值
        System.out.println(integer.hashCode());   //:1000
        System.out.println(integer1.hashCode());   //:1000

        Double d=new Double(12.3);
        Double d1=new Double(12.3);
        System.out.println(d.hashCode());  //:-642711549
        System.out.println(d1.hashCode());  //:-642711549
    }

    public static void testinnObject(){
        for (int i=0;i<4;i++){
            new Thread(){
                @Override
                public void run() {
                    Object o1=new Object();
                    //可以看出hashCode值各不相同   说明每个objec都是线程独有的
                    System.out.println(o1.hashCode());
                    latch.countDown();
                }
            }.start();
        }
    }

    public static void testoutObject(){
        Object o=new Object();
        for (int i=0;i<4;i++){
            new Thread(){
//                Object o=new Object();   //写在这里就不相同了，说明在run方法中new和在run方法上面new一样每次都会被线程执行
                @Override
                public void run() {
                    //hashCode值都相同
                    System.out.println(o.hashCode());
                }
            }.start();
        }
    }

}
