package designPattern.factory.factorymethod;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 17:04
 **/
public class Test {
    public static void main(String[] args) {
        Factory circlefactory = new CircleFactory();
        Shape circle = circlefactory.getShape();
        circle.draw();
    }
}
