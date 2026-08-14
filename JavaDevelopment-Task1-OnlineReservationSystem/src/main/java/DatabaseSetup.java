import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createUsersTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL
                );
                """;

        try (Connection connection = DBConnection.connect();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

            System.out.println("Users table created successfully!");

        } catch (Exception e) {
            System.out.println("Failed to create users table!");
            e.printStackTrace();
        }
    }
}