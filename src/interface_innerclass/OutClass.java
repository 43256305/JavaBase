package interface_innerclass;

/**
 * @program: type_
 * @description: 内部类
 * @author: xjh
 * @create: 2020-03-11 22:23
 **/

//内部类可以访问拥有此自己的类的所有数据，包括私有
//内部类对同一个包中的其他类隐藏
//内部类让多继承变成可能，比如父类继承一个，内部类也可以继承
public class OutClass {
    private int id;
    private String name;

    public OutClass(int id,String name){
        this.id=id;
        this.name=name;
    }

    //局部内部类和匿名内部类只能访问局部final变量
    public void localClass(){
        int i=0;
        //局部内部类不能有private等修饰符
        class LocalClass{
            private String name="default";
            public void desc(){
                System.out.println(i);
            }

        }
        //局部内部类只能在方法块的下面访问，也需要new
        LocalClass local=new LocalClass();
        System.out.println("Local Class'name is "+local.name);
        System.out.println("outer Class'name is "+OutClass.this.name);
    }

    public void anonClass(){
        //匿名内部类的写法：直接new 然后重写方法
//        new OnClickListenen() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//
//            }
//        }
    }
    //外部类也能访问静态内部类的私有属性
    public void staticClass(){
        StaticClass staticClass=new StaticClass();
        System.out.println(staticClass.name);;
    }

    //静态内部类不是全部是静态的，只是作为一个静态属性存在于外部类，但他本身是个类，也有this关键字
    static class StaticClass{
        //静态内部类不能使用外部类的非static方法和属性
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }
    }

    private void desc(){
        System.out.println("外部类：OutClass");
    }

    public void descAboDraw(){
        //外部类访问内部类私有属性，必须要new
        InnClass d=new InnClass("default name");
        System.out.println("Draw's name is "+d.name);
    }

    public InnClass getInnClassIns(String s){
        return new InnClass(s);
    }

    //为public时，所有类都能通过外部类访问，为private时，只有外部类能使用  下面的InnerTest就会报错
    public class InnClass{
        private String name;

        public InnClass(String name){
            this.name=name;
        }

        public void draw(){
            //当内部类与外面属性名相同时，用类.this.attr的方法访问
            System.out.println("id is "+id+" name of outside is "+ OutClass.this.name+" name of inner is "+InnClass.this.name);
            //访问外面的方法
            desc();
        }
    }
}

class InnerTest{
    public static void main(String[] args) {
        //创建内部类
        OutClass outClass=new OutClass(1,"jack");
        //创建内部类必须要借助外部类的对象
        OutClass.InnClass innClass=outClass.new InnClass("mike");
        //通过外部类对象的方法创建
        OutClass.InnClass innClass1=outClass.getInnClassIns("mike");

//        OutClass.InnClass innClass2=new OutClass.InnClass("mike");  //报错，不能通过new直接创建

        //静态内部类对象生成不需要外部类对象
        OutClass.StaticClass staticClass=new OutClass.StaticClass();
    }
}
