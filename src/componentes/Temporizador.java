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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * 
 * 
 * Contenido de la clase FXML Temporizador.
 *
 * @author Kristian Johansson Dougal
 */
public class Temporizador extends HBox implements Initializable {

    @FXML
    private Label etiqueta;
    @FXML
    private Label tiempo;
    @FXML
    private Label medida;
    
    private Timeline timeline;
    private int ciclos = 0;
    private ObjectProperty<EventHandler<ActionEvent>> OnAction = new SimpleObjectProperty<EventHandler<ActionEvent>>();
    
    /**
     * Inicializa la clase controladora.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    /**
     * Constructor de Temporizador sin argumentos.
     */
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
    
    /**
     * Método de comienzo del contador.
     */
    public void startTimeline(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent arg0) {
                    countDown();
                    
                }
        }));
        timeline.setCycleCount(ciclos);
        timeline.setOnFinished(new EventHandler<ActionEvent>(){
                @Override
                    public void handle(ActionEvent event) {
                        onActionProperty().get().handle(event);
                    }
            });
        timeline.play();
    }
    
    /**
     * Cálculo del número de ciclos del Timeline
     */
    public void calculaCycleCount(){
        
        ciclos =  (Integer.parseInt(tiempo.getText().substring(6))) + (Integer.parseInt(tiempo.getText().substring(3,5))*60) + (Integer.parseInt(tiempo.getText().substring(0,2))*3600);
    }
    
    /**
     * Método setter del atributo Etiqueta
     * @param valor - Valor del texto
     */
    
    public void setEtiqueta(String valor){
        this.etiqueta.setText(valor);
    }
    
    /**
     * Método para asignarle colores y un radio del borde predeterminado.
     * @param color1 - Color de etiquetas exteriores.
     * @param color2 - Color de etiqueta del Temporizador
     * @param radio - Radio del Background de las esquinas
     */
    
    public void setColoresYBorde(String color1, String color2, int radio){
        String rad = Integer.toString(radio);
        etiqueta.setStyle(("-fx-background-radius: " + rad + "px "+ "0px 0px " + rad + "px;")+ color1);
        medida.setStyle(("-fx-background-radius: "+ "0px " + rad + "px "+  rad + "px"+ "0px;")+color1);
        tiempo.setStyle(color2);
    }
    
    /**
     * Método que le asigna al Temporizador el tiempo que debe calcular para usar.
     * @param horas - Horas asignadas para el Temporizador.
     * @param minutos - Minutos asignadas para el Temporizador. 
     * @param segundos - Segundos asignadas para el Temporizador.
     */
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
            this.tiempo.setText("No válido\n>99 horas");
        }
        
    }
    
    /**
     * Método setter para el valor textual del atributo Medida
     * @param valor - Valor de texto
     */
    
    public void setMedida(String valor){
        this.medida.setText(valor);
    }
    
    /**
     * Método que calcula cuando modificar tanto los segundos, como los minutos como las horas.
     */
    
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
    
    /**
     * Método que salta con una alerta estándar para su uso al terminar el timeline.
     */
    
    public void alertaEstandar(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(etiqueta.getText() + " ha acabado");
        alert.show();
    }
    
    /**
     * Método que retorna la Property OnAction
     * @return OnAction
     */
    
    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
    return OnAction;
    }
    
    /**
     * Método que asigna el handler al terminar el Timeline.
     * @param handler - Se le pasa la acción que ejecutará.
     */
    
    public final void setOnFinished(EventHandler<ActionEvent> handler) {
        OnAction.set(handler);
    }
    
    /**
     * Método para recuperar el método OnFinished
     * @return - Retorna OnAction asignado.
     */
    public final EventHandler<ActionEvent> getOnFinished() {
        return OnAction.get();
    }
}
