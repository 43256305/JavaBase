package javanet.IO;

import java.io.*;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-29 12:08
 **/
public class HandleIO {
    public static void main(String[] args) {
        String path="test.txt";
        System.out.println(readChar(path));
        System.out.println(readString(path));
    }

    /**
    * @Description: InputStreamReader转换input读取的byte数组为char数组
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/29
    */
    public static String readChar(String filePath){
        FileInputStream fis=null;
        InputStreamReader isr=null;
        String result = "" ;
        try {
            fis  = new FileInputStream( filePath );

            isr=new InputStreamReader(fis);
            char[] chars=new char[1024];
            isr.read(chars);

            result=new String(chars);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            if ( fis != null) {
                try {
                    fis.close();
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result ;
    }

    /**
    * @Description: 使用缓冲处理流（同时也是字符流）：BufferedReader来处理，可以直接读取String，读取String最高效
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/29
    */
    public static String readString(String filePath){
        FileInputStream fis=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        String result = "" ;
        try {
            fis  = new FileInputStream( filePath );

            isr=new InputStreamReader(fis);
            br=new BufferedReader(isr);

            result=br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            if ( fis != null) {
                try {
                    fis.close();
                    isr.close();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result ;
    }
}
