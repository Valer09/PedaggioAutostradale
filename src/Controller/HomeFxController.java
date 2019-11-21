package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeFxController implements Initializable {

    @FXML
    Button btn;

    private void switchScene(javafx.event.ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/sample.fxml"));
        Stage stage = (Stage) btn.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn.setOnAction(this::switchScene);

    }


}
