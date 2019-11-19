package Model;

import Controller.DBManager;

public class User {
    private String user,password;
    private boolean type;

    public User(String user, String password, boolean type){
        DBManager.addUser(user,password,type);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }






}
