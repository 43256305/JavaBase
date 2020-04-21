package designPattern.factory.factorymethod;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 17:03
 **/
public class SquareFactory implements Factory {
    @Override
    public Shape getShape() {
        // TODO Auto-generated method stub
        return new Square();
    }
}
