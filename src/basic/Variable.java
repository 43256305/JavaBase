package basic;


import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.Scanner;

public class Variable {
    private int i;
    private String s;
    private boolean b;
    public static void main(String[] args) {
//当变量为局部变量时，不初始化会报错，除了数组默认为0
        int i;
        double d;
        boolean b;
        char c;
//        System.out.println(i);  报错：i没有被初始化
//        System.out.println(d);   报错：d没有被初始化
//        System.out.println(b);   报错
//        System.out.println(c);    报错

        int[] array=new int[3];
        for (int a:array){
            System.out.print(a+"  ");  //：0  0  0
        }

        String s;
//        System.out.println(s);  报错

//当变量为成员变量时，默认为0，对象为null
        Variable variable=new Variable();
        System.out.println(variable.i);  //:0
        System.out.println(variable.s);  //null
        System.out.println(variable.b);  //false

        int i1=3;
        //基本类型转换，只能按照顺序转换:小的转换大的
        float f1=i1;
//        i1=f1;  //报错
        short sh1=44;
        i1=sh1;
        int i2='e';
        //char类型的阿斯克码
        System.out.println(i2);  //:101

        //强制类型转换
        float f2=2.333f;
        int i3=(int) f2;
        System.out.println(i3);  //:2
        int i4=(int) 6.66;

        //不管是普通类型转换还是强制类型转换，都是7(没有boolean)种基本类型之间，二与对象转换则要用方法
        System.out.println(String.valueOf("1212"));  //:1212

        //文件操作：只支持文本文件
        try{
            //写入文件，不存在则创建
            PrintWriter pw=new PrintWriter("test.txt");
            pw.println("xiejiehui");
            pw.printf("xie jie hui is %d years old",21);
            //别忘了关闭，要不然写入不进去
            pw.close();

            //读取文件，注意必须要加paths，要不然会直接输出文件名，而不是读取文件
            Scanner in=new Scanner(Paths.get("test.txt"));
            System.out.println(in.nextLine());
            in.close();

            Scanner in2=new Scanner("test.txt");
            System.out.println(in2.nextLine());  //:test.txt

        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }


        String s1="333333333333333333";
        //new大数没有空的构造器，一定要传入字符串等    大数的精度无限大，可以存储任意精度的数值
        BigInteger bi=new BigInteger(s1);
        System.out.println(bi);  //:333333333333333333
//        BigInteger bi1=new BigInteger();  //报错
        //声明大数的另一种方法
        BigInteger bi2=BigInteger.valueOf(1);
        System.out.println(bi2);  //:1
        //大数相加
        BigInteger bi3=bi2.add(bi);
        System.out.println(bi3);  //:333333333333333334

        //浮点数大数
        BigDecimal bd=new BigDecimal("2.5555555555555555");
        System.out.println(bd);  //:2.5555555555555555

        System.out.println(Integer.MAX_VALUE);  //:2147483647
        System.out.println(Double.MAX_VALUE);   //:1.7976931348623157E308

        //移位操作:相当于乘以2，除以2    右移相当于减少一位，如1000变为100，1001变为100   左移相当于增加一个0，如100变为1000
        System.out.println(10 << 1);  //：20
        System.out.println(10 >> 1);  //:5
        System.out.println(10 >>> 1);  //:5
        //无符号右移运行
        System.out.println(-5 >>> 1);  //:2147483645
        System.out.println(-5 >> 1);  //:-3

        //^异或运算 （不同就为1）
        System.out.println(8^1);  //:9    1000^1=1001
        System.out.println(9^1);  //:8     1001^1=1000

        //或运算(有一个1就是1)
        System.out.println(8|1);  //:9
        System.out.println(9|1);  //9

        //与运算(同时为1才是1)
        System.out.println(8&1);  //:0   1000&0001=0000
        System.out.println(9&1);  //:1
    }
}
