package Controller;

/**
 * Utility controller
 */

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

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
