import java.util.Random;
import java.util.Scanner;

public class BadGameMain {
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Enter player's name");
        String name = sc.nextLine();
        int points=0;
        Random rand = new Random();
        int upperbound = 100;
        int random_number = (rand.nextInt(upperbound));


        print_user_details(name,points);

        System.out.println("Lets start the High Low game");
        System.out.println("Rules:");
        System.out.println("1. If the user guess is incorrect then -5 points");
        System.out.println("2. If the user guess is correct in less than 5 turns then " +
                               "+80 points else +50 points are rewarded");
        System.out.println("3. User gets maximum of 10 guesses");
        System.out.println("Range of the number is 0 - 99");
        System.out.println("Random number generated");
        int guess = 1;
        boolean correct=false;
        int guess_no;
        while(guess<=10){
            guess_no = guess_the_number();
            guess++;
            correct = check_guess_number(guess_no,random_number);
            points = get_points(correct,points,guess);
            if(correct){
                break;
            }
            sc.nextLine();
        }
        if(points<0){
            System.out.println("Game Lost");
        }
        else{
            System.out.println("Game won");
        }
        print_user_details(name,points);
    }
    public static int get_points(boolean c, int pt,int g){
        if(c==true){
            if(g<5){
                pt+=80;
            }
            else{
                pt+=50;
            }
        }
        else{
            pt-=5;
        }
        return pt;
    }
    public static int guess_the_number(){
        int guess_no;
        System.out.println("Take a guess for the number");
        guess_no = sc.nextInt();
        return guess_no;
    }
    public static boolean check_guess_number(int g_no, int r_no){
        if(g_no==r_no){
            System.out.println("Number is matched");
            return true;
        }
        else if(g_no>r_no){
            System.out.println("Enter a lower number");
            return false;
        }
        else{
            System.out.println("Enter a higher number");
            return false;
        }
    }
    public static void print_user_details(String name, int points){
        System.out.println("Name is "+name);
        System.out.println("Total points are "+points);
    }
}