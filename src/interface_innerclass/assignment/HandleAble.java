package interface_innerclass.assignment;
/**
* @Description: 定义HandleAble接口，具备一个处理字符串数字的抽象方法方法HandleString（String num）  处理方式1：取整数部分。  处理方式2：保留指定位小数，四舍五入。
* @Param: 
* @return: 
* @Author: xjh
* @Date: 2020/3/12
*/
public interface HandleAble {

    void handleString(String num);

}

class Run{
    public static void main(String[] args) {
        new HandleAble(){
            @Override
            public void handleString(String num){
                //先转换为Double，再转换为double，在去除小数
                System.out.println((int)(double)Double.valueOf(num));
            }
        }.handleString("123.4");
        new HandleAble(){
            @Override
            public void handleString(String num) {
                System.out.printf("%.2f",Double.valueOf(num));
            }
        }.handleString("2.56778");
    }
}
