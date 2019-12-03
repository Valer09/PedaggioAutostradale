package Controller.View_Controller;

import Controller.DB_Controller.DBManager;
import Model.Highway;
import Model.TollBoth;
import Model.User;
import Model.ImposteGeneriche;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GestionaleFXController implements Initializable {

    User user;
    @FXML
    ListView caselliList, autostradeList, listUser;
    @FXML
    Button backButton, addAutostrada, deleteAutostrada, modifyAutostrada, addCasello, modifyCasello, deleteCasello, addUt, deleteUt, modifyUt, eraseImpostaButton, newImpostaButton, editImpostaButton;
    @FXML
    TableColumn <ImposteGeneriche, String>key;
    @FXML
    TableColumn <ImposteGeneriche, Double>value;
    @FXML
    TableView <ImposteGeneriche> classesTable;
    ObservableList <ImposteGeneriche> imposteGeneriche;
    ObservableList <String> autostradeLista, caselliLista, utentiLista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnAction(this::goBack);

        //Caselli
        addCasello.setOnAction(this::aggiungiCasello);
        deleteCasello.setOnAction(this::rimuoviCasello);
        modifyCasello.setOnAction(this::modificaCasello);
        ArrayList<TollBoth> caselli = DBManager.getTollBoths();
        caselliLista = FXCollections.observableArrayList();
        caselli.forEach(casello -> {
            caselliLista.add(casello.getName());
        });
        caselliList.setItems(caselliLista);

        //Classi e imposte
        eraseImpostaButton.setOnAction(this::eliminaImposta);
        editImpostaButton.setOnAction(this::modificaImposta);
        newImpostaButton.setOnAction(this::creaImposta);
        refreshImposte();

        //Autostrade
        addAutostrada.setOnAction(this::aggiungiAutostrada);
        deleteAutostrada.setOnAction(this::rimuoviAutostrada);
        modifyAutostrada.setOnAction(this::modificaAutostrada);
        autostradeLista = FXCollections.observableArrayList();
        ArrayList <Highway> highways = DBManager.getHighways();
        highways.forEach(autostrada -> {
            autostradeLista.add(autostrada.getName());
        });
        autostradeList.setItems(autostradeLista);

        //Utenti
        addUt.setOnAction(this::aggiungiUtente);
        modifyUt.setOnAction(this::modificaUtente);
        deleteUt.setOnAction(this::rimuoviUtente);
        createUserList();
    }

    //Imposte
    /**
     * Elimina Imposta: elimina il record di Imposta selezionato
     * @param actionEvent Evento click del pulstante <b>eraseImpostaButton</b>
     */
    private void eliminaImposta(ActionEvent actionEvent) {
        String imposta =  classesTable.getSelectionModel().getSelectedItem().getNome();
        DBManager.delImposta(imposta);
        refreshImposte();
    }

    /**
     * Modifica di una imposta: apre un modale (view modifyImposte.fxml) che consente la modifica del nome e valore dell'imposta
     * @param actionEvent Evento click del pulstante <b>editImpostaButton</b>
     */
    private void modificaImposta(ActionEvent actionEvent) {
        String nomeImposta;
        Double valoreImposta ;
        ImposteGeneriche selected = classesTable.getSelectionModel().getSelectedItem();
        nomeImposta=selected.getNome();
        valoreImposta=selected.getValore();
        System.out.println(nomeImposta+ " "+ valoreImposta );

        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/modifyImposte.fxml"));
        try {
            root = loader.load();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ModifyImposteFXController controller = loader.<ModifyImposteFXController>getController();
        controller.setReason(false);
        controller.setFields(nomeImposta, valoreImposta);

        stage.setScene(new Scene(root));
        stage.setTitle("Crea Nuovo Imposta");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) actionEvent.getSource()).getScene().getWindow() );

        stage.show();
        //Evento chiusura del modale
        stage.setOnHiding((WindowEvent event1) -> {
            this.refreshImposte();
        });


    }

    /**
     * Crea una nuova imposta: apre un modale (view modifyImposte.fxml) che consente la modifica del nome e valore dell'imposta
     * @param actionEvent Evento click del pulstante <b>newImpostaButton</b>
     */
    private void creaImposta(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/modifyImposte.fxml"));
        try {
            root = loader.load();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ModifyImposteFXController controller = loader.<ModifyImposteFXController>getController();
        controller.setReason(true);
        System.out.println(controller.creating+"1");
        stage.setScene(new Scene(root));
        stage.setTitle("Crea Nuovo Imposta");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) actionEvent.getSource()).getScene().getWindow() );

        stage.show();
        //Evento chiusura del modale
        stage.setOnHiding((WindowEvent event1) -> {
            this.refreshImposte();
        });

    }

    /**
     * Il metodo crea riempie la TableView <b>classesTable</b>; vengono utilizzate due strutture dati di apposggio: una HashMap ed una ObslervableList di tipo Imposte (Modello)
     */
    private void refreshImposte(){
        imposteGeneriche = FXCollections.observableArrayList();

        //chiave-valore => Nome-valore imposte
        HashMap<String, Double> classes = DBManager.getClasses();
        classes.forEach((K,V) -> {
            imposteGeneriche.add(new ImposteGeneriche(K,V));
        });
        key.setCellValueFactory(cellData ->  cellData.getValue().getNomeImpostaProperty());
        value.setCellValueFactory(cellData ->  cellData.getValue().getvaloreImpostaProperty().asObject());
        classesTable.setItems(imposteGeneriche);
        classesTable.getSortOrder().add(key);
    }

    //Utenti

    /**
     * Questo metodo crea una nuova finestra modale per l'aggiunta di un nuovo Utente. Al termine della creazione chiama il metodo createUserList() per aggiornare la lista degli utenti
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void aggiungiUtente(ActionEvent e){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    AddUtentiModalController.class.getResource("../../View/modaleUtenti.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Aggiungi Utente");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        stage.show();
        stage.setOnHiding((WindowEvent event1) -> {
            System.out.println("Chiuso");
            this.createUserList();
        });
    }

    /**
     * Questo metodo prende la lista degli Utenti dal DB e dopo aver creato una lista l'assegna alla ListView associata agli utenti
     */
    public void createUserList(){
        System.out.println("Refresh");
        ArrayList <String> utenti = DBManager.userList();
        utentiLista = FXCollections.observableArrayList();
        utenti.forEach(utente ->{
            utentiLista.add(utente);
        });
        listUser.setItems(utentiLista);
    }

    /**
     * Questo metodo si occupa della rimozione dell'utente selezionato dal DB
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    public void rimuoviUtente(ActionEvent e){
        String utente = (String) listUser.getSelectionModel().getSelectedItem();
        DBManager.delUser(utente);
        System.out.println("Utente eliminato: "+utente);
        this.createUserList();

    }

    /**
     * Queato metodo crea una nuova finestra modale per la modifica dell'utente selezionato. Al termine chiam il metodo createUserList() per aggiornare la lista degli Utenti
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void modificaUtente(ActionEvent e){
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/modifyUtenti.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Modifica Utente");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        ModifyUtentiController controller = loader.getController();
        String utente = (String) listUser.getSelectionModel().getSelectedItem();
        controller.setUser(utente);
        stage.show();
        stage.setOnHiding((WindowEvent event1) -> {
            System.out.println("Chiuso");
            this.createUserList();
        });
    }

    //Autostrade

    /**
     * Queato metodo crea una nuova finestra modale per l'aggiunta di una nuova autostrada. Al termine chiama il metodo refreshAutostrade() per aggiornare la lista delle autostrade
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void aggiungiAutostrada(ActionEvent e){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    AddAutostradeModalController.class.getResource("../../View/modaleAutostrade.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Aggiungi Autostrada");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        stage.show();
        stage.setOnHiding((WindowEvent event1) -> {
            System.out.println("Chiuso");
            this.refreshAutostrade();
        });
    }

    /**
     * Queato metodo prende le autostrade dal DB e dopo aver creato una lista l'assegna alla ListView associata alle Autostrade
     */
    private void refreshAutostrade(){
        ArrayList<Highway> highways = DBManager.getHighways();
        autostradeLista = FXCollections.observableArrayList();
        highways.forEach(autostrada -> {
            autostradeLista.add(autostrada.getName());
        });
        autostradeList.setItems(autostradeLista);
    }

    /**
     * Questo metodo si occupa della rimozione dell'autostrada selezionata. Al termine chiama il metodo refreshAutostrade() per aggiornare la lista delle autostrade
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void rimuoviAutostrada(ActionEvent e){
        String autostrada = (String) autostradeList.getSelectionModel().getSelectedItem();
        DBManager.delHighway(autostrada);
        this.refreshAutostrade();
    }

    /**
     * Questo metodo crea una nuova finestra modale per la modifica dell'autostrada selezionata. Al termine chiama il metodo refreshAutostrade() per aggiornare la lista delle autostrade
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void modificaAutostrada(ActionEvent e){
        String autostrada = (String) autostradeList.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/modifyAutostrada.fxml"));
        try {
            if (loader == null) System.out.println("Vuoto");
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Modifica Autostrada");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        ModifyAutostradaController controller = loader.getController();
        controller.setHWname(autostrada);
        stage.show();
        stage.setOnHiding((WindowEvent event1) -> {
            System.out.println("Chiuso");
            this.refreshAutostrade();
        });
    }

    //Caselli

    /**
     * Questo metodo si occupa di creare una nuova finestra modale per l'aggiunta di un nuovo casello.
     * Quando viene chiusta la finestra modale, questo metodo si occupa di intercettare l'evento e ricaricare la lista dei caselli
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void aggiungiCasello(ActionEvent e){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    getClass().getResource("../../View/addCasello.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Aggiungi Casello");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        stage.show();
        //Evento chiusura del modale
        stage.setOnHiding((WindowEvent event1) -> {
            this.refreshCaselli();
        });
    }

    /**
     * Questo metodo pernde tutti i caselli nel DB e dopo averli inseriti in una lista li mostra nella ListView associata ai caselli
     */
    private void refreshCaselli(){
        ArrayList<TollBoth> caselli = DBManager.getTollBoths();
        caselliLista = FXCollections.observableArrayList();
        caselli.forEach(casello -> {
            caselliLista.add(casello.getName());
        });
        caselliList.setItems(caselliLista);
    }

    /**
     * Questo metodo si occupa della cancellazione di un casello dal DB. Dopo aver effettuato l'operazione di rimozione chiama il metodo refreshCaselli() per aggiornare la lista
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void rimuoviCasello(ActionEvent e){
        String casello = (String) caselliList.getSelectionModel().getSelectedItem();
        DBManager.delTollboth(casello);
        System.out.println("Eliminato il casello: "+casello);
        this.refreshCaselli();
    }

    /**
     * Questo metodo crea una nuova finestra modale per la modifica del casello selezionato. Alla chiusura chiama il metodo refreshCaselli() per aggiornare la lista dei caselli
     * @param e Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void modificaCasello(ActionEvent e){
        //ottengo il nome del casello dalla selezione
        TollBoth casello = DBManager.getTollBoth((String) caselliList.getSelectionModel().getSelectedItem());
        //Creo il modale per la modifica del casello
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/modifyCasello.fxml"));
        try {
            if (loader == null) System.out.println("Vuoto");
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setTitle("Modifica Casello "+casello);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow() );
        //Passo il nome del casello al modale
        ModifyCaselloController controller = loader.getController();
        controller.setTb(casello, autostradeLista);
        stage.setScene(new Scene(root));
        //mostro il modale
        stage.show();
        //Evento chiusura del modale
        stage.setOnHiding((WindowEvent event1) -> {
            this.refreshCaselli();
        });
    }


    //Generale

    /**
     * Questo metodo associato al pulsante Indietro si occupa di cambiare finestra e tornare alla Home
     * @param event Parametro di tipo ActionEvent che rappresenta l'evento che ha causato la chiamata al metodo
     */
    private void goBack(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/home.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
    }

    /**
     * Questo metodo viene chiamato dopo aver effettuato il login e si occupa di preservare l'utente nella parte gestionale
     * @param user Parametro di tipo Utente
     */
    public void setUser(User user){
        this.user=user;
        System.out.println(user.getUsername());
    }
}
