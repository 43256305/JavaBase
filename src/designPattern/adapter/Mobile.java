package designPattern.adapter;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 20:30
 **/
public class Mobile {
    /**
     * 充电
     * @param power
     */
    public void inputPower(V5Power power)
    {
        int provideV5Power = power.provideV5Power();
        System.out.println("手机（客户端）：我需要5V电压充电，现在是-->" + provideV5Power + "V");
    }

}
