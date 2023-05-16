
import java.util.Scanner;

public class Shelf {
    private String[] ShelfItems;
    private int count = 0; // current number of ShelfItems

    public Shelf(int size) {
        ShelfItems = new String[size];
    }

    public void addNewShelfItem(String item) {
        if (count < ShelfItems.length) {
            ShelfItems[count++] = item;
        }
    }

    public int getUserSelection() {
        System.out.println("THE BOOK TIGER COMPANY");
        int result = 0;
        if (count > 0) { // print out ShelfItems
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + "-" + ShelfItems[i]);
            }
            System.out.print("Please select an operation: ");
            Scanner sc = new Scanner(System.in);
            result = sc.nextInt();
        }
        return result;
    }
}
