package designPattern.factory.Abstract;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 17:17
 **/
public class M4A1_Bullet implements Bullet {
    @Override
    public void load() {
        System.out.println("Load bullets with M4A1");
    }
}
