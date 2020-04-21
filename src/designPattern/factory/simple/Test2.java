package designPattern.factory.simple;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 16:57
 **/
public class Test2 {
    public static void main(String[] args) {

        Circle circle = (Circle) ShapeFactory2.getClass(Circle.class);
        circle.draw();

        Rectangle rectangle = (Rectangle) ShapeFactory2.getClass(Rectangle.class);
        rectangle.draw();

        Square square = (Square) ShapeFactory2.getClass(Square.class);
        square.draw();
    }
}
