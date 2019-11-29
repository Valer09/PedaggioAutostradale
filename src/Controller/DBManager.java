/**
 * Classe per inizializzazione e connessione con DB
 *
 * @author Valerio Marchitelli
 */
package Controller;
import Model.Highway;
import Model.TollBoth;
import Model.Vehicle;
import org.jetbrains.annotations.Contract;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBManager {

    private Statement st = null;
    private ResultSet rs = null;
    private static String path = "eu-cdbr-west-02.cleardb.net/heroku_3838b0b01f11d0f";
    private static Connection connection = initializeConnection();

    //DB METHODS
    public static Connection initializeConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ciao");
        }

        try {
            return DriverManager.getConnection("jdbc:mysql://" + path, "b5d4014795a1c2", "ea612ec6");
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return connection;
    }

    @Contract(pure = true)
    public static Connection getConnection() {

        return connection;

    }

    //IVA
    public static double getIVA() {
        double res = 0;
        try {
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

    //VEHICLE METHODS
    public static double getAmbientalClassValue(String nomeClasse) {
        double res = 0;
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Val FROM costants WHERE Name = '" + nomeClasse + "'");
            while (rs.next()) {

                res = rs.getDouble("Val");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static double getClassValue(String classe) {

        Statement stm = null;
        double res = 0;

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
        try {
            while (rs.next()) {

                res = rs.getDouble("Val");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;


    }

    public ResultSet getVeichleInfo(String vlp) {

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM veicolo WHERE targa=" + "'" + vlp + "'");
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //HIGHWAYS METHODS

    public static ArrayList<Highway> getHighways() {
        Statement st;
        ResultSet rs;

        ArrayList<Highway> highways = new ArrayList<Highway>();
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT nome FROM autostrada");
            while (rs.next()) {
                highways.add(new Highway(rs.getString("nome")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highways;
    }

    public static String getHighwayByTollbooth(String tollbooth) {

        Statement st;
        ResultSet rs;
        String highway = "";
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT Autostrada FROM tollbooths WHERE Name='" + tollbooth + "'");
            while (rs.next()) {
                highway = rs.getString("Autostrada");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highway;


    }

    /**
     * getHighwayTU recover TU of a specific highway from DB
     *
     * @return double
     */
    public static double getHighwayTU(String highway) {

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


        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {

                return rs.getDouble("tu");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 1;

    }

    public static void setTU(String highway, double TU) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE autostrada SET tu=" + TU + " WHERE nome='" + highway + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void setHighwayName(String highway, String newName) {
        try {
            ResultSet rs;
            Statement stm = connection.createStatement();
            Statement stm2 = connection.createStatement();
            rs = stm2.executeQuery("SELECT Name,KM FROM tollbooths WHERE Autostrada='" + highway + "'");
            stm.executeUpdate("DELETE FROM tollbooths WHERE Autostrada='" + highway + "'");
            stm.executeUpdate("UPDATE autostrada SET nome='" + newName + "' WHERE nome='" + highway + "'");
            while (rs.next()) {
                stm.executeUpdate(
                        "INSERT INTO tollbooths (Autostrada, Name, KM) " +
                                "VALUES ('" + newName + "','" + rs.getString("Name") + "','" + rs.getDouble("KM") + "')");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void addHighway(String name, double TU) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("INSERT INTO autostrada (nome,tu)  VALUES (" + "'" + name + "'," + "'" + TU + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void delHighway(String highway) {
        try {
            ResultSet rs;
            Statement stm = connection.createStatement();
            Statement stm2 = connection.createStatement();
            stm2.executeUpdate("DELETE FROM tollbooths  WHERE Autostrada='" + highway + "'");
            stm.executeUpdate("DELETE FROM autostrada WHERE nome='" + highway + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public HashMap<String, Double> getHighwayTollbooths(String highway) {
        HashMap<String, Double> tb = new HashMap<String, Double>();

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT Name,KM FROM tollbooths WHERE Autostrada=" + "'" + highway + "'");

            while (rs.next()) {
                tb.put(rs.getString("Name"), rs.getDouble("KM"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tb;

    }

    //TOLLBOOTH METHODS
    public static void addTollboth(String highway, String name, double KM) {
        System.out.println("Aggiunto Casello: " + name + " KM: " + KM + " Autostrada: " + highway);
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("INSERT INTO tollbooths (Autostrada,Name,KM)  VALUES (" + "'" + highway + "'," + "'" + name + "'," + "'" + KM + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void setTollbothName(String tollbooth, String newName) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE tollbooths SET Name='" + newName + "' WHERE Name='" + tollbooth + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void setTollbothKM(String tollbooth, double KM) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE tollbooths SET KM=" + KM + " WHERE Name='" + tollbooth + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void delTollboth(String tollbooth) {
        try {
            ResultSet rs;
            Statement stm = connection.createStatement();
            stm.executeUpdate("DELETE FROM tollbooths WHERE Name='" + tollbooth + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public double getTollBothKm(String tollbooth) {
        double km = 0;

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT KM FROM tollbooths WHERE Name=" + "'" + tollbooth + "'");

            while (rs.next()) {
                km = rs.getDouble("KM");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return km;

    }

    public static ArrayList<TollBoth> getTollBoths() {
        ArrayList<TollBoth> caselli = new ArrayList<TollBoth>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tollbooths ");

            while (rs.next()) {
                caselli.add(new TollBoth(rs.getString("Name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return caselli;
    }

    public static void setTollbothHigway(String tollboth, String Higway) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE tollbooths SET Autostrada='" + Higway + "' WHERE Name='" + tollboth + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //USER METHODS
    public static void addUser(String name, String password) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("INSERT INTO user (username,password)  VALUES (" + "'" + name + "'," + "'" + password + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void setUsername(String user, String username) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE user SET username= '" + username + "' WHERE username= '" + user + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void setUserPsw(String user, String newpsw) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE user SET password='" + newpsw + "' WHERE username='" + user + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean checkUser(String user) {

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT username FROM user WHERE username='" + user + "'");
            if (!rs.next())
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;


    }

    public static String getUser(String user) {

        Statement st;
        ResultSet rs;
        String usr = "";

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT username FROM user WHERE username='" + user + "'");
            while (rs.next())
                usr = rs.getString("username");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usr;


    }

    public static String getPassword(String user) {

        Statement st;
        ResultSet rs;
        String psw = "";

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT password FROM user WHERE username='" + user + "'");
            while (rs.next())
                psw = rs.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return psw;


    }

    public static boolean checkUserPsw(String user, String password) {

        Statement st;
        ResultSet rs;
        String psw = "";

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT password FROM user WHERE username='" + user + "'");
            while (rs.next()) {
                psw = rs.getString("password");
                if (psw.equals(password))
                    return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static ArrayList<String> userList() {
        Statement st;
        ResultSet rs;

        ArrayList<String> userList = new ArrayList<String>();
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT username FROM user");
            while (rs.next()) {
                userList.add(rs.getString("username"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static void delUser(String username) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM user WHERE username = '" + username + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}