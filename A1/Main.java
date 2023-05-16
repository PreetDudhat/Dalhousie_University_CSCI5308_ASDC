import java.util.*;
class player{
    int bal = 10000;
    int life_count=1;
    int salary;
    int debt_amt;
    String career;
    int lucky_no;
    String name;
    int hm;
    String wife;
    int initial_pos=0;
    int last_pos=0;
    int raise_amt;
    int won_amt;
    int lost_amt;
    int kids=0;
    int bonus=0;
    int treasure;
    int jackpot;
    boolean retirement=false;
}

public class Main {
    public static void main(String[] args) {
        if(args.length>0 && args[0].equalsIgnoreCase("test")){
            //call test
            mockDB md=new mockDB();
            md.mockDB();
            Gametest t=new Gametest();
            t.debt_test();
            t.printPlayerDetails_test();
            t.getWifeName_test();
            t.retirement_test();
            t.dice_roll_test();
            t.spin_the_wheel_test();
            t.house_price_test();
            t.debt_test();
            t.gamble_test();
            t.change_turn_test();
            t.getSalary_test();
            t.result_test();
        }
        else{
            Scanner sc = new Scanner(System.in);
            System.out.println("to run the main code: java Main");
            System.out.println("to run the test code: java Main test");
            System.out.println("Instructions to play the Game");
            // Instructions
            System.out.println("Instructions to play the Game:\n");
            System.out.println("1. Player chooses a career and a lucky number.");
            System.out.println("2. For every lucky number player gets he will receive a $2000 amount.");
            System.out.println("3. At the end of the 32 step game players will have their " +
                    "net worth compared with each other.");
            System.out.println("4. At every 5th step player will gain his salary and " +
                    "have a chance to raise by $1.5k X random number.");
            System.out.println("5. All the odd step player will gain a life line" +
                    "(calculated at last; each life equal to $1.5k X random number");
            System.out.println("6. There will be some special tiles where player will " +
                    "have some bonus and risks in them.");
            System.out.println("7. Winner will be decided whoever " +
                    "has the largest amount of balance at the end");
            System.out.println("8. The dice rolled will be in the range of 1 - 4");

            new Game();
            player[] p = new player[2];
            p[0] = new player();
            p[1] = new player();
            System.out.println("Start the Game");
            System.out.println("Enter your Name: ");
            p[0].name = Game.getName();
            p[0].lucky_no = Game.getLuckyNumber();
            p[1].name="Joey";
            //assigning the computer a lucky number
            p[1].lucky_no=p[0].lucky_no;
            while(p[1].lucky_no==p[0].lucky_no){
                p[1].lucky_no= Game.getDiceRoll();
            }
            System.out.println("Enter your choice: ");
            while (true) {
                System.out.println("1. College Career(Loan $50000)");
                System.out.println("2. Skill based Career(Loan $10000)");
                int ch = sc.nextInt();
                if (ch == 1) {
                    p[0].career = "SOFTWARE DEVELOPER";
                    p[0].salary = Game.getSalary(p[0].career);
                    p[0].debt_amt= Game.payDebt(p[0].career);
                    //   gm.debtCounter(debt_amt);
                    Game.printPlayerDetails(p[0].name, p[0].lucky_no, p[0].salary,
                            p[0].life_count, p[0].bal, p[0].career, p[0].debt_amt);
                    Game.pressEnt();
                    break;
                }
                else if (ch == 2) {
                    p[0].career = "BASKETBALL PLAYER";
                    p[0].salary = Game.getSalary(p[0].career);
                    p[0].debt_amt= Game.payDebt(p[0].career);
                    Game.printPlayerDetails(p[0].name, p[0].lucky_no, p[0].salary,
                            p[0].life_count, p[0].bal, p[0].career,p[0].debt_amt);
                    Game.pressEnt();
                    break;
                }
                else {
                    System.out.println("Enter appropriate choice");
                }
            }
            System.out.println("\nDetails of the opponent a.k.a the one and only "+ p[1].name);
            int ch= Game.SpinTheWheel();
            if(ch>=3)
            {
                p[1].career="SOFTWARE DEVELOPER";
            }
            else {
                p[1].career = "BASKETBALL PLAYER";
            }
            p[1].debt_amt= Game.payDebt(p[0].career);
            p[1].salary = Game.getSalary(p[1].career);
            Game.printPlayerDetails(p[1].name, p[1].lucky_no, p[1].salary,
                    p[1].life_count, p[1].bal, p[1].career,p[1].debt_amt);
            Game.pressEnt();
            System.out.println("Let the Game Begin");

            System.out.println("Player "+p[0].name+" will move first");
            System.out.println("Roll the Dice");
            Game.pressEnt();

            int Turn=0,rd, luck_money1, luck_money2;

            while(!p[0].retirement || !p[1].retirement){
                Game.getHousePrice();
                rd= Game.getDiceRoll();

                if(p[Turn].retirement){
                    System.out.println("You are retired now though you get to roll the dice for luck number ");
                    System.out.println("Roll the dice");
                    System.out.println("you rolled "+ rd);
                    luck_money1 = Game.check_lucky_no(rd,p[0].lucky_no);
                    luck_money2 = Game.check_lucky_no(rd,p[1].lucky_no);
                    if(luck_money1>0){
                        p[0].bal+=luck_money1;
                        System.out.println("Player "+p[0].name+" got his lucky number");
                        System.out.println("Current balance of "+p[0].name+" is $"+p[0].bal);
                        Game.pressEnt();
                    }
                    else if(luck_money2>0){
                        p[1].bal+=luck_money2;
                        System.out.println("Player "+p[1].name+" got his lucky number");
                        System.out.println("Current balance of "+p[1].name+" is $"+p[1].bal);
                        Game.pressEnt();
                    }
                    else{
                        System.out.println("Better Luck next time");
                    }
                    Turn = Game.change_turn(Turn);
                }
                else{
                    p[Turn].last_pos+=rd;

                    if(p[Turn].last_pos>=32){
                        p[Turn].last_pos=32;
                    }

                    System.out.println("Current tile of "+p[Turn].name+" is "+p[Turn].last_pos);
                    System.out.println("Dice rolled, you got "+ rd);
                    luck_money1 = Game.check_lucky_no(rd,p[0].lucky_no);
                    luck_money2 = Game.check_lucky_no(rd,p[1].lucky_no);
                    if(luck_money1>0){
                        p[0].bal+=luck_money1;
                        System.out.println("Player "+p[0].name+" got his lucky number");
                        System.out.println("Current balance of "+p[0].name+" is $"+p[0].bal);
                        Game.pressEnt();
                    }
                    if(luck_money2>0){
                        p[1].bal+=luck_money2;
                        System.out.println("Player "+p[1].name+" got his lucky number");
                        System.out.println("Current balance of "+p[1].name+" is $"+p[1].bal);
                        Game.pressEnt();
                    }
                    for(int t=p[Turn].initial_pos+1;t<=p[Turn].last_pos;t++) {

                        if (t % 5 == 0){
                            System.out.println("Finally, it's time for the pay day");
                            if(t % 10 == 0){
                                //increment
                                System.out.println("Good Work, you are getting a raise");
                                p[Turn].raise_amt = 1500 * (Game.SpinTheWheel());
                                p[Turn].salary += p[Turn].raise_amt;
                                System.out.println("You got raise of $"+p[Turn].raise_amt);
                                System.out.println("Your current salary is $"+p[Turn].salary);
                            }
                            p[Turn].bal += p[Turn].salary;
                            System.out.println(p[Turn].name + " Current balance is:" + p[Turn].bal);
                            Game.pressEnt();
                        }
                        if(t==6) {
                            //Marriage
                            System.out.println("Congratulations!!! You are getting married");
                            p[Turn].wife = Game.getWifeName(Turn);
                            System.out.println("Your wife name is: "+ p[Turn].wife);
                            System.out.println("Spinning the wheel for Marriage Gift amount from other Player");
                            int mar_money = 1000 * (Game.SpinTheWheel());
                            // update the balance
                            p[Turn].bal += mar_money;
                            System.out.println(p[Turn].name + " Current balance is:" + p[Turn].bal);
                            p[Turn].initial_pos = p[Turn].last_pos;
                            Turn = Game.change_turn(Turn);
                        }
                        if(t==11){
                            //Buying a house
                            System.out.println("Hurrah!! You are buying a new House");
                            System.out.println("Spinning the wheel for the price of your new House");
                            p[Turn].hm = Game.getHousePrice();
                            System.out.println("Your Mansion is of :"+ p[Turn].hm);
                            p[Turn].bal-=p[Turn].hm;
                            System.out.println("Player's Current balance is:"+p[Turn].bal);
                            p[Turn].initial_pos=p[Turn].last_pos;
                            Turn = Game.change_turn(Turn);
                        }
                    }
                    if(p[Turn].last_pos % 5 == 0 ){
                        p[Turn].initial_pos=p[Turn].last_pos;
                        Turn = Game.change_turn(Turn);
                    }
                    else if(p[Turn].last_pos == 1 || p[Turn].last_pos == 3
                            || p[Turn].last_pos == 22 || p[Turn].last_pos == 29 )
                    {
                        //won a lawsuit
                        System.out.println("Congratulations!!! You have won a Law Suit");
                        p[Turn].won_amt=100*(Game.SpinTheWheel());
                        System.out.println("You will receive "+p[Turn].won_amt + " from other player");
                        if(Turn==0){
                            p[1].bal-=p[Turn].won_amt;
                            System.out.println(p[1].name+" Lost the case");
                            System.out.println(p[1].name+"'s Current balance is:"+p[1].bal);
                        }
                        if(Turn==1){
                            p[0].bal-=p[Turn].won_amt;
                            System.out.println(p[0].name+" Lost the case");
                            System.out.println(p[0].name+"'s Current balance is:"+p[0].bal);
                        }
                        p[Turn].bal+=p[Turn].won_amt;
                        System.out.println(p[Turn].name+"'s Current balance is:"+p[Turn].bal);
                        p[Turn].initial_pos=p[Turn].last_pos;
                        Turn = Game.change_turn(Turn);
                    }
                    else if(p[Turn].last_pos == 24 || p[Turn].last_pos == 17
                            || p[Turn].last_pos == 14 || p[Turn].last_pos == 13)
                    {
                        //lost a lawsuit
                        System.out.println("Sorry!!! You were sued");
                        System.out.println("You are charged with Fraud");
                        p[Turn].lost_amt=150*(Game.SpinTheWheel());
                        System.out.println("Penalty amount = "+p[Turn].lost_amt);
                        p[Turn].bal-=p[Turn].lost_amt;
                        System.out.println("Player's Current balance is:"+p[Turn].bal);
                        if(Turn==0){
                            p[1].bal+=p[Turn].lost_amt;
                        }
                        else{
                            p[0].bal+=p[Turn].lost_amt;
                        }

                        p[Turn].initial_pos=p[Turn].last_pos;
                        Turn = Game.change_turn(Turn);
                    }
                    else if(p[Turn].last_pos == 20 || p[Turn].last_pos == 18)
                    {
                        //Draw the Lawsuit
                        System.out.println("Yow are sued");
                        System.out.println("But it went on a draw");
                        System.out.println("So, no amount will be deducted");
                        Turn = Game.change_turn(Turn);
                    }
                    else if(p[Turn].last_pos == 30){
                        //Gamble
                        int limit=50000;
                        System.out.println("You landed on a gamble tile");
                        System.out.println("Both Players will be playing");
                        System.out.println("Gamble amount limit: "+limit);
                        if(p[Turn]==p[0]){
                            System.out.println(p[Turn].name+" will gamble first");
                            System.out.println("Enter the amount you will gamble on ");
                            int z=sc.nextInt();
                            System.out.println("Enter a number from 1-10");
                            int num = sc.nextInt();
                            int uw = Game.gamble(z,limit,num);
                            p[0].bal+=uw;
                            if(uw>0){
                                System.out.println("$"+uw+ " amount is added");
                            }
                            else{
                                System.out.println(uw+ " amount is deducted");
                            }
                            Game.pressEnt();
                            System.out.println("Now "+ p[1].name + "will play the game");
                            int z2=10000;
                            int num2 = Game.SpinTheWheel();
                            System.out.println(p[1].name+" will gamble an amount of $"+z2);
                            System.out.println(p[1].name+" selected number "+num2);
                            int uw2 = Game.gamble(z2,limit,num2);
                            p[1].bal+=uw2;
                            if(uw2>0){
                                System.out.println("$"+uw2+ " amount is added");
                            }
                            else{
                                System.out.println(uw2+ " amount is deducted");
                            }
                        }
                        else{
                            int z2=10000;
                            int num2 = Game.SpinTheWheel();
                            System.out.println(p[Turn].name+" will gamble first");
                            System.out.println(p[1].name+" will gamble an amount of $"+z2);
                            System.out.println(p[1].name+" selected number "+num2);
                            int uw2 = Game.gamble(z2,limit,num2);
                            p[1].bal+=uw2;
                            if(uw2>0){
                                System.out.println("$"+uw2+ " amount is added");
                            }
                            else{
                                System.out.println(uw2+ " amount is deducted");
                            }
                            Game.pressEnt();
                            System.out.println(p[1].name+" will play now");
                            System.out.println("Gamble amount limit: "+limit);
                            System.out.println("Enter the amount you will gamble on ");
                            int z=sc.nextInt();
                            System.out.println("Enter a number from 1-10");
                            int num = sc.nextInt();
                            int uw = Game.gamble(z,limit,num);
                            p[0].bal+=uw;
                            if(uw>0){
                                System.out.println("$"+uw+ " amount is added");
                            }
                            else{
                                System.out.println(uw+ " amount is deducted");
                            }
                            System.out.println("Now "+ p[1].name + "will play the game");
                        }
                        Turn = Game.change_turn(Turn);
                    }
                    else if(p[Turn].last_pos == 19 || p[Turn].last_pos == 8
                            || p[Turn].last_pos ==28 || p[Turn].last_pos == 7
                            || p[Turn].last_pos == 9 || p[Turn].last_pos == 10){
                        //kids
                        int b_mon = 700*(Game.SpinTheWheel());
                        if(Turn == 0){
                            System.out.println("Congrats on your new born baby");
                            System.out.println("You will receive a small token of appreciation from "
                                    + p[1].name+" of $" +b_mon+"\n");
                            p[1].bal-=b_mon;
                            p[0].bal+=b_mon;
                        }
                        if(Turn == 1){
                            System.out.println("Congrats on your new born baby");
                            System.out.println("You will receive a small token o appreciation from "+
                                    p[0].name +" of $" +b_mon+"\n");
                            p[0].bal-=b_mon;
                            p[1].bal+=b_mon;
                        }
                        System.out.println(p[0].name+"'s Current balance is:"+p[0].bal);
                        System.out.println(p[1].name+"'s Current balance is:"+p[1].bal);
                        p[Turn].initial_pos=p[Turn].last_pos;
                        Turn = Game.change_turn(Turn);
                        //add kids to the associated player
                    }
                    else if (p[Turn].last_pos == 21)
                    {
                        //JACKPOT
                        p[Turn].jackpot=10000*(Game.SpinTheWheel());
                        System.out.println("Awesome!! You have won a Jackpot worth $"+p[Turn].jackpot);
                        p[Turn].bal+=p[Turn].jackpot;

                        p[Turn].initial_pos=p[Turn].last_pos;
                        Turn = Game.change_turn(Turn);
                    }
                    else if (p[Turn].last_pos == 27) {
                        //TREASURE
                        p[Turn].treasure=13000*(Game.SpinTheWheel());
                        System.out.println("Awesome!! You have found a treasure worth $"+p[Turn].treasure);
                        p[Turn].bal+=p[Turn].treasure;
                        p[Turn].initial_pos=p[Turn].last_pos;
                        Turn = Game.change_turn(Turn);
                    }
                    else if(p[Turn].last_pos == 5 || p[Turn].last_pos == 15
                            || p[Turn].last_pos == 23 ||p[Turn].last_pos == 25){
                        //life+bonus
                        p[Turn].life_count++;
                        p[Turn].bonus=7500;
                        System.out.println("You have Landed on a special tile");
                        System.out.println("You will gain a life and bonus of $"+p[Turn].bonus);
                        p[Turn].bal+=p[Turn].bonus;
                        p[Turn].initial_pos=p[Turn].last_pos;
                        Turn = Game.change_turn(Turn);
                    }
                    else if(p[Turn].last_pos==32){
                        p[Turn].bal = Game.retirement(p[Turn].life_count,p[Turn].kids,
                                p[Turn].hm,p[Turn].bal,p[Turn].career,p[Turn].wife);

                        p[Turn].retirement=true;
                        Turn = Game.change_turn(Turn);
                        //retirement
                    }
                    else{
                        System.out.println("You got a life");
                        p[Turn].life_count++;
                        p[Turn].initial_pos=p[Turn].last_pos;
                        Turn = Game.change_turn(Turn);
                    }
                    Game.pressEnter();
                }
            }
            Game.result(p[0].bal, p[1].bal, p[0].name, p[1].name);
        }
    }
}

