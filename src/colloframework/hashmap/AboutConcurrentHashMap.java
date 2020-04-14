package colloframework.hashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-11 22:20
 **/
public class AboutConcurrentHashMap {
    public static void main(String[] args) {
        //hashtable效率比较低，一个线程访问其他线程都不能操作
        //concurrenthashmap运用锁分段技术
        ConcurrentHashMap map=new ConcurrentHashMap();
        map.put("1","value");

    }
}
