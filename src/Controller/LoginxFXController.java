
package Controller;
import Model.User;
import javafx.event.Event;
import javafx.event.EventHandler;
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
    private boolean logged=false;
    private boolean isfirst=true;
    private User user=null;
    int limitaccess=0;

    @FXML
    Label Error;
    @FXML
    Button annulla;
    @FXML
    Button accedi;
    @FXML
    TextField username;
    @FXML
    TextField password;


    EventHandler limit = new EventHandler() {
        @Override
        public void handle(Event event) {
            Error.setText("Numero di tentativi esaurito. Riprovare più tardi");
        }
    };

    EventHandler click = new EventHandler() {
        @Override
        public void handle(Event event) {

            String un = username.getText();
            String pw = password.getText();
            String usern = un;
            String passw = pw;
            user = new User(usern, passw);
            if (user.getstatus().getValue()) {
                logged = true;
            } else {
                logged = false;
            }
            if (isfirst) {
                isfirst = false;
                login();
            } else {
                username.setText("");
                password.setText("");
                login();

            }

        }
    };

    private void login()  {

        if (!logged && limitaccess <= 10) {
                Error.setText(user.getstatus().getKey());
                limitaccess++;
        }

        if (logged) {
            switchToGestionalScene(user);
        }

        if (limitaccess > 10) {
            Error.setText("Numero di tentativi esaurito");
            accedi.setOnAction(limit);
            user=null;

        }
    }
    private void switchToGestionalScene(User usr){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/gestionale.fxml"));
        Stage stage = (Stage) accedi.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        GestionaleFXController controller =
        loader.<GestionaleFXController>getController();
        controller.setUser(usr);
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

        limitaccess=0;
        isfirst=true;
        accedi.setOnAction(click);
        annulla.setOnAction(this::switchToHomeScene);






    }


}
