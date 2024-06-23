package com.test.pruebatecnica.controller;


import com.test.pruebatecnica.PruebatecnicaApplication;
import com.test.pruebatecnica.config.StageManager;
import com.test.pruebatecnica.model.Usuario;
import com.test.pruebatecnica.service.UsuarioService;
import com.test.pruebatecnica.view.FxmlView;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;

    @Lazy
    @Autowired
    private StageManager stageManager;
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
     public void onLogin() {
         String username = txtUser.getText();
         String password = txtPassword.getText();
         Usuario usuario = this.usuarioService.findByEmail(username);
         Boolean init = this.usuarioService.authenticate(username, password);
         System.out.println(usuario);

    }

    @FXML
    private void redirectToRegister(ActionEvent event) throws IOException {

        stageManager.switchScene(FxmlView.REGISTER);

    }

}
