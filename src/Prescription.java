import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.awt.event.InputEvent;
import java.sql.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Prescription {

    private int prescriptionID;
    private int customerID;

    private double rodEsf;
    private double rodCil;
    private double rodEje;
    private double rodAd;

    private double roiEsf;
    private double roiCil;
    private double roiEje;
    private double roiAd;

    private double pd1;
    private double pd2;
    private double avod;
    private double avoi;

    private double rlcodEsf;
    private double rlcodCil;
    private double rlcodEje;
    private double rlcodAd;

    private double rlcoiEsf;
    private double rlcoiCil;
    private double rlcoiEje;
    private double rlcoiAd;

    private double rpodEsf;
    private double rpodCil;
    private double rpodEje;
    private double rpodAd;

    private double rpoiEsf;
    private double rpoiCil;
    private double rpoiEje;
    private double rpoiAd;

    private String lensType;
    private String notas;
    private Date date;


    //Creating new Prescription
    public Prescription(){
    }

    //Load Existing Prescription from database
    public Prescription(int prescriptionID){  //Retrieving from database

        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.prepareStatement("select * from prescriptions where Prescription_ID = ?");
            myStmt.setInt(1, prescriptionID);
            myRs = myStmt.executeQuery();
            while (myRs.next()){
                this.setPrescriptionID(prescriptionID);
                this.setCustomerID(myRs.getInt("Customer_ID"));

                this.setRodEsf(myRs.getDouble("R_OD_Esf"));
                this.setRodCil(myRs.getDouble("R_OD_Cil"));
                this.setRodEje(myRs.getDouble("R_OD_Eje"));
                this.setRodAd(myRs.getDouble("R_OD_Ad"));

                this.setRoiEsf(myRs.getDouble("R_OI_Esf"));
                this.setRoiCil(myRs.getDouble("R_OI_Cil"));
                this.setRoiEje(myRs.getDouble("R_OI_Eje"));
                this.setRoiAd(myRs.getDouble("R_OI_Ad"));

                this.setPd1(myRs.getDouble("Pd_1"));
                this.setPd2(myRs.getDouble("Pd_2"));
                this.setAvod(myRs.getDouble("AV_OD"));
                this.setAvoi(myRs.getDouble("AV_OI"));

                this.setRlcodEsf(myRs.getDouble("LC_OD_Esf"));
                this.setRlcodCil(myRs.getDouble("LC_OD_Cil"));
                this.setRlcodEje(myRs.getDouble("LC_OD_Eje"));
                this.setRlcodAd(myRs.getDouble("LC_OD_Ad"));

                this.setRlcoiEsf(myRs.getDouble("LC_OI_Esf"));
                this.setRlcoiCil(myRs.getDouble("LC_OI_Cil"));
                this.setRlcoiEje(myRs.getDouble("LC_OI_Eje"));
                this.setRlcoiAd(myRs.getDouble("LC_OI_Ad"));

                this.setRpodEsf(myRs.getDouble("Prev_OD_Esf"));
                this.setRpodCil(myRs.getDouble("Prev_OD_Cil"));
                this.setRpodEje(myRs.getDouble("Prev_OD_Eje"));
                this.setRpodAd(myRs.getDouble("Prev_OD_Ad"));

                this.setRpoiEsf(myRs.getDouble("Prev_OI_Esf"));
                this.setRpoiCil(myRs.getDouble("Prev_OI_Cil"));
                this.setRpoiEje(myRs.getDouble("Prev_OI_Eje"));
                this.setRpoiAd(myRs.getDouble("Prev_OI_Ad"));

                this.setLensType(myRs.getString("Lens_Type"));
                this.setNotas(myRs.getString("Notas"));
                this.setDate(myRs.getDate("Date"));

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);

    }


    //Add to Database
    public void addToDatabase(){
        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.prepareStatement("insert into prescriptions (Customer_ID, " +
                    "R_OD_Esf, R_OD_Cil, R_OD_Eje, R_OD_Ad, " +
                    "R_OI_Esf, R_OI_Cil, R_OI_Eje, R_OI_Ad, " +
                    "Pd_1, Pd_2, AV_OD, AV_OI, " +
                    "LC_OD_Esf, LC_OD_Cil, LC_OD_Eje, LC_OD_Ad, " +
                    "LC_OI_Esf, LC_OI_Cil, LC_OI_Eje, LC_OI_Ad, " +
                    "Prev_OD_Esf, Prev_OD_Cil, Prev_OD_Eje, Prev_OD_Ad, " +
                    "Prev_OI_Esf, Prev_OI_Cil, Prev_OI_Eje, Prev_OI_Ad, " +
                    "Lens_Type, Notas, Date)"
                    + "values (?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?)");

            myStmt.setInt(1,this.getCustomerID());

            myStmt.setDouble(2,this.getRodEsf());
            myStmt.setDouble(3,this.getRodCil());
            myStmt.setDouble(4,this.getRodEje());
            myStmt.setDouble(5,this.getRodAd());

            myStmt.setDouble(6,this.getRoiEsf());
            myStmt.setDouble(7,this.getRoiCil());
            myStmt.setDouble(8,this.getRoiEje());
            myStmt.setDouble(9,this.getRoiAd());

            myStmt.setDouble(10,this.getPd1());
            myStmt.setDouble(11,this.getPd2());
            myStmt.setDouble(12,this.getAvod());
            myStmt.setDouble(13,this.getAvoi());

            myStmt.setDouble(14,this.getRlcodEsf());
            myStmt.setDouble(15,this.getRlcodCil());
            myStmt.setDouble(16,this.getRlcodEje());
            myStmt.setDouble(17,this.getRlcodAd());

            myStmt.setDouble(18,this.getRlcoiEsf());
            myStmt.setDouble(19,this.getRlcoiCil());
            myStmt.setDouble(20,this.getRlcoiEje());
            myStmt.setDouble(21,this.getRlcoiAd());

            myStmt.setDouble(22,this.getRpodEsf());
            myStmt.setDouble(23,this.getRpodCil());
            myStmt.setDouble(24,this.getRpodEje());
            myStmt.setDouble(25,this.getRpodAd());

            myStmt.setDouble(26,this.getRpoiEsf());
            myStmt.setDouble(27,this.getRpoiCil());
            myStmt.setDouble(28,this.getRpoiEje());
            myStmt.setDouble(29,this.getRpoiAd());

            myStmt.setString(30,this.getLensType());
            myStmt.setString(31,this.getNotas());
            myStmt.setDate(32,this.getDate());



            myStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
    }


    //Update Values in Database
    public void updateDatabase(){
        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.prepareStatement("UPDATE prescriptions SET " +
                    "R_OD_Esf = ?, R_OD_Cil = ?, R_OD_Eje = ?, R_OD_Ad = ?, " +
                    "R_OI_Esf = ?, R_OI_Cil = ?, R_OI_Eje = ?, R_OI_Ad = ?, " +
                    "Pd_1 = ?, Pd_2 = ?, AV_OD = ?, AV_OI = ?, " +
                    "LC_OD_Esf = ?, LC_OD_Cil = ?, LC_OD_Eje = ?, LC_OD_Ad = ?, " +
                    "LC_OI_Esf = ?, LC_OI_Cil = ?, LC_OI_Eje = ?, LC_OI_Ad = ?, " +
                    "Prev_OD_Esf = ?, Prev_OD_Cil = ?, Prev_OD_Eje = ?, Prev_OD_Ad = ?, " +
                    "Prev_OI_Esf = ?, Prev_OI_Cil = ?, Prev_OI_Eje = ?, Prev_OI_Ad = ?, " +
                    "Lens_Type = ?, Notas = ?, Date = ? WHERE Prescription_ID = ?");


            myStmt.setDouble(1,this.getRodEsf());
            myStmt.setDouble(2,this.getRodCil());
            myStmt.setDouble(3,this.getRodEje());
            myStmt.setDouble(4,this.getRodAd());

            myStmt.setDouble(5,this.getRoiEsf());
            myStmt.setDouble(6,this.getRoiCil());
            myStmt.setDouble(7,this.getRoiEje());
            myStmt.setDouble(8,this.getRoiAd());

            myStmt.setDouble(9,this.getPd1());
            myStmt.setDouble(10,this.getPd2());
            myStmt.setDouble(11,this.getAvod());
            myStmt.setDouble(12,this.getAvoi());

            myStmt.setDouble(13,this.getRlcodEsf());
            myStmt.setDouble(14,this.getRlcodCil());
            myStmt.setDouble(15,this.getRlcodEje());
            myStmt.setDouble(16,this.getRlcodAd());

            myStmt.setDouble(17,this.getRlcoiEsf());
            myStmt.setDouble(18,this.getRlcoiCil());
            myStmt.setDouble(19,this.getRlcoiEje());
            myStmt.setDouble(20,this.getRlcoiAd());

            myStmt.setDouble(21,this.getRpodEsf());
            myStmt.setDouble(22,this.getRpodCil());
            myStmt.setDouble(23,this.getRpodEje());
            myStmt.setDouble(24,this.getRpodAd());

            myStmt.setDouble(25,this.getRpoiEsf());
            myStmt.setDouble(26,this.getRpoiCil());
            myStmt.setDouble(27,this.getRpoiEje());
            myStmt.setDouble(28,this.getRpoiAd());

            myStmt.setString(29,this.getLensType());
            myStmt.setString(30,this.getNotas());
            myStmt.setDate(31,this.getDate());

            myStmt.setInt(32,this.getPrescriptionID());



            myStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);
    }

    //Return ObservableList of all prescription ids to load on stage6
    public ObservableList<Prescription> getAllPrescriptions(int customerID){
        ObservableList<Prescription> allPrescriptions = FXCollections.observableArrayList();

        Connection myConn = DataBaseHandler.connectToDataBase();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.prepareStatement("select * from prescriptions where Customer_ID = ?");
            myStmt.setInt(1,customerID);
            myRs = myStmt.executeQuery();
            while(myRs.next()) {
                Prescription currPres = new Prescription(myRs.getInt(1));
                allPrescriptions.add(currPres);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataBaseHandler.disconnectFromDataBase(myRs,myStmt,myConn);


        return allPrescriptions;
    }





    public int getPrescriptionID() {
        return prescriptionID;
    }
    public void setPrescriptionID(int prescriptionID) {
        this.prescriptionID = prescriptionID;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public double getRodEsf() {
        return rodEsf;
    }
    public void setRodEsf(double rodEsf) {
        this.rodEsf = rodEsf;
    }
    public double getRodCil() {
        return rodCil;
    }
    public void setRodCil(double rodCil) {
        this.rodCil = rodCil;
    }
    public double getRodEje() {
        return rodEje;
    }
    public void setRodEje(double rodEje) {
        this.rodEje = rodEje;
    }
    public double getRodAd() {
        return rodAd;
    }
    public void setRodAd(double rodAd) {
        this.rodAd = rodAd;
    }
    public double getRoiEsf() {
        return roiEsf;
    }
    public void setRoiEsf(double roiEsf) {
        this.roiEsf = roiEsf;
    }
    public double getRoiCil() {
        return roiCil;
    }
    public void setRoiCil(double roiCil) {
        this.roiCil = roiCil;
    }
    public double getRoiEje() {
        return roiEje;
    }
    public void setRoiEje(double roiEje) {
        this.roiEje = roiEje;
    }
    public double getRoiAd() {
        return roiAd;
    }
    public void setRoiAd(double roiAd) {
        this.roiAd = roiAd;
    }
    public double getPd1() {
        return pd1;
    }
    public void setPd1(double pd1) {
        this.pd1 = pd1;
    }
    public double getPd2() {
        return pd2;
    }
    public void setPd2(double pd2) {
        this.pd2 = pd2;
    }
    public double getAvod() {
        return avod;
    }
    public void setAvod(double avod) {
        this.avod = avod;
    }
    public double getAvoi() {
        return avoi;
    }
    public void setAvoi(double avoi) {
        this.avoi = avoi;
    }
    public double getRlcodEsf() {
        return rlcodEsf;
    }
    public void setRlcodEsf(double rlcodEsf) {
        this.rlcodEsf = rlcodEsf;
    }
    public double getRlcodCil() {
        return rlcodCil;
    }
    public void setRlcodCil(double rlcodCil) {
        this.rlcodCil = rlcodCil;
    }
    public double getRlcodEje() {
        return rlcodEje;
    }
    public void setRlcodEje(double rlcodEje) {
        this.rlcodEje = rlcodEje;
    }
    public double getRlcodAd() {
        return rlcodAd;
    }
    public void setRlcodAd(double rlcodAd) {
        this.rlcodAd = rlcodAd;
    }
    public double getRlcoiEsf() {
        return rlcoiEsf;
    }
    public void setRlcoiEsf(double rlcoiEsf) {
        this.rlcoiEsf = rlcoiEsf;
    }
    public double getRlcoiCil() {
        return rlcoiCil;
    }
    public void setRlcoiCil(double rlcoiCil) {
        this.rlcoiCil = rlcoiCil;
    }
    public double getRlcoiEje() {
        return rlcoiEje;
    }
    public void setRlcoiEje(double rlcoiEje) {
        this.rlcoiEje = rlcoiEje;
    }
    public double getRlcoiAd() {
        return rlcoiAd;
    }
    public void setRlcoiAd(double rlcoiAd) {
        this.rlcoiAd = rlcoiAd;
    }
    public double getRpodEsf() {
        return rpodEsf;
    }
    public void setRpodEsf(double rpodEsf) {
        this.rpodEsf = rpodEsf;
    }
    public double getRpodCil() {
        return rpodCil;
    }
    public void setRpodCil(double rpodCil) {
        this.rpodCil = rpodCil;
    }
    public double getRpodEje() {
        return rpodEje;
    }
    public void setRpodEje(double rpodEje) {
        this.rpodEje = rpodEje;
    }
    public double getRpodAd() {
        return rpodAd;
    }
    public void setRpodAd(double rpodAd) {
        this.rpodAd = rpodAd;
    }
    public double getRpoiEsf() {
        return rpoiEsf;
    }
    public void setRpoiEsf(double rpoiEsf) {
        this.rpoiEsf = rpoiEsf;
    }
    public double getRpoiCil() {
        return rpoiCil;
    }
    public void setRpoiCil(double rpoiCil) {
        this.rpoiCil = rpoiCil;
    }
    public double getRpoiEje() {
        return rpoiEje;
    }
    public void setRpoiEje(double rpoiEje) {
        this.rpoiEje = rpoiEje;
    }
    public double getRpoiAd() {
        return rpoiAd;
    }
    public void setRpoiAd(double rpoiAd) {
        this.rpoiAd = rpoiAd;
    }
    public String getNotas() {
        return notas;
    }
    public void setNotas(String notas) {
        this.notas = notas;
    }
    public String getLensType() {
        return lensType;
    }
    public void setLensType(String lensType) {
        this.lensType = lensType;
    }
    public Date getDate(){return date;}
    public void setDate(Date date) {this.date=date;}
}
