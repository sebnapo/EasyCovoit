/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easycovoit.controller;

import easycovoit.EasyCovoit;
import easycovoit.model.Database;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author anonymous
 */
public class LoginController implements Initializable {

    @FXML
    private Button connectButton,inscriptionButton;
    @FXML
    private TextField emailEntry,passwordEntry;
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
        connectButton.setOnMouseClicked(event -> {
            ResultSet rs;
            try {
                if((rs = db.seConnecter(emailEntry.getText(),passwordEntry.getText())) != null){
                    try {
                        EasyCovoit.setIdUser(Integer.parseInt(rs.getString("idUtilisateur")));
                        Stage stage = (Stage) inscriptionButton.getScene().getWindow();
                        System.out.println(stage);
                        Parent root = FXMLLoader.load(getClass().getResource("../view/Principale.fxml"));
                        stage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        inscriptionButton.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) inscriptionButton.getScene().getWindow();
                System.out.println(stage);
                Parent root = FXMLLoader.load(getClass().getResource("../view/Inscription.fxml"));
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }    
    
}
