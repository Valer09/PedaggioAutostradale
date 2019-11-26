package Model;

import Controller.DBManager;
import Controller.Tools;
import javafx.util.Pair;

public class User {
    private String username,password;
    Pair<String,Boolean> status=null;

    public User(String user, String passw){
        status=Tools.checkLogIn(user,passw);

        if ( (getLogStatus() )){

            username=DBManager.getUser(user);
            password=DBManager.getPassword(user);
        }
        else
        {
            System.out.println("Reinserire i dati corretti");
        }
    }
    public String getUsername() {
        return this.username;
    }
    private String getPsw(){
        if ( (getLogStatus() ))
            return this.password;
        System.out.println("Utente non loggato correttamente");
        return "";
    }
    public void setOtherUsername(String user, String username) {
        if ( (getLogStatus() )) {
            if (Tools.checkUserExists(username) == false)
                System.out.println("Utente non esistente");
            else {
                DBManager.setUsername(user, username);
                System.out.println("Fatto");
            }
        }
        else
            System.out.println("Utente non loggato correttamente");
    }
    public void setMyUsername(String username) {
        if ( (getLogStatus() )) {
                DBManager.setUsername(this.username, username);
                this.username=username;
                System.out.println("Fatto");
        }
        else
            System.out.println("Utente non loggato correttamente");
    }
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
    public void editUserPsw(String user, String newpsw) {
        if ( (getLogStatus() )) {

            if (Tools.checkUserExists(user)) {
                DBManager.setUserPsw(user, newpsw);
            } else
                System.out.println("Utente non trovato");
        }
        else
            System.out.println("Utente non loggato correttamente");

    }

    public Pair<String,Boolean> getstatus(){
        return this.status;
    }
    public boolean getLogStatus(){
        return this.status.getValue();
    }








}
