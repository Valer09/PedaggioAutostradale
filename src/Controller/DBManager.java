/**
 * Classe per inizializzazione e connessione con DB
 *
 * @author Valerio Marchitelli
 */
package Controller;
import Model.Highway;
import Model.TollBoth;
import javafx.util.Pair;
import org.jetbrains.annotations.Contract;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DBManager {

    private Statement st = null;
    private ResultSet rs = null;
    private static String path = "eu-cdbr-west-02.cleardb.net/heroku_3838b0b01f11d0f";
    private static Connection connection = initializeConnection();

    //DB METHODS

    /**
     * initializeConnection inizializza la connessione con il DB
     * @return
     */
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

    /**
     * getIVA ritorna il valore dell'iva
     * @return
     */
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

    /**
     * getAmbientalClassValue ritorna il valore della costante associata al nome della classe passata come paramentro
     * @param nomeClasse
     * @return
     */
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

    /**
     * getClassValue ritorna il valore della costante associata al nome della classe passata come parametro
     * @param classe
     * @return
     */
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

    /**
     * getVeichleInfo ritorna le informazioni associate ad un veicolo
     * @param vlp
     * @return
     */
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

    //HIGHWAYS METHODS

    /**
     * getHighways recupera i nomi di tutte le autostrade
     * @return
     */
    public static ArrayList <Highway> getHighways(){
        Statement st;
        ResultSet rs;

        ArrayList<Highway> highways = new ArrayList<Highway>();
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM autostrada");
            while (rs.next()) {
                highways.add(new Highway(rs.getString("nome"), rs.getDouble("tu")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highways;
    }

    public static Highway getHighway(String nome){
        Statement st;
        ResultSet rs;
        Highway autostrada = new Highway(" ", 0);
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM autostrada WHERE nome = '"+nome+"'");
            if (rs.next()) {
                autostrada = new Highway(rs.getString("nome"), rs.getDouble("tu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autostrada;
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

    /**
     * setTU imposta il valore della tariffa unitaria di un'autostrada nel DB
     *
     * @param highway
     * @param TU
     */
    public static void setTU(String highway, double TU) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE autostrada SET tu=" + TU + " WHERE nome='" + highway + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * setHighwayName aggiorna il nome di un'autostrada e aggiorna i rispettivi caselli
     * @param highway
     * @param newName
     */
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

    /**
     * addHighway aggiunge una nuova autostrada
     * @param name
     * @param TU
     */
    public static void addHighway(String name, double TU) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("INSERT INTO autostrada (nome,tu)  VALUES (" + "'" + name + "'," + "'" + TU + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * elimina un'autostrada e i riferimenti ai caselli associati
     * @param highway
     */
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

    /**
     * getHighwayTollbooths recupera il nome e il chilometro dei caselli associati ad un'autostrada
     * @param highway
     * @return
     */
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

    /**
     * addTollboth inserisce un nuovo casello
     * @param highway
     * @param name
     * @param KM
     */
    public static void addTollboth(String highway, String name, double KM) {
        System.out.println("Aggiunto Casello: " + name + " KM: " + KM + " Autostrada: " + highway);
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("INSERT INTO tollbooths (Autostrada,Name,KM)  VALUES (" + "'" + highway + "'," + "'" + name + "'," + "'" + KM + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * setTollbothName aggiorna il nome di un casello
     * @param tollbooth
     * @param newName
     */
    public static void setTollbothName(String tollbooth, String newName) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE tollbooths SET Name='"+newName+"' WHERE Name='"+tollbooth+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * setTollbothKM aggiorna il chilometro di un casello
     * @param tollbooth
     * @param KM
     */
    public static void setTollbothKM(String tollbooth, double KM){
        try{
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE tollbooths SET KM="+KM+" WHERE Name='"+tollbooth+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delTollboth cancella un casello
     * @param tollbooth
     */
    public static void delTollboth(String tollbooth){
        try{
            ResultSet rs;
            Statement stm = connection.createStatement();
            stm.executeUpdate("DELETE FROM tollbooths WHERE Name='"+tollbooth+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * getTollBothKM recupera il chilometro associato ad un casello
     * @param tollbooth
     * @return
     */
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
        return km;
    }

    /**
     * getTollBoths recupera tutti i caselli
     * @return
     */
    public static ArrayList<TollBoth> getTollBoths(){
        ArrayList<TollBoth> caselli = new ArrayList<TollBoth>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tollbooths ");

            while(rs.next()){
                caselli.add(new TollBoth(rs.getString("Name"), rs.getDouble("KM"), rs.getString("Autostrada")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return caselli;
    }

    /**
     * getTollBoth recupera un casello
     * @param name
     * @return
     */
    public static TollBoth getTollBoth(String name){
        TollBoth casello = new TollBoth("", 0, "");
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tollbooths WHERE Name = '"+name+"'");

            if(rs.next()){
                casello = new TollBoth(rs.getString("Name"), rs.getDouble("KM"), rs.getString("Autostrada"));
                return casello;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return casello;
    }

    /**
     * setTollbothHighway aggiorna l'autostrada associata ad un casello
     * @param tollboth
     * @param Higway
     */
    public static void setTollbothHigway(String tollboth, String Higway){
        try{
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE tollbooths SET Autostrada='" + Higway + "' WHERE Name='" + tollboth + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //USER METHODS

    /**
     * addUser aggiunge un nuovo utente
     * @param name
     * @param password
     */
    public static void addUser(String name, String password) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("INSERT INTO user (username,password)  VALUES (" + "'" + name + "'," + "'" + password + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * setUsername aggiorna l'username associato ad un utente
     * @param user
     * @param username
     */
    public static void setUsername(String user, String username) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE user SET username= '" + username + "' WHERE username= '" + user + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * setUserPsw aggiorna la password associata all'utente
     * @param user
     * @param newpsw
     */
    public static void setUserPsw(String user, String newpsw) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE user SET password='" + newpsw + "' WHERE username='" + user + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Pair <Pair<String,String>,  Boolean  > checkUserAndPassword(String user, String password) {
        Pair <  Pair<String,String>,  Boolean  > status=null;
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT username,password FROM user WHERE username='" + user + "'");
            if (!rs.next())
                status= new Pair<>(new Pair<>("", ""), false);
            else{
                if (!rs.getString("password").equals(password) ){
                    status= new Pair<>(new Pair<>(user, ""), false);
                }
                else{
                    status= new Pair<>(new Pair<>(user, password), true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * checkUser controlla che un utente è nel DB
     * @param user
     * @return
     */
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
        return false;

    }

    /**
     * checkUserPsw controlla che la password passata al metodo sia uguale a quella salvata nel DB
     * @param user
     * @param password
     * @return
     */
    public static boolean checkUserPsw(String user, String password){

        Statement st;
        ResultSet rs;
        String psw="";

        try {
            st = connection.createStatement();
            rs=st.executeQuery("SELECT password FROM user WHERE username='" + user + "'");
            while(rs.next()) {
                psw = rs.getString("password");
                if (psw.equals(password))
                    return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * getUser recupera l'username di un utente
     * @param user
     * @return
     */
    public static String getUser(String user) {

        Statement st;
        ResultSet rs;
        String usr="";

        try {
            st = connection.createStatement();
            rs=st.executeQuery("SELECT username FROM user WHERE username='" + user + "'");
            while(rs.next())
                usr=rs.getString("username");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usr;

    }

    /**
     * getPassword recupera la password di un utente
     * @param user
     * @return
     */
    public static String getPassword(String user){

        Statement st;
        ResultSet rs;
        String psw="";

        try {
            st = connection.createStatement();
            rs=st.executeQuery("SELECT password FROM user WHERE username='" + user + "'");
            while(rs.next())
                psw=rs.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return psw;

    }

    /**
     * userList recupera la lista degli username degli utenti
     * @return
     */
    public static ArrayList <String> userList(){
        Statement st;
        ResultSet rs;

        ArrayList <String> userList= new ArrayList <String> () ;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT username FROM user");
            while (rs.next()) {
                userList.add(rs.getString("username"));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    //CLASS METHODS

    /**
     * getClasses recupera nome e valore delle costanti ordinate secondo il nome
     * @return
     */
    public static HashMap <String, Double> getClasses(){
        HashMap<String, Double> cl = new HashMap<String, Double>();
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT Name,Val FROM costants ORDER BY Name");
            while(rs.next()){
                cl.put(rs.getString("Name"),rs.getDouble("Val"));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cl;

    }

    /**
     * delUser elimina un utente
     * @param username
     */
    public static void delUser(String username) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM user WHERE username = '" + username + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void delImposta(String nomeImposta){
        try{
        Statement stm = connection.createStatement();
        stm.executeUpdate("DELETE FROM costants WHERE Name='"+nomeImposta+"'");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void addImposta(String nomeImposta, double valoreImposta){
        try{
            Statement stm = connection.createStatement();
            stm.executeUpdate("INSERT INTO costants (Name, Val) VALUES (" + "'" + nomeImposta + "'," + "'" + valoreImposta + "')");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void setImposta(String nomeImposta, String newNomeImposta, double valoreImposta){
        try {
            Statement stm = connection.createStatement();
            System.out.println(nomeImposta+ " "+ newNomeImposta+" "+ valoreImposta);
            stm.executeUpdate("UPDATE costants SET Name='"+newNomeImposta+"', Val='"+valoreImposta+"' WHERE Name='"+nomeImposta+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
