import java.util.Scanner;

class Trangle {
    private double side1;
    private double side2;
    private double side3;
    private double perimeter;
    private double area;
    private boolean canFormTriangle;

    public Trangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        canFormTriangle = (side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1);
        if (canFormTriangle) {
            perimeter = side1 + side2 + side3;
            double s = perimeter / 2;
            area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        } else {
            perimeter = 0;
            area = 0;
        }
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getArea() {
        return area;
    }



    public boolean canFormTriangle() {
        return canFormTriangle;
    }
}

class Lader {
    private double upperBase;
    private double lowerBase;
    private double height;
    private double area;

    public Lader(double upperBase, double lowerBase, double height) {
        this.upperBase = upperBase;
        this.lowerBase = lowerBase;
        this.height = height;
        area = (upperBase + lowerBase) * height / 2;
    }

    public double getArea() {
        return area;
    }
}

class Circle {
    private double radius;
    private double perimeter;
    private double area;

    public Circle(double radius) {
        this.radius = radius;
        perimeter = 2 * Math.PI * radius;
        area = Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getArea() {
        return area;
    }
}

public class Shape {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 处理三角形输入和输出
        System.out.println("请输入三角形的三条边，以空格分隔：");
        double triangleSide1 = scanner.nextDouble();
        double triangleSide2 = scanner.nextDouble();
        double triangleSide3 = scanner.nextDouble();
        Trangle triangle = new Trangle(triangleSide1, triangleSide2, triangleSide3);
        System.out.println("三角形能构成：" + triangle.canFormTriangle());
        System.out.println("三角形周长：" + triangle.getPerimeter());
        System.out.println("三角形面积：" + triangle.getArea());

        // 处理梯形输入和输出
        System.out.println("请输入梯形的上底、下底和高，以空格分隔：");
        double laderUpperBase = scanner.nextDouble();
        double laderLowerBase = scanner.nextDouble();
        double laderHeight = scanner.nextDouble();
        Lader lader = new Lader(laderUpperBase, laderLowerBase, laderHeight);
        System.out.println("梯形面积：" + lader.getArea());

        // 处理圆形输入和输出
        System.out.println("请输入圆的半径：");
        double circleRadius = scanner.nextDouble();
        Circle circle = new Circle(circleRadius);
        System.out.println("圆的周长：" + String.format("%.2f", circle.getPerimeter()));
        System.out.println("圆的面积：" + String.format("%.2f", circle.getArea()));

        scanner.close();
    }
}
