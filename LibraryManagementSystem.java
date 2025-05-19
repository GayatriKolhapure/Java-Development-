import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

class Book implements Serializable { 
    int bookId;
    String title;
    String author;
    boolean isIssued;

    public int getId(){
        return bookId;
    }

    public Book(int bookId, String title, String author){
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
    void DisplayDetails(){
        System.out.println("Book Id: "+bookId);
        System.out.println("Title: "+title);
        System.out.println("Author: "+author);
        System.out.println("Status: "+(isIssued ? "IsIssued" : "Available"));
        System.out.println("-----------------------------------------------------");
    }
}
public class LibraryManagementSystem {
    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
   
    public static void main(String[] args) {
        loadBooksromFile();
        String role = login();

        int choice;
        do { 
            System.out.println("\n------Library Menu----");
            System.out.println("1. View All Books");

            if(role.equals("admin")){
                System.out.println("2. Add Book");
                System.out.println("3. Issue Book");
                System.out.println("4. Return Book");
                System.out.println("5. Remove Book.");

            }
            System.out.println("6. Search Book");
            System.out.println("7. Exit");
            System.out.println("Choose an options: ");


            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1: viewBooks(); break;
                case 2: {
                    if(role.equals("admin")) addBook();
                    else System.out.println("Unauthorized access.");
                }  break;
                case 3:  {
                    if(role.equals("admin")) issueBook();
                    else System.out.println("Unauthorized access.");
                }  break;
                case 4:  {
                    if(role.equals("admin")) returnBook();
                    else System.out.println("Unauthorized access.");
                }  break;               
                case 5:  {
                    if(role.equals("admin")) removeBook(sc);
                    else System.out.println("Unauthorized access.");
                }  break;
                case 6: searchBook(); break;
                case 7: System.out.println("Thank you! Exiting...!"); break;

                default: System.out.println("Invalid choice! Try again. ");
            }
            
        }while(choice != 7);

        saveBooksToFile();

    }
    static void addBook(){
        System.out.println("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if(findBookById(id) != null){
            System.out.println("Book with this ID already exists! ");
            return;
        }
        System.out.println("Enter Book Name: ");
        String BookName = sc.nextLine();
        System.out.println("Enter Author Name: ");
        String author = sc.nextLine();
        books.add(new Book(id, BookName, author));
        System.out.println("Book added Succefully. ");
    }
    static void viewBooks(){
        if(books.isEmpty()) {
            System.out.println("No Books in Library. ");
            return;
        }else {
            System.out.println("\n Available Books: ");
            for(Book b: books){
                b.DisplayDetails();
            }
        }
    }
    static void issueBook(){
        System.out.println("Enter Book ID to issue: ");
        int id = sc.nextInt();
        Book b  = findBookById(id);
        if(b == null){
            System.out.println("Book not found");
        }else if(b.isIssued){
            System.out.println("Book is already issued.");
        }else{
            b.isIssued = true;
            System.out.println("Book issued succefully. ");
        }
        
    }
    static void returnBook(){
        System.out.println("Enter Book ID to return: ");
        int id = sc.nextInt();
        Book b  = findBookById(id);

        if(b == null){
           System.out.println("Book not found.");
        }else if(!b.isIssued){
            System.out.println("Book is not issued. ");
        }else{
            b.isIssued = false;
            System.out.println("Book returned successfully. ");
        }
                   
    }
    static Book findBookById(int id){
        for(Book b: books){
            if(b.bookId == id){
                return b;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static void loadBooksromFile(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.dat"))){
            books = (ArrayList<Book>) ois.readObject();
            System.out.println("Books loaded from file. ");
        }catch (FileNotFoundException e){
            System.out.println("No existing data found. starting fresh.");
        }catch(Exception e){
            System.out.println("Error Loading books: "+e.getMessage());
        }
    }

    public static void saveBooksToFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.dat"))){
            oos.writeObject(books);
            System.out.println("Books saved to files.");
        }catch(IOException e) {
            System.out.println("Error saving books "+ e.getMessage());
        }
    }

    static void searchBook() {
        System.out.println("\n -----Search Menu------");
        System.out.println("1. Search by Book Name");
        System.out.println("2. Search by Author");
        System.out.println("Choose an option: ");

        int option = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter search keyword: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;

        for(Book b: books){
            if((option == 1 && b.title.toLowerCase().contains(keyword)) || (option == 2 && b.author.toLowerCase().contains(keyword))){
                b.DisplayDetails();
                found = true;
            }
        }
        if(!found){
            System.out.println("No matching book found.");
        }
    }
    static void removeBook(Scanner sc) {
        System.out.println("Enter Book ID to remove: ");
        int id = sc.nextInt();
        sc.nextLine();

        Book bookToRemove = null;
        for(Book book: books){
            if(book.getId() == id){
                bookToRemove = book;
                break;
            }
        }

        if(bookToRemove != null){
            books.remove(bookToRemove);
            System.out.println("Book removed succefully.");
            saveBooksToFile();
        }else{
            System.out.println("Book not found.");
        }


    }

    static String login() {
        System.out.println("=== Library System Login ===");
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        if(username.equals("admin") && password.equals("admin123")){
            System.out.println("Welcome, Admin!");
            return "admin";
        }else if(username.equals("guest") && password.equals("guest123")){
            System.out.println("Welcome, Guest!");
            return "guest";
        }
        System.out.println("Invalid credentials. Exixteing...");
        System.exit(0);
        return "";
    }


}
