package designPattern.factory.Abstract;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 17:18
 **/
public class AK_Factory implements Factory {
    @Override
    public Gun produceGun() {
        return new AK();
    }

    @Override
    public Bullet produceBullet() {
        return new AK_Bullet();
    }
}
