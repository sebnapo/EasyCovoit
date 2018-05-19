/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easycovoit.controller;

import easycovoit.model.Database;
import easycovoit.model.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author anonymous
 */
public class InscriptionController implements Initializable {
    @FXML
    private TextField emailEntry,passwordEntry,telEntry;
    @FXML
    private Button inscriptionButton;

    private Database db;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.db = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.inscriptionButton.setOnMouseClicked(event -> {
            try {
                this.db.insererUtilisateur(new Utilisateur(emailEntry.getText(),passwordEntry.getText(),telEntry.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Stage stage = (Stage) inscriptionButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
