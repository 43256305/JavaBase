package designPattern.templatemethod;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 15:47
 **/
public class ManagerWorker extends Worker {
    public ManagerWorker(String name)
    {
        super(name);
    }

    @Override
    public void work()
    {
        System.out.println(name + "打dota...");
    }
}
