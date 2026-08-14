import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        DBConnection.connect();
        DatabaseSetup.createUsersTable();
        UserSetup.insertDefaultUser();
        ReservationSetup.createReservationsTable();

        JFrame frame = new JFrame("Online Reservation System");

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Title
        JLabel title = new JLabel("ONLINE RESERVATION SYSTEM");
        title.setBounds(130, 40, 250, 30);
        frame.add(title);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(80, 110, 100, 30);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(180, 110, 200, 30);
        frame.add(usernameField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(80, 160, 100, 30);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(180, 160, 200, 30);
        frame.add(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(180, 220, 100, 35);
        frame.add(loginButton);

        loginButton.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (LoginService.validateLogin(username, password)) {
                JOptionPane.showMessageDialog(frame,
                    "Login Successful!");

                frame.dispose();
                Dashboard.showDashboard(username);
            }else {
                JOptionPane.showMessageDialog(frame,
                    "Invalid username or password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            }
    
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
       
    }
}