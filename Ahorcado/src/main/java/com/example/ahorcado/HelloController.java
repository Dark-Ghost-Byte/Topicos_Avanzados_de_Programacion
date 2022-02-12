package com.example.ahorcado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.Locale;
import java.util.Random;

public class HelloController {
    @FXML HBox contenedor;
    String[] palabras = {"Hidrogeno","Helio","Litio","Berilio",""};

    public void btnIniciar(ActionEvent event){
        Random random = new Random();
        int aleatorio = random.nextInt(palabras.length-1);
        String p = palabras[aleatorio].toUpperCase(Locale.ROOT);
        //HELIO = 5 PALABRAS
        int indexAyuda = random.nextInt(p.length()-1);
        contenedor.getChildren().clear();
        for (int x=0;x<p.length();x++){
            TextField txtLetra = new TextField();
            txtLetra.prefWidth(80);
            txtLetra.prefHeight(90);
            if (x == indexAyuda){
                txtLetra.setText(p.charAt(x) + "");
                txtLetra.setText(p.charAt(x) + "");
                txtLetra.setText(p.charAt(x) + "");
            }//LLAVE IF
            contenedor.getChildren().add(txtLetra);
        }//LLAVE FOR
        System.out.println(p);
    }//LLAVE INICIAR
}