package designPattern.factory.Abstract;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 17:18
 **/
public class M4A1_Factory implements Factory {
    @Override
    public Gun produceGun() {
        return new M4A1();
    }

    @Override
    public Bullet produceBullet() {
        return new M4A1_Bullet();
    }
}
