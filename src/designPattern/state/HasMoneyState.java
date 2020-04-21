package designPattern.state;

import designPattern.state.VendingMachine;

import java.util.Random;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-20 16:05
 **/
public class HasMoneyState implements State {
    private VendingMachine machine;
    private Random random = new Random();

    public HasMoneyState(VendingMachine machine)
    {
        this.machine = machine;
    }

    @Override
    public void insertMoney()
    {
        System.out.println("您已经投过币了，无需再投....");
    }

    @Override
    public void backMoney()
    {
        System.out.println("退币成功");

        machine.setState(new NoMoneyState(machine));
    }

    @Override
    public void turnCrank()
    {
        System.out.println("你转动了手柄");
        int winner = random.nextInt(10);
        if (winner == 0 && machine.getCount() > 1)
        {
            machine.setState(new WinnerState(machine));
        } else
        {
            machine.setState(new SoldState(machine));
        }
    }

    @Override
    public void dispense()
    {
        throw new IllegalStateException("非法状态！");
    }
}
