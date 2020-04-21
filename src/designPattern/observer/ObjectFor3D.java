package designPattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 14:41
 **/
public class ObjectFor3D implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    /**
     * 3D彩票的号码
     */
    private String msg;

    @Override
    public void registerObserver(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer)
    {
        int index = observers.indexOf(observer);
        if (index >= 0)
        {
            observers.remove(index);
        }
    }

    /**
    * @Description: 通知观察者：调用了所有观察者的update方法
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/18
    */
    @Override
    public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.update(msg);
        }
    }

    /**
     * 主题更新消息：调用通知观察者方法
     *
     * @param msg
     */
    public void setMsg(String msg)
    {
        this.msg = msg;

        notifyObservers();
    }
}