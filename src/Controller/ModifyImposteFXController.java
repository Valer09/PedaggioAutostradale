package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller Modifica Imposte: controlla la view per la modifica delle imposte (modifyImposte)
 *
 *   @author Valerio Marchitelli
 *   @author Jacopo Cicoria
 *   @author Antonio Angelini
 *   @author Mattia Lenza
 *
 * */
public class ModifyImposteFXController implements Initializable {

    public boolean creating;
    @FXML
    TextField nametext;
    @FXML
    TextField valuefield;
    @FXML
    Button enter;
    @FXML
    Label Errore,label1;

    private String oldName;
    private Double valore;


    /**
     * Funzione resa accessibile al controller GestionaleFXController permette ad esso di settare il valore <b>creating</b>
     * @param creating - <b>boolean</b>  viene  impostato in base all'azione di modifica oppure di creazione di un imposta dall'utente
     */
    public void setReason (boolean creating){
        this.creating=creating;
    }

    /**
     * Permette al controller GestionaleFXController di settare il nome e il valore dell'imposta che sta per essere creata o modificata dall'utente
     * @param nome <b>String</b> - nome imposta
     * @param valore <b>Double</b> - valore imposta
     */
    public void setFields (String nome, Double valore){
        nametext.setText(nome);
        valuefield.setText(valore.toString());
        oldName=nome;
        this.valore=valore;
    }


    /**
     * Crea o modifica un imposta secondo l'azione scelta dall'utente. Eventualmente mostra un messaggio di errore nella label Errore.
     * @param actionEvent evento di click sul pulsante enter
     */
    private void creaOmodifica(ActionEvent actionEvent) {
        if (creating){
            valore=Double.parseDouble(valuefield.getText());
            label1.setText("Creazione Nuova Imposta");
            if (Tools.importoFormatCheck(valore)){
                DBManager.addImposta(nametext.getText(), valore);
                Stage stage = (Stage) enter.getScene().getWindow();
                stage.close();
            }
            else
                {
                Errore.setText("Importo non valido. \nL'importo deve indicare una percentuale \ne quindi essere compreso tra 0 e 1");
                }

        }
        else
            {
            valore=Double.parseDouble(valuefield.getText());
            label1.setText("Modifica Imposta");
            if (Tools.importoFormatCheck(valore)){
                DBManager.setImposta(oldName,nametext.getText(), valore);
                Stage stage = (Stage) enter.getScene().getWindow();
                stage.close();
            }
            else{
                Errore.setText("Importo non valido. /nL'importo deve indicare una percentuale e quindi essere compreso tra 0 e 1");
            }
        }
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param url  The location used to resolve relative paths for the root object, or
     *                  <b>null</b> if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or <b>null</b> if
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(creating+"2");
        enter.setOnAction(this::creaOmodifica);

    }
}
