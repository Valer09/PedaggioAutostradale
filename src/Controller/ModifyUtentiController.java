package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyUtentiController implements Initializable {
@FXML
Button modifica;
@FXML
    TextField text;

    String  user;

    /**
     *Metodo che viene inizializzato all'apertura della view
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifica.setOnAction(this::modUt);
    }

    /**
     * Seleziona un utente dalla lista utenti
     * @param utente Parametro di tipo Stringa che rappresenta il nome dell'utente
     */
    public void setUser(String utente){
        this.user = utente;
        text.setText(this.user);
    }

    /**
     * Modifica un utente nel database
     * @param actionEvent Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void modUt(javafx.event.ActionEvent actionEvent) {
        String username = "";
        username = text.getText();
        DBManager.setUsername(user, username);
        System.out.println(username);
        Stage stage = (Stage) modifica.getScene().getWindow();
        stage.close();
    }

}