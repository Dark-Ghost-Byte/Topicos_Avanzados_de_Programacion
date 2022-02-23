package com.example.paint;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML Canvas lienzo;
    @FXML ColorPicker comboColor;
    GraphicsContext ctx;
    String tipoDibujo = "";
    Color color = Color.WHITESMOKE;
    @FXML protected void initialize(){
        ctx = lienzo.getGraphicsContext2D();
        ctx.setFill(color);
        //ctx.fillRect(375,375,100,70);
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