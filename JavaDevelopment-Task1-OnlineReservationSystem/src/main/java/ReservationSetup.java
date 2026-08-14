import java.sql.Connection;
import java.sql.Statement;

public class ReservationSetup {

    public static void createReservationsTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS reservations (
                    pnr INTEGER PRIMARY KEY AUTOINCREMENT,
                    passenger_name TEXT NOT NULL,
                    train_number INTEGER NOT NULL,
                    train_name TEXT NOT NULL,
                    class_type TEXT NOT NULL,
                    journey_date TEXT NOT NULL,
                    source TEXT NOT NULL,
                    destination TEXT NOT NULL
                );
                """;

        try (Connection connection = DBConnection.connect();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

            System.out.println("Reservations table created successfully!");

        } catch (Exception e) {
            System.out.println("Failed to create reservations table!");
            e.printStackTrace();
        }
    }
}
