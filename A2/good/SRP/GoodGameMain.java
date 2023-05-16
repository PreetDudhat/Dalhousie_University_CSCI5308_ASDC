import java.util.Scanner;
import java.util.Random;
public class GoodGameMain {
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        PrintUserDetails pud = new PrintUserDetails();
        GetPoints gp = new GetPoints();
        GuessTheNumber gn = new GuessTheNumber();
        CheckTheNumber cn = new CheckTheNumber();

        Random rand = new Random(); //instance of random class
        int upperbound = 100;
        int random_number = rand.nextInt(upperbound);

        System.out.println("Enter player's name");
        String name = sc.nextLine();
        int points=0;
        pud.PrintDetails(name,points);

        System.out.println("Lets start the High Low game");
        System.out.println("Rules:");
        System.out.println("1. If the user guess is incorrect then -5 points");
        System.out.println("2. If the user guess is correct in less than 5 turns then " +
                "+80 points else +50 points are rewarded");
        System.out.println("3. User gets maximum of 10 guesses");
        System.out.println("Range of the number is 0 - 99");
        System.out.println("Random number generated");
        int guess = 1;
        boolean correct = false;
        int guess_no;
        while(guess<=10){
            guess_no = gn.guess_the_number();
            guess++;
            correct = cn.check_guess_number(guess_no,random_number);
            points = gp.get_points(correct,points,guess);
            if(correct){
                break;
            }
            sc.nextLine();
        }
    }
}