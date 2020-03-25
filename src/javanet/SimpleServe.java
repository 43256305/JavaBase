package javanet;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-03-25 09:01
 **/
public class SimpleServe {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(2020);
        Socket s=ss.accept();
        InputStream is=s.getInputStream();

        byte[] b=new byte[1024];
        int i=0;
        byte l;
        while((l=(byte)is.read())!=-1){
            //发送的是字节流，所以需要把byte数组收集数据，再把byte[]转换为String
            b[i]=l;
            i++;
        }
        //把byte流改为string输出
        System.out.println(new String(b));

        //别忘了关闭
        is.close();
        s.close();
        ss.close();
    }
}
