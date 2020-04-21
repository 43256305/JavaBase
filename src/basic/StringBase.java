package basic;

public class StringBase {
    //介绍String基本知识和常用方法
    public static void main(String[] args) {
        comKnowledge();
        comMethods();

    }

    public static void comKnowledge(){
        //String基本理解：：
        String a="hello";
//字符串与数字相加自动转换为字符串相加
        String b=a+2;
//https://www.jianshu.com/p/88aa19fc21c6  字符串相加（+，+=）
//https://segmentfault.com/a/1190000009888357  String常量池
//a与2相加，因为字符串是不可变的，所以会产生新的对象（中间要通过StringBuilder在用toString()，要处理大量字符串相加最好直接用StringBuilder）
        System.out.println(b);  //hello2
        final String g="po";
//而这种情况会直接连接作为常量。（中间没有生成新的对象），也就是说，只有常量相加，才不会生成新的String对象（直接用常量池中的，而对象在堆中）
        String f="kkk"+"kjk"+"lkj"+g;
        String str1="kkkkjklkjpo";
        System.out.println(str1==f);  //:true

//此步骤生成了一个或两个对象，一个String在常量池中（如果常量池中已经存在则不重新创建），一个h指向的对象在堆中，注意，常量池中的String也是对象
        String h=new String("kkk");

//直接赋值（没有使用new），c指向常量池（也就是说String类型的值不能改变，赋值也只是改变了引用而已）
//常量池是为了避免频繁的创建和销毁对象而影响系统性能，其实现了对象的共享。例如字符串常量池，在编译阶段就把所有的字符串文字放到一个常量池中
        String c="hello";
        String d="hel"+"lo";
//e指向堆内存   堆内存用于存放由new创建的对象和数组，空间大。
//在函数中定义的基本类型的变量和对象的引用变量都是在函数的栈内存中分配，速度比堆快。
//堆是用来存放对象的，栈是用来存放执行程序的
        String e=new String("hello");
        System.out.println(c==d);  //:true
        System.out.println(c==e);  //:false
//有以上可知，==号比较的是栈内存中的值

        d=c;
        c="kkk";
        System.out.println(d==c);  //false

//String.intern()  如果此字符串或者字符串的值在常量池中，则返回常量池中那个字符串对象的引用
// 如果此字符串没在常量池中且常量池也没有值等于此字符串的对象，则在常量池中增加一个字符串对象
        String i="pppp";
        String j=i.intern();
        System.out.println(j);  //:pppp
        System.out.println(i==j);  //:true
        String k=new String("pppp");
        System.out.println(i==k.intern());   //:true

//StringBuffer（线程同步（方法前有synchronized），速度慢），StringBuilder（线程不同步，快），两者都可以改变字符串的值
//速度：StringBuilder>StringBuffer>String(因为String每次都要new对象)

    }

    public static void comMethods(){
        //String常用方法：：https://blog.csdn.net/jakezhang1990/article/details/56836240
        //转换成char类型数组
        "qwer".toCharArray();
        //char数组转换为字符串(注意用toString会返回地址)
        char[] ch={'1','2','3'};
        new String(ch);
        //取指定位置字符
        "qwer".charAt(2);
        byte[] by=null;
        try{
            //返回byte数组（根据阿斯克码），注意指定编码格式
            by="qwer".getBytes("utf-8");
        }catch (Exception E){

        }
//        byte[] by={1,2,3,4,5};  注意，自己用数字随便写的字节流文件很可能解析不出意思（12345代表什么呢），最后会乱码
        try{
            //byte数组转换为字符串
            System.out.println(new String(by,"utf-8"));  //：qwer
        }catch (Exception ex){

        }
        //返回字符/字符串存在的位置
        System.out.println("qwer".indexOf('e'));//:2
        System.out.println("qwer".indexOf("kk"));//:-1
        //字符截取
        System.out.println("qwer".substring(1,3));//:we
        //大小写转换
        "QWER".toLowerCase();
        "qwer".toUpperCase();
        //判断是否以指定的字符串开头或结尾
        System.out.println("qwer".startsWith("r"));//:false
        System.out.println("qwer".endsWith("r"));//:true
//重点，判断字符串是否相等，建议已知放在前
        "qwer".equals(new String("qwer"));
        //不区分大小写
        System.out.println("QWER".equalsIgnoreCase("qwer"));  //:true
        //拆分，替换请看regex

    }
}
