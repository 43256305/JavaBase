import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String s="kkkkkhkhk  kkk";
        s=s.replaceAll("\\s+","k");
        System.out.println(s);
    }

}
