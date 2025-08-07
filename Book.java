import java.util.*;

public class Book {
    private String id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issue() { isIssued = true; }
    public void returned() { isIssued = false; }

    @Override
    public String toString() {
        return id + " - " + title + " by " + author + (isIssued ? " [Issued]" : " [Available]");
    }
public class User {
    private String userId;
    private String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
} import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void showAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void issueBook(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                if (!book.isIssued()) {
                    book.issue();
                    System.out.println("Book issued: " + book.getTitle());
                } else {
                    System.out.println("Book already issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                if (book.isIssued()) {
                    book.returned();
                    System.out.println("Book returned: " + book.getTitle());
                } else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }
}import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Adding some books initially
        library.addBook(new Book("101", "Java Basics", "James Gosling"));
        library.addBook(new Book("102", "OOP in Java", "Bjarne Stroustrup"));
        library.addBook(new Book("103", "Data Structures", "Mark Allen"));

        while (true) {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Show all books");
            System.out.println("2. Issue book");
            System.out.println("3. Return book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    library.showAllBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID to issue: ");
                    String issueId = sc.nextLine();
                    library.issueBook(issueId);
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    String returnId = sc.nextLine();
                    library.returnBook(returnId);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}