import javax.swing.*;

public class CancellationForm {

    public static void showForm() {

        JFrame frame = new JFrame("Cancel Reservation");

        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("CANCEL RESERVATION");
        title.setBounds(210, 30, 200, 30);
        frame.add(title);

        JLabel pnrLabel = new JLabel("Enter PNR:");
        pnrLabel.setBounds(100, 100, 100, 30);
        frame.add(pnrLabel);

        JTextField pnrField = new JTextField();
        pnrField.setBounds(200, 100, 200, 30);
        frame.add(pnrField);

        JButton fetchButton = new JButton("Fetch");
        fetchButton.setBounds(410, 100, 90, 30);
        frame.add(fetchButton);

        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBounds(100, 160, 400, 180);
        frame.add(scrollPane);

        JButton cancelButton = new JButton("Cancel Booking");
        cancelButton.setBounds(210, 370, 160, 35);
        cancelButton.setEnabled(false);
        frame.add(cancelButton);

        final int[] currentPNR = {-1};

        fetchButton.addActionListener(e -> {

            String pnrText = pnrField.getText().trim();

            try {

                int pnr = Integer.parseInt(pnrText);

                String details =
                        CancellationService.getBookingDetails(pnr);

                if (details != null) {

                    detailsArea.setText(details);

                    currentPNR[0] = pnr;

                    cancelButton.setEnabled(true);

                } else {

                    detailsArea.setText("");

                    cancelButton.setEnabled(false);

                    JOptionPane.showMessageDialog(
                            frame,
                            "No booking found for this PNR.",
                            "Not Found",
                            JOptionPane.WARNING_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "PNR must be numeric.",
                        "Invalid PNR",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        cancelButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to cancel this booking?",
                    "Confirm Cancellation",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                boolean cancelled =
                        CancellationService.cancelBooking(currentPNR[0]);

                if (cancelled) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Booking cancelled successfully!",
                            "Cancellation",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    detailsArea.setText("");
                    pnrField.setText("");
                    cancelButton.setEnabled(false);
                    currentPNR[0] = -1;

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Cancellation failed.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
