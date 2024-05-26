import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame implements ActionListener {
   
    

    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JButton login = new JButton("LOGIN");
    JButton reset = new JButton("RESET");

    public Login() {
        // Set the font for all components
        Font font = new Font("Tahoma", Font.PLAIN, 14);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("PasswordField.font", font);
        UIManager.put("Button.font", font);

        // Set the foreground color for all components to white
        UIManager.put("Label.foreground", Color.WHITE);
        // UIManager.put("TextField.foreground", Color.WHITE);
        UIManager.put("PasswordField.foreground", Color.WHITE);

        // Label "Library Management System"
        JLabel label = new JLabel("Library Management System");
        label.setBounds((500 - label.getPreferredSize().width) / 2-30, 30, 300, 25);
        this.add(label);

        // Label "Welcome to the Library"
        JLabel label2 = new JLabel("Welcome to the Library");   
        label2.setBounds((500 - label.getPreferredSize().width) / 2-15, 70, 200, 25);
        this.add(label2);

        // Label "Login to continue"
        JLabel label3 = new JLabel("Login to continue");
        label3.setBounds((500 - label.getPreferredSize().width) / 2-5, 110, 200, 25);
        this.add(label3);

        JLabel userLabel = new JLabel("Username:");        
        userLabel.setBounds(50, 160, 100, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 200, 100, 25);

        username.setBounds(150, 160, 200, 25);
        password.setBounds(150, 200, 200, 25);

        login.addActionListener(this);
        login.setBounds(100, 250, 100, 30); 
        login.setFocusable(false);

        reset.addActionListener(this);
        reset.setBounds(220, 250, 100, 30);
        reset.setFocusable(false);

        // login frame ->
        this.setTitle("Library Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(450, 350);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Center the frame on the screen
        // adding components to frame
        this.add(userLabel);
        this.add(passwordLabel);
        this.add(username);
        this.add(password);
        this.add(login);
        this.add(reset);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login) {
            LoginInfo login1 = new LoginInfo( username.getText(), new String(password.getPassword()));
            int id = login1.connectToDatabase();
            if( id != -1){
                System.out.println("Login successful");               
                JOptionPane.showMessageDialog(null, "Login successful");
                this.dispose();
                new Library( id );  
            } else {
                JOptionPane.showMessageDialog(null, "Login failed");
            }
        } else if(e.getSource() == reset) {
            username.setText("");
            password.setText("");
        }
    }   
}
