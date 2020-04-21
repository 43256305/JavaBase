package designPattern.decorator;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 14:24
 **/
public class DecoratorShirt extends Decorator {
    public DecoratorShirt(People people) {
        super(people);
    }

    public void wear() {
        super.wear();
        System.out.println("穿个衬衫");
    }
}
