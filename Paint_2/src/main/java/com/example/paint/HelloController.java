package com.example.paint;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML Canvas lienzo;
    @FXML ColorPicker comboColor;
    @FXML Slider slider;
    @FXML ComboBox miCombo;
    GraphicsContext ctx;
    String tipoDibujo = "";
    Color color = Color.WHITESMOKE;
    @FXML protected void initialize(){
        ctx = lienzo.getGraphicsContext2D();
        ctx.setFill(color);
        miCombo.getItems().addAll("Cuadricula", "Chess");
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                slider(number.intValue());
            }
        });
        //ctx.fillRect(375,375,100,70);
    }
    public void slider(int valor){
        //CUADRICULA
        ctx.setFill(Color.BLACK);
        ctx.fillRect(0,0, lienzo.getWidth(), lienzo.getHeight());
        ctx.setFill(color);
        ctx.setStroke(color);

        if (miCombo.getSelectionModel().getSelectedIndex() == 0){
            int divisiones = valor;
            int incrementoX = (int) lienzo.getWidth() / divisiones;
            int incrementoY = (int) lienzo.getHeight() / divisiones;
            for (int x=1;x<divisiones+1;x++){
                //(x1,y1. x2,y2)
                ctx.strokeLine(0, incrementoY*x, lienzo.getWidth(), incrementoY*x);
                ctx.strokeLine(incrementoX*x, 0, incrementoX*x, lienzo.getWidth());
            }
        }else if (miCombo.getSelectionModel().getSelectedIndex() == 1){
            //CHESS
            int divX = (int) lienzo.getWidth() / valor;
            int divY = (int) lienzo.getHeight() / valor;
            boolean bandera = true;
            for (int y=0;y<valor;y++){
                for (int x=0;x<valor;x++){
                    if (bandera){
                        ctx.setFill(Color.BROWN);
                    }else{
                        ctx.setFill(Color.WHITE);
                    }
                    bandera = !bandera;
                    ctx.fillRect(x*divX, y*divY, divX, divY);
                }
            }//LLAVE Y
        }
    }
    public void pincel(ActionEvent event){
        tipoDibujo = "pincel";
    }
    public void arrastrar(MouseEvent event){
        if (tipoDibujo.equals("pincel")){
            ctx.fillOval(event.getX(), event.getY(), 10, 10);
        }
    }
    public void cambiarColor(ActionEvent event){
        color = comboColor.getValue();
        ctx.setFill(color);
    }

}