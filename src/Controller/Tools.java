package Controller;

import Controller.DB_Controller.DBManager;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Exception.WrongLPException;

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
     * <b>status</b> - è una coppia "Pair-Boolean", dove il valore booleano è vero soltanto se la username esiste e la password è corretta; la chiave Pair "String-String" contiene le stringhe username-password le quali saranno vuote se non corrispondono nel database. Questo consente di generare dei messaggi di errore dinamici. L'utilizzo di questa struttura dati, consente di effettuare una sola query dal DBManager, ma ciò nonostante, restituire tutti i dati necessari alla gestione della logica di login; questo si rende necessarrio dal momento in cui il linguaggio Java non consente la restituzione di più tipi di dato.
     * La struttura <b>status</b> viene riempita in maniera pertinente nel DBManager dove si effettua il Controllo sul DB dell'esattezza dei dati. Il metodo <b>checkLogin</b> quindi si serve del DBManager per costruire tale struttura dati e gestirne la logica.
     * <b>checkLogin</b> viene principalmente utilizzato dal modello User durante un tentativo di instanzione dello stesso nella fase di login, e quindi nel controller che gestisce la view login.fxml
     * @param user - username da testare
     * @param password - password da testare
     * @return Pair - Resistuisce una coppia chiave-valore corrispondente a String-boolean che indica se il check è andato a buon fine (user e password corrispondenti) e l'eventuale causa di un fallimento (username o password errati)
     */
    public static Pair <String,Boolean> checkLogIn(String user, String password){
        //è una coppia <Pair-Boolean>. Boolean<=>username e password validi in DB; Pair <username,password>
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
     * CONTROLLA IL FORMATO TARGA.
     * @param lp targa veicolo
     * @throws WrongLPException - Eccezione per formata targa errato
     * @return <b> boolean </b> - restituisce true se il formato è valido
     */
    public static boolean LPCheck(@NotNull String lp) throws WrongLPException {
        boolean format=true;
        if (! ( lp.length()==7 ) ) {
            throw new WrongLPException();
        }
        Matcher m = Pattern.compile("[a-zA-Z0-9. ]*").matcher(lp);
        if (m.find()) return true;
        throw new WrongLPException();
    }

    /**
     * Questo metodo legge un file in input e memorizza il contenuto in una stringa
     * @param filePath percorso del file
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
     * @return <b>boolean</b> - restituisce true se l'importo è nel formato corretto
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
