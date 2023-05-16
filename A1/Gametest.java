public class Gametest {
    mockDB md=new mockDB();

    public void printPlayerDetails_test(){
        try{
            new Game();
            System.out.println("printPlayerDetails Test\n");
            Game.printPlayerDetails("Zeus",4,30000,
                    7,90000,"PROFESSOR", 90000);
            System.out.println("Test Successful");
        }
        catch (Exception e){
            System.out.println("printPlayerDetails method not working");
        }
    }

    public void retirement_test(){
        try{
            System.out.println("retirement Test\n");
            md.mockDB();
            int bal = Game.retirement(md.gethMap2("Life_count"),md.gethMap2("kids"),
                    md.gethMap2("house_money"),md.gethMap2("bal3"),
                    md.gethMap("career"),md.gethMap("wife"));
            if(bal>0){
                System.out.println("Test Successful");
            }
            else{
                System.out.println("Test Failed");
                System.out.println("Balance can't be negative");
            }
        }
        catch(Exception e){
            System.out.println("Test Failed");
            System.out.println("Retirement method not working");
        }
    }

    public void gamble_test(){
        System.out.println("Gamble test");
        int lim = md.gethMap2("limit");
        int gmn = md.gethMap2("gmoney");
        int g_num = md.gethMap2("g_num");
        int xx = Game.gamble(gmn,lim,g_num);
        try{
            if(xx<0){
                System.out.println("Test Successful");
            }
            else{
                System.out.println("Test Failed");
            }
        }
        catch(Exception e){
            System.out.println("gamble method not working");
        }
    }

    public void dice_roll_test(){
        System.out.println("dice_roll Test\n");
        int roll = Game.getDiceRoll();
        try{
            if(roll<=4 && roll>=1){
                System.out.println("Test Success");
            }
            else{
                System.out.println("Test Failed");
            }
        }
        catch(Exception e ){
            System.out.println("Test Failed");
            System.out.println("GetDiceRoll method not working");
        }
    }

    public void spin_the_wheel_test(){
        int roll = Game.getDiceRoll();
        try{
            System.out.println("spin_the_wheel Test\n");
            if(roll<=10 && roll>=1){
                System.out.println("Test Success");
            }
            else{
                System.out.println("Test Failed");
            }
        }
        catch(Exception e ){
            System.out.println("Test Failed");
            System.out.println("Spin the Wheel method not working");
        }
    }

    public void house_price_test(){
        try{
            System.out.println("houseprice Test\n");
            int hm = Game.getHousePrice();
            if(hm>0){
                System.out.println("Test Successful");
            }
            else{
                System.out.println("Test Failed");
            }
        }
        catch (Exception e){
            System.out.println("getHousePrice method not working");
        }
    }

    public void debt_test() {
        try{
            System.out.println("debt Test\n");
            md.mockDB();
            String career = md.gethMap("career");
            System.out.println(career);
            System.out.println("Career " + career);
            int dtest = Game.payDebt(career);
            if(dtest>0){
                System.out.println("TEST SUCCESS");
            }
            else{
                System.out.println("Test Failed");
                System.out.println("This career does not exist");
            }
        }
        catch(Exception e){
            System.out.println("paydebt method not working");
        }
    }

    public void change_turn_test(){
        try{
            System.out.println("changeTurn Test\n");
            int tx = md.gethMap2("turn");
            int t= Game.change_turn(tx);
            if(t==0){
                System.out.println("Test Successful");
            }
            else{
                System.out.println("Test Failed");
            }
        }
        catch (Exception e){
            System.out.println("change turn method not working");
        }
    }

    public void getSalary_test(){
        try{
            System.out.println("getSalary Test\n");
            String car = md.gethMap("career");
            int car2 = Game.getSalary(car);
            if(car2<70000){
                System.out.println("Test Successful");
            }
            else{
                System.out.println("Test Failed");
            }
        }
        catch(Exception e){
            System.out.println("getSalary method not working");
        }
    }

    public void result_test(){
        try{
            System.out.println("Result Test\n");
            int bal1 =md.gethMap2("bal");
            int bal2 = md.gethMap2("bal2");
            String n1 = md.gethMap("name");
            String n2 = md.gethMap("name2");
            Game.result(bal1,bal2,n1,n2);
            System.out.println("Test Success");
        }
        catch(Exception e){
            System.out.println("Test Failed");
            System.out.println("getSalary method not working");
        }
    }

    public void getWifeName_test(){
        try{
            int x = md.gethMap2("turn");
            String wname = Game.getWifeName(x);
            if(wname == null){
                System.out.println("Test Success");
            }
            else{
                System.out.println("Test Failed");
            }
        }
        catch(Exception e){
            System.out.println("getWifeName method not working");
        }
    }
}

