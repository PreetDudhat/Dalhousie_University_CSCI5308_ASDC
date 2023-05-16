import java.util.*;
public class Game {
    static public Scanner sc = new Scanner(System.in);
    public static String getName(){
        //try and catch exception
        String n = sc.nextLine();
        if(n == null){
            System.out.println("Name cannot be null");
            System.out.println("Enter the name again");
            getName();
        }
        return n;
    }
    public static int payDebt(String career){
        //add debt to players
        //try and exception
        int debt=0;
        if(career.equals("SOFTWARE DEVELOPER")){
            debt=50000;
        }
        else if(career.equals("BASKETBALL PLAYER")){
            debt=10000;
        }
        return debt;
    }
    public static void printPlayerDetails(String name, int lucky_no, int salary,
                                          int life_count,int balance,String career, int debt){
        System.out.println("Name : " + name);
        System.out.println("Lucky Number : " + lucky_no);
        System.out.println("Career : " + career);
        System.out.println("Salary : $" + salary);
        System.out.println("Total Life : " + life_count);
        System.out.println("Debt is: "+debt);
        System.out.println("Current Balance is: "+ balance);
    }
    public static int retirement(int L_cnt,int kids,int hm,int bal,String career,String wife){
        int pL1_cnt=(SpinTheWheel()*1500)*L_cnt;
        int k1_cnt=20000*kids;
        int w1_cnt=25000;
        int h_cnt=SpinTheWheel();
        int hm_cnt=((h_cnt*hm)/10)+hm;
        int debt=payDebt(career);
        System.out.println("ITS TIME FOR YOUR RETIREMENT");
        if(h_cnt>=5){
            System.out.println("Congratulations!! The price of your house went up by more than 49%");
            System.out.println("Your house was sold in: $"+hm_cnt);
        }
        else{
            System.out.println("Your house was sold in: $"+hm);
            hm_cnt=hm;
        }
        System.out.println("Your each life tile is worth: $"+(pL1_cnt/L_cnt));
        System.out.println("Your kids gave you money: $"+ (k1_cnt));
        System.out.println(wife+" gave you a gift token of $"+w1_cnt);
        bal+=pL1_cnt+k1_cnt+w1_cnt+hm_cnt-debt;
        System.out.println("You have debt of: $"+ debt);
        System.out.println("Your Current Balance is: $"+bal);
        System.out.println("Enjoy Your Retirement");
        return bal;
    }
    /*
     *
     *    Title: Random number generator code
     *    Author: Geeks4Geeks
     *    Availability: https://www.geeksforgeeks.org/java-util-random-nextint-java/
     *
     */
    public static int getDiceRoll(){
        Random d_roll = new Random();
        return 1 + d_roll.nextInt(4);
    }
    public static int SpinTheWheel(){
        Random ranNum = new Random();
        return 1 + ranNum.nextInt(10);
    }
    public static int getHousePrice()
    {
        return 10000*SpinTheWheel();
    }
    public static String getWifeName(int Turn){
        String w="";
        if (Turn == 0) {
            w="Isabel";
        }
        if (Turn == 1) {
            w="Monica";
        }
        return w;
    }
    public static int check_lucky_no(int rd, int n){
        int mon=0;
        if(rd==n){
            mon=2000;
        }
        return mon;
    }
    public static int gamble(int user_mon,int limit,int num){
        int bal;
        int penalty = 50000;
        int penalty2 = 30000;
        int l_num;
        if(user_mon>limit){
            System.out.println("Gambling limit exceeded");
            System.out.println("You are getting a penalty");
            System.out.println("Amount "+penalty+"has been applied");
            System.out.println("You can't play now");
            bal=-penalty;
        }
        else{
            if(num>1 && num<=10){
                System.out.println("Spinning the wheel now");
                l_num=SpinTheWheel();
                if(num==l_num){
                    System.out.println("Congratulations you have won your gamble");
                    System.out.println("You will twice the return");
                    bal=user_mon*2;
                }
                else{
                    System.out.println("Sorry to say but you lost");
                    bal=-user_mon;
                }
            }
            else{
                System.out.println("You didn't entered proper number");
                System.out.println("You are getting a penalty");
                System.out.println("Amount "+penalty2+"has been applied");
                System.out.println("You can't play now");
                bal=-penalty2;
            }
        }
        return bal;
    }

    public static int change_turn(int turn){
        if(turn==0){
            turn=1;
        }
        else{
            turn=0;
        }
        return turn;
    }
    public static void pressEnter(){
        System.out.println("Press any key for next player's turn");
        sc.nextLine();
    }
    public static void pressEnt(){
        System.out.println("Press any key/enter to continue");
        sc.nextLine();
    }

    public static int getSalary(String ch){
        int sal;
        if(Objects.equals(ch, "SOFTWARE DEVELOPER")){
            sal = 70000;
        }
        else {
            sal = 65000;
        }
        return sal;
    }
    public static int getLuckyNumber(){
        int lucky_no;
        System.out.println("Choose a Lucky number from 1 - 4");
        System.out.println("Enter your Lucky Number: ");
        lucky_no = sc.nextInt();
        if(lucky_no>4 || lucky_no<1){
            System.out.println("Enter appropriate number");
            getLuckyNumber();}
        return  lucky_no;
    }
    public static void result(int bal1, int bal2, String n1, String n2){
        if(bal1>bal2){
            System.out.println("Winner");
            System.out.println(n1+" has won the game with an outstanding balance of "+bal1);
            System.out.println("Runner up");
            System.out.println(n2+ " is the runner up with total balance of "+bal2);
        }
        else{
            System.out.println("Winner");
            System.out.println(n2+" has won the game with an outstanding balance of "+bal2);
            System.out.println("Runner up");
            System.out.println(n1+ " is the runner up with total balance of "+bal1);
        }
    }
}
