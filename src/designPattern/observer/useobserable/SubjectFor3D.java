package designPattern.observer.useobserable;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.Observable;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 15:07
 **/
public class SubjectFor3D extends Observable {
    private String msg;

    public void setMsg(String msg){
        this.msg=msg;
        setChanged();
        notifyObservers();
    }

    public String getMsg(){
        return msg;
    }
}
