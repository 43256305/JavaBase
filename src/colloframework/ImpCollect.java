package colloframework;

import java.util.*;
import java.util.Collections;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-03-16 10:36
 **/
public class ImpCollect {
    public static void main(String[] args) {
//        aboutLinkedList();
//        System.out.println("-----------------------------------------");
        aboutArrayList();
//        System.out.println("-----------------------------------------");
//        aboutSet();
//        System.out.println("-----------------------------------------");
//        treeSet();
//        System.out.println("-----------------------------------------");
//        AboutMap();
    }

    //频繁在任意位置插入、删除时选择
    public static void aboutLinkedList(){
        //LinkedList是线程不安全的
        String[] array={"qqq","www","eee","rrr"};
        //List<E>是一个继承了Collection的接口  LinkedList继承了AbstractCollection
        List<String> list=new LinkedList<>(Arrays.asList(array));
        //LinkedList类有返回ListIterator的方法:ListIterator<E> listIterator(int index)（返回一个实现了ListIterator接口的对象）
        //ListIterator（增加了对元素的增，改，前一个）接口继承了Iterator<E>（下一个，删）接口
        // 新增：boolean hasPrevious();E previous();void set(E e);void add(E e);

        //尽量不要使用这个方法（get方法每次都是从头到尾搜索），要大量访问指定位置的值最好用数组或ArrayList
        System.out.println(list.get(2));  //:eee
//        for (int i=0;i<list.size();i++){
//            System.out.println(list.get(i));   //:效率极低
//        }

        ListIterator<String> iterator=list.listIterator();
        System.out.println(list);  //:[qqq, www, eee, rrr]
        iterator.next();
        //在list的中间插入
        iterator.add("ppp");
        iterator.add("ooo");
        System.out.println(list);  //:[qqq, ppp, ooo, www, eee, rrr]
        System.out.println(iterator.next());  //:www
        //反向
        System.out.println(iterator.previous());  //:www
        //替换（remove，set，add都是与上一个next/previous相关，如set的上一个previous就是:www）
        iterator.set("uuu");
        System.out.println(list);  //:[qqq, ppp, ooo, uuu, eee, rrr]

        ListIterator<String> iterator1=list.listIterator();
//        iterator1.set("lll");  // 报错，set之前没有next()
        //新建的迭代器元素与原有的迭代器元素相同，只是指针位置不同
        System.out.println(iterator1.next());  //:qqq
        System.out.println(iterator.next());  //:uuu

        ListIterator<String> iterator2=list.listIterator();
        iterator1.remove();
//        System.out.println(iterator2.next());  //报错：第一个迭代器已经删除了第一个元素，第二个迭代器还想访问第一个，所以报错
        //所以当有多个迭代器时，应该只用一个可以读写，其他都只能读
    }

    //非同步、非频繁删除时选择
    public static void aboutArrayList(){
        //ArrayList也实现了List接口，他封装了一个动态再分配的对象数组
        List<String> list=new ArrayList<>();
        //ArrayList的扩容机制  没有指定大小时为10（先声明为size为0的数组，再在第一个add时初始化大小为10），小于0抛出异常，大于或等于0就设置为这个，输入容器时先把容器转换为数组，再判断大小
        //每次数组的实际大小变为原来的1.5倍

        //ArrayList线程不安全，要线程安全必须用Collections生成
        list.add("1");
        list.add("2");
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            list.add("3");   //用迭代器迭代时，更改list会报错
        }


    }

    public static void aboutSet(){
        //散列表为每个对象计算一个整数（散列码），每个对象的散列码都不同
        //散列表用链表数组实现，插入位置为：hashcode%bucket=index，通常，将桶数设置为素数，如果散列表太满（装填因子（如：0.75）已满）就需要重新散列
        String str1="qqq";
        System.out.println(str1.hashCode());  //:112209

        //set中的元素没有重复，没有顺序，可以随机访问（运算一下就可以知道它的位置）
        //set继承了Collection接口  HashSet继承了AbstractSet
        Set<String> set=new HashSet<>();
        set.add("qqq");
        //只有增删方法
        set.add("www");
        set.remove("www");
        System.out.println(set.contains("qqq"));  //:true
        Iterator<String> setItera=set.iterator();
        while (setItera.hasNext()){
            System.out.println(setItera.next());  //:qqq
        }
    }

    public static  void treeSet(){
        //TreeSet与散列表类似，但是是有序的，排序是用红黑树完成的（插入元素的时间O(logn)）   继承了AbstractSet
        String[] array={"qqq","www","eee","rrr"};
        TreeSet<String> treeSet=new TreeSet<>(Arrays.asList(array));
        //将值放入树中比散列表慢，但比数组和链表块
        treeSet.add("ttt");
        System.out.println(treeSet.first()); //:eee
        //顺序与输入位置不同
        System.out.println(treeSet);  //:[eee, qqq, rrr, ttt, www]
        //first在last之前，所以返回负值，treeset的排序就是按照这个方法来排序的  Comparable接口下的 comparaTo()
        System.out.println(treeSet.first().compareTo(treeSet.last()));  //:-18
        treeSet.remove("ttt");

        //如果要自定义排序，可以写一个类实现Comparator接口：int compara(E a,E b)，把此接口传入TreeSet
        treeSet.comparator();

    }

    public static void AboutMap(){
        //HashMap对key进行散列（无序，更快），TreeMap用key的顺序对value进行排序（有序）
        //两个类都继承了AbstractMap
        Map<Integer,String> hashMap=new HashMap<>();
        Map<Integer,String> treeMap=new TreeMap<>();

        //增
        hashMap.put(1,"qqq");
        //删
        hashMap.remove(1);
        hashMap.put(1,"www");
        //改
        hashMap.put(1,"eee");
        System.out.println(hashMap);  //:1=eee
        //查
        System.out.println(hashMap.get(1));  //:eee
        //迭代
        for (int key:hashMap.keySet()){
            System.out.println("key:"+key+" value:"+hashMap.get(key));
        }
    }

    public static void collections(){
        //实现线程同步的ArrayList（其实就是把List的方法重新用Syn关键字包起来）
        List<String> synList= Collections.synchronizedList(new ArrayList<>());

    }
}
