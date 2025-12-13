import java.io.*;
import java.util.*;

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
                if (adminLogin()) adminMenu();
            } else if (loginChoice == 2) {
                if (studentLogin()) studentMenu();
            } else if (loginChoice == 3) {
                System.out.println("Exiting.");
                break;
            } else {
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
                PrintWriter pw = new PrintWriter(accFile);
                pw.println("admin,786");
                pw.println("student1,786");
                pw.println("student2,687");
                pw.println("student3,123");
                pw.close();
            }

            if (!bookFile.exists()) {
                PrintWriter pw = new PrintWriter(bookFile);
                pw.println("1001,The Republic,Plato,Philosophy,380,9780140455113,Available");
                pw.println("1002,Meditations,Marcus Aurelius,Philosophy,180,9780140449334,Available");
                pw.println("1003,Nicomachean Ethics,Aristotle,Philosophy,340,9780199213610,Available");
                pw.println("2001,Clean Code,Robert C. Martin,Programming,2008,9780132350884,Available");
                pw.println("2002,Java Complete Reference,Herbert Schildt,Programming,2022,9781260440232,Available");
                pw.println("2003,Introduction to Algorithms,CLRS,Programming,2009,9780262033848,Available");
                pw.println("2004,Head First Java,Kathy Sierra,Programming,2005,9780596009205,Available");
                pw.println("3001,Fundamentals of Physics,Halliday & Resnick,Physics,2014,9781118230718,Available");
                pw.println("3002,A Brief History of Time,Stephen Hawking,Physics,1988,9780553380163,Available");
                pw.println("3003,The Elegant Universe,Brian Greene,Physics,2003,9780393338102,Available");
                pw.println("4001,Economics,Paul Samuelson,Economics,2010,9780073511290,Available");
                pw.println("4002,The Wealth of Nations,Adam Smith,Economics,1776,9780140432084,Available");
                pw.println("4003,Capital in the Twenty-First Century,Thomas Piketty,Economics,2013,9780674430006,Available");
                pw.println("5001,Thinking Fast and Slow,Daniel Kahneman,Economics,2011,9780374533557,Available");
                pw.println("5002,The Metaphysics,Aristotle,Philosophy,350,9780140446197,Available");
                pw.println("6001,The Myth of Sisyphus,Albert Camus,Philosophy,1942,9780679733737,Available");
                pw.println("6002,Code Complete,Steve McConnell,Programming,2004,9780735619678,Available");
                pw.println("6003,Astrophysics for People in a Hurry,Neil deGrasse Tyson,Physics,2017,9780393609394,Available");
                pw.println("6004,Why Nations Fail,Acemoglu & Robinson,Economics,2012,9780307719225,Available");
                pw.println("6005,The Art of Computer Programming,Donald Knuth,Programming,1997,9780201896831,Available");
                pw.println("6006,Beyond Good and Evil,Friedrich Nietzsche,Philosophy,1886,9780141395837,Available");
                pw.close();
            }

            if (!histFile.exists()) {
                PrintWriter pw = new PrintWriter(histFile);
                pw.close();
            }

        } catch (Exception e) {
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
            } catch (InputMismatchException e) {
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
            Scanner accountScanner = new Scanner(new File(accountsFile));
            while (accountScanner.hasNextLine()) {
                String line = accountScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    if (parts[0].trim().equals(username) && parts[1].trim().equals(password)) {
                        System.out.println("Login Successful.");
                        accountScanner.close();
                        return true;
                    }
                }
            }
            accountScanner.close();
        } catch (Exception e) {
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
                } catch (InputMismatchException e) {
                    System.out.println("Please enter numbers only.");
                    sc.nextLine();
                }
            }

            if (choice == 1) addBook();
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
                } catch (InputMismatchException e) {
                    System.out.println("Please enter numbers only.");
                    sc.nextLine();
                }
            }

            if (choice == 1) 
                searchBook();
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
        System.out.print("Enter Book ID: ");
        String bookId = sc.nextLine();

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Publish Year: ");
        String year = sc.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(booksFile, true));
            pw.println(bookId + "," + title + "," + author + "," + category + "," + year + "," + isbn + ",Available");
            pw.close();
            System.out.println("Book added.");
        } catch (IOException e) {
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
                String[] f = line.split(",");
                if (f.length == 7) {
                    System.out.println("\nID: " + f[0]);
                    System.out.println("Title: " + f[1]);
                    System.out.println("Author: " + f[2]);
                    System.out.println("Category: " + f[3]);
                    System.out.println("Publish Year: " + f[4]);
                    System.out.println("ISBN: " + f[5]);
                    System.out.println("Availability: " + f[6]);
                    System.out.println("-----------------------------");
                }
            }
            bookScanner.close();
        } catch (Exception e) {
            System.out.println("Error reading books file.");
        }
    }

    static void updateBookInfo() {

    System.out.print("Enter Book ID to update: ");
    String id = sc.nextLine();
    boolean found = false;
    String updatedData = "";

    try {
        Scanner fs = new Scanner(new File(booksFile));

        while (fs.hasNextLine()) {
            String line = fs.nextLine();
            String[] f = line.split(",", -1);

            if (f[0].equals(id)) {
                found = true;

                System.out.println("Current Book Info:");
                System.out.println("Title: " + f[1]);
                System.out.println("Author: " + f[2]);
                System.out.println("Category: " + f[3]);
                System.out.println("Publish Year: " + f[4]);
                System.out.println("ISBN: " + f[5]);
                System.out.println("Availability: " + f[6]);

                System.out.print("New Title (leave blank to keep): ");
                String input = sc.nextLine();
                if (!input.isEmpty()) 
                    f[1] = input;

                System.out.print("New Author (leave blank to keep): ");
                input = sc.nextLine();
                if (!input.isEmpty()) 
                    f[2] = input;

                System.out.print("New Category (leave blank to keep): ");
                input = sc.nextLine();
                if (!input.isEmpty()) 
                    f[3] = input;

                System.out.print("New Year (leave blank to keep): ");
                input = sc.nextLine();
                if (!input.isEmpty()) 
                    f[4] = input;

                System.out.print("New ISBN (leave blank to keep): ");
                input = sc.nextLine();
                if (!input.isEmpty()) 
                    f[5] = input;

                line = String.join(",", f);
                System.out.println("Book updated.");
            }

            updatedData += line + "\n";
        }

        fs.close();

        if (!found) {
            System.out.println("Book ID not found.");
            return;
        }

        PrintWriter pw = new PrintWriter(booksFile);
        pw.print(updatedData);
        pw.close();

    } catch (Exception e) {
        System.out.println("Error updating books file.");
    }
}

    static void searchBook() {

    while (true) {

        System.out.println("\n--- Search Book Menu ---");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");
        System.out.println("4. Search by Category");
        System.out.println("5. Search by Publish Year");
        System.out.println("6. Search by ISBN");
        System.out.println("7. Exit Search");
        System.out.print("Enter your choice: ");

        int choice = 0;
        try {
            choice = sc.nextInt();
            sc.nextLine();
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers only.");
            sc.nextLine();
            continue;
        }
        catch (Exception e) {
            System.out.println("Unexpected input error.");
            sc.nextLine();
            continue;
        }

        if (choice == 7) {
            break;
        }

        System.out.print("Enter search keyword: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;

        try {
            Scanner fileScanner = new Scanner(new File(booksFile));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] fields = line.split(",", -1);

                boolean match = false;

                switch (choice) {
                    case 1:
                        if (fields[0].equalsIgnoreCase(keyword)) 
                            match = true;
                        break;
                    case 2:
                        if (fields[1].toLowerCase().contains(keyword)) 
                            match = true;
                        break;
                    case 3:
                        if (fields[2].toLowerCase().contains(keyword)) 
                            match = true;
                        break;
                    case 4:
                        if (fields[3].toLowerCase().contains(keyword)) 
                            match = true;
                        break;
                    case 5:
                        if (fields[4].equals(keyword)) 
                            match = true;
                        break;
                    case 6:
                        if (fields[5].equals(keyword)) 
                            match = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

                if (match) {
                    found = true;
                    System.out.println("\nBook Found:");
                    System.out.println("ID: " + fields[0]);
                    System.out.println("Title: " + fields[1]);
                    System.out.println("Author: " + fields[2]);
                    System.out.println("Category: " + fields[3]);
                    System.out.println("Publish Year: " + fields[4]);
                    System.out.println("ISBN: " + fields[5]);
                    System.out.println("Availability: " + fields[6]);
                    System.out.println("-----------------------------");
                }
            }

            fileScanner.close();

            if (!found) {
                System.out.println("No matching book found.");
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("Books file not found.");
        }
        catch (Exception e) {
            System.out.println("Error while searching books.");
        }
    }
}

    static void issueBook() {

    System.out.print("Enter Book ID: ");
    String id = sc.nextLine();
    boolean found = false;
    String updatedData = "";

    try {
        Scanner fs = new Scanner(new File(booksFile));

        while (fs.hasNextLine()) {
            String line = fs.nextLine();
            String[] f = line.split(",", -1);

            if (f[0].equals(id)) {
                found = true;

                if (f[6].equals("Available")) {
                    f[6] = "Issued";
                    PrintWriter history = new PrintWriter(new FileWriter(historyFile, true));
                    history.println("Book " + id + " issued");
                    history.close();
                    System.out.println("Book issued.");
                } else {
                    System.out.println("Book not available.");
                }

                line = String.join(",", f);
            }

            updatedData += line + "\n";
        }

        fs.close();

        if (!found) {
            System.out.println("Book ID not found.");
            return;
        }

        PrintWriter pw = new PrintWriter(booksFile);
        pw.print(updatedData);
        pw.close();

    } catch (Exception e) {
        System.out.println("Error issuing book.");
    }
}

    static void returnBook() {
    System.out.print("Enter Book ID: ");
    String id = sc.nextLine();
    boolean found = false;
    String updatedData = "";

    try {
        Scanner fs = new Scanner(new File(booksFile));

        while (fs.hasNextLine()) {
            String line = fs.nextLine();
            String[] f = line.split(",", -1);

            if (f[0].equals(id)) {
                found = true;

                if (f[6].equals("Issued")) {
                    f[6] = "Available";
                    PrintWriter history = new PrintWriter(new FileWriter(historyFile, true));
                    history.println("Book " + id + " returned");
                    history.close();
                    System.out.println("Book returned.");
                } else {
                    System.out.println("Book was not issued.");
                }

                line = String.join(",", f);
            }

            updatedData += line + "\n";
        }

        fs.close();

        if (!found) {
            System.out.println("Book ID not found.");
            return;
        }

        PrintWriter pw = new PrintWriter(booksFile);
        pw.print(updatedData);
        pw.close();

    } catch (Exception e) {
        System.out.println("Error returning book.");
    }
}

    static void viewHistory() {
        try {
            Scanner hs = new Scanner(new File(historyFile));
            if (!hs.hasNextLine()) {
                System.out.println("No history available.");
                hs.close();
                return;
            }
            System.out.println("\n--- History ---");
            while (hs.hasNextLine()) {
                System.out.println(hs.nextLine());
            }
            hs.close();
        } catch (Exception e) {
            System.out.println("Error reading history.");
        }
    }

    static void checkAvailability() {
    try {
        Scanner bookScanner = new Scanner(new FileReader(booksFile));
        boolean found = false;

        System.out.println("\n--- Available Books ---");

        while (bookScanner.hasNextLine()) {
            String line = bookScanner.nextLine();
            String[] f = line.split(",", -1);

            if (f.length == 7 && f[6].equals("Available")) {
                System.out.println("ID: " + f[0]);
                System.out.println("Title: " + f[1]);
                System.out.println("Author: " + f[2]);
                System.out.println("Category: " + f[3]);
                System.out.println("Publish Year: " + f[4]);
                System.out.println("ISBN: " + f[5]);
                System.out.println("Availability: " + f[6]);
                System.out.println("-----------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available books at the moment.");
        }

        bookScanner.close();

    } catch (Exception e) {
        System.out.println("Error reading books file.");
    }
}

    static void viewBorrowedBooks() {
        try {
            Scanner bookScanner = new Scanner(new FileReader(booksFile));
            boolean found = false;
            while (bookScanner.hasNextLine()) {
                String[] f = bookScanner.nextLine().split(",", -1);
                if (f[6].equals("Issued")) {
                    System.out.println("ID: " + f[0] + ", Title: " + f[1] + ", Author: " + f[2]);
                    found = true;
                }
            }
            if (!found) System.out.println("No borrowed books.");
            bookScanner.close();
        } catch (Exception e) {
            System.out.println("Error reading books file.");
        }
    }
}