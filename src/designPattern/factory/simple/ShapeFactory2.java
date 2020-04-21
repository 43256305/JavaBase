package designPattern.factory.simple;

import designPattern.factory.factorymethod.Shape;

/**
 * @program: type_
 * @description: 使用反射
 * @author: xjh
 * @create: 2020-04-18 16:56
 **/
public class ShapeFactory2 {
    public static Object getClass(Class<? extends Shape> clazz) {
        Object obj = null;

        try {
            obj = Class.forName(clazz.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
