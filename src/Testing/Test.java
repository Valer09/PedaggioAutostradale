package Testing;
import Controller.DBManager;
import Model.*;

import java.sql.*;

/**
 *
 * @author Vale09
 * @author Jacic
 * @author Mattu
 * @author Bob
 *
 */
public class Test {
    public static void main(String []args){
        /*LV camioncino = new LV("Ford","Fiesta","CR984RT", 100, 1.10,800);
        HV5 camion = new HV5("IVECO","GH77","KR94RFY",8000,1.80,8000);

        System.out.println("CAMIONCINO: \n" + camioncino.prinfInfo());
        System.out.println("CAMION: \n" + camion.prinfInfo());
        System.out.println( camioncino.getBrand());*/

        //DBManager.initializeConnection();
        //DBManager.initializeConnection();
       /* Connection con = DBManager.getConnection();
        Statement stm = null;
        try {
            stm = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stm.executeQuery("SELECT Name,Val FROM costants");
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
                System.out.println(rs.getDouble("Val"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/

       HV5 carroarmato= new HV5("BOH","BOH2","YRHFUIT456");
       Highway h1= new Highway();
       Toll t=new Toll(h1, carroarmato);
       System.out.println(t.calculateToll());






    }
}
