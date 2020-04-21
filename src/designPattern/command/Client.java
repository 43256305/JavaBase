package designPattern.command;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 13:36
 **/
public class Client {
    //接受者传进命令，命令传进调用者，调用者执行动作（客户端执行调用者，调用者执行命令，命令执行接收者）
    public static void main(String[] args) {
        //创建接收者
        Receiver receiver = new Receiver();
        //创建命令对象，设定其接收者
        Command command = new ConcreteCommand(receiver);
        //创建请求者，把命令对象设置进去
        Invoker invoker = new Invoker(command);
        //执行方法
        invoker.action();
    }
}
