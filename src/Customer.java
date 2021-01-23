import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Customer extends Person{

    private int customerID;
    private String address;
    private String occupation;
    private int phone1;
    private int phone2;

    //Default Constructor
    public Customer() {
    }


    //Load customer from database
    public Customer(String cedula){
        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.prepareStatement("select * from customers where Cedula = ?");
            myStmt.setString(1, cedula);
            myRs = myStmt.executeQuery();
            while (myRs.next()){
                super.setCedula(cedula);
                super.setFirstName(myRs.getString("First_Name"));
                super.setLastName(myRs.getString("Last_Name"));
                super.setDateOfBirth(myRs.getDate("Date_of_Birth"));
                customerID=Integer.parseInt(myRs.getString("Customer_ID"));
                address=myRs.getString("Address");
                occupation=myRs.getString("Occupation");
                phone1=myRs.getInt("Phone1");
                phone2=myRs.getInt("Phone2");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
    }
    public Customer(int customerID){
        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.prepareStatement("select * from customers where Customer_ID = ?");
            myStmt.setInt(1, customerID);
            myRs = myStmt.executeQuery();
            while (myRs.next()){
                super.setCedula(myRs.getString("Cedula"));
                super.setFirstName(myRs.getString("First_Name"));
                super.setLastName(myRs.getString("Last_Name"));
                super.setDateOfBirth(myRs.getDate("Date_of_Birth"));
                this.customerID=customerID;
                address=myRs.getString("Address");
                occupation=myRs.getString("Occupation");
                phone1=myRs.getInt("Phone1");
                phone2=myRs.getInt("Phone2");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
    }


    //Prepare to add to database
    public Customer(String fName, String lName, String currCedula, Date currDOB, String currAddress,
                    String currOccupation, int currPhone1, int currPhone2){
        super.setFirstName(fName);
        super.setLastName(lName);
        super.setCedula(currCedula);
        super.setDateOfBirth(currDOB);
        address=currAddress;
        occupation=currOccupation;
        phone1=currPhone1;
        phone2=currPhone2;
    }


    public boolean checkIfCustomerExists(String cedula){
        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.prepareStatement("select * from customers where Cedula = ?");
            myStmt.setString(1,cedula);
            myRs = myStmt.executeQuery();
            if (!myRs.next()){
                DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
                return false;
            }
            else {
                DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
        return false;
    }

    //This method is dif from CheckIfCustomerExists because it does not take into account its own cedula
    public boolean checkIfCedulaExists(String cedula, int currCustID){
        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.prepareStatement("select * from customers where Cedula = ?");
            myStmt.setString(1,cedula);
            myRs = myStmt.executeQuery();
            if (!myRs.next()){
                DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
                return false;
            }
            else {
                int custID=Integer.parseInt(myRs.getString("Customer_ID"));
                if (custID!=currCustID) {
                    DataBaseHandler.disconnectFromDataBase(myRs, myStmt, myConn);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
        return false;
    }

    public void addCustomerToDataBase(){
        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.prepareStatement("insert into customers (First_Name, Last_Name, Cedula, Date_of_Birth, Address, Occupation, Phone1, Phone2)"
                    + "values (?, ?, ?, ?, ?, ?, ?, ?)");
            myStmt.setString(1,super.getFirstName());
            myStmt.setString(2,super.getLastName());
            myStmt.setString(3,super.getCedula());
            myStmt.setDate(4,super.getDateOfBirth());
            myStmt.setString(5,address);
            myStmt.setString(6,occupation);
            myStmt.setInt(7,phone1);
            myStmt.setInt(8,phone2);
            myStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
    }

    public void updateDatabase(){
        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.prepareStatement("UPDATE customers SET " +
                    "First_Name = ?, Last_Name = ?, Cedula = ?, Date_of_Birth = ?, " +
                    "Address = ?, Occupation = ?, Phone1 = ?, Phone2 = ? " +
                    "WHERE Customer_ID = ?");

            myStmt.setString(1,super.getFirstName());
            myStmt.setString(2,super.getLastName());
            myStmt.setString(3,super.getCedula());
            myStmt.setDate(4,super.getDateOfBirth());
            myStmt.setString(5,this.getAddress());
            myStmt.setString(6,this.getOccupation());
            myStmt.setInt(7,this.getPhone1());
            myStmt.setInt(8,this.getPhone2());

            myStmt.setInt(9,this.getCustomerID());

            myStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);

    }




    //Getters and Setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getPhone1() {
        return phone1;
    }

    public void setPhone1(int phone1) {
        this.phone1 = phone1;
    }

    public int getPhone2() {
        return phone2;
    }

    public void setPhone2(int phone2) {
        this.phone2 = phone2;
    }


}
