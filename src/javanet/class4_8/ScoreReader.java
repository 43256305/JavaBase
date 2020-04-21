package javanet.class4_8;

import java.io.*;
import java.util.Random;

/**
 * @program: type_
 * @description:  字符流输出
 * @author: xjh
 * @create: 2020-04-08 09:47
 **/
public class ScoreReader {
    public static void main(String[] args) {
        FileInputStream fis;
        try{
            //文件默认在项目根目录下，而不是包目录
            fis=new FileInputStream("score.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            String line;
            int n=0;
            double total=0;
            while ((line=br.readLine())!=null){
//                System.out.println(line);
                String[] tokens=line.split(",");
                if (tokens.length==3){
                    total+=Integer.parseInt(tokens[2]);
                    n++;
                }
            }
            System.out.printf("average:%f\n",total/n);
            br.close();
            fis.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
