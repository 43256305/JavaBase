package basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-05 20:33
 **/
public class Lambda {
    public static void main(String[] args) {
        nomalUsage();
//        thread();
    }

    public static void nomalUsage(){
        String[] strings={"qqq","www","eee","rrr","ttt"};
        List<String> list= Arrays.asList(strings);
        //打印
        list.forEach((l)-> System.out.println(l));
//        list.forEach(Lambda::print);   //功能与上面相同

        //排序   第二个参数也是实现了接口中的comparaTo方法
        Arrays.sort(strings,(String s1,String s2)->(s1.compareTo(s2)));
        System.out.println(Arrays.toString(strings));
    }
    public static void print(String s){
        System.out.println(s);
    }

    public static void thread(){
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //用lambda表达式相当于代替了interface和实现的run方法
        threadPool.submit(()->{
            System.out.println("用lamda表达式");
        });

        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("不用lambda表达式");
            }
        });

        threadPool.shutdown();
    }
}
