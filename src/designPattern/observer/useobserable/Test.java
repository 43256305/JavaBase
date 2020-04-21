package designPattern.observer.useobserable;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 15:16
 **/
public class Test {
    public static void main(String[] args) {
        SubjectFor2D d2=new SubjectFor2D();
        SubjectFor3D d3=new SubjectFor3D();

        Observer1 o1=new Observer1(d2);
        d3.addObserver(o1);  //同时订阅3d

        d2.setMsg("2d更新内容");
        d3.setMsg("3d更新内容");
    }
}
