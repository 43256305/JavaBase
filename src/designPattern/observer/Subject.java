package designPattern.observer;

/**
* @Description: 主题接口，所有的主题必须实现此接口
 * 观察者模式定义：定义了对象之间的一对多的依赖，这样一来，当subject改变时，它的所有的依赖者都会收到通知并自动更新。
 * 主题接口：注册观察者，删除观察者，通知观察者
* @Param: 
* @return: 
* @Author: xjh
* @Date: 2020/4/18
*/
public interface Subject {
    /**
     * 注册一个观察着
     *
     * @param observer
     */
    public void registerObserver(Observer observer);

    /**
     * 移除一个观察者
     *
     * @param observer
     */
    public void removeObserver(Observer observer);

    /**
     * 通知所有的观察者
     */
    public void notifyObservers();
}
