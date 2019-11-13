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

    public boolean LPExists(){return true;}


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


        /*for(int i=0; i<2; i++){

            if(! ((int)lp.codePointAt(i) > 64 && (int)lp.codePointAt(i) < 123))
                return false;

        }

        for(int i=5; i<8; i++){

            if(! ((int)lp.codePointAt(i) > 47 && (int)lp.codePointAt(i) < 58) );
                return false;

        }

        for(int i=2; i<5; i++){

            if(! ((int)lp.codePointAt(i) > 64 && (int)lp.codePointAt(i) < 123) );
                return false;

        }
        */

        return false;

    }

    public static String fileReader(String filename){
        String content="";
        BufferedReader reader=null;


        try {
            reader = new BufferedReader(new FileReader(Constant.getInputRoot()+"/"+filename));

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