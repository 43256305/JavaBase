package colloframework;

import java.util.*;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-03-16 09:59
 **/
public class Collections {
    public static void main(String[] args) {
        //java类的基本接口是Collection<E>接口，这个接口继承了Iterable接口(for each循环可以使用在扩展了此接口的类)
        //Iterable<E>接口有两个方法：Iterator<T> iterator(); default void forEach(Consumer<? super T> action)
        //Collection接口又两个基本方法：boolean add(E e); Iterator<E> iterator();（java集合都可以使用迭代器,hashSet随机访问）
        //除了Map(实现了Map接口)外的集合类都实现了Collection接口
        //iterator接口有三个方法:E next(); boolean hasNext();void remove();
        String[] array={"111","222","333"};
        ArrayList<String> list= new ArrayList<>(Arrays.asList(array));
        for (String s:list){
            System.out.printf(s);  //:111222333
        }
        System.out.println();
        Iterator<String> iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.printf(iterator.next());  //:111222333
        }
        //删除一个元素（此方法必须紧接在next()后面，删除的就是这个next()的输出）
        iterator.remove();
        System.out.println(list);  //[111,222]

        //为了简化接口实现，AbstractCollection<E>抽象类实现了Collection<E>接口，他将size()和iterator()方法抽象化了,其他方法都实现了

        Collection<String> collection=new Collection<String>() {
            //一下是这个接口的所有方法
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };

        AbstractCollection<String> abstractCollection=new AbstractCollection<String>() {
            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }
        };

    }
}
