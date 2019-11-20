package Model;

import Controller.DBManager;
import Controller.Tools;

public class User {
    private String username,password;
    private boolean accessOK=false;

    public User(String user, String password){
        int limitaccess=0;
        while (! (Tools.checkLogIn(user,password) ) && limitaccess <=10){
            limitaccess++;
            System.out.println("Reinserire i dati corretti");

        }
        if (limitaccess<=10){
            accessOK=true;
            username=DBManager.getUser(user);
            password=DBManager.getPassword(user);
        }
        else System.out.println("ACCESSO FALLITO");


    }
    public String getUsername() {
        return this.username;
    }
    private String getPsw(){
        if (accessOK)
            return this.password;
        System.out.println("Utente non loggato correttamente");
        return "";
    }

    public void setUsername(String user, String username) {
        if (accessOK) {
            if (Tools.checkUserExists(username) == false)
                System.out.println("Utente non esistente");
            else {
                DBManager.setUsername(user, username);
                this.username = username;
                System.out.println("Fatto");
            }
        }
    }
    private void editMyPsw(String oldpsw, String newpsw){
        if (accessOK){
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
    private void editUserPsw(String user, String newpsw) {
        if (accessOK) {

            if (Tools.checkUserExists(user)) {
                DBManager.setUserPsw(user, newpsw);
            } else
                System.out.println("Utente non trovato");
        }
        else
            System.out.println("Utente non loggato correttamente");

    }
    private void createUser(String user, String password){
        if (accessOK) {
            DBManager.addUser(user, password);
            System.out.println("Utente creato");
        }
        else
            System.out.println("Utente non loggato correttamente");

    }








}
