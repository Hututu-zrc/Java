import java.io.*;//java.io：用于输入输出操作，包含了文件读写、网络通信等相关类。
import java.util.*;//java.util：提供了集合框架（如List、Set、Map）、日期时间处理类（如Date、Calendar）、随机数生成器等工具类。
import java.math.*;
import java.lang.*;
public class Main {
    public static void Print(int []arr)
    {
        for(int  e:arr)
        {
            System.out.print(e+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {

        int a=-1;
        System.out.println(Math.abs(a));
        char ch='A';
        if(Character.isLetter(ch)){
            System.out.println(ch);

        }
        else
        {
            System.out.println("小写字母");
        }
        String str="            4565467914123000          ";
        System.out.println(str);
        System.out.println(str.replaceAll("0","abc"));

        StringBuffer sb=new StringBuffer();
        sb.append("123456");
        System.out.println(sb);

        sb.reverse();
        System.out.println(sb);
        int []arr={1,2,3,-1 ,8888,6,7,8,9};
        Print(arr);
        Arrays.sort(arr);
        Print(arr);
        int pos=Arrays.binarySearch(arr,9);
        System.out.println("pos: "+pos);
        Arrays.fill(arr,-1);
        Print(arr);
        Date date=new Date();
        System.out.println(date.getTime());
    }
}