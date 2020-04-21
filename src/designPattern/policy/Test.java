package designPattern.policy;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 20:17
 **/
public class Test {
    public static void main(String[] args) {
        Strategy addStrategy = new AddStrategy();
        //把add策略传进去
        Context context = new Context(addStrategy);
        // 输出3
        System.out.println(context.calculate(1, 2));

        Strategy subStrategy = new SubtractStrategy();
        // 动态替换算法(策略)
        context.replaceStrategy(subStrategy);
        // 输出-1
        System.out.println(context.calculate(1, 2));
    }
}
