package designPattern.factory.simple;

import designPattern.factory.factorymethod.Shape;

/**
 * @program: type_
 * @description:简单工厂模式：1.解耦：把对象的创建和使用的过程分开（聚合变为依赖）
 * 当新加图形时，必须要修改工厂类的代码，所以违背了开闭原则（即增加新功能老代码不能改变）
 * 角色：抽象产品，具体产品，具体工厂
 * 适用场景：1，需要创建的对象较少。  2.客户端不关心对象的创建过程。
 * @author: xjh
 * @create: 2020-04-18 16:56
 **/
public class ShapeFactory {
    // 使用 getShape 方法获取形状类型的对象
    public static Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
