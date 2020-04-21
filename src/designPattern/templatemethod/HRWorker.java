package designPattern.templatemethod;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 15:46
 **/
public class HRWorker extends Worker {
    public HRWorker(String name)
    {
        super(name);
    }

    @Override
    public void work()
    {
        System.out.println(name + "看简历-打电话-接电话");
    }
}
