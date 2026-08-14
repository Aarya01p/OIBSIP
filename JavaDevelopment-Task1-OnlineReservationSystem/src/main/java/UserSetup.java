import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserSetup {

    public static void insertDefaultUser() {

        String sql = "INSERT OR IGNORE INTO users (username, password) VALUES (?, ?)";

        try (Connection connection = DBConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "admin");
            statement.setString(2, "1234");

            statement.executeUpdate();

            System.out.println("Default user added successfully!");

        } catch (Exception e) {
            System.out.println("Failed to add default user!");
            e.printStackTrace();
        }
    }
}