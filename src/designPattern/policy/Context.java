package designPattern.policy;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 20:16
 **/
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    // 动态替换算法(策略)
    public void replaceStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(int a, int b) {
        return strategy.calculate(a, b);
    }
}
