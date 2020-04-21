package designPattern.decorator;

/**
 * @program: type_
 * @description:  // 具体的对象，该对象将被附加一些额外的操作
 * @author: xjh
 * @create: 2020-04-20 14:25
 **/
public class Jane implements People {
    public void wear() {
        System.out.println("今天该穿什么呢?");
    }
}
