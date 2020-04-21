package designPattern.adapter;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 20:31
 **/
public class V220Power {
    /**
     * 提供220V电压
     * @return
     */
    public int provideV220Power() {
        System.out.println("我提供220V交流电压。");
        return 220 ;
    }
}
