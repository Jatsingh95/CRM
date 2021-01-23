import java.sql.*;
import java.awt.*;

public class DataBaseHandler {

    public DataBaseHandler() {
    }

    public static Connection connectToDataBase(){

        String url = "jdbc:mysql://localhost:3306/visionplus";
        String user = "root";
        String password = "Singh123";
        Connection myConn = null;

        try {
            myConn = DriverManager.getConnection(url, user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myConn;
    }

    public static void disconnectFromDataBase(ResultSet myRs, PreparedStatement myStmt, Connection myConn){
        try { myRs.close(); } catch (Exception e) { /* ignored */ }
        try { myStmt.close(); } catch (Exception e) { /* ignored */ }
        try { myConn.close(); } catch (Exception e) { /* ignored */ }
    }
}
