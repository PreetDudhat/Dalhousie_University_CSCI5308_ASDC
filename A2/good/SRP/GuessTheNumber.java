import java.util.Scanner;

public class GuessTheNumber {
    public static Scanner sc=new Scanner(System.in);
    public static int guess_the_number(){
        int guess_no;
        System.out.println("Take a guess for the number");
        guess_no = sc.nextInt();
        return guess_no;
    }
}
