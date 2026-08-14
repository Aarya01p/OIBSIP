import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ReservationForm {

    public static void showForm() {

        JFrame frame = new JFrame("Online Reservation System");

        frame.setSize(600, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Title
        JLabel title = new JLabel("TRAIN RESERVATION");
        title.setBounds(220, 30, 200, 30);
        frame.add(title);

        // Passenger Name
        JLabel passengerLabel = new JLabel("Passenger Name:");
        passengerLabel.setBounds(80, 90, 120, 30);
        frame.add(passengerLabel);

        JTextField passengerField = new JTextField();
        passengerField.setBounds(210, 90, 250, 30);
        frame.add(passengerField);

        // Train Number
        JLabel trainNumberLabel = new JLabel("Train Number:");
        trainNumberLabel.setBounds(80, 140, 120, 30);
        frame.add(trainNumberLabel);

        JTextField trainNumberField = new JTextField();
        trainNumberField.setBounds(210, 140, 250, 30);
        frame.add(trainNumberField);

        // Train Name
        JLabel trainNameLabel = new JLabel("Train Name:");
        trainNameLabel.setBounds(80, 190, 120, 30);
        frame.add(trainNameLabel);

        JTextField trainNameField = new JTextField();
        trainNameField.setBounds(210, 190, 250, 30);
        trainNameField.setEditable(false);
        frame.add(trainNameField);
        
        trainNumberField.addActionListener(e -> {

            String trainNumber = trainNumberField.getText().trim();

            switch (trainNumber) {

                case "12345":
                    trainNameField.setText("Rajdhani Express");
                    break;

                case "12001":
                    trainNameField.setText("Shatabdi Express");
                    break;

                case "12951":
                    trainNameField.setText("Mumbai Rajdhani");
                    break;

                default:
                    trainNameField.setText("Train not found");
            }
        });
        // Class Type
        JLabel classLabel = new JLabel("Class Type:");
        classLabel.setBounds(80, 240, 120, 30);
        frame.add(classLabel);

        String[] classes = {
                "Select Class",
                "Sleeper",
                "AC 3 Tier",
                "AC 2 Tier",
                "AC First Class"
        };

        JComboBox<String> classBox = new JComboBox<>(classes);
        classBox.setBounds(210, 240, 250, 30);
        frame.add(classBox);

        // Journey Date
        JLabel dateLabel = new JLabel("Journey Date:");
        dateLabel.setBounds(80, 290, 120, 30);
        frame.add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(210, 290, 250, 30);
        frame.add(dateField);

        // Source
        JLabel sourceLabel = new JLabel("Source Station:");
        sourceLabel.setBounds(80, 340, 120, 30);
        frame.add(sourceLabel);

        JTextField sourceField = new JTextField();
        sourceField.setBounds(210, 340, 250, 30);
        frame.add(sourceField);

        // Destination
        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(80, 390, 120, 30);
        frame.add(destinationLabel);

        JTextField destinationField = new JTextField();
        destinationField.setBounds(210, 390, 250, 30);
        frame.add(destinationField);

        // Book button
        JButton bookButton = new JButton("BOOK TICKET");
        bookButton.setBounds(210, 450, 150, 35);
        frame.add(bookButton);

        bookButton.addActionListener(e -> {

            String passengerName = passengerField.getText().trim();
            String trainNumber = trainNumberField.getText().trim();
            String trainName = trainNameField.getText().trim();
            String journeyDate = dateField.getText().trim();
            String source = sourceField.getText().trim();
            String destination = destinationField.getText().trim();

            String classType = (String) classBox.getSelectedItem();

            // Check empty fields
            if (passengerName.isEmpty() ||
                trainNumber.isEmpty() ||
                trainName.isEmpty() ||
                journeyDate.isEmpty() ||
                source.isEmpty() ||
                destination.isEmpty()) {

                    JOptionPane.showMessageDialog(
                        frame,
                        "Please fill in all required fields.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                    );
                     return;
                }

                // Check class selection
                if (classBox.getSelectedIndex() == 0) {

                    JOptionPane.showMessageDialog(
                        frame,
                        "Please select a class.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
            }

            // Check train number
            try {
                Integer.parseInt(trainNumber);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Train number must be numeric.",
                    "Invalid Train Number",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            int pnr = ReservationService.bookTicket(
                passengerName,
                trainNumber,
                trainName,
                classType,
                journeyDate,
                source,
                destination
            );
            if (pnr != -1) {

                JOptionPane.showMessageDialog(
                    frame,
                    "Booking Successful!\n\n" +
                    "PNR: " + pnr + "\n" +
                    "Passenger: " + passengerName + "\n" +
                    "Train: " + trainName + "\n" +
                    "Journey Date: " + journeyDate,
                    "Booking Confirmation",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {

                JOptionPane.showMessageDialog(
                    frame,
                    "Booking failed. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
        JButton cancelButton = new JButton("CANCEL BOOKING");
        cancelButton.setBounds(380, 450, 150, 35);
        frame.add(cancelButton);

        cancelButton.addActionListener(e -> {
            CancellationForm.showForm();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
