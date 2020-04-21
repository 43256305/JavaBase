package designPattern.facade;

import java.util.logging.Logger;

/**
 * @program: type_
 * @description: 提供一个统一的接口，用来访问子系统中的一群接口，外观定义了一个高层的接口，让子系统更容易使用。其实就是为了方便客户
 * 的使用，把一群操作，封装成一个方法。
 *子系统：cpu，disk，memory（每个都有启动关闭方法）  统一接口：computer（只有两个方法启动关闭）
 *
 * 有点：1.使得客户端和子系统之间解耦，让子系统内部的模块功能更容易扩展和维护；2.客户端根本不需要知道子系统内部的实现，或者根本不需要知道
 * 子系统内部的构成，它只需要跟Facade类交互即可。3.隐藏子系统内部细节
 * @author: xjh
 * @create: 2020-04-20 14:48
 **/
public class Computer {

    private CPU cpu;
    private Memory memory;
    private Disk disk;
    public Computer()
    {
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }
    public void start()
    {
        System.out.println("Computer start begin");
        cpu.start();
        disk.start();
        memory.start();
        System.out.println("Computer start end");
    }

    public void shutDown()
    {
        System.out.println("Computer shutDown begin");
        cpu.shutDown();
        disk.shutDown();
        memory.shutDown();
        System.out.println("Computer shutDown end...");
    }
}
