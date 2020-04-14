package multithreading;


import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-05 16:56
 **/
public class CallableAndFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        ArrayList<Future<String>> results=new ArrayList<>();  //Future相当于接受Callable接口返回值的容器
        for (int i=0;i<5;i++){
            //submit返回Future对象
            results.add(executorService.submit(new TaskWithResult(i)));   //执行Callable接口类用的是submit，而Runable用的是execute
        }
        for (Future<String> f:results){
            if (f.isDone()){  //判断是否运行结束
                System.out.println(f.get());  //通过Future的get方法获取运行结果，如果没有结束，get会阻塞直到任务结束
            }else{
                System.out.println("Future result is not yet complete");
            }
        }

        //FutureTask的使用  相当于callable与future的结合（事实上他确实实现了Runable和Future这两个接口）
        FutureTask<String> futureTask=new FutureTask<String>(new TaskWithResult(10));
        executorService.submit(futureTask);
        System.out.println(futureTask.get());
        executorService.shutdown();

    }
}

//Callable接口里面只有一个call()方法
//Callable接口与Runable不同的是，前者线程执行完毕后可以返回数据或者抛出异常，且Callable要与ExecutorService，Future一起使用
class TaskWithResult implements Callable<String> {
    private int i;
    public TaskWithResult(int i){
        this.i=i;
    }
    @Override
    public String call() throws Exception {
//        Thread.sleep(1000);
        return "TaskWithResult:"+i;
    }
}