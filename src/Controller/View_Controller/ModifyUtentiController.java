package Controller.View_Controller;

import Controller.DB_Controller.DBManager;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyUtentiController implements Initializable {
@FXML
Button modifica;
@FXML
    TextField text;

    String  user;
    User admin;

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
    public void setUser(String utente, User admin){
        this.user = utente;
        this.admin = admin;
        text.setText(this.user);
    }

    /**
     * Modifica un utente nel database
     * @param actionEvent Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void modUt(ActionEvent actionEvent) {
        String username = "";
        username = text.getText();
        this.admin.setUserUsername(this.user, username);
        System.out.println(username);
        Stage stage = (Stage) modifica.getScene().getWindow();
        stage.close();
    }

}