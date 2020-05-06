package javanet.IO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-29 11:52
 **/
public class ReaderAndWriter {
    public static void main(String[] args) {
        String path="test.txt";
        System.out.println(readFile(path));;

        writeFile(path,"谢杰辉");
    }

    /**
    * @Description: 字符流操作用char（char占2字节，即16位，根据unicode码，任何字符都是2字节，所有输出char可以直接输出字符，而不用转换）
     * FileReader，FileWriter操作文件的字符流
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/4/29
    */
    public static String readFile( String filePath ){
        FileReader fis=null;
        String result = "" ;
        try {
            // 根据path路径实例化一个输入流的对象
            fis  = new FileReader( filePath );

            char[] chars=new char[1024];
            fis.read(chars);
//            System.out.println(chars);  //可以不用转化位String直接输出
            result = new String(chars);

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
        FileWriter fos = null ;
        try {
            //1、根据文件路径创建输出流
            fos  = new FileWriter( filePath );

            char[] chars=content.toCharArray();

            fos.write( chars );

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
