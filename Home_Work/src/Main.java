interface Flyable {
     //默认是public abstract
     void fly();
}
interface Swimming {
     void swim();
}
 abstract class Bird {
     abstract public void eat();
}
class Penguin extends Bird implements Swimming  {
     @Override
     public void eat()
     {
        System.out.println("企鹅吃南极磷虾");
     }
     @Override
     public void swim()
     {
        System.out.println("企鹅下海捉虾");
     }

}
class Swan extends Bird implements Flyable , Swimming {
    @Override
    public void eat() {
        System.out.println("天鹅吃水生植物的根茎和种子、水生昆虫、螺类和小鱼.");
    }
    @Override
    public void fly() {
        System.out.println("天鹅展趣高飞，天南海北任我游");
    }
    @Override
    public void swim() {
        System.out.println("天鹅把羽毛洗的程亮，顺便提条鱼");
    }


}
class Chicken extends Bird implements Flyable {
    @Override
    public void eat() {
        System.out.println("鸡吃谷子");
    }

    @Override
    public void fly() {
        System.out.println("鸡上房揭瓦，满院子乱扑腾");
    }
}

public class Main {
    public static void main(String[] args) {
            Bird []bird=new Bird[3];
            bird[0]=new Penguin();
            bird[1]=new Swan();
            bird[2]=new Chicken();
            for(int i=0;i<bird.length;i++)
            {
                switch(i)
                {
                    //父类对象指向子类，调用子类接口函数，必须要强转成为接口类型
                    case 0:
                        System.out.println("Penguin: ");
                        bird[i].eat();
                        ((Swimming) bird[i]).swim();
                        break;
                    case 1:
                        System.out.println("Swan: ");
                        bird[i].eat();
                        ((Flyable) bird[i]).fly();
                        ((Swimming) bird[i]).swim();
                        break;
                    case 2:
                        System.out.println("Chicken: ");
                        bird[i].eat();
                        ((Flyable) bird[i]).fly();
                }
            }
        }
 }
