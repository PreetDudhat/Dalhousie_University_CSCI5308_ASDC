    import java.util.*;
    public class BadBookstoreMain {
        public void main(String[] args) throws Exception {
            List<Books> list = new ArrayList<>();
            Scanner scan = new Scanner(System.in);
            int menu = 0;
            System.out.println("Book Store Inventory");
            System.out.println();
            System.out.println("1. Add book");
            System.out.println("2. List all Book Details");
            System.out.println("3. Quit");
            boolean quit = false;
            do {
                System.out.print("Please enter your choice: ");
                menu = scan.nextInt();
                System.out.println();
                switch (menu) {
                    case 1:
                        System.out.println("ISBN: ");
                        String no = scan.nextLine();
                        System.out.println("Price: ");
                        double p = scan.nextDouble();
                        list.add(new Books(no, p));
                        break;
                    case 2:
                        System.out.println("ISBN Price");
                        for (Books s : list) {
                            System.out.println(s.getIsbn() + " " + s.getPrice());
                        }
                        break;
                    case 3:
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid Entry!");
                }
            } while (!quit);
        }

        class Books {
            public String isbn;
            public double price;
            public Books() {
            }
            public Books(String isbn) {
                this.isbn = isbn;
            }

            public Books(String isbn, double price) {
                super();
                this.isbn = isbn;
                this.price = price;
            }

            public String getIsbn() {
                return isbn;
            }

            public double getPrice() {
                return price;
            }
        }
    }
