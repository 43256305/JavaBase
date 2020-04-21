package designPattern.state.wrongexample;

import designPattern.state.wrongexample.VendingMachine;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 16:02
 **/
public class Test1 {
    public static void main(String[] args)
    {
        VendingMachine machine = new VendingMachine(10);
        machine.insertMoney();
        machine.backMoney();

        System.out.println("-----------");

        machine.insertMoney();
        machine.turnCrank();

        System.out.println("----------压力测试-----");
        machine.insertMoney();
        machine.insertMoney();
        machine.turnCrank();
        machine.turnCrank();
        machine.backMoney();
        machine.turnCrank();

    }
}
