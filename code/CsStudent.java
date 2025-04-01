import java.util.Scanner;
class StudentOf2024CS
{
	private String _name;//学生姓名
	private int _money=100;//不能为负数 //可支配金额
	private static int _numberOfObjects=0;//初始值为0，每次加1
	private static int  _classMoney=0;//全体学生缴纳的班费余额
	StudentOf2024CS(String name)
	{
		this._name=name;
		_numberOfObjects++;
	}
	StudentOf2024CS(String name ,int money)
	{
		this._name=name;
		this._money=money;
		_numberOfObjects++;
	}
	
	public void setName(String name) {this._name = name;}
    public void setMoney(int money) { this._money = money;}
	public String getName() { return _name; }
	public int getMoney()	{ return _money;}
	public static int getNumberOfObjects(){	return _numberOfObjects;}
    public static int getClassMoney(){ return _classMoney;}
	
	
	
	public void payClassMoney(int amount) //个人所缴班费
	{
		if(amount>_money)
		{
			_classMoney+=_money;
			this._money=0;
		}
		else
		{
			this._money-=amount;
			_classMoney+=amount;
		}
	}
	public static void classActivity(int amount) //举行活动，班费支出
	{
		_classMoney-=amount;
		if(_classMoney<0)	_classMoney=0;
	}
}
public class CsStudent
{
	public static void main(String []args)
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("numberOfObjects=" + StudentOf2024CS.getNumberOfObjects() + ",classMoney=" + StudentOf2024CS.getClassMoney());

		


        // 创建三个对象
        StudentOf2024CS student1 = new StudentOf2024CS("zhangsan");
        StudentOf2024CS student2 = new StudentOf2024CS("lisi", 200);
        StudentOf2024CS student3 = new StudentOf2024CS("wangwu", 300);

        // 三个学生分别支付班费
        System.out.print("请输入第一个学生支付的班费金额: ");
        int st1Pay = scanner.nextInt();
        student1.payClassMoney(st1Pay);
        System.out.println("numberOfObjects=" + StudentOf2024CS.getNumberOfObjects() + ",classMoney=" + StudentOf2024CS.getClassMoney());

        System.out.print("请输入第二个学生支付的班费金额: ");
        int st2Pay = scanner.nextInt();
        student2.payClassMoney(st2Pay);
        System.out.println("numberOfObjects=" + StudentOf2024CS.getNumberOfObjects() + ",classMoney=" + StudentOf2024CS.getClassMoney());

        System.out.print("请输入第三个学生支付的班费金额: ");
        int st3Pay = scanner.nextInt();
        student3.payClassMoney(st3Pay);
        System.out.println("numberOfObjects=" + StudentOf2024CS.getNumberOfObjects() + ",classMoney=" + StudentOf2024CS.getClassMoney());

        // 组织班级活动
        System.out.print("请输入第一次班级活动需要的金额: ");
        int act1 = scanner.nextInt();
        StudentOf2024CS.classActivity(act1);
        System.out.println("numberOfObjects=" + StudentOf2024CS.getNumberOfObjects() + ",classMoney=" + StudentOf2024CS.getClassMoney());

        System.out.print("请输入第二次班级活动需要的金额: ");
        int act2 = scanner.nextInt();
        StudentOf2024CS.classActivity(act2);
        System.out.println("numberOfObjects=" + StudentOf2024CS.getNumberOfObjects() + ",classMoney=" + StudentOf2024CS.getClassMoney());

        scanner.close();
		
	}
	
}