package object_class.extend;

/**
 * @program: type_
 * @description: 父类
 * @author: xjh
 * @create: 2020-03-10 11:30
 **/
public class FatherClas {
    private String lastName;
    //1.子类（可以不在同一个包） 2.同一个包
    protected int age;
    int test;

    public FatherClas() {

    }

    public FatherClas(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void works(){
        System.out.println("Father is working");
    }
}
