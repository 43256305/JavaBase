package designPattern.factory.simple;

import designPattern.factory.factorymethod.Shape;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 16:55
 **/
public class Rectangle implements Shape {
    public Rectangle() {
        System.out.println("Rectangle");
    }
    @Override
    public void draw() {
        System.out.println("Draw Rectangle");
    }
}
