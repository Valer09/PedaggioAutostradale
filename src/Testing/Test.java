package Testing;
import Controller.DBManager;
import Controller.VarGetter;
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
        Constant.setParams();

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
/*
       HV5 carroarmato= new HV5("BOH","BOH2","YRHFUIT456");
       Highway h1= new Highway();
       Toll t=new Toll(h1, carroarmato);
       System.out.println(t.calculateToll());*/

//TESTING CONSTANTS INITIALIZATION
        System.out.println(Constant.getVar("IVA"));
        System.out.println(Constant.getVar("SURA"));
        System.out.println(Constant.getVar("SURB"));
        System.out.println(Constant.getVar("SUR3"));
        System.out.println(Constant.getVar("SUR4"));
        System.out.println(Constant.getVar("SUR5"));
        System.out.println(Constant.getVar("EUR1"));
        System.out.println(Constant.getVar("EUR2"));
        System.out.println(Constant.getVar("EUR3"));
        System.out.println(Constant.getVar("EUR4"));
        System.out.println(Constant.getVar("EUR5"));
        System.out.println(Constant.getVar("EUR6"));

//TESTING VARGETTER CLASS AND METHODS
        System.out.println(VarGetter.getSurcharge('3')); //TEST GESURCHARGE METHOD
        System.out.println(VarGetter.getIVA()); //TEST GETIVA METHOD
        System.out.println(VarGetter.getTU("A24")); //TEST GETTU METHOD
        System.out.println(VarGetter.getTU("A1")); //TEST GETTU METHOD







    }
}
