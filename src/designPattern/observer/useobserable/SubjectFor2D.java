package designPattern.observer.useobserable;

import java.util.Observable;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 15:13
 **/
public class SubjectFor2D extends Observable {
    private String msg;

    public void setMsg(String msg){
        this.msg=msg+" 这是2d哦！";
        setChanged();
        notifyObservers();
    }

    public String getMsg(){
        return msg;
    }
}
