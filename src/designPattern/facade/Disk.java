package designPattern.facade;

import java.util.logging.Logger;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 14:46
 **/
public class Disk {
    public void start()
    {
        System.out.println("Disk is start...");
    }

    public void shutDown()
    {
        System.out.println("Disk is shutDown...");
    }
}
