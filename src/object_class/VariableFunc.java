package object_class;

/**
 * @program:
 * @description: 参数可变的方法
 * @author: xjh
 * @create: 2020-03-10 20:36
 **/
public class VariableFunc {
    public static void main(String[] args) {
        //后面的参数可是是多个
        System.out.printf("xie jie hui is %d years old\n",20);
        print(12,3,4,5);  //:12 3 4 5
        int[] array={1,2,3,4};
        //允许把数组传递给可变参函数
        print(array);  //:1 2 3 4
        //如果函数没用三个点，而是用数组，则必须传入数组
        print1(array);  //1234
//        print1(1,2,3,4);  //:报错
    }

    public static void print(int ... args){
        for (int a:args){
            System.out.println(a);
        }
    }

    public static void print1(int[] args){
        for (int s:args){
            System.out.print(s);
        }
    }
}
