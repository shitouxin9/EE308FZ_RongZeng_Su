package ee308_lab;
import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Lab_1_1 {
public static void main(String[] args)throws IOException{
	Scanner sc = new Scanner(System.in);
	long startTime = System.currentTimeMillis();    //获取开始时间
//	  System.out.println("Enter your file path");
//	  String file="";
//	  file=sc.nextLine();
//	  System.out.println("Enter test level");
//	  int l= sc.nextInt();
	   ReadTxt("\\Users\\ASUS\\Desktop\\samplec.c",4);
//	  ReadTxt(file,l);
	  long endTime = System.currentTimeMillis();
	  System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
}
public static void ReadTxt(String path,int level)throws IOException{ 
		String text="";	
		String fileName=path;
		 FileReader fileReader = new FileReader(fileName);
	       BufferedReader bufferedReader = new BufferedReader(fileReader);
	       String line =bufferedReader.readLine();
	       while (line!=null){
	           text=text+line;
	           line = bufferedReader.readLine();
	       }
	       bufferedReader.close();
	       fileReader.close();
	       leveluse(level,text);
	}

public static void leveluse(int level,String s){
      if(level==1){
          level1(s);
      }
    if(level==2){
        level2(s);
    }
    if(level==3){
        level2(s);
    	level34(s,3);
    }
    if(level==4){
        level2(s);
        level34(s,4);
    }
}

public static void level1(String s) {
        int num = 0;
        String keyword[] = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern", "float", "for", "goto", "if", "int", "long",
                "register", "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef", "unsigned", "union", "void", "volatile", "while"};
        for (int i = 0; i < keyword.length; i++) {
            String temp = "[^a-zA-Z_]" + keyword[i] + "[^a-zA-Z_]";
            Pattern pattern = Pattern.compile(temp);
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                num++;
            }
        }
        System.out.println("total num: " + num);
    }

public  static void level2(String s) {
        level1(s);
        int num = 0;
        Pattern pattern = Pattern.compile("switch");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            num++;
        }
        System.out.println("switch num: " + num);
        String temp[] = s.split("switch");
        int caseNum[] = new int[num];
        int count = 0;
        Pattern pattern2 = Pattern.compile("case");
        for (int i = 0; i < num; i++) {
            Matcher matcher2 = pattern2.matcher(temp[i + 1]);
            while (matcher2.find()) {
                count++;
            }
            caseNum[i] = count;
            count = 0;
        }
        System.out.println("case num: " + caseNum[0] + " " + caseNum[1]);
    }

public static void level34(String st,int level) {
    int if_else_num=0,if_else_if_else_num=0;
    Pattern p = Pattern.compile("else *if|else|if");
    Matcher matcher=p.matcher(st);
    Stack<String> s = new Stack();
    int count=0;
    while(matcher.find()) {
        String temp=st.substring(matcher.start(),matcher.end());
        s.push(temp);
    }
    while(!s.isEmpty()) {
        String temp=s.pop();
        if(temp.equals("else")) {
            String temp2=s.pop();
            if(temp2.equals("if")) if_else_num++;
            else if(temp2.equals("else if")) if_else_if_else_num++;
            else if(temp2.equals("else")) count+=2;
        }else if(count>0) {
            if(temp.equals("else if")) if_else_if_else_num++;
            else if(temp.equals("if")) if_else_num++;
            count--;
        }
    }
    System.out.println("if else num is "+if_else_num);
    if(level==4)    System.out.println("if else if else num is "+if_else_if_else_num);
}
      }


