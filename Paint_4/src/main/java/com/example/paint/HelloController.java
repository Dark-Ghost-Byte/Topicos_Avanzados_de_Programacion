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
import javafx.scene.effect.ColorAdjust;
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
        miCombo.getItems().addAll("Cuadricula", "Chess", "Circulos", "Estrella", "Eestrella doble");
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                slider(number.intValue());
            }
        });
        //ctx.fillRect(375,375,100,70);
    }
    public void slider(int valor){
        ctx.setFill(Color.BLACK);
        ctx.fillRect(0,0, lienzo.getWidth(), lienzo.getHeight());
        ctx.setFill(color);
        ctx.setStroke(color);
        //CUADRICULA

        if (miCombo.getSelectionModel().getSelectedIndex() == 0){
            int divisiones = valor;
            int incrementoX =(int) lienzo.getWidth()/divisiones;
            int incrementoY =(int) lienzo.getHeight()/divisiones;
            for (int x=1;x<divisiones+1;x++){
                //(x1,y1. x2,y2)
                ctx.strokeLine(0, incrementoY*x, lienzo.getWidth(), incrementoY*x);
                ctx.strokeLine(incrementoX*x, 0, incrementoX*x, lienzo.getWidth());
            }

        }else if (miCombo.getSelectionModel().getSelectedIndex() == 1){
            //CHESS
            int divX =(int) lienzo.getWidth()/valor;
            int divY =(int) lienzo.getHeight()/valor;
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

        }else if (miCombo.getSelectionModel().getSelectedIndex() == 2){
            int grado = 360;
            double circulo =grado / valor;
            double dimX=lienzo.getWidth()/4;
            double dimy=lienzo.getHeight()/4;
            double radio=Math.toRadians(0);
            double CoordenadaX=Math.round(dimX * Math.cos(radio));
            double CoordenadaY=(dimy * Math.sin(radio));
            while (radio<=2*Math.PI) {
                ctx.strokeOval(CoordenadaX+lienzo.getWidth()/4, CoordenadaY+lienzo.getHeight()/4,
                        lienzo.getWidth()/2, lienzo.getHeight()/2);
                radio=radio+Math.toRadians(circulo);
                CoordenadaX=Math.round(dimX * Math.cos(radio));
                CoordenadaY=(dimy * Math.sin(radio));
            }

        } else if (miCombo.getSelectionModel().getSelectedIndex() == 3){
            //ESTRELLA NORMAL
            int mitadAncho = (int) lienzo.getWidth() / 2;
            int mitadAlto = (int) lienzo.getHeight() / 2;
            ctx.strokeLine(mitadAncho, 0, mitadAncho, lienzo.getHeight());
            ctx.strokeLine(0, mitadAlto, lienzo.getWidth(), mitadAlto);
            int divisiones = mitadAncho / valor;
            for (int j=0;j<valor;j++){
                //PICO SUPERIOR DERECHO
                ctx.strokeLine(mitadAncho, j*divisiones, mitadAncho+(divisiones*j), mitadAlto);
                //PICO INFERIOR DERECHO
                ctx.strokeLine(mitadAncho, lienzo.getHeight()-(j*divisiones), mitadAncho+(divisiones*j), mitadAlto);
                //PICO SUPERIOR IZQUIERDO
                ctx.strokeLine(mitadAncho, j*divisiones, mitadAncho-(divisiones*j), mitadAlto);
                //PICO INFERIOR IZQUIERDO
                ctx.strokeLine(mitadAncho, lienzo.getHeight()-(j*divisiones), mitadAncho-(divisiones*j), mitadAlto);
            }//LLAVE FOR

        } else if (miCombo.getSelectionModel().getSelectedIndex() == 4){
            //ESTRELLA DOBLE
            int mitadAncho = (int) lienzo.getWidth() / 2;
            int mitadAlto = (int) lienzo.getHeight() / 2;
            ctx.strokeLine(mitadAncho, 0, mitadAncho, lienzo.getHeight());
            ctx.strokeLine(0, mitadAlto, lienzo.getWidth(), mitadAlto);
            int divisiones = mitadAncho / valor;
            for (int j=0;j<valor;j++){
                //PRIMERA ESTRELLA
                ctx.strokeLine(mitadAncho, j*divisiones, mitadAncho+(divisiones*j), mitadAlto);
                ctx.strokeLine(mitadAncho, lienzo.getHeight()-(j*divisiones), mitadAncho+(divisiones*j), mitadAlto);
                ctx.strokeLine(mitadAncho, j*divisiones, mitadAncho-(divisiones*j), mitadAlto);
                ctx.strokeLine(mitadAncho, lienzo.getHeight()-(j*divisiones), mitadAncho-(divisiones*j), mitadAlto);
                //SEGUNDA ESTRELLA
                ctx.strokeLine(j*divisiones, j*divisiones, mitadAncho+(divisiones*j), mitadAncho-(divisiones*j));
                ctx.strokeLine(j*divisiones, j*divisiones, mitadAncho-(divisiones*j), mitadAncho+(divisiones*j));
                ctx.strokeLine(lienzo.getHeight()-(j*divisiones), lienzo.getWidth()-(j*divisiones), mitadAncho+(divisiones*j), mitadAlto-(divisiones*j));
                ctx.strokeLine(lienzo.getHeight()-(j*divisiones), lienzo.getWidth()-(j*divisiones), mitadAncho-(divisiones*j), mitadAlto+(divisiones*j));
            }//LLAVE FOR
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