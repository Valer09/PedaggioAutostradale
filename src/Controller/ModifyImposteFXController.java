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


    public void setReason (boolean creating){
        this.creating=creating;
    }

    public void setFields (String nome, Double valore){
        nametext.setText(nome);
        valuefield.setText(valore.toString());
        oldName=nome;
        this.valore=valore;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(creating+"2");
        enter.setOnAction(this::creaOmodifica);

    }

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
}
