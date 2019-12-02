package Model;
import Controller.DBManager;
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
     * La classe viene istanziata al momento della login, se quest'ultima va a buon fine attraverso il costruttore.
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
     * Il metodo, a disposizione di un utente amministratore loggato,
     * consente ad esso di modificare la username di un altro utente
     * @param user è la username dell'utente di cui si vuole effettuare la modifica username
     * @param username è la nuova username da inserire
     */
    public void setOtherUsername(String user, String username) {
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
     * Setta una nuova username per l'utente loggato (istanza di User)
     * @param username - nuova username da settare per l'utente loggato
     */
    public void setMyUsername(String username) {
        if ( (getLogStatus() )) {
                DBManager.setUsername(this.username, username);
                this.username=username;
                System.out.println("Fatto");
        }
        else
            System.out.println("Utente non loggato correttamente");
    }

    /**
     * Setta una nuova password per l'utente loggato (istanza di User)
     * @param oldpsw - vecchia password per check sicurezza
     * @param newpsw - nuova password da settare per l'utente loggato
     */
    public void editMyPsw(String oldpsw, String newpsw){
        if ( (getLogStatus() )){
            int limitaccess=0;
            while (! (DBManager.checkUserPsw(this.username, oldpsw) ) && limitaccess <=10){
                limitaccess++;
                System.out.println("Password vecchia errata");
            }

            if (limitaccess<=10){
                DBManager.setUserPsw(this.username,newpsw);
                this.password=newpsw;
                System.out.println("OK");
            }
            else
                System.out.println("Numero di tentativi esaurito. Ritentare");

        }
        else System.out.println("Utente non loggato correttamente");


    }

    /**
     * Il metodo, a disposizione di un utente amministratore loggato,
     * consente ad esso di modificare la password di un altro utente
     * @param user username utente da editare
     * @param newpsw nuova password da inserire per l'utente
     */
    public void editUserPsw(String user, String newpsw) {
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
     * Restituisce <b>status</b>, una coppia di valori che indicano lo stato dell'utente istanziato,
     * ovvero se è la login è andata a buon fine, e (in caso contrario) una stringa indicante
     * il motivo del fallimento login (Username errata o password errata)
     * @return String - status
     */
    public Pair<String,Boolean> getstatus(){
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








}
