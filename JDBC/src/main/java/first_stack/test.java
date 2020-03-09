package first_stack;
import java.util.Scanner;
class test{
    public static void main(String[] patgs) {
          Scanner rad = new Scanner(System.in);
          Scanner hei = new Scanner(System.in);
          int radius = rad.nextInt();
          int height = hei.nextInt();

          System.out.println(volume(radius,height));
          System.out.println(volume(radius,height));
    }
    public  static int volume(int a, int b){
           double Pi=3.14;


           return (int) (Pi*a*a*b);
    }
}