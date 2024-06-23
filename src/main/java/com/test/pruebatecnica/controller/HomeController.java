package com.test.pruebatecnica.controller;


import com.test.pruebatecnica.config.StageManager;
import com.test.pruebatecnica.repository.UsuarioRepository;
import com.test.pruebatecnica.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository repository;


    @Lazy
    @Autowired
    private StageManager stageManager;

    public void logout(){
        stageManager.switchScene(FxmlView.LOGIN);
    }
}
