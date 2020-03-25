package object_class;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @program:
 * @description: 日期相关类
 * @author: xjh
 * @create: 2020-03-07 22:50
 **/
public class Time {
    public static void main(String[] args) {
        //输出日期的方法，其实本质都是转换为String，很显然，Date类重写了toString()方法
        System.out.println(new Date());  //:Sat Mar 07 22:54:45 CST 2020
        String date=(new Date()).toString();
        System.out.println(date);  //:Sat Mar 07 22:55:31 CST 2020

        //格式化输出日期时间
        Date date1=new Date();
        //下面模式中，每个字母代表不同信息，如：HH：24小时  E：星期几 a:上下午  可以根据情况自己添加   yyyy.MM.dd E hh:mm:ss
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //重新设置格式
        simpleDateFormat.applyPattern("yyyy.MM.dd E a hh:mm:ss");  //2020.03.09 星期一 下午 02:22:42
        System.out.println(simpleDateFormat.format(date1));  //:2020-03-09 02:09:39
        //字符串转换日期
        try{
            date1=simpleDateFormat.parse("2020-03-09 02:09:39");  //:Mon Mar 09 02:09:39 CST 2020
            System.out.println(date1);
        }catch (ParseException e){
            System.out.println(e.getMessage());
        }



        System.out.println("----------------------------------------------------------");
        //Calendar用法，date用于获取当前所有时间信息，calendar可以单独输出年月日等，设置日期
        Calendar calendar=Calendar.getInstance();
        System.out.println(calendar.get(Calendar.YEAR));  //:2020
        //输出day of month
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));  //:9
        //注意，calendar的月份是从0开始的
        System.out.println(calendar.get(Calendar.MONTH)); //2
        //让日期加一
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));  //:10
        System.out.println(calendar.get(Calendar.DATE));  //:10

        //自己设置时间，而不是用当前时间
        Calendar calendar2=Calendar.getInstance();
        //在设置calendar之前，要clear一下，不然很多会继承系统时间
        calendar2.clear();
        calendar2.set(1999,2,3);  //3月
        //date转换成calendar
        calendar2.setTime(new Date());

        //获取年份，月份等
        Calendar calendar1=Calendar.getInstance();
        System.out.println(calendar1.get(Calendar.DATE));  //:9
        System.out.println(calendar1.get(Calendar.YEAR));  //:2020

        //时间戳转换成时间
        long timeStamp=System.currentTimeMillis();
        System.out.println(timeStamp);  //:1583737616056    时间是用距离一个固定时间点的毫秒数表示的
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd");
        Date date2=new Date(timeStamp);
        System.out.println(simpleDateFormat1.format(date2));  //2020-03-09

        long timeStamp1=date2.getTime();
        System.out.println(timeStamp1);  //:1583737616056


        //如果是JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter代替SimpleDateFormat


    }
}
