import java.util.Scanner;

public class code {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一个自然数: ");
        
        int number = scanner.nextInt();
        int result = sumOfDigits(number);
        
        System.out.println(number + "的各位数字之和为: " + result);
        scanner.close();
    }
    
    public static int sumOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;  // 获取最后一位数字
            n /= 10;        // 去掉最后一位
        }
        return sum;
    }
}



