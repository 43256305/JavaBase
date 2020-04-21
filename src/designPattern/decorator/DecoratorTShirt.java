package designPattern.decorator;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 14:25
 **/
public class DecoratorTShirt extends Decorator {
    public DecoratorTShirt(People people) {
        super(people);
    }

    public void wear() {
        super.wear();
        System.out.println("穿个T-Shirt");
    }
}
