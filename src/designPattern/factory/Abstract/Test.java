package designPattern.factory.Abstract;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 17:19
 **/
public class Test {
    public static void main(String[] args) {

        Factory factory;
        Gun gun;
        Bullet bullet;

        factory =new AK_Factory();
        bullet=factory.produceBullet();
        bullet.load();
        gun=factory.produceGun();
        gun.shooting();

    }
}
