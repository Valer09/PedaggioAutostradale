/**
 * Classe per inizializzazione e connessione con DB
 *
 * @author Valerio Marchitelli
 */
package Controller;
import java.sql.*;

public class DBManager {
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

    public static Connection getConnection(){

        return connection;

    }

}
