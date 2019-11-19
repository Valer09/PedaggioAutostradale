/**
 * Classe per inizializzazione e connessione con DB
 *
 * @author Valerio Marchitelli
 */
package Controller;
import org.jetbrains.annotations.Contract;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBManager {

    private Statement st = null;
    private ResultSet rs = null;
    private static String path="eu-cdbr-west-02.cleardb.net/heroku_3838b0b01f11d0f";
    private static Connection connection = initializeConnection();


    public static double getClassValue(String classe){

        Statement stm = null;
        double res=0;

        stm = null;
        try {
            stm = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;

        try {
            rs = stm.executeQuery("SELECT Val FROM costants WHERE Name=" + "'" + classe + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            while (rs.next()) {

                res = rs.getDouble("Val");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;



    }

    public static double getIVA(){
        double res = 0;
        try{
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Val FROM costants WHERE Name = 'IVA'");
            while (rs.next()) {

                res = rs.getDouble("Val");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Connection initializeConnection() {

        try {
              Class.forName("com.mysql.jdbc.Driver");
            }
        catch (ClassNotFoundException e)
        {
            System.out.println("ciao");
        }

        try {
            return DriverManager.getConnection("jdbc:mysql://"+path, "b5d4014795a1c2","ea612ec6");
        }
         catch (SQLException e) {
            e.printStackTrace();

        }

        return connection;
    }

    public static double getAmbientalClassValue(String nomeClasse){
        double res = 0;
        try{
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Val FROM costants WHERE Name = '"+nomeClasse+"'");
            while (rs.next()) {

                res = rs.getDouble("Val");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Contract(pure = true)
    public static Connection getConnection(){

        return connection;

    }

    public double getKM(String tollbooths){
        double km=0;

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT KM FROM tollbooths WHERE Name=" + "'" + tollbooths + "'");

            while(true){
                if (! (rs.next())) break;
                km = rs.getDouble("KM");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return km;

    }

    public HashMap <String, Double> getHighwayTollbooths(String highway){
        HashMap<String, Double> tb = new HashMap<String, Double>();

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT Name,KM FROM tollbooths WHERE Autostrada=" + "'" + highway + "'");

            while(true){
                if (! (rs.next())) break;
                tb.put(rs.getString("Name"),rs.getDouble("KM"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tb;

    }



}
