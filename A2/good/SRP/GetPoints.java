public class GetPoints {
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
}
