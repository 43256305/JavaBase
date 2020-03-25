package javanet.start;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-03-25 09:08
 **/
public class SimpleClient {
    public static void main(String[] args) {
        try{
            Socket s=new Socket("localhost",2020);
            OutputStream os=s.getOutputStream();

            os.write("Hello World!".getBytes());
            os.close();
            s.close();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
