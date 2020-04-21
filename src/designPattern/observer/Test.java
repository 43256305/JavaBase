package designPattern.observer;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 14:44
 **/
public class Test {
    public static void main(String[] args) {
        //模拟一个3D的服务号
        ObjectFor3D subjectFor3d = new ObjectFor3D();
        //new 客户并把自己加入Subject
        Observer observer1 = new Observer1(subjectFor3d);
        Observer observer2 = new Observer2(subjectFor3d);

        //subject调用setMsg更新，setmsg调用通知方法，通知方法通知本subject的所有观察者
        subjectFor3d.setMsg("第一次更新message" );
        subjectFor3d.setMsg("第二次更新message" );
    }
}
