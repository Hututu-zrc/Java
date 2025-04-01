public class PerfectNumber {
    public static void main(String[] args) {
        int limit = 10000;
        System.out.println("1 到 " + limit + " 之间的完数有：");
        for (int i = 1; i <= limit; i++) {
            if (isPerfectNumber(i)) {
                System.out.println(i);
            }
        }
    }

    // 判断一个数是否为完数的方法
    public static boolean isPerfectNumber(int num) {
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum == num;
    }
}