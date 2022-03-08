package com.example.restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML TextField txtUser;
    @FXML PasswordField txtPassword;


    public void ingresar(ActionEvent event) throws IOException {
        String usuario = txtUser.getText();
        String pass = txtPassword.getText();
        if (usuario.equals("admin") && pass.equals("123")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("principal.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            HelloApplication.primaryStage.setScene(scene);
            HelloApplication.primaryStage.setMaximized(true);

        }
    }
}