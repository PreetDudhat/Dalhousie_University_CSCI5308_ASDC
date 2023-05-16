import java.util.Scanner;
    public class BookList implements BookInterface{

        private int count = 0;
        private final int MAX_SIZE = 50;
        private Book[] list = null;

        public BookList()
        {
            list = new Book[MAX_SIZE];
        }

        int find(String isbn) {
            for (int i = 0; i < count; i++) {
                if (isbn.equals(list[i].getIsbn())) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public void add() {
            if (count == list.length) {
                System.out.println("List is full!");
            } else {
                String isbn;
                double price = 0;
                int publishedYear = 0;
                Scanner sc = new Scanner(System.in);

                System.out.print("Enter book isbn: ");
                isbn = sc.next();

                /* Check if isbn is already in the list*/
                int index = find(isbn);
                if (index >= 0) {
                    System.out.println("ISBN already exists. Please try another!!!");
                    return;
                }

                System.out.print("Enter book price: ");
                price = sc.nextDouble();
                Book b = new Book();
                b.setIsbn(isbn);
                b.setPrice(price);

                list[count++] = b;
                System.out.println("New book has been added.");
            }
        }

        @Override
        public void remove() {
            String removedISBN;
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the isbn of the book you wish to remove: ");
            removedISBN = sc.nextLine().trim();
            int pos = find(removedISBN);
            if (pos < 0) {
                System.out.println("This book does not exist.");
            } else {  // Shift up the remainder of the list
                for (int i = pos; i < count - 1; i++) {
                    list[i] = list[i + 1];
                }
                count--;
                System.out.println("The book with the ISBN: " + removedISBN + " has been removed");
            }
        }

        @Override
        public void update() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the ISBN of the book to update: ");
            String isbn = sc.nextLine();
            int pos = find(isbn);
            if (pos < 0) {
                System.out.println("This book does not exist.");
            } else {
                System.out.print("Enter the new price: ");
                double newPrice = sc.nextDouble();
                list[pos].setPrice(newPrice);
                System.out.println("The book with the ISBN: " + isbn + " has been updated");
            }
        }

        @Override
        public void print() {
            if (count == 0) {
                System.out.println("There is no books in the system");
                return;
            }

            System.out.println("LIST OF BOOKS:");
            for (int i = 0; i < count; i++) {
                System.out.println(list[i]);
            }
        }
    }