package object_class.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-03-10 21:19
 **/
public class ChangeAttr {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ChangeAttr t=new ChangeAttr();
        try {
			t.getPrivateMeth();
            t.getPrivateField();
        } catch (Exception e) {
            // TODO: handle exception
        }


    }

    //访问私有方法
    private static void getPrivateMeth() throws Exception{  //invoke  判处异常
        TestClass testClass=new TestClass();
        Class mclass=testClass.getClass();
        //获得某个方法（根据private，参数）
        Method method=mclass.getDeclaredMethod("privateMethod", String.class, int.class);  //会抛出异常
        if (method!=null){
            method.setAccessible(true); //因为是private方法，获取访问权限（当获取私有信息报异常时，都可以用此来获取权限）
            //调用获得的私有方法
            method.invoke(testClass, "java reflection",666);
        }
    }

    //修改私有变量（在私有变量没有setter的情况下）
    private static void getPrivateField() throws Exception{  //set   抛出异常
        TestClass testClass=new TestClass();
        Class mclass=testClass.getClass();
        Field field=mclass.getDeclaredField("message");
        if (field!=null){
            field.setAccessible(true);
            System.out.println("以前的message："+testClass.getMess());
            //通过set修改变量的值(要修改的对象，要修改成的值)
            field.set(testClass, "modify");
            System.out.println("现在的message："+testClass.getMess());
        }
    }

}

class TestClass {
    private String message="original";

    private void privateMethod(String mess1,int mess2){
        System.out.println("用反射调用了此方法");
        System.out.println(mess1+"  "+mess2);
    }

    public String getMess(){
        return message;
    }

}


