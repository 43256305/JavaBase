package colloframework.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-09 10:46
 **/
public class AboutHashMap {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("1", "k");
        //1.计算位于数组中的位置 2.判断此位置是否有元素，没有则直接插入，有则比较key值是否相等，相等则更新value值 3.不相等则判断此位置是否
        //为红黑树，是则插入红黑树中，不是则插入链表中，插入链表后判断链表长度是否大于或等于8
        //具体put位置在table中的的计算（高位运算，取模运算）    在一个链表上插入第8个之后，转化为红黑树
        int hashcode = "1".hashCode();
        int hash = hashcode ^ (hashcode >>> 16);   //高位运算
        System.out.println((16-1) & hash);   //：1   计算"1"在数组中放置的位置（16-1为length-1，length为初始化值16）
        System.out.println(hash%16);   //:1   其实上面一步达到的效果就是length%i  取模运算

    }
}
