package Controller;

import Controller.DBManager;
import java.sql.*;

/**
 * This class defines all useful constants getting her values from DB.
 */
public class Constant {
    public static double IVA,SURA,SURB,SUR3,SUR4,SUR5,EUR1,EUR2,EUR3,EUR4,EUR5,EUR6;

    /**
     * Return constant
     * @param var
     * @return double
     */
    public static double getVar(String var) {

        switch (var) {
            case "IVA":
                return IVA;
            case "SURA":
                return SURA;
            case "SURB":
                return SURB;
            case "SUR3":
                return SUR3;
            case "SUR4":
                return SUR4;
            case "SUR5":
                return SUR5;
            case "EUR1":
                return EUR1;
            case "EUR2":
                return EUR2;
            case "EUR3":
                return EUR3;
            case "EUR4":
                return EUR4;
            case "EUR5":
                return EUR5;
            case "EUR6":
                return EUR6;


        }
        return 1;
    }

    /**
     * Set params, sets constants getting values from DB;
     */
    public static void setParams() {

        Connection con = DBManager.getConnection();
        char c='a';

        /** SETTING IVA PARAMS **/

        Statement stm = null;
        try {
            stm = con.createStatement();
        }    catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stm.executeQuery("SELECT Val FROM costants WHERE Name='IVA'");
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

                IVA=rs.getDouble("Val");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**SETTING SUR PARAMS**/

        for (int i=1; i<6; i++){
            switch (i){
                case 1:c='A';
                    break;
                case 2:c='B';
                    break;
                case 3:c='3';
                    break;
                case 4:c='4';
                    break;
                case 5:c='5';
                    break;
            }

            String DBCLASS = "SUR" + c;
            stm = null;
            try {
                stm = con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
            try {
                rs = stm.executeQuery("SELECT Val FROM costants WHERE Name=" + "'" + DBCLASS + "'");
                System.out.println(DBCLASS);
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
                        switch (i){
                            case 1:SURA=rs.getDouble("Val");

                            case 2:SURB=rs.getDouble("Val");

                            case 3:SUR3=rs.getDouble("Val");

                            case 4:SUR4=rs.getDouble("Val");

                            case 5:SUR5=rs.getDouble("Val");

                        }
                        }
                    catch (SQLException e) {
                        e.printStackTrace();
                        }
                }
        }

        /** SETTING EUR PARAMS **/
        for (int i=1; i<7; i++){
            switch (i){
                case 1:c='1';
                    break;
                case 2:c='2';
                    break;
                case 3:c='3';
                    break;
                case 4:c='4';
                    break;
                case 5:c='5';
                    break;
                case 6:c='6';
                    break;


            }

            String EUR = "EUR" + c;
            stm = null;
            try {
                stm = con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
            try {
                rs = stm.executeQuery("SELECT Val FROM costants WHERE Name=" + "'" + EUR + "'");
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
                    switch (i){
                        case 1:EUR1=rs.getDouble("Val");
                            break;
                        case 2:EUR2=rs.getDouble("Val");
                            break;
                        case 3:EUR3=rs.getDouble("Val");
                            break;
                        case 4:EUR4=rs.getDouble("Val");
                            break;
                        case 5:EUR5=rs.getDouble("Val");
                            break;
                        case 6:EUR6=rs.getDouble("Val");
                            break;

                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
