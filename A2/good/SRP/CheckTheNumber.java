public class CheckTheNumber {
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
}
