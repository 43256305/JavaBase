package javanet.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @program: type_
 * @description: java流可以分为：1.根据数据类型：字节流，字符流  2.根据输入输出：输入流，输出流  3.根据功能：节点流，处理流
 * 输入流：InputStream或者Reader：从文件中读到程序中，输出流：OutputStream或者Writer：从程序中输出到文件中
 * 所有字节流类的抽象父类：InputStream，OutputStream，凡是类名中带有input或output的都是字节流
 * 所有字符流抽象父类：Reader，Writer凡是类名中带有这两个的都是字符流
 *
 * 字节流：一次读入或读出是8位二进制。设备上的数据无论是图片或者视频，文字，它们都以二进制存储的。二进制的最终都是以一个8位为数据单元进行体现，所以计算机中的最小数据单元就是字节。意味着，字节流可以处理设备上的所有数据，所以字节流一样可以处理字符数据
 * 字符流：一次读入或读出是16位二进制。（因为java是unicode字符集，utf编码），所以输入输出文本就用字符流
 *
 * ByteArrayInputStream、StringBufferInputStream、FileInputStream 是三种基本的介质流，它们分别从Byte 数组、StringBuffer、和本地文件中读取数据
 * ByteArrayOutputStream、FileOutputStream 是两种基本的介质流，它们分别向Byte 数组、和本地文件中写入数据
 *
 * 节点流：InputStream 、OutputStream、 Reader、 Writer的子类（对文件，数组，字符串，管道进行操作）
 * 常用处理流：转换流：InputStreamReader 、OutputStreamReader实现字节流和字符流之间的转换（要InputStream或OutputStream作为参数）。还有缓冲流，数据流等
 *
 *
 * https://blog.csdn.net/zhaoyanjun6/article/details/54292148
 * @author: xjh
 * @create: 2020-04-29 11:27
 **/
public class InputAndOutput {
    public static void main(String[] args) {
        String path="test.txt";
        //实际的项目中，所有的IO操作都应该放到子线程中操作，避免堵住主线程。
        new Thread(()->{
            System.out.println(readFile(path));
        }).start();

        String content="谢杰辉";
        new Thread(()->{writeFile(path,content);}).start();
    }

    
    /**
    * @Description:字节流操作用一个字节即8位，所以需要转化成String才能输出字符
     * FileInputStrinm，FileOutputStream操作文件的字节流
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/29
    */
    public static String readFile( String filePath ){
        FileInputStream fis=null;
        String result = "" ;
        try {
            // 根据path路径实例化一个输入流的对象
            fis  = new FileInputStream( filePath );

            //2. 返回这个输入流中可以被读的剩下的bytes字节的估计值；
            int size =  fis.available() ;
            //3. 根据输入流中的字节数创建byte数组；
            byte[] array = new byte[size];
            //4.把数据读取到数组中；
            fis.read( array ) ;
//            System.out.println(Arrays.toString(array));  //输出数字

            //5.根据获取到的Byte数组新建一个字符串，然后输出；
            result = new String(array);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            if ( fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result ;
    }

    public static void writeFile( String filePath , String content ){
        FileOutputStream fos = null ;
        try {
            //1、根据文件路径创建输出流
            fos  = new FileOutputStream( filePath );

            //2、把string转换为byte数组；
            byte[] array = content.getBytes() ;
            //3、把byte数组输出；
            fos.write( array );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            if ( fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
