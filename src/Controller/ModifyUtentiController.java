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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifica.setOnAction(this::modUt);
    }
    public void setUser(String utente){
        this.user = utente;
        text.setText(this.user);
    }

    private void modUt(javafx.event.ActionEvent actionEvent) {
        String username = "";
        username = text.getText();
        DBManager.setUsername(user, username);
        System.out.println(username);
        Stage stage = (Stage) modifica.getScene().getWindow();
        stage.close();
    }

    /*private void modUt( ActionEvent e) {
        String username = "";
        String user = (String) listUser.getSelectionModel().getSelectedItem();
        username = text.getText();
        DBManager.setUsername(username, user);
        System.out.println(username);
        Stage stage = (Stage) modifica.getScene().getWindow();
        stage.close();

    }*/
}