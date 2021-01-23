import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LensType {

    public LensType(){
    }


    public ArrayList<String> getAllLensTypes(){
        ArrayList<String> lensTypes = new ArrayList<>();

        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.prepareStatement("select * from lenstypes");
            myRs = myStmt.executeQuery();
            while(myRs.next()) {
                lensTypes.add(myRs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
        return lensTypes;
    }

}
