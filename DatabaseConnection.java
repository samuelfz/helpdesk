import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/seuprojeto";
    private static final String USER = "heron";
    private static final String PASSWORD = "codelocal";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do PostgreSQL não encontrado", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
