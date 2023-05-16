import java.util.Scanner;

public class GoodBookstoreMain {
        public static void main(String[] args) {
            Shelf Shelf = new Shelf(5);
            Shelf.addNewShelfItem("Add new book");
            Shelf.addNewShelfItem("Remove a book");
            Shelf.addNewShelfItem("Update a book");
            Shelf.addNewShelfItem("List all books");
            Shelf.addNewShelfItem("Quit");

            BookInterface list = new BookList();

            int choice;
            boolean flag = true;
            do {
                choice = Shelf.getUserSelection();
                switch (choice) {
                    case 1:
                        list.add();
                        break;
                    case 2:
                        list.remove();
                        break;
                    case 3:
                        list.update();
                        break;
                    case 4:
                        list.print();
                        break;
                    case 5:
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid Shelf option!");
                }
            } while (flag);
        }
}