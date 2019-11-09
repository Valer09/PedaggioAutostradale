package Controller;
import java.sql.*;

/**
 *  TaxGetter recover tax and surgcharge form DB
 */
public class SurGetter {


    public double getSurcharge(char c) {

        String DBCLASS = "SUR"+c;
        double res = 0;
        Connection con = DBManager.getConnection();
        Statement stm = null;
        try {
            stm = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stm.executeQuery("SELECT Val FROM costants WHERE Name="+"'"+DBCLASS+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }


            while (true){
                try {
                    if (!rs.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    res=rs.getDouble("Val");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }


        return res;


}
}
