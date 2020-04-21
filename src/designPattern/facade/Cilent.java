package designPattern.facade;

import java.util.logging.Logger;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 14:48
 **/
public class Cilent {
    public static void main(String[] args)
    {
        Computer computer = new Computer();
        computer.start();
        System.out.println("=================");
        computer.shutDown();
    }
}
