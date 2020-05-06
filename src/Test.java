import java.util.Random;

public class Test {
    private static Random random=new Random();
    private static volatile int stock=random.nextInt(50)+50;
    private static byte[] bytes=new byte[0];

    public static void main(String[] args) {
        new Thread(new Sell()).start();
        new Thread(new Purchase()).start();
    }

    static class Sell implements Runnable{
        @Override
        public void run() {
            int Count;
            while (true){
                Count=random.nextInt(stock);
                stock-=Count;
                System.out.println("卖出"+Count+"件商品");
                try{
                    Thread.sleep(random.nextInt(2)*1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    static class Purchase implements Runnable{
        @Override
        public void run() {
            int count=0;
            while (true){
                if (stock<10){
                    count=random.nextInt(50)+50;
                    stock+=count;
                    System.out.println("进货"+count+"件商品");
                    System.out.println("库存商品数："+stock);
                }
            }
        }
    }
}
