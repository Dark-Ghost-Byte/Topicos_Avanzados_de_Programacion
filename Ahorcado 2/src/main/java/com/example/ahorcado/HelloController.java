package com.example.ahorcado;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.util.Locale;
import java.util.Random;

public class HelloController {
    @FXML
    HBox contenedor;
    @FXML
    AnchorPane padre;
    String[] palabras = {"HIDROGENO", "HELIO", "LITIO", "BERILIO", ""};
    int aleatorio = 1;
    TextField[] arrayTxts;
    int errores = 0;
    int x = 0;

    public void btnIniciar(ActionEvent event) {
        Random random = new Random();
        aleatorio = random.nextInt(palabras.length - 1);
        String p = palabras[aleatorio].toUpperCase(Locale.ROOT);
        arrayTxts = new TextField[palabras[aleatorio].length()];

        //HELIO = 5 PALABRAS
        int indexAyuda = random.nextInt(p.length() - 1);
        contenedor.getChildren().clear();

        for (int x = 0; x < p.length(); x++) {
            arrayTxts[x] = new TextField();
            arrayTxts[x].setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            arrayTxts[x].prefWidth(150);
            arrayTxts[x].prefHeight(120);

            if (x == indexAyuda) {
                arrayTxts[x].setText(p.charAt(x) + "");
            }//LLAVE IF
            arrayTxts[x].setId("txt_" + x);
            arrayTxts[x].setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    TextField disparo = (TextField) keyEvent.getTarget();
                    //txt_0
                    String[] arr = disparo.getId().split("_");
                    int indice = Integer.parseInt(arr[1]);
                    if (palabras[aleatorio].toLowerCase().charAt(indice) == disparo.getText().charAt(0)) {
                        System.out.println("CORRECTO");
                        disparo.setDisable(true);
                    } else {
                        System.out.println("INCORRECTO");
                        errores++;
                        disparo.setText("");
                        pintarErrores();
                    }
                    System.out.println(disparo.getId());
                }
            });
            contenedor.getChildren().add(arrayTxts[x]);
        }//LLAVE FOR
        System.out.println(p);
    }//LLAVE INICIAR

    public void pintarErrores() {
        System.out.println(errores);
        if (errores == 1) {
            File f = new File("src/main/resources/com/example/ahorcado/img/h1.png");
            System.out.println(f.getAbsoluteFile());
            ImageView imageView = new ImageView(new Image(f.toURI().toString()));
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setLayoutX(625);
            imageView.setLayoutY(50);
            padre.getChildren().add(imageView);
        } else if (errores == 2) {
            File f = new File("src/main/resources/com/example/ahorcado/img/h2.png");
            System.out.println(f.getAbsoluteFile());
            ImageView imageView = new ImageView(new Image(f.toURI().toString()));
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setLayoutX(620);
            imageView.setLayoutY(140);
            padre.getChildren().add(imageView);
        } else if (errores == 3) {
            File f = new File("src/main/resources/com/example/ahorcado/img/h3.png");
            System.out.println(f.getAbsoluteFile());
            ImageView imageView = new ImageView(new Image(f.toURI().toString()));
            imageView.setFitWidth(25);
            imageView.setFitHeight(100);
            imageView.setLayoutX(625);
            imageView.setLayoutY(155);
            padre.getChildren().add(imageView);
        } else if (errores == 4) {
            File f = new File("src/main/resources/com/example/ahorcado/img/h4.png");
            System.out.println(f.getAbsoluteFile());
            ImageView imageView = new ImageView(new Image(f.toURI().toString()));
            imageView.setFitWidth(25);
            imageView.setFitHeight(100);
            imageView.setLayoutX(697);
            imageView.setLayoutY(156);
            padre.getChildren().add(imageView);
        }else if (errores == 5) {
            File f = new File("src/main/resources/com/example/ahorcado/img/h5.png");
            System.out.println(f.getAbsoluteFile());
            ImageView imageView = new ImageView(new Image(f.toURI().toString()));
            imageView.setFitWidth(50);
            imageView.setFitHeight(100);
            imageView.setLayoutX(680);
            imageView.setLayoutY(238);
            padre.getChildren().add(imageView);
        }else if (errores == 6) {
            File f = new File("src/main/resources/com/example/ahorcado/img/h6.png");
            System.out.println(f.getAbsoluteFile());
            ImageView imageView = new ImageView(new Image(f.toURI().toString()));
            imageView.setFitWidth(25);
            imageView.setFitHeight(100);
            imageView.setLayoutX(650);
            imageView.setLayoutY(238);
            padre.getChildren().add(imageView);
        }else if (errores == 7) {
            File f = new File("src/main/resources/com/example/ahorcado/img/perdio.png");
            System.out.println(f.getAbsoluteFile());
            ImageView imageView = new ImageView(new Image(f.toURI().toString()));
            imageView.setFitWidth(900);
            imageView.setFitHeight(500);
            imageView.setLayoutX(0);
            imageView.setLayoutY(0);
            padre.getChildren().add(imageView);
        }
    }
}