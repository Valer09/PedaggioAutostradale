package Controller;

/**
 * Utility controller
 */

import Model.User;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

    public static boolean checkLogIn(String user, String password){
        boolean exists=checkUserExists(user);
        if (exists==false){
            System.out.println("Username errato!");
            return false;
        }
        boolean pswcorrect= DBManager.checkUserPsw(user, password);
        System.out.println(pswcorrect);
        if (pswcorrect==false){
            System.out.println("Password errata");
            return false;
        }

        return true;

    }

    public static boolean login(){
        boolean logged=false;
        int limitaccess=0;
        User user=null;

        while(!logged && limitaccess <=10){
            limitaccess++;
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter username");
            String username = myObj.nextLine();  // Read user input

            myObj = new Scanner(System.in);
            System.out.println("Enter password");
            String password = myObj.nextLine();  // Read user input

            user= new User(username, password);
            if(user.getstatus())
                logged=true;

        }
        if(!user.getstatus())
            user=null;
        if (limitaccess>10){
            System.out.println("Numero di tentativi esaurito");
        }
        return logged;
    }

    public static boolean checkUserExists(String username){
        return DBManager.checkUser(username);
    }

    /**
     * DA COMPLETARE; CONTROLLA IL FORMATO TARGA.
     * @param lp
     * @return
     */
    public static boolean LPCheck(@NotNull String lp){
        boolean format=true;

        if (! ( lp.length()==7 ) )
            return false;
        Matcher m = Pattern.compile("[a-zA-Z0-9. ]*").matcher(lp);
        if (m.find())
            return true;

        return false;
    }

    public static String fileReader(String filename){
        String content="";
        BufferedReader reader=null;

        try {
            reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/IO"+"/"+filename));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            content = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

}
