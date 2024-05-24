package chk;
import javax.swing.*;

public class BookSelectionDialog {

    public static void main(String[] args) {
        // Book details
        String[][] books = {
            {"9780078022159", "Database System Concepts", "English", "Abraham Silberschatz", "Non-fictional"},
            {"9780124077263", "Computer Organization and Design", "English", "David A. Patterson, John L. Hennessy", "Non-fictional"},
            {"9780156027328", "Life of Pi", "English", "Yann Martel", "Fictional"},
            {"9780486275598", "Treasure Island", "English", "Robert Louis Stevenson", "Fictional"},
            {"9780590353427", "Harry Potter", "English", "J. K. Rowling", "Fictional"}
        };

        // Book titles for selection
        String[] bookTitles = new String[books.length];
        for (int i = 0; i < books.length; i++) {
            bookTitles[i] = books[i][1];
        }

        // Create a JList for book selection
        JList<String> bookList = new JList<>(bookTitles);
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Show JOptionPane with the JList
        int result = JOptionPane.showConfirmDialog(null, new JScrollPane(bookList), "Select a Book", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            int selectedIndex = bookList.getSelectedIndex();
            if (selectedIndex != -1) {
                // Get selected book details
                String[] selectedBook = books[selectedIndex];
                StringBuilder message = new StringBuilder("<html><body>");
                message.append("<h2>Book Details</h2>");
                message.append("<table border='1' style='border-collapse: collapse;'>");
                message.append("<tr><th>ISBN</th><td>").append(selectedBook[0]).append("</td></tr>");
                message.append("<tr><th>Title</th><td>").append(selectedBook[1]).append("</td></tr>");
                message.append("<tr><th>Language</th><td>").append(selectedBook[2]).append("</td></tr>");
                message.append("<tr><th>Author</th><td>").append(selectedBook[3]).append("</td></tr>");
                message.append("<tr><th>Genre</th><td>").append(selectedBook[4]).append("</td></tr>");
                message.append("</table>");
                message.append("</body></html>");

                // Display the book details in a JOptionPane
                JOptionPane.showMessageDialog(null, new JLabel(message.toString()), "Book Details", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
