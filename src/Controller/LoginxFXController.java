package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginxFXController implements Initializable {

    @FXML
    Label Error;
    @FXML
    Button annulla;
    Button accedi;

    public void setText(ActionEvent event, String s){

    }

       private void switchToGestionalScene(javafx.event.ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/gestionale.fxml"));
        Stage stage = (Stage) accedi.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
    }

    private void switchToHomeScene(javafx.event.ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/home.fxml"));
        Stage stage = (Stage) annulla.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);

    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Error.setText("ciao");
        accedi.setOnAction(this::switchToGestionalScene);
        annulla.setOnAction(this::switchToHomeScene);

    }


}
