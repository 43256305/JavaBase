package basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-05 20:33
 **/
public class Lambda {
    public static void main(String[] args) {
        String[] strings={"qqq","www","eee","rrr","ttt"};
        List<String> list= Arrays.asList(strings);
        //打印
        list.forEach((l)-> System.out.println(l));
//        list.forEach(Lambda::print);   //功能与上面相同

        //排序
        Arrays.sort(strings,(String s1,String s2)->(s1.compareTo(s2)));
        System.out.println(Arrays.toString(strings));

    }
    public static void print(String s){
        System.out.println(s);
    }
}
