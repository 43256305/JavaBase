package designPattern.factory.factorymethod;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 17:02
 **/
public class CircleFactory implements Factory {
    @Override
    public Shape getShape() {
        // TODO Auto-generated method stub
        return new Circle();
    }
}
