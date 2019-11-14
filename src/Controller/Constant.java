package Controller;

import Controller.DBManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

/**
 * This class defines all useful constants getting her values from DB.
 */
public class Constant {

    private static String inputPath=System.getProperty("user.dir")+"/IO";

    @Contract(pure = true)
    public static String getInputRoot(){

        return inputPath;
    }


    /**
     * Return constant
     * @param var
     * @return double
     */
    @Contract(pure = true)
    public static double getVar(@NotNull String var) {

        Connection con = DBManager.getConnection();
        Statement stm = null;
        double res=0;

        stm = null;
        try {
            stm = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;

        try {
            rs = stm.executeQuery("SELECT Val FROM costants WHERE Name=" + "'" + var + "'");

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


}
