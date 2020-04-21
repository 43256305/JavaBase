package designPattern.decorator;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 14:24
 **/
public class DecoratorSuit extends Decorator {
    public DecoratorSuit(People people) {
        super(people);
    }

    public void wear() {
        super.wear();
        System.out.println("穿个西服");
    }
}
