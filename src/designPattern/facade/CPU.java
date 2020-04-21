package designPattern.facade;

import java.util.logging.Logger;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 14:45
 **/
public class CPU {
    public void start()
    {
        System.out.println("cpu is start...");
    }

    public void shutDown()
    {
        System.out.println("CPU is shutDown...");
    }
}
