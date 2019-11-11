package Controller;
import org.jetbrains.annotations.Contract;

import java.sql.*;

/**
 *  VarGetter recover variables from DB
 */
public class VarGetter {

    /**
     * getSurcharge recover surgcharge from DB
     * @param vclass
     * @return double
     */
    @Contract(pure = true)
    public static double getSurcharge(char vclass){
        String var="SUR"+vclass;
        return Constant.getVar(var);

    }

    /**
     * getIVA recover IVA tax from DB
     *
     * @return double
     */
    @Contract(pure = true)
    public static double getIVA(){

        return Constant.getVar("IVA");

    }


    /**
     * getTU recover TU of a specific highway from DB
     *
     * @return double
     */
    public static double getTU(String highway){

        Connection con = DBManager.getConnection();

        Statement stm = null;
        try {
            stm = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stm.executeQuery("SELECT TU FROM tus WHERE Autostrada=" + "'" + highway + "'");
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

                return rs.getDouble("TU");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 1;

    }
}
