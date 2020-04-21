package designPattern.state;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 16:08
 **/
public class VendingMachine {

    private int count = 0;
    private State currentState;

    public VendingMachine(int count)
    {
        State noMoneyState = new NoMoneyState(this);
        if (count > 0)
        {
            this.count = count;
            currentState = noMoneyState;
        }
    }

    //具体的动作交给当前状态去执行
    public void insertMoney()
    {
        currentState.insertMoney();
    }

    public void backMoney()
    {
        currentState.backMoney();
    }

    public void turnCrank()
    {
        currentState.turnCrank();
        if (currentState instanceof SoldState || currentState instanceof WinnerState)
            currentState.dispense();
    }

    public void dispense()
    {
        System.out.println("发出一件商品...");
        if (count != 0)
        {
            count -= 1;
        }
    }

    public void setState(State state)
    {
        this.currentState = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
