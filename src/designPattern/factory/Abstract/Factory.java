package designPattern.factory.Abstract;

/**
* @Description: 角色：抽象工厂，具体工厂，抽象产品，具体产品
 * 使用场景：1.和工厂方法一样客户端不需要知道它所创建的对象的类。2.需要一组对象共同完成某种功能时，并且可能存在多组对象完成不同功能的情况。
 * （同属于同一个产品族的产品） 3.系统结构稳定，不会频繁的增加对象。（因为一旦增加就需要修改原有代码，不符合开闭原则）
 *抽象工厂和工厂方法不同地方在于，抽象工厂不只生产一种产品
* @Param:
* @return:
* @Author: xjh
* @Date: 2020/4/18
*/
public interface Factory {
    public Gun produceGun();
    public Bullet produceBullet();
}
