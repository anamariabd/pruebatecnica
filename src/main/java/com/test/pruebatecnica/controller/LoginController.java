package com.test.pruebatecnica.controller;


import com.test.pruebatecnica.PruebatecnicaApplication;
import com.test.pruebatecnica.service.UsuarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;


    private Stage stage;

    @FXML
    private PasswordField txtPassword;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void eventKey(KeyEvent keyEvent) {
    }


     @FXML
    public void onLogin(){
        String username = txtUser.getText();
        String password  = txtPassword.getText();
        System.out.println(username + "  "+ password);

    }

    @FXML
    private void redirectToRegister(ActionEvent event) throws IOException {
        Parent loader = null;
        try {
            loader = FXMLLoader.load(PruebatecnicaApplication.class.getResource("/register.fxml"));
            stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
