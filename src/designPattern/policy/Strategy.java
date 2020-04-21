package designPattern.policy;

/**
 * @program: type_
 * @description: 定义一系列的算法，把每一个算法封装起来，并且使它们可以相互替换。这个模式中使得各个算法可以独立于使用它的客户而变化
 * 角色：抽象策略，具体策略，具体环境
 * 我们只需要给新的策略继承Strategy，在client中传入Context即可
 * @author: xjh
 * @create: 2020-04-18 20:15
 **/
public interface Strategy {
    int calculate(int a, int b);
}
