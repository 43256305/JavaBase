//当没有创建包时，java文件会放置在默认的包中
package object_class;
//静态导入
import static java.lang.System.*;

/**
 * @program:
 * @description: 用户自定义类
 * @author: xjh
 * @create: 2020-03-10 11:01
 **/
public class CusClass {
    private int att1;
    private String att2;
    
    //如果一个类没有编写构造器，那个系统就会提供一个无参数构造器，这个构造器会将所有成员变量设置为初始值（0或null）
    //事实上无论无论什么构造器，java都会给成员变量一个默认值，比如调用下面的
    private CusClass(){
        //构造器也能私有，这种只运行自己调用，别人不能调用这个空构造器
    }

    public CusClass(int a1){
        //this可以调用另一个构造器或者所有方法，还可以访问此对象所有属性。
        this();
        this.att1=a1;
    }

    public CusClass(int a1,String a2){
        this(a1);
        this.att2=a2;
    }

    public int getAtt1() {
        return att1;
    }

    public String getAtt2() {
        return att2;
    }

    public static void main(String[] args) {
        CusClass cusClass=new CusClass();
        System.out.println(cusClass.getAtt2());  //:null

        CusClass cusClass1=new CusClass(1);
        System.out.println(cusClass1.getAtt2());  //:null
        //由以上可以看出，无论调用什么构造器，java都会给所有成员变量初始化

        out.println("静态导入后不用写类名就可以调用静态方法");

    }

    public void aboutObject(){
        Object o=new Object();
        //Object的方法：toString，wait，notify,notifyAll,getClass,equals,hashCode(注意还有clone方法，只是不能直接调用)
        //clone() 是native方法，即java调用非java接口的代码（如c）就会用native修饰
    }


}
