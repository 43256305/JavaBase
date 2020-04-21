package designPattern.command;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 13:35
 **/
public class Invoker {
    /**
     * 持有命令对象
     */
    private Command command = null;
    /**
     * 构造方法
     * @param command
     */
    public Invoker(Command command) {
        this.command = command;
    }
    /**
     * 行动方法
     */
    public void action() {
        command.execute();
    }
}
