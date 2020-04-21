package designPattern.policy;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 20:16
 **/
public class AddStrategy implements Strategy {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
