package javanet.class4_8;

import java.io.*;
import java.util.Arrays;

/**
 * @program: type_
 * @description: 字节流
 * @author: xjh
 * @create: 2020-04-08 10:09
 **/
public class ScoreReader1 {
    public static void main(String[] args) {
        FileInputStream fis;
        try{
            fis=new FileInputStream("score.txt");
            InputStreamReader input=new InputStreamReader(fis);
            char[] c=new char[1024];
            int n=input.read(c);
            System.out.println(new String(c));
            input.close();
            fis.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
