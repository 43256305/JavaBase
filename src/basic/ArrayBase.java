package basic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayBase {
    //Array的基本知识和常用方法
    public static void main(String[] args) {
        //args为命令行参数
        System.out.println(Arrays.toString(args));  //:[]

        comKnowledge();
        comMethods();
    }

    public static void comKnowledge(){
        //初始化
        int[] array1={1,2,3};
        int[] array2=new int[3];
        System.out.println(array1);  //:地址
        for (int i:array1){
            System.out.println(i);  //:1   2   3
        }

        //函数
        System.out.println(array1==array2);  //:false
        array2=function(array1);
        //函数传参时，基本数据类型传的是值的拷贝，而对象传的是对象引用的拷贝（函数内的引用与外面指向同一对象，函数内的引用指向别的对象后不影响外面）
        System.out.println(array1==array2);  //:true

        //二维数组  [行][列]
        int[][] a=new int[6][1];
        System.out.println(a.length);  //:6
        System.out.println(a[1].length);  //:1
        //此数组两行三列
        int[][] b={{1,2,3},{4,5,6}};
        System.out.println(b.length);  //:2
        System.out.println(b[1].length);  //:3

        System.out.println(Arrays.toString(b));  //:输出地址
        //输出二维数组
        System.out.println(Arrays.deepToString(b));  //:[[1,2,3],[4,5,6]]

    }

    public static int[] function(int[] a){
        return a;
    }

    public static void comMethods(){
        System.out.println("------------------------------------------------");
        int[] array1={1,2,3};
        //用arrays输出数组
        System.out.println(Arrays.toString(array1));  //:[1,2,3]

        int[] array2={2,1,4,6,4,8,9,3};
        //给array2数组排序
        Arrays.sort(array2);
        System.out.println(Arrays.toString(array2));  //:[1, 2, 3, 4, 4, 6, 8, 9]

        //二分法查找已经排好序数组中某个数，没找到返回负数
        System.out.println(Arrays.binarySearch(array2,3));  //:2
        int[] array3={3,2,1,5,2,6};
        System.out.println(Arrays.binarySearch(array2,5));  //:-6

        //判断数组相等
        int[] array4={1,2,3};
        System.out.println(array1==array4);  //:false
        System.out.println(Arrays.equals(array1,array4));  //:true

        //根据数组创建ArrayList
        String[] stringArray = { "a", "b", "c", "d", "e" };
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(stringArray));
        System.out.println(arrayList);

        //ArrayList转数组
        String[] array6=new String[5];
        arrayList.toArray(array6);
        System.out.println(Arrays.toString(array6));  //:[a,b,c,d,e]

        //数组转Set
        Set<String> set = new HashSet<>(Arrays.asList(stringArray));
        System.out.println(set);  //:[d, e, b, c, a]

        //copy数组  后一个数值可以可以大于或者小于i1数组的length
        int[] i1={1,2,3,4};
        int[] i2=Arrays.copyOf(i1,i1.length);
        System.out.println(Arrays.toString(i2));  //:[1,2,3,4]



    }
}
