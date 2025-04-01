import java.util.Scanner;

public class BMI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入你的体重（千克）: ");
        double weight = scanner.nextDouble();

        System.out.print("请输入你的身高（米）: ");
        double height = scanner.nextDouble();

        double bmi = weight / (height * height);

        // 国际BMI分类判断
        String internationalCategory;
        if (bmi < 18.5) {
            internationalCategory = "偏瘦";
        } else if (bmi < 25) {
            internationalCategory = "正常";
        } else if (bmi < 30) {
            internationalCategory = "偏胖";
        } else {
            internationalCategory = "肥胖";
        }

        // 国内BMI分类判断
        String domesticCategory;
        if (bmi < 18.5) {
            domesticCategory = "偏瘦";
        } else if (bmi < 24) {
            domesticCategory = "正常";
        } else if (bmi < 28) {
            domesticCategory = "偏胖";
        } else {
            domesticCategory = "肥胖";
        }

        System.out.println("国际BMI标准下，你属于：" + internationalCategory);
        System.out.println("国内BMI标准下，你属于：" + domesticCategory);

        scanner.close();
    }
}
