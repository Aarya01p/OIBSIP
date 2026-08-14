import javax.swing.*;

public class Dashboard {

    public static void showDashboard(String username) {

        JFrame frame = new JFrame("Online Reservation System");

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("ONLINE RESERVATION SYSTEM");
        title.setBounds(130, 40, 250, 30);
        frame.add(title);

        JLabel welcome = new JLabel("Welcome, " + username + "!");
        welcome.setBounds(190, 90, 150, 30);
        frame.add(welcome);

        JButton bookButton = new JButton("Book Ticket");
        bookButton.setBounds(150, 140, 200, 40);
        frame.add(bookButton);

        JButton cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBounds(150, 200, 200, 40);
        frame.add(cancelButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(150, 260, 200, 40);
        frame.add(exitButton);

        // Book Ticket
        bookButton.addActionListener(e -> {
            ReservationForm.showForm();
        });

        // Cancel Ticket
        cancelButton.addActionListener(e -> {
            CancellationForm.showForm();
        });

        // Exit
        exitButton.addActionListener(e -> {
            frame.dispose();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}