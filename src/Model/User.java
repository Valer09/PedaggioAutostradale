package Model;
import Controller.DB_Controller.DBManager;
import Controller.Tools;
import javafx.util.Pair;

/**
 * Modello utente: Gestisce i dati di un utente
 *
 *   @author Valerio Marchitelli
 *   @author Jacopo Cicoria
 *   @author Antonio Angelini
 *   @author Mattia Lenza
 *
 * */
public class User {
    private String username,password;
    Pair<String,Boolean> status=null;

    /**
     * La classe viene istanziata al momento della login, (se quest'ultima va a buon fine) attraverso il costruttore.
     * Il costruttore si serve della struttura <b>status</b> generata nel controller Tools per decidere se instanziare l'oggetto impostandone i parametri.
     * Nonostante la gestione della login avviene nel LoginFXController, questa scelta fornisce ulteriore sicurezza sulla login, in quanto una eventuale istanza di classe successiva ud un login fallito, determina l'impossibilità di utilizzare i metodi della classe User
     * @param user Username
     * @param passw Password
     */
    public User(String user, String passw){
        status=Tools.checkLogIn(user,passw);

        if ( (getLogStatus() )){

            username=user;
            password=passw;

        }
        else
        {
            System.out.println("Reinserire i dati corretti");
        }
    }

    /**
     * Restituisce la username dell'utente loggato (istanza di User)
     * @return una String della username utente
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Restituisce la password dell'itente loggato (istanza di User)
     * @return una String della password utente
     */
    private String getPsw(){
        if ( (getLogStatus() ))
            return this.password;
        System.out.println("Utente non loggato correttamente");
        return "";
    }

    /**
     * Restituisce <b>status</b>, una coppia di valori che indicano lo stato dell'utente istanziato,
     * ovvero se è la login è andata a buon fine, e (in caso contrario) una stringa indicante
     * il motivo del fallimento login (Username errata o password errata)
     * @return String - status
     */
    public Pair<String,Boolean> getStatus(){
        return this.status;
    }

    /**
     * Restituisce un booleano che indica lo stato della login per l'utente di istanza,
     * ovvero se ha superato la login oppure no.
     * @return boolean - status
     */
    public boolean getLogStatus(){
        return this.status.getValue();
    }

    /**
     * Il metodo, a disposizione di un utente amministratore loggato,
     * consente ad esso di modificare la username di un altro utente
     * @param user è la username dell'utente di cui si vuole effettuare la modifica username
     * @param username è la nuova username da inserire
     */
    public void setUserUsername(String user, String username) {
        if ( (getLogStatus() )) {
            if (DBManager.checkUser(user) == false)
                System.out.println("Utente non esistente");
            else {
                DBManager.setUsername(user, username);
                System.out.println("Fatto");
            }
        }
        else
            System.out.println("Utente non loggato correttamente");
    }


    /**
     * Il metodo, a disposizione di un utente amministratore loggato,
     * consente ad esso di modificare la password di un altro utente
     * @param user username utente da editare
     * @param newpsw nuova password da inserire per l'utente
     */
    public void setUserPsw(String user, String newpsw) {
        if ( (getLogStatus() )) {

            if (DBManager.checkUser(user)) {
                DBManager.setUserPsw(user, newpsw);
            } else
                System.out.println("Utente non trovato");
        }
        else
            System.out.println("Utente non loggato correttamente");

    }

    /**
     * addUser aggiunge un nuovo utente
     * @param user stringa che rappresenta il nome del nuovo utente
     * @param psw stringa che rappresenta la password del nuovo utente
     */
    public void addUser(String user, String psw){
        DBManager.addUser(user, psw);
    }

    /**
     * delUser elimina un utente
     * @param user stringa che rappresenta l'username dell'utente da eliminare
     */
    public void delUser(String user){
        DBManager.delUser(user);
    }


}
