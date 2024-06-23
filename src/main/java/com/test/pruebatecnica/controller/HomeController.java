package com.test.pruebatecnica.controller;


import com.test.pruebatecnica.config.StageManager;
import com.test.pruebatecnica.model.Usuario;
import com.test.pruebatecnica.repository.UsuarioRepository;
import com.test.pruebatecnica.service.UsuarioService;
import com.test.pruebatecnica.view.FxmlView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class HomeController  implements Initializable {

    @Autowired
    private UsuarioService service;


    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    private Label userName, lastName, email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         Usuario user = this.service.getUserLogin();
         if(user != null){
             this.userName.setText("Nombre de usuario: "+ user.getNombre());
             this.lastName.setText("Apellido del usuario: " + user.getApellido());
             this.email.setText("Correo del usuario: " + user.getCorreo());
         }
    }

    public void logout(){
        stageManager.switchScene(FxmlView.LOGIN);
    }
}
