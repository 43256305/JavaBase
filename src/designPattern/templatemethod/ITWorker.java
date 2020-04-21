package designPattern.templatemethod;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 15:45
 **/
public class ITWorker extends Worker {
    public ITWorker(String name)
    {
        super(name);
    }

    @Override
    public void work()
    {
        System.out.println(name + "写程序-测bug-fix bug");
    }

    //钩子
    @Override
    public boolean isNeedPrintDate()
    {
        return true;
    }

}
