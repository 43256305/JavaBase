package designPattern.factory.simple;

import designPattern.factory.factorymethod.Shape;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 16:54
 **/
public class Circle implements Shape {
    public Circle() {
        System.out.println("Circle");
    }
    @Override
    public void draw() {
        System.out.println("Draw Circle");
    }
}
