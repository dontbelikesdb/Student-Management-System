import java.sql.*;

public class dbConnect {
    private static Connection mycon = null;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String db = "studata"; // replace with your database name
        String user = "root", pass = "Deep@2004";
        String url = "jdbc:mysql://localhost:3306/studata";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
}
