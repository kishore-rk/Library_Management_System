import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Library extends JFrame {
    int id;
    public Library(int i) {
        id =i;

        // view book 
        JLabel view_book = new JLabel("View Books");
        view_book.setBounds(170, 50, 100, 25);
        this.add(view_book);

        JButton view_book_button = new JButton("View Books");
        view_book_button.setBounds(150, 80, 150, 25);
        view_book_button.setFocusable(false);
        view_book_button.addActionListener(e -> {
            try {
                new ViewBooks();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        this.add(view_book_button);

        // get book
        JLabel get_book = new JLabel("Get Book");
        get_book.setBounds(170, 120, 100, 25);
        this.add(get_book);

        JButton get_book_button = new JButton("Get Book");
        get_book_button.setBounds(150, 150, 150, 25);
        get_book_button.setFocusable(false);
        get_book_button.addActionListener(e -> {
            try {
                new GetBook(id);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        this.add(get_book_button);

        JLabel return_book = new JLabel("Return Book");
        return_book.setBounds(170, 190, 100, 25);
        this.add(return_book);

        JButton return_book_button = new JButton("Return Book");
        return_book_button.setBounds(150, 220, 150, 25);
        return_book_button.setFocusable(false);
        return_book_button.addActionListener(e -> {
            try {
                new ReturnBook(id);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        this.add(return_book_button);

        JLabel view_borrowed_books = new JLabel("View Borrowed Books");
        view_borrowed_books.setBounds(170, 260, 150, 25);
        this.add(view_borrowed_books);

        JButton view_borrowed_books_button = new JButton("Borrowed Books");
        view_borrowed_books_button.setBounds(150, 290, 150, 25);
        view_borrowed_books_button.setFocusable(false);
        view_borrowed_books_button.addActionListener(e -> {
            try {
                new ViewBorrowedBooks(id);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        this.add(view_borrowed_books_button);

        JLabel add_book = new JLabel("Add Book");
        add_book.setBounds(170, 310, 100, 25);
        

        JButton add_book_button = new JButton("Add Book");
        add_book_button.setBounds(150,350,100,25);   
        add_book_button.setFocusable(false);
        add_book_button.addActionListener(e -> {
            new Admin();
        });
        if( id ==0){
            this.add(add_book);
            this.add(add_book_button);
        }

        this.setTitle("Library Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        
        this.getContentPane().setBackground(Color.BLACK);
        this.setSize(600, 550);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        new Library(1);
    }
    
}
