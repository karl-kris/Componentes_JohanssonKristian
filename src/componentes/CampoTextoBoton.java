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
 * FXML Controller class
 *
 * @author Kristian
 */
public class CampoTextoBoton extends HBox implements Initializable {
    @FXML private TextField textField;
    @FXML
    private Button boton;
    
    private ObjectProperty<EventHandler<MouseEvent>> OnAction = new SimpleObjectProperty<EventHandler<MouseEvent>>();

    public void initialize(URL url, ResourceBundle rb) {
        
        boton.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                    public void handle(MouseEvent event) {
                        onActionProperty().get().handle(event);
                    }
            });
    };
    
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

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }
    
    public void setButtonText(String value) {
        this.boton.setText(value);
    }

    public StringProperty textProperty() {
        return textField.textProperty();
    }

    
    public final ObjectProperty<EventHandler<MouseEvent>> onActionProperty() {
        return OnAction;
    }
    public final void setOnAction(EventHandler<MouseEvent> handler) {
        OnAction.set(handler);
    }
    public final EventHandler<MouseEvent> getOnAction() {
        return OnAction.get();
    }
}
