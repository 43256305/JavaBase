package object_class.extend;

/**
 * @program: type_
 * @description: 子类
 * @author: xjh
 * @create: 2020-03-10 11:30
 **/
public class SonClas extends FatherClas {
    private int age;
    private String firstName;

    public SonClas(String firstName,String lastNaame) {
        //利用super调用父类的构造器，方法，属性
        //super与this很多用法相同，但super只是关键字，而this为对象的引用
        super(lastNaame);
        super.age=100;
        System.out.println(super.getLastName());
        this.firstName=firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void works(){
        System.out.println("Son is studying");
    }

    public static void main(String[] args) {
        SonClas sonClas=new SonClas("jack","lee");
        //可见子类拥有父类的所有域，只是私有的不能直接访问而已，可以通过公有的间接访问私有的
        System.out.println(sonClas.getFirstName()+"."+sonClas.getLastName());  //:jack.lee
        FatherClas fatherClas=new FatherClas();
        fatherClas.works();  //Father is working
        //子类转换为父类，直接赋值（多态），父类的方法会替换成子类重写的方法
        fatherClas=sonClas;
        fatherClas.works();  //Son is studying

        //强制转换为他的子类  sonClass转换成fatherClass后，内部的属性（子类独有的）并没有消失，fatherClass又转换成sonClass后子类属性又出来了
        if (fatherClas instanceof SonClas){
            SonClas sonClas1=(SonClas)fatherClas;
            sonClas1.works();  //:Son is studying
            System.out.println(sonClas1.getLastName());  //:lee
            System.out.println(sonClas1.getFirstName());  //:jack
        }else{
            System.out.println("不能转换");
        }
    }
}
