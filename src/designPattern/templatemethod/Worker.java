package designPattern.templatemethod;

import java.util.Date;

/**
 * @program: type_
 * @description: 定义了一个算法的骨架，而将一些步骤延迟到子类中，模版方法使得子类可以在不改变算法结构的情况下，重新定义算法的步骤。
 * 抽象模板，具体模板     具体模板中实现了抽象模板定义的抽象方法
 * @author: xjh
 * @create: 2020-04-20 15:45
 **/
public abstract class Worker {
    protected String name;

    public Worker(String name)
    {
        this.name = name;
    }

    /**
     * 记录一天的工作
     */
    public final void workOneDay() {

        System.out.println("-----------------work start ---------------");
        enterCompany();
        computerOn();
        work();
        computerOff();
        exitCompany();
        System.out.println("-----------------work end ---------------");

    }

    /**
     * 工作   抽象方法
     */
    public abstract void work();

    /**
     * 关闭电脑
     */
    private void computerOff()
    {
        System.out.println(name + "关闭电脑");
    }

    /**
     * 打开电脑
     */
    private void computerOn()
    {
        System.out.println(name + "打开电脑");
    }

    /**
     * 进入公司
     */
    public void enterCompany()
    {
        System.out.println(name + "进入公司");
    }

    /**
     * 离开公司
     */
//    public void exitCompany()
//    {
//        System.out.println(name + "离开公司");
//    }

    //实现此方法，并让他返回true，就可以打印离开公司时间
    public boolean isNeedPrintDate()
    {
        return false;
    }
    /**
     * 离开公司   定义钩子
     */
    public void exitCompany()
    {
        if (isNeedPrintDate())
        {
            System.out.print(new Date().toLocaleString()+"-->");
        }
        System.out.println(name + "离开公司");
    }

}
