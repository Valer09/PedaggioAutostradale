package Controller.View_Controller;

import Controller.DB_Controller.DBManager;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUtentiModalController  implements Initializable {
    @FXML
    Button btn;
    @FXML
    TextField text, text1;
    User admin;

    /**
     *Metodo che viene inizializzato all'apertura della view
     * @param url url
     * @param resourceBundle local specific object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn.setOnAction(this::addUtente);
    }

    /**
     * Aggiunge un utente al database
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    public void addUtente(ActionEvent e){
        String user = "";
        String password = "";
        user = text.getText();
        password = text1.getText();
        this.admin.addUser(user,password);
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void setUser(User admin){
        this.admin = admin;
    }

}
