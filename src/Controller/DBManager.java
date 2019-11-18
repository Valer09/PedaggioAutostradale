/**
 * Classe per inizializzazione e connessione con DB
 *
 * @author Valerio Marchitelli
 */
package Controller;
import Model.Vehicle;
import org.jetbrains.annotations.Contract;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBManager {

    private Statement st = null;
    private ResultSet rs = null;
    private static String path="eu-cdbr-west-02.cleardb.net/heroku_3838b0b01f11d0f";
    private static Connection connection = initializeConnection();


    public static double getClass(String classe){


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

        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {

                res = rs.getDouble("Val");
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    @Contract(pure = true)
    public static Connection getConnection(){

        return connection;

    }

    public ResultSet getVeichleInfo(String vlp){

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM veicolo WHERE targa=" + "'" + vlp + "'");
            return rs;
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean updateTariffClass(Vehicle veicolo, String tariff_class){
        try {
            st = connection.createStatement();
            int count = st.executeUpdate("UPDATE veicolo SET c_tariffaria = '"+tariff_class+"' WHERE targa=" + "'" + veicolo.getVlp() + "'");
            if (count > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getTollBothKm(String tollbooth){
        double km=0;

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT KM FROM tollbooths WHERE Name=" + "'" + tollbooth + "'");

            while(rs.next()){
                km = rs.getDouble("KM");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Casello: "+tollbooth+" KM: "+km);
        return km;

    }

    public HashMap <String, Double> getHighwayTollbooths(String highway){
        HashMap<String, Double> tb = new HashMap<String, Double>();

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT Name,KM FROM tollbooths WHERE Autostrada=" + "'" + highway + "'");

            while(rs.next()){
                tb.put(rs.getString("Name"),rs.getDouble("KM"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tb;

    }

    /**
     * getHighwayTU recover TU of a specific highway from DB
     *
     * @return double
     */
    public static double getHighwayTU(String highway){

        Connection con = DBManager.getConnection();

        Statement stm = null;
        try {
            stm = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stm.executeQuery("SELECT tu FROM autostrada WHERE nome=" + "'" + highway + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        while (true)
        {
            try {
                if (!rs.next()) break;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {

                return rs.getDouble("tu");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 1;

    }


}
