import java.util.*;
import java.io.*;

public class LibraryManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static final String accountsFile = "accounts.txt";
    static final String booksFile = "books.txt";
    static final String historyFile = "history.txt";

    public static void main(String[] args) {

        initializeFiles();

        while (true) {

            int loginChoice = loginMenu();

            if (loginChoice == 1) {
                boolean success = adminLogin();
                if (success)
                    adminMenu();
            }
            else if (loginChoice == 2) {
                boolean success = studentLogin();
                if (success)
                    studentMenu();
            }
            else if (loginChoice == 3) {
                System.out.println("Exiting.");
                break;
            }
            else {
                System.out.println("Invalid option.");
            }
        }
    }


    static void initializeFiles() {

        try {
            File accFile = new File(accountsFile);
            File bookFile = new File(booksFile);
            File histFile = new File(historyFile);

            if (!accFile.exists()) {
                FileWriter w = new FileWriter(accountsFile);
                w.write("admin,786\nstudent,786\n");
                w.close();
            }

            if (!bookFile.exists()) {
                FileWriter bw = new FileWriter(booksFile);

                bw.write("1001,The Republic,Plato,Philosophy,380,9780140455113,Available\n");
                bw.write("1002,Meditations,Marcus Aurelius,Philosophy,180,9780140449334,Available\n");
                bw.write("1003,Nicomachean Ethics,Aristotle,Philosophy,340,9780199213610,Available\n");
                bw.write("2001,Clean Code,Robert C. Martin,Programming,2008,9780132350884,Available\n");
                bw.write("2002,Java Complete Reference,Herbert Schildt,Programming,2022,9781260440232,Available\n");
                bw.write("2003,Introduction to Algorithms,CLRS,Programming,2009,9780262033848,Available\n");
                bw.write("2004,Head First Java,Kathy Sierra,Programming,2005,9780596009205,Available\n");
                bw.write("3001,Fundamentals of Physics,Halliday & Resnick,Physics,2014,9781118230718,Available\n");
                bw.write("3002,A Brief History of Time,Stephen Hawking,Physics,1988,9780553380163,Available\n");
                bw.write("3003,The Elegant Universe,Brian Greene,Physics,2003,9780393338102,Available\n");
                bw.write("4001,Economics,Paul Samuelson,Economics,2010,9780073511290,Available\n");
                bw.write("4002,The Wealth of Nations,Adam Smith,Economics,1776,9780140432084,Available\n");
                bw.write("4003,Capital in the Twenty-First Century,Thomas Piketty,Economics,2013,9780674430006,Available\n");
                bw.write("5001,Thinking Fast and Slow,Daniel Kahneman,Economics,2011,9780374533557,Available\n");
                bw.write("5002,The Metaphysics,Aristotle,Philosophy,350,9780140446197,Available\n");
                bw.write("6001,The Myth of Sisyphus,Albert Camus,Philosophy,1942,9780679733737,Available\n");
                bw.write("6002,Code Complete,Steve McConnell,Programming,2004,9780735619678,Available\n");
                bw.write("6003,Astrophysics for People in a Hurry,Neil deGrasse Tyson,Physics,2017,9780393609394,Available\n");
                bw.write("6004,Why Nations Fail,Acemoglu & Robinson,Economics,2012,9780307719225,Available\n");
                bw.write("6005,The Art of Computer Programming,Donald Knuth,Programming,1997,9780201896831,Available\n");
                bw.write("6006,Beyond Good and Evil,Friedrich Nietzsche,Philosophy,1886,9780141395837,Available\n");

                bw.close();
            }

            if (!histFile.exists()) {
                FileWriter h = new FileWriter(historyFile);
                h.write("");
                h.close();
            }

        }
        catch (Exception e) {
            System.out.println("Error initializing files.");
        }
    }


    static int loginMenu() {

        System.out.println("\n--- Login Menu ---");
        System.out.println("1. Admin Login");
        System.out.println("2. Student Login");
        System.out.println("3. Exit");

        while (true) {
            System.out.print("Enter choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();
                return choice;
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter numbers only.");
                sc.nextLine();
            }
        }
    }


    static boolean adminLogin() {
        System.out.println("\n--- Admin Login ---");
        System.out.print("Username: ");
        String user = sc.next();
        System.out.print("Password: ");
        String pass = sc.next();
        sc.nextLine();
        return validateLogin(user, pass);
    }

    static boolean studentLogin() {
        System.out.println("\n--- Student Login ---");
        System.out.print("Username: ");
        String user = sc.next();
        System.out.print("Password: ");
        String pass = sc.next();
        sc.nextLine();
        return validateLogin(user, pass);
    }


    static boolean validateLogin(String username, String password) {

        try {
            Scanner accountScanner = new Scanner(new FileReader(accountsFile));

            while (accountScanner.hasNextLine()) {
                String line = accountScanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 2) {
                    String storedUser = parts[0].trim();
                    String storedPass = parts[1].trim();

                    if (storedUser.equals(username) && storedPass.equals(password)) {
                        System.out.println("Login Successful.");
                        accountScanner.close();
                        return true;
                    }
                }
            }

            accountScanner.close();
        }
        catch (Exception e) {
            System.out.println("Error reading accounts file.");
        }

        System.out.println("Invalid credentials.");
        return false;
    }


    static void adminMenu() {

        while (true) {

            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Update Book Info");
            System.out.println("4. Search Book");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View History");
            System.out.println("8. Logout");

            int choice = 0;

            while (true) {
                System.out.print("Enter choice: ");
                try {
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                }
                catch (InputMismatchException e) {
                    System.out.println("Please enter numbers only.");
                    sc.nextLine();
                }
            }

            if (choice == 1)
                addBook();
            else if (choice == 2)
                viewBooks();
            else if (choice == 3)
                updateBookInfo();
            else if (choice == 4)
                searchBook();
            else if (choice == 5)
                issueBook();
            else if (choice == 6)
                returnBook();
            else if (choice == 7)
                viewHistory();
            else if (choice == 8)
                break;
            else
                System.out.println("Invalid option.");
        }
    }


    static void studentMenu() {

        while (true) {

            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Search Book");
            System.out.println("2. Check Availability");
            System.out.println("3. View Borrowed Books");
            System.out.println("4. Logout");

            int choice = 0;

            while (true) {
                System.out.print("Enter choice: ");
                try {
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                }
                catch (InputMismatchException e) {
                    System.out.println("Please enter numbers only.");
                    sc.nextLine();
                }
            }

            if (choice == 1)
                searchBookStudent();
            else if (choice == 2)
                checkAvailability();
            else if (choice == 3)
                viewBorrowedBooks();
            else if (choice == 4)
                break;
            else
                System.out.println("Invalid option.");
        }
    }


    static void addBook() {

        int bookId = 0;
        while (true) {
            System.out.print("Enter Book ID: ");
            try {
                bookId = sc.nextInt();
                sc.nextLine();
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        }

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        int publishYear = 0;
        while (true) {
            System.out.print("Enter Publish Year: ");
            try {
                publishYear = sc.nextInt();
                sc.nextLine();
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a valid year.");
                sc.nextLine();
            }
        }

        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();

        String record = bookId + "," + title + "," + author + "," + category + "," + publishYear + "," + isbn + "," + "Available";

        try {
            FileWriter writer = new FileWriter(booksFile, true);
            writer.write(record + "\n");
            writer.close();
            System.out.println("Book added.");
        }
        catch (IOException e) {
            System.out.println("Error writing books file.");
        }
    }


    static void viewBooks() {

        try {
            Scanner bookScanner = new Scanner(new FileReader(booksFile));

            if (!bookScanner.hasNextLine()) {
                System.out.println("No books available.");
                bookScanner.close();
                return;
            }

            while (bookScanner.hasNextLine()) {
                String line = bookScanner.nextLine();
                String[] fields = line.split(",");

                if (fields.length == 7) {
                    System.out.println("\nID: " + fields[0]);
                    System.out.println("Title: " + fields[1]);
                    System.out.println("Author: " + fields[2]);
                    System.out.println("Category: " + fields[3]);
                    System.out.println("Publish Year: " + fields[4]);
                    System.out.println("ISBN: " + fields[5]);
                    System.out.println("Availability: " + fields[6]);
                    System.out.println("-----------------------------");
                }
            }

            bookScanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("books.txt not found.");
        }
        catch (Exception e) {
            System.out.println("Error reading books file.");
        }
    }


    static void updateBookInfo() {}
    static void searchBook() {}
    static void issueBook() {}
    static void returnBook() {}
    static void viewHistory() {}
    static void searchBookStudent() {}
    static void checkAvailability() {}
    static void viewBorrowedBooks() {}

}
