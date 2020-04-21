package designPattern.command;

/**
 * @program: type_
 * @description:命令模式把一个请求或者操作封装到一个对象中。命令模式允许系统使用不同的请求把客户端参数化，对请求排队或者记录请求
 * 日志，可以提供命令的撤销和恢复功能。
 * 每一个命令都是一个操作：请求的一方发出请求要求执行一个操作；接收的一方接收到请求，并执行操作。命令模式允许请求的一方和接收的一方
 * 独立开来，使得请求的一方不必知道接收请求的一方的接口，更不必知道请求是怎么被接收、以及操作是否被执行、何时被执行、怎么被执行的。
 *优点：1.命令模式使新的命令很容易被加入到系统里。2.允许接受请求的一方决定是否要否决请求。3.能较容易的设计一个命令队列。
 * 4.可以容易的实现对请求的撤销和恢复。5.在需要的情况下，可以较容易的将命令记入日志。
 *
 *角色：客户端，命令，具体命令，接收者，调用者
 *
 * https://www.jianshu.com/p/5901e76a6348
 * @author: xjh
 * @create: 2020-04-20 13:34
 **/
public class Receiver {
    /**
     * 真正执行命令相应的操作
     */
    public void action() {
        System.out.println("执行操作");
    }
}
