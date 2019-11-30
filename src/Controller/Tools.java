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

    public static Pair <String,Boolean> checkLogIn(String user, String password){
        Pair <  Pair<String,String>,  Boolean  > status;
        status = DBManager.checkUserAndPassword(user,password);
        Pair <String, Boolean > result=null;

        if (status.getKey().getKey().equals("")){
            result= new Pair < String, Boolean>("Utente Errato", false);
            return result;
        }

        if (status.getKey().getValue().equals("")){
            result= new Pair<String, Boolean>("Password errata",false);
            return result;
        }
        result= new Pair<String, Boolean>("OK",true);
        return result;

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

    public static boolean importoFormatCheck(double importo) {
        if (importo > 1 || importo < 0)
            return false;
        return true;

    }
    public static double roundUp(double value){
        value=Math.round(value * 10) / 10.0;
        value=Math.round(value * 100.0) / 100.0;
        return value;
    }


}
