/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package componentes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class Temporizador extends HBox implements Initializable {

    @FXML
    private Label etiqueta;
    @FXML
    private Label tiempo;
    @FXML
    private Label medida;
    
    private Timeline timeline;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent arg0) {
                    countDown();
                    
                }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setOnFinished((e) -> {
            
            System.out.println("He acabado");});
        timeline.play();
        
    } 
    
    public Temporizador() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
"temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    public int calculaCycleCount(int horas, int minutos, int segundos){
        return horas * 3600 + minutos * 60 + segundos;
    }
    
    public void setEtiqueta(String valor){
        this.etiqueta.setText(valor);
    }
    
    public void setColoresYBorde(String color1, String color2, int radio){
        String rad = Integer.toString(radio);
        etiqueta.setStyle(("-fx-background-radius: " + rad + "px "+ "0px 0px " + rad + "px;")+ color1);
        medida.setStyle(("-fx-background-radius: "+ "0px " + rad + "px "+  rad + "px"+ "0px;")+color1);
        tiempo.setStyle(color2);
    }
    
    
    public void setTiempo(int horas, int minutos, int segundos){
        
        while(segundos > 59){
            segundos -= 60;
            minutos+= 1;
        }
        while(minutos > 59){
            minutos -= 60;
            horas+= 1;
        }
        if(horas < 100){
            if(horas < 10 && minutos < 10 && segundos < 10)
                this.tiempo.setText("0"+Integer.toString(horas)+":0"+Integer.toString(minutos)+":0"+Integer.toString(segundos));
            else if(horas < 10 && minutos < 10)
                this.tiempo.setText("0"+Integer.toString(horas)+":0"+Integer.toString(minutos)+":"+Integer.toString(segundos));
            else if(horas < 10 && segundos < 10)
                this.tiempo.setText("0"+Integer.toString(horas)+":"+Integer.toString(minutos)+":0"+Integer.toString(segundos));
            else if(minutos < 10 && segundos < 10)
                this.tiempo.setText(Integer.toString(horas)+":0"+Integer.toString(minutos)+":0"+Integer.toString(segundos));
            else if(horas < 10)
                this.tiempo.setText("0"+Integer.toString(horas)+":"+Integer.toString(minutos)+":"+Integer.toString(segundos));
            else if(minutos < 10)
                this.tiempo.setText(Integer.toString(horas)+":0"+Integer.toString(minutos)+":"+Integer.toString(segundos));
            else if(segundos < 10)
                this.tiempo.setText(Integer.toString(horas)+":"+Integer.toString(minutos)+":0"+Integer.toString(segundos));
            else
                this.tiempo.setText(Integer.toString(horas)+":"+Integer.toString(minutos)+":"+Integer.toString(segundos));
        
        }
        else{
            this.tiempo.setText("No válido");
        }
        
    }
    
    public void setMedida(String valor){
        this.medida.setText(valor);
    }
    
    public void countDown(){
        
        int horas, mins, secs;
        
        secs = Integer.parseInt(tiempo.getText().substring(6));
        if(secs == 0){
            secs = 59;
        }
        else{
            secs -= 1;
        }
        mins = Integer.parseInt(tiempo.getText().substring(3,5));
        
        
        horas = Integer.parseInt(tiempo.getText().substring(0,2));
        
        if(secs == 0 && horas == 0 && mins == 0){
            timeline.stop();
        }
        
        if(secs == 59){
            if(mins == 0)
                mins = 59;
            else
                mins = Integer.parseInt(tiempo.getText().substring(3,5)) - 1;
        }
        
        if(secs == 59 && mins == 59){
            horas = Integer.parseInt(tiempo.getText().substring(0,2)) - 1;
        }
        
        
        
        setTiempo(horas, mins, secs);
        
    }
    
}
