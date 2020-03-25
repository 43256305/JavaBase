package basic;

import java.util.Scanner;

public class TypeOfData {

    public static void main(String[] args) {
        //java有8种数据类型 (4整型，2浮点，char，boolean)
        //1字节，2^7(表示正负，所以不是8次方)
        byte b;
        //2字节，2^15
        short s;
        //4字节，2^31
        int i;
        //8字节，2^63
        long l;
//        b=1000;//:报错
        l=1000;
        //当用包装类时要加l的后缀
        Long l1=99999l;

        //没有f后缀时默认为double  4字节
        float f=3.14f;
        //数据类型精度为float的两倍  8字节，只能精确到小数点后15-16位
        double d=3.14;
        d=3.12345678901234567788888;  //:3.1234567890123457
//        f=3.12345678912345;  //:报错required float
        f=3.12345678912345f;  //:3.1234567
        System.out.println(d);
        System.out.println(f);

        //注意要用单引号  占用2字节
        char c='a';

        boolean bo=true;
//        if (1){}  报错：与c不同在java中，boolean类型与整型不能相互转换

        //java输入
        Scanner in=new Scanner(System.in);
        String s1=in.nextLine();
        System.out.println(s1);

        System.out.printf("xie jie hui is %d years old",21);
        //格式化输出字符串，8代表宽8位，2代表保留2为小数
        System.out.printf("the shirt sell %8.2f yuan\n",2.3333);
        //用format创建一个格式化字符串
        String s2=String.format("the shirt sell %8.2f yuan",2.3333);



    }
}
