package javanet.start;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-03-25 09:27
 **/
public class SimpleServer1 {
    public static void main(String[] args) {
        try{
            ServerSocket ss=new ServerSocket(2020);
            Socket s=ss.accept();
            //这里用的是reader，另一个服务器用的是inputstream
            Reader reader=new InputStreamReader(s.getInputStream());

            char[] chArray=new char[1024];
            reader.read(chArray);
            System.out.println(chArray);

            reader.close();
            s.close();
            ss.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
