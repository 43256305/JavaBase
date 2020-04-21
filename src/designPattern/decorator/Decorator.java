package designPattern.decorator;

/**
 * @program: type_
 * @description:作用：当我们设计好了一个类，我们需要给这个类添加一些辅助的功能，并且不希望改变这个类的代码，这时候就是装饰者模
 * 式大展雄威的时候了。这里还体现了一个原则：类应该对扩展开放，对修改关闭。
 * 是继承关系的一个替代方案。就增加功能来说，Decorator模式比生成子类更为灵活。
 * 角色：抽象组件，具体组件，装饰，具体装饰
 *
 * @author: xjh
 * @create: 2020-04-20 14:23
 **/
public class Decorator implements People {
    private People people;

    public Decorator(People people) {
        this.people = people;
    }
    public void wear() {
        people.wear();
    }
}
