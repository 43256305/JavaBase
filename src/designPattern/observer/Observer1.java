package designPattern.observer;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 14:43
 **/
public class Observer1 implements Observer{
    private Subject subject;

    /**
    * @Description: 构造器：把自己加入Subject
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/18
    */
    public Observer1(Subject subject)
    {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg)
    {
        System.out.println("observer1收到更新：  -->" + msg );
    }

}
