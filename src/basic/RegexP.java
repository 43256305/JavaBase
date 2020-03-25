package basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//\\d 单个数字  \\w 单个字符  \\s  空格
		//加了?表示非贪婪模式，如果没加下面的while只有一个输出，加了才有两个输出
		Pattern pattern=Pattern.compile(".*?(hui\\s+is\\s+)(\\d+).*?");
		Matcher ma=pattern.matcher("xie jie hui is 22 years old,xie jie hui is 23 years old");//需要匹配的string
		while (ma.find()){
			//分组为每一次匹配到的结果中的第二个括号  0：整个匹配到的  1：第一个括号
			System.out.println("分组："+ma.group(2));   //:分组：22  分组：23
		}


		Pattern pattern1=Pattern.compile("\\d+");
		String s=new String("jj20 ie jie 20 hui is 20 years old");
		String[] str=pattern1.split(s);
		//返回一个Matcher对象,Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
		Matcher ma1=pattern1.matcher(s);
		//Matcher.find()可以输出字符串中所有匹配到的，注意，使用一次find()方法就消耗了一次匹配
		while (ma1.find()){
			System.out.println(ma1.group());  //:20  20  20
		}
		for (int i=0;i<str.length;i++){
			System.out.println(str[i]); //:jj  ie jie   hui is   years olf
		}

		String str1="kkkkkhkhk  kkk";
		//replaceAll与replace的区别：1.都是全部替换  2.并不改变原字符串  3.replaceAll支持正则，而replace不支持
		System.out.println(str1.replaceAll("\\s+","k"));  //：kkkkkhkhkkkkk
		System.out.println(str1.replace("h","k"));  //：kkkkkkkkk  kkk
		System.out.println(str1);  //:kkkkkhkhk  kkk

//Pattern.maches的用法，字符串与正则完全匹配，不能多，不能少
//		Pattern.compile(regex).matcher(input).matches() 另一个用法
//		System.out.println(Pattern.matches("\\d+","2223"));//返回true
//		System.out.println(Pattern.matches("\\d+\\w+","2223aa"));//返回false,需要匹配到所有字符串才能返回true,这里aa不能匹配到 ,加了\\w+(字符串）,true
//		System.out.println(Pattern.matches("\\d+","22bb23"));//返回false,需要匹配到所有字符串才能返回true,这里bb不能匹配到

//		lookingAt()匹配最前面的字符串      find()匹配任意位置的字符串
//		Mathcer.start()/ Matcher.end()/ Matcher.group()
//		当使用matches(),lookingAt(),find()执行匹配操作后,就可以利用以上三个方法得到更详细的信息.
//		start()返回匹配到的子字符串的第一个字符在字符串中的索引位置.
//		end()返回匹配到的子字符串的最后一个字符在字符串中的索引位置.
//		group()返回匹配到的子字符串
//      注意:只有当匹配操作成功,才可以使用start(),end(),group()三个方法,否则会抛出java.lang.IllegalStateException,
//		           也就是当matches(),lookingAt(),find()其中任意一个方法返回true时,才可以使用.

//		start(),end(),group()均有一个重载方法它们是start(int i),end(int i),group(int i)专用于分组操作,Mathcer类还有一个groupCount()用于返回有多少组.
	}

}
