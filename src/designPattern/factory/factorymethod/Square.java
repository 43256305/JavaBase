package designPattern.factory.factorymethod;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 16:55
 **/
public class Square implements Shape {
    public Square() {
        System.out.println("Square");
    }

    @Override
    public void draw() {
        System.out.println("Draw Square");
    }
}
