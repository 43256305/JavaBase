package designPattern.adapter;

/**
 * @program: type_
 * @description: 把v220转换为v5
 * @author: xjh
 * @create: 2020-04-18 20:32
 **/
public class V5PowerAdapter implements V5Power {
    /**
     * 组合的方式
     */
    private V220Power v220Power ;

    public V5PowerAdapter(V220Power v220Power)
    {
        this.v220Power = v220Power ;
    }

    /**
    * @Description: 把
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/18
    */
    @Override
    public int provideV5Power()
    {
        int power = v220Power.provideV220Power() ;
        //power经过各种操作-->5
        System.out.println("适配器：我悄悄的适配了电压。");
        return 5 ;
    }
}
