package com.test.pruebatecnica.controller;


import com.test.pruebatecnica.PruebatecnicaApplication;
import com.test.pruebatecnica.config.StageManager;
import com.test.pruebatecnica.model.Usuario;
import com.test.pruebatecnica.repository.UsuarioRepository;
import com.test.pruebatecnica.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegisterController {

    @Autowired
    private  UsuarioRepository repository;


    @Lazy
    @Autowired
    private StageManager stageManager;
    private Stage stage;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;



    public void eventKey(KeyEvent keyEvent) {
    }

    @FXML
    public  void  registrar(){

    }

    @FXML
    private void saveUser(ActionEvent event){

    //    Usuario userEmail = repository.findByEmail(getEmail());

        if(getEmail() != null){
            if(validate("Email", getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
                    !getPassword().isEmpty() && getPassword().length() >=8){

                Usuario user = new Usuario();
                user.setNombre(getFirstName());
                user.setApellido(getLastName());
                user.setCorreo(getEmail());
                user.setPassword(getPassword());

                Usuario newUser = repository.save(user);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Usuario registrado correctamente.");
                alert.setHeaderText(null);
                alert.setContentText("El usuario "+newUser.getNombre()+" "+newUser.getApellido() +" ha sido creado exitosamente");
                alert.showAndWait();
            }

        }else{
            Usuario user = repository.findByEmail(getEmail());
            user.setNombre(getFirstName());
            user.setApellido(getLastName());
            user.setCorreo(getEmail());
            user.setPassword(getLastName());
//            Usuario updatedUser =  repository.update(user);
          // updateAlert(updatedUser);
        }

       // clearFields();
    }

    private boolean validate(String field, String value, String pattern){
        if(!value.isEmpty()){
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if(m.find() && m.group().equals(value)){
                return true;
            }else{
                validationAlert(field, false);
                return false;
            }
        }else{
            validationAlert(field, true);
            return false;
        }
    }

    private void validationAlert(String field, boolean empty){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }


    public String getEmail() {
        return email.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    @FXML
    private void redirectToLogin(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.LOGIN);
    }
}
