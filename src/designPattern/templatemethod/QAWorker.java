package designPattern.templatemethod;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 15:46
 **/
public class QAWorker extends Worker {
    public QAWorker(String name)
    {
        super(name);
    }

    @Override
    public void work()
    {
        System.out.println(name + "写测试用例-提交bug-写测试用例");
    }
}
