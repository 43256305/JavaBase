package multithreading;

/**
 * @program: type_
 * @description: 同步块
 * @author: xjh
 * @create: 2020-03-21 14:52
 **/
//syn语句相当于lock.lock()，有获得锁的作用，只不过syn会一直阻塞直到获得锁，不能设置等待时间，而且syn会自动帮我们释放锁
public class Synchronized {
    public static void main(String[] args) {
//        block();
        object();
//        staticMethod();
//        class1();
    }

    public static void block(){
        //注意这是对象锁，所以只有一个对象才起作用，两个对象时，两个线程就互不干扰了
        SynThis synThis=new SynThis();
        new Thread(synThis).start();
        new Thread(synThis).start();
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("-------------------------------------");
    }

    public static void object(){
        //修饰对象
        Account account=new Account("zhang san",10000f);
        AccountOperator accountOperator=new AccountOperator(account);
        for (int i=0;i<5;i++){
            new Thread(accountOperator).start();
        }

    }

    public static void staticMethod(){
        //修饰静态方法   这里两个SynStaticFunc对象的静态域也实现了同步
        new Thread(new SynStaticFunc(),"static field1").start();
        new Thread(new SynStaticFunc(),"static field2").start();

        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void class1(){
        //修饰类时，类的所有对象用同一把锁（虽然域不同步（count不同步），但执行代码的顺序是先获取锁先执行，执行结束后后一个线程执行）
        new Thread(new SynClass(),"class field1").start();
        new Thread(new SynClass(),"class field2").start();
    }
}

//修饰代码块
class SynThis implements Runnable{
    private int count;

    @Override
    public void run() {
        //当两个并发线程(thread1和thread2)访问同一个对象(syncThread)中的synchronized代码块时，在同一时刻只能有一个线程得到执行，
        // 另一个线程受阻塞，必须等待当前线程执行完这个代码块以后才能执行该代码块
        //注意，只有在synchronized中的代码块才不能同时执行，其他代码都可以同时执行
        synchronized (this){
            for (int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+":" +(count++));
                try{
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}


//修饰对象（其实修饰对象和this一样，只不多把this对象换成了别的对象）
/**
 * 银行账户类
 */
class Account {
    String name;
    float amount;

    public Account(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }
    //存钱
    public  void deposit(float amt) {
        amount += amt;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //取钱
    public  void withdraw(float amt) {
        amount -= amt;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public float getBalance() {
        return amount;
    }
}

/**
 * 账户操作类
 */
class AccountOperator implements Runnable{
    private Account account;
    public AccountOperator(Account account) {
        this.account = account;
    }

    public void run() {
        //可以看到，如果没用锁，最后的余额不是10000了
        //当一个线程用syn给account加锁时，只有获得了锁才能继续执行代码块里面的代码，要不然会阻塞直到获得锁
        synchronized (account) {
            //下面的输出语句是10s运行一次，说明获取对象锁才能执行syn代码块
            System.out.println(Thread.currentThread().getName()+" is running");
            try{
                Thread.sleep(10000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            account.deposit(500);
            account.withdraw(500);
            System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
        }
//        account.deposit(500);
//        account.withdraw(500);
//        System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
    }
}

class SynStaticFunc implements Runnable{
    private static int count;

    //修饰静态方法，此方法只能访问非静态内容
    //修饰静态方法时，只要是一个类就会同步，不同对象之间也能实现锁
    public synchronized static void method() {
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //synchronized修饰方法只需要在方法前面加上关键字即可(public synchronized void run(){})，效果与public synchronized void run(){synchronized (this){}}一样
    //synchronized修饰方法并不能继承给子类（父类同步，如果子类重写的方法没有加上关键字，方法是不同步的）
    @Override
    public synchronized void run() {
        method();
    }
}

//修饰类（不重要）
class SynClass implements Runnable{
    private int count;

    @Override
    public void run() {
        //修饰类，类的代码有锁（只有获得锁的才能执行此类的代码），但对象的count域却不同步，每个对象有每个对象的count，要实现同步只能有一个对象（即用对象锁）
        synchronized (SynClass.class){
            for (int i = 0; i < 5; i ++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

