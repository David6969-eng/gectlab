import java.sql.*;

public class DBConnection {

    public static Connection getConnection() {

        Connection conn = null;

        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection URL (with fixes)
            String url = "jdbc:mysql://localhost:3306/library_db?useSSL=false&allowPublicKeyRetrieval=true";

            String user = "root";
            String password = "";   // 🔥 CHANGE if your system has password

            conn = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to database!");

        } catch(Exception e) {
            System.out.println("Connection FAILED!");
            e.printStackTrace();
        }

        return conn;
    }
}
