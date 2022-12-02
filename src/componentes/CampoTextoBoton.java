/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package componentes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * Contenido de la clase CampoTextoBoton.
 *
 * @author Kristian
 */
public class CampoTextoBoton extends HBox implements Initializable {
    @FXML private TextField textField;
    @FXML
    private Button boton;
    
    private ObjectProperty<EventHandler<MouseEvent>> OnAction = new SimpleObjectProperty<EventHandler<MouseEvent>>();

    /**
     * 
     * Inicializa la clase controladora.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        
        boton.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                    public void handle(MouseEvent event) {
                        onActionProperty().get().handle(event);
                    }
            });
    };
    
    /**
     * Constructor por defecto.
     */
    public CampoTextoBoton() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
"CampoTextoBoton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Método getter para el texto del campo de texto.
     * @return - Retorna el textProperty
     */
    public String getText() {
        return textProperty().get();
    }
    /**
     * Método setter para el texto del campo de texto.
     * @param value - Asigna el valor del campo de texto mediante un String
     */
    public void setText(String value) {
        textProperty().set(value);
    }
    /**
     * Método setter para el texto del botón.
     * @param value - String con valor textual del botón.
     */
    public void setButtonText(String value) {
        this.boton.setText(value);
    }
    /**
     * Método del textproperty
     * @return - Retorna el textProperty del textField
     */
    public StringProperty textProperty() {
        return textField.textProperty();
    }
    /**
     * Método que retorna la Property OnAction
     * @return OnAction
     */
    
    public final ObjectProperty<EventHandler<MouseEvent>> onActionProperty() {
        return OnAction;
    }
    
    /**
     * Método que asigna el handler al terminar la acción.
     * @param handler - Se le pasa la acción que ejecutará.
     */
    
    public final void setOnAction(EventHandler<MouseEvent> handler) {
        OnAction.set(handler);
    }
    
    /**
     * Método para recuperar el método OnAction
     * @return - Retorna OnAction asignado.
     */
    
    public final EventHandler<MouseEvent> getOnAction() {
        return OnAction.get();
    }
}
