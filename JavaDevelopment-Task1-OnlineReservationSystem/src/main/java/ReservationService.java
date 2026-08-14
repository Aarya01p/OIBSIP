import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReservationService {

    public static int bookTicket(
            String passengerName,
            String trainNumber,
            String trainName,
            String classType,
            String journeyDate,
            String source,
            String destination) {

        String sql = """
                INSERT INTO reservations
                (passenger_name, train_number, train_name, class_type,
                 journey_date, source, destination)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, passengerName);
            statement.setInt(2, Integer.parseInt(trainNumber));
            statement.setString(3, trainName);
            statement.setString(4, classType);
            statement.setString(5, journeyDate);
            statement.setString(6, source);
            statement.setString(7, destination);

            statement.executeUpdate();

            // Get the automatically generated PNR
            String pnrQuery = "SELECT last_insert_rowid()";

            try (PreparedStatement pnrStatement =
                         connection.prepareStatement(pnrQuery);
                 ResultSet result = pnrStatement.executeQuery()) {

                if (result.next()) {
                    return result.getInt(1);
                }
            }

        } catch (Exception e) {
            System.out.println("Booking failed!");
            e.printStackTrace();
        }

        return -1;
    }
}