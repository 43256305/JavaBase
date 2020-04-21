package designPattern.observer.useobserable;

import java.util.Observable;
import java.util.Observer;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 15:09
 **/
public class Observer1 implements Observer {

    public Observer1(Observable o){
        o.addObserver(this);
    }

    /**
    * @Description: 由于subject有两个，所有要用instanceof判断到底是那个
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/18
    */
    @Override
    public void update(Observable o, Object arg) {

        //如果传入的是3D
        if (o instanceof SubjectFor3D)
        {
            SubjectFor3D subjectFor3d = (SubjectFor3D) o;
            System.out.println("subjectFor3d's msg -- >" + subjectFor3d.getMsg());
        }

        //如果传入的是2D
        if (o instanceof SubjectFor2D)
        {
            SubjectFor2D subjectFor2d = (SubjectFor2D) o;
            System.out.println("subjectFor2d's msg -- >" + subjectFor2d.getMsg());
        }
    }
}
