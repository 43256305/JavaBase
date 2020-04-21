package designPattern.observer;

/**
* @Description: 所有的观察者需要实现此接口
 * 观察者接口：更新消息
* @Param: 
* @return: 
* @Author: xjh
* @Date: 2020/4/18
*/
public interface Observer {
    public void update(String msg);
}
