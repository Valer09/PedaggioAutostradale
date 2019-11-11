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
    private static String path="localhost:3306/highwaydb";
    private static Connection connection = initializeConnection();


    public static Connection initializeConnection() {

        try {
              Class.forName("org.mariadb.jdbc.Driver");
            }
        catch (ClassNotFoundException e)
        {
            System.out.println("ciao");
        }

        try {
            return DriverManager.getConnection("jdbc:mariadb://"+path, "root","");
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
