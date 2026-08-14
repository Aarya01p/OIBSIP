import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CancellationService {

    public static String getBookingDetails(int pnr) {

        String sql = "SELECT * FROM reservations WHERE pnr = ?";

        try (Connection connection = DBConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pnr);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                return "PNR: " + result.getInt("pnr") + "\n"
                        + "Passenger: " + result.getString("passenger_name") + "\n"
                        + "Train Number: " + result.getInt("train_number") + "\n"
                        + "Train Name: " + result.getString("train_name") + "\n"
                        + "Class: " + result.getString("class_type") + "\n"
                        + "Journey Date: " + result.getString("journey_date") + "\n"
                        + "Source: " + result.getString("source") + "\n"
                        + "Destination: " + result.getString("destination");
            }

        } catch (Exception e) {
            System.out.println("Failed to fetch booking!");
            e.printStackTrace();
        }

        return null;
    }

    public static boolean cancelBooking(int pnr) {

        String sql = "DELETE FROM reservations WHERE pnr = ?";

        try (Connection connection = DBConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pnr);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {
            System.out.println("Cancellation failed!");
            e.printStackTrace();
            return false;
        }
    }
}