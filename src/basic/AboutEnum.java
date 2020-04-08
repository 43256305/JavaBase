package basic;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: type_
 * @description: 对枚举的介绍
 * @author: xjh
 * @create: 2020-03-07 23:18
 **/
public class AboutEnum {
    //枚举只能当成员变量，默认从0开始，java会自动帮枚举加上static修饰符（这就是为什么枚举不能当局部变量），所以不用实例化此类就能访问Color
    enum Color{RED,YELLOW,BLUE};

    public static void main(String[] args) {
        //直接输出会自动帮你调用toString()
        System.out.println(Color.RED);  //:RED
        System.out.println(Color.RED.toString());  //:RED
        System.out.println(Color.RED.ordinal());  //:0
        //Color.values  以数组的形式返回所有枚举实例
        for (Color c:Color.values()){
            System.out.println(c+"  "+c.ordinal());  //RED  0,YELLOW  1,BLUE  2
        }

        Color GREEN=Color.RED;
        System.out.println(GREEN+"  "+GREEN.ordinal());  //:RED  0
        System.out.println(GREEN.equals(Color.RED));  //:true
        //输出枚举大小
        System.out.println(Color.values().length);  //:3

        //使用自定义枚举类
        for (ErrorCodeEn s : ErrorCodeEn.values()) {
            System.out.println("code: " + s.getCode() + ", description: " + s.getDescription());  //：code: 0, description: 成功
        }

        getTrafficInstruct(Color.RED);  //:红灯

        EnumSet<ErrorCodeEn> errSet=EnumSet.allOf(ErrorCodeEn.class);
        for (ErrorCodeEn e:errSet){
            System.out.println(e.name()+"  "+e.ordinal());  //:OK  0,ERROR_A  1,ERROR_B  2
        }

        // EnumMap的使用
        System.out.println("EnumMap展示");
        EnumMap<AboutEnum.Color, String> errMap = new EnumMap(AboutEnum.Color.class);
        //让Map以（enum,String）的方式，这样可以直接用枚举来取相对应的String
        errMap.put(AboutEnum.Color.RED, "红灯");
        errMap.put(AboutEnum.Color.YELLOW, "黄灯");
        errMap.put(AboutEnum.Color.BLUE, "蓝灯");
        for (Iterator<Map.Entry<Color, String>> iter = errMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<AboutEnum.Color, String> entry = iter.next();
            System.out.println(entry.getKey().name() + " : " + entry.getValue());  //：RED ： 红灯
        }
        System.out.println(errMap.get(Color.RED));  //:红灯

        //访问接口中的枚举
        System.out.println(Plant.Fruit.APPLE);  //:APPLE

        //单例模式，只有一个实例：instance
        SingletonEnum.instance.singletonMethod();

    }

    //枚举常与switch结合使用
    public static void getTrafficInstruct(Color color){
        switch (color){
            case RED:
                System.out.println("红灯");
                break;
            case BLUE:
                System.out.println("蓝灯");
                break;
            case YELLOW:
                System.out.println("黄灯");
                break;
        }
    }
}

//自定义一个枚举对象，自己赋值，添加自定义方法
//枚举可以实现接口，枚举不能继承，因为所有的枚举都继承自java.lang.Enum
enum ErrorCodeEn{
    //必须先定义实例，在实例后面放置方法   注意与后面的code，description对应：注意这是枚举实例，下面的是重载的枚举构造方法，实例就是对构造方法的调用
    OK(0, "成功"),
    ERROR_A(100, "错误A"),
    ERROR_B(200, "错误B");

    //这是每个自定义枚举都有的属性
    private int code;
    private String description;
    //构造方法  枚举构造方法只能为private，且可以省略
    private ErrorCodeEn(int number, String description) {
        this.code = number;
        this.description = description;
    }

    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    //静态方法
    public static void ouput(){
        System.out.println("枚举");
    }

}

interface INumberEnum {
    int getCode();
    String getDescription();
}

//可以将类型相近的枚举通过接口或类组织起来。
//Java接口在编译时会自动为enum类型加上public static修饰符；Java类在编译时会自动为 enum 类型加上static修饰符。看出差异了吗？没错，就是说，
// 在类中组织 enum，如果你不给它修饰为 public，那么只能在本包中进行访问
interface Plant {
    //枚举实现接口
    enum Vegetable implements INumberEnum {
        POTATO(0, "土豆"),
        TOMATO(0, "西红柿");

        Vegetable(int number, String description) {
            this.code = number;
            this.description = description;
        }

        private int code;
        private String description;

        @Override
        public int getCode() {
            return 0;
        }

        @Override
        public String getDescription() {
            return null;
        }
    }

    enum Fruit implements INumberEnum {
        APPLE(0, "苹果"),
        ORANGE(0, "桔子"),
        BANANA(0, "香蕉");

        Fruit(int number, String description) {
            this.code = number;
            this.description = description;
        }

        private int code;
        private String description;

        @Override
        public int getCode() {
            return 0;
        }

        @Override
        public String getDescription() {
            return null;
        }
    }
}

enum SingletonEnum{
    //此枚举的实例
    instance;
    SingletonEnum(){}

    public void singletonMethod(){
        System.out.println("单例模式中的方法");
    }

}

