package com.test.pruebatecnica.controller;

import com.test.pruebatecnica.config.StageManager;
import com.test.pruebatecnica.model.Usuario;
import com.test.pruebatecnica.repository.UsuarioRepository;
import com.test.pruebatecnica.service.UsuarioService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UsuarioController implements Initializable {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;
    @FXML
    private ListView<Model> list;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnCreate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //   userTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //       setColumnProperties();

        // Add all users into table
        loadUserDetails();
    }


    @Autowired
    private UsuarioService usuarioService;

    private ObservableList<Usuario> userList = FXCollections.observableArrayList();

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Logout and go to the login page
     */
    @FXML
    private void logout(ActionEvent event) throws IOException {
        /*Scene scene= new Scene();
        stageManager.switchScene();*/
    }

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void saveUser(ActionEvent event){

            if(getEmail() != null && getPassword() != null){
                if(validate("Email", getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
                && validate("Password", getPassword(), "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,}$")
                ){

                    Usuario user = new Usuario();
                    user.setNombre(getFirstName());
                    user.setApellido(getLastName());
                    user.setCorreo(getEmail());
                    user.setPassword(getPassword());

                    Usuario newUser = usuarioService.save(user);

                  //  saveAlert(newUser);
                }

            }else{
                Usuario user = usuarioService.findByEmail(getEmail());
                user.setNombre(getFirstName());
                user.setApellido(getLastName());
                user.setCorreo(getEmail());
                user.setPassword(getLastName());
                Usuario updatedUser =  usuarioService.update(user);
                updateAlert(updatedUser);
            }

            clearFields();
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

    private void clearFields() {
        firstName.clear();
        lastName.clear();
        email.clear();
        password.clear();
    }


    private void updateAlert(Usuario user){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The user "+user.getNombre()+" "+user.getApellido() +" has been updated.");
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


    /*
     *  Add All users to observable list and update table
     */
    private void loadUserDetails(){
        userList.clear();
    //    userList.addAll(UsuarioService.findAll());

     //   userTable.setItems(userList);
    }


    private boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
            validationAlert(field, true);
            return false;
        }
    }

    private void validationAlert(String field, boolean empty){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Role")) alert.setContentText("Please Select "+ field);
        else{
            if(empty) alert.setContentText("Please Enter "+ field);
            else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
    }


}
