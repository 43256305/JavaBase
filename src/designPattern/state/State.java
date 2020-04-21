package designPattern.state;

/*
*当对象的内部状态改变时，它的行为跟随状态的改变而改变了，看起来好像重新初始化了一个类似的。
* 状态的转换基本上都是内部行为，主要在状态模式内部来维护。
*状态模式的功能就是分离状态的行为，通过维护状态的变化，来调用不同状态对应的不同功能。也就是说，状态和行为是相关联的，它们的关系可以
* 描述为：状态决定行为。
* 抽象状态，具体状态，环境
*
* 每个状态都有几个动作，这几个动作也是context的动作，context根据多态调用不同状态对象的方法，不同的状态对象内部方法都有转移
* 到其他状态的代码
* */
public interface State {
    /**
     * 放钱
     */
    public void insertMoney();
    /**
     * 退钱
     */
    public void backMoney();
    /**
     * 转动曲柄
     */
    public void turnCrank();
    /**
     * 出商品
     */
    public void dispense();
}
