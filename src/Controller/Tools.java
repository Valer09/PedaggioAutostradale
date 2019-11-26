package Controller;

/**
 * Utility controller
 */

import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

    public static Pair<String,Boolean> checkLogIn(String user, String password){
        Pair <String,Boolean>status;
        boolean exists=checkUserExists(user);
        if (exists==false){
            status= new Pair<String, Boolean>("Utente errato",false);
            return status;
        }
        boolean pswcorrect= DBManager.checkUserPsw(user, password);
        System.out.println(pswcorrect);
        if (pswcorrect==false){
            status= new Pair<String, Boolean>("Password errata",false);
            return status;
        }
        status= new Pair<String, Boolean>("OK",true);
        return status;

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

    public static String fileReader(String filePath){
        StringBuilder content = new StringBuilder();
        Scanner reader=null;

        try {
            reader = new Scanner (new FileReader(filePath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String st;

        while(reader.hasNext()){
            st = reader.next();
            content.append(st);
        }
        reader.close();
        return content.toString();
    }

}
