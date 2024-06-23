package com.test.pruebatecnica.controller;


import com.test.pruebatecnica.config.StageManager;
import com.test.pruebatecnica.service.UsuarioService;
import com.test.pruebatecnica.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;

import static com.test.pruebatecnica.service.UsuarioServiceImp.generarCadena;

@Controller
public class PasswordController {

    @Lazy
    @Autowired
    private StageManager stageManager;


    @Autowired
    private UsuarioService usuarioService;

    @FXML
    private void redirectToLogin(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.LOGIN);

    }

}
