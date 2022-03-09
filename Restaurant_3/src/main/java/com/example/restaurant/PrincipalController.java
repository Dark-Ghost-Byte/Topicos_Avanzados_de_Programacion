package com.example.restaurant;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;

public class PrincipalController {
    @FXML HBox contenedor;

    public void btnProductos(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("productos.fxml"));
        Scene scene = contenedor.getScene();
        parent.translateYProperty().set(scene.getHeight());
        contenedor.getChildren().add(parent);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(parent.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(2), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                contenedor.getChildren().removeAll();
            }
        });
        timeline.play();
    }
}
