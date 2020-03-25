package basic;

import java.util.ArrayList;

//包裹类和常量池
public class WraAndConPoo {
    public static void main(String[] args) {
        wrapper();
        constantPool();
    }

//包裹类
    public static void wrapper() {
        //包装类为final，因此不能继承，同时对象包装器类是不可变的，即一旦构造了包装器，就不允许更改包装在其中的值
        Integer I1=new Integer(40);
        //转换成int，拆箱
        int i1=I1;
        i1=I1.intValue();
        //拆箱直接返回Integer中的value属性
        System.out.println(i1);  //:40
        //转换为String
        String s1=I1.toString();

        //包装类可以用在范类中
        ArrayList<Integer> al=new ArrayList<>();
        //装箱
        al.add(1);
        al.add(Integer.valueOf(1));
        //自动装箱过程：先判断是否在-128-127之间，如果是直接从IntegerCache.cache中获取包装类并返回，如果不是，生成一个新的Integer
        //只有Double和Float没有用缓存机制，其他几种都实现了缓存

        //比较大小
        Integer I2=999;
        Integer I3=999;
        System.out.println(I2.equals(I3));  //:true
        //加法
        System.out.println(I2+I3);  //:1998

        //注意加上l
        Long l=999l;

        //为什么s没变呢？s传过去的是引用的拷贝，而String是不可变类型，引用的拷贝指向了新的字符串，而s还是指向ppp
        //Integer同理，Integer也是不可变类型，函数里面的value也指向了新的对象
        Integer y=new Integer(8);
        String s="ppp";
        changeInteger(y,s);
        System.out.println(y);  //:8
        System.out.println(s);   //:ppp

        System.out.println("-------------------------------------");

    }

//常量池   https://blog.csdn.net/zm13007310400/article/details/77534349
    public static void constantPool(){
//字符串常量池，jdk7后的字符串常量池放到堆中了
//实现字符串常量池的是一个StringTable，他是一个Hash表，被所有类所共享，默认值为1009，StringTable中放的是字符串常量或者堆内字符串对象的引用

//class常量池 每一个类被编译后就会形成一个class文件，class文件中除了包含类的版本、字段、方法、接口等描述信息外，
// 还有一项信息就是常量池(constant pool table)，用于存放编译器生成的各种字面量和符号引用
//字面量：文本字符串，8种基本类型的值（不是包装类），被声明为final的常量  符号引用：类，方法，字段（成员变量）的名称和描述符
        //包装类的常量池和String不同的点在相加时，String会生成StringBuilder生成新的对象，而包装类的常量相加只要结果相等且[-128,127]最后也相等
        //不管什么，只要用到了new，就永远不可能==别的对象
        Integer i1=20;
        Integer i2=20;
        Integer i3=new Integer(20);
        //基本类型的包装类的大部分也都实现了常量池技术，即Byte,Short,Integer,Long,Character,Boolean（只有Float和Double没有）
        System.out.println(i1==i2);  //:true
        System.out.println(i1==i3);  //:false
        Integer i6=10;
        Integer i7=10;
        Integer i8=i7+i6;
        //而包装类的常量相加只要结果相等且[-128,127]最后也相等
        System.out.println(i1==i8);  //：true

        Integer i4=999;
        Integer i5=999;
        //当超出[-128,127]的数值范围后仍会创建新的对象，也就是说这6种包装类默认创建了数值[-128，127]的相应类型的缓存数据
        System.out.println(i4==i5);  //false

        //Long要在数字后加上l
        final Long l1=999L;
        final Long l2=999L;
        //常量但是大于127也不会相等
        System.out.println(l1==l2);  //：false
//理解：相当于存储了256个数字，只要计算处于范围内，包装类的对象都会指向这几个数字，而不会新创建对象。而字符串就不能实现了，因为字符串太多了。


//运行时常量池存在于内存中，也就是class常量池被加载到内存之后的版本，不同之处是：它的字面量可以动态的添加(String#intern()),符号引用可以被解析为直接引用
//在解析阶段，会把符号引用替换为直接引用，解析的过程会去查询字符串常量池，也就是我们上面所说的StringTable，以保证运行时常量池所引用的字符串与字符串常量池中是一致的。

    }

    public static void changeInteger(Integer value,String s){
        value=3*value;
        s="kkk";
    }
}
