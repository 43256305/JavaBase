package designPattern.decorator;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 14:25
 **/
public class Test {
    public static void main(String[] args) {
        //具体装饰者调用具体组件，顺便在调用周围添加一些功能
        People p1 = new DecoratorSuit(new DecoratorShirt(new Jane()));
        p1.wear();
        System.out.println("--------------");
        People p2 = new DecoratorShirt(new Jane());
        p2.wear();
        System.out.println("--------------");
        People p4=new DecoratorSuit(new DecoratorTShirt(new DecoratorShirt(new Jane())));
        p4.wear();
    }
}
