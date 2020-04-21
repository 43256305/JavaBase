package javanet.assignment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: type_
 * @description:
 * @author: xjh
 * @create: 2020-04-19 22:00
 **/
public class Test{
    public static void main(String[] args) throws Exception{
        List<ScoreRecord> list=new ArrayList<>();
        FileOutputStream write=new FileOutputStream("score.data",true);
        FileInputStream read=new FileInputStream("score.data");
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("请输入你想选择的功能：1.增  2.删  3.改  4.查 ");
            System.out.println("按任意字符退出系统");
            switch (scanner.nextLine()){
                case "1":
                    System.out.println("请输入no,name,score，间隔为回车符(其中no为8位数字):");
                    ScoreRecord scoreRecord=new ScoreRecord(scanner.nextLine(),scanner.nextLine(),Integer.valueOf(scanner.nextLine()));
                    write.write(scoreRecord.toBytes());
                    break;
                case "2":
                    break;
                case "3":
                    System.out.println(3);
                    break;
                case "4":
                    byte[] b=new byte[1024];
                    read.read(b);
                    ScoreRecord s=new ScoreRecord();
                    s.loadBytes(b);
                    break;
                default:
                    write.close();
                    read.close();
                    System.exit(1);
                    break;
            }
        }

    }

}

class ScoreRecord{

    private String no;

    private String name;

    private int score;


    public ScoreRecord() {


    }


    public ScoreRecord(String no,String name,int score) {

        this.no = no;

        this.name = name;

        this.score = score;

    }


    public String getNo() {

        return no;

    }

    public void setNo(String no) {

        this.no = no;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public int getScore() {

        return score;

    }

    public void setScore(int score) {

        this.score = score;

    }


    public byte[] toBytes() {

        byte[] recordBytes = new byte[29];

        byte[] bytes = asciiBytes(no);

        for (int i=0;i<8;i++) {

            recordBytes[i]=bytes[i];

        }

        bytes = name.getBytes();

        for (int i=0;i<bytes.length;i++) {

            recordBytes[i+8]=bytes[i];

        }

        recordBytes[bytes.length+8]=0;

        recordBytes[28]=(byte)score;

        return recordBytes;

    }


    public void printInfo() {

        System.out.printf("no:%s,name:%s,score:%d\n", no,name,score);

    }


    public void loadBytes(byte[] recordBytes) {

        char[] chars = new char[8];

        for (int i=0;i<chars.length;i++)

            chars[i] = (char)recordBytes[i];

        no = new String(chars);

        int pos=0;

        for (int i=8;i<28;i++) {

            if (recordBytes[i] == 0) {

                pos=i;

                break;

            }

        }

        name = new String(recordBytes,8,pos-8);

        score = (int)recordBytes[28];

    }


    public static byte[]  asciiBytes(String s) {

        byte[] bytes = new byte[s.length()];

        for (int i=0;i<bytes.length;i++)

            bytes[i] = (byte)s.charAt(i);

        return bytes;

    }



}
