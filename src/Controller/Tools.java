package Controller;

import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility controller: contiente dei metodi statici utili per supportare la fase di sviluppo
 * @author Valerio Marchitelli
 * @author Jacopo Cicoria
 * @author Antonio Angelini
 * @author Mattia Lenza
 *
 */
public class Tools {

    /**
     * Questo metodo, interagendo con l'apposito metodo che genera le query sul DB, fa un check sulla username e password inserite da un utente nella view di login.
     * @param user
     * @param password
     * @return Pair - Resistuisce una coppia chiave-valore corrispondente a String-boolean che indica se il check è andato a buon fine (user e password corrispondenti) e l'eventuale causa di un fallimento (username o password errati)
     */
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
     * @param lp targa veicolo
     * @return <b> boolean </b> - restituisce true se il formato è valido
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

    /**
     * Questo metodo legge un file in input e memorizza il contenuto in una stringa
     * @param filePath
     * @return <b>String</b> - restituisce il contenuto del file
     */
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

    /**
     * Questo metodo verifica che il valore di un imposta sia nel formato corretto (razionale positivo compreso tra 0 e 1)
     * @param importo importo dell'imposta
     * @return <b>boolean</> - restituisce true se l'importo è nel formato corretto
     */
    public static boolean importoFormatCheck(double importo) {
        if (importo > 1 || importo < 0)
            return false;
        return true;

    }

    /**
     * Effettua l'arrotondamento a 2 cifre di un double.
     * @param value importo da arrotondare
     * @return <b>double</b> restituisce l'importo con arrotondamento
     */
    public static double roundUp(double value){
        value=Math.round(value * 10) / 10.0;
        value=Math.round(value * 100.0) / 100.0;
        return value;
    }


}
