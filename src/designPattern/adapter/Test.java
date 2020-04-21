package designPattern.adapter;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-18 20:32
 **/
public class Test {
    public static void main(String[] args) {
        Mobile mobile = new Mobile();
        V5Power v5Power = new V5PowerAdapter(new V220Power()) ;
        mobile.inputPower(v5Power);
    }
}
