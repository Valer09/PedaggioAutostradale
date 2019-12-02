package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddUtentiModalController  implements Initializable {
    @FXML
    Button btn;
    @FXML
    TextField text, text1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn.setOnAction(this::addUtente);
    }

    /**
     * Aggiunge un utente al database
     * @param e
     */
    public void addUtente(ActionEvent e){
        String user = "";
        String password = "";
        user = text.getText();
        password = text1.getText();
        DBManager.addUser(user, password);
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
