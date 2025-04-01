import java.util.*;

public class E {

   public static void main(String args[]){

      int [] a ={10,20,30,40,50,60}, b, c, d;

      // 将数组a中的元素复制到b中，调用Arrays.copyOf方法。
      b = Arrays.copyOf(a, a.length);

      System.out.println("数组 a 的各个元素中的值:");
      System.out.println(Arrays.toString(a));

      System.out.println("数组 b 的各个元素中的值:");
      System.out.println(Arrays.toString(b));

      // 将数组a中的第3到第5个元素复制到c中，调用Arrays.copyOfRange方法。
      // 注意Java数组是从0开始计数，第3到第5个元素下标为2到4
      c = Arrays.copyOfRange(a, 2, 5);

      System.out.println("数组 c 的各个元素中的值:");
      System.out.println(Arrays.toString(c));

      // 将数组a中的第3到第9个元素复制到d中，调用Arrays.copyOfRange方法。
      d = Arrays.copyOfRange(a, 2, 6);

      System.out.println("数组 d 的各个元素中的值:");
      System.out.println(Arrays.toString(d));

      // 将数组d中的元素重新排序，并判断0是否在在该数组中。
      Arrays.sort(d);
      System.out.println("数组 d 排序后各个元素的值:");
      System.out.println(Arrays.toString(d));

      System.out.println("0 是否在数组 d 中: " + (Arrays.binarySearch(d, 0) >= 0));
   }
} 