/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package componentes;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Contenido de la clase SelectorDeslizamiento.
 * @author Kristian Johansson Dougal
 */
public class SelectorDeslizamiento extends AnchorPane {

    @FXML
    private Button previousButton;
    @FXML
    private Label label;
    @FXML
    private Button nextButton;
    ArrayList<String> items;
    int selectedIndex;
    private boolean repetitive;

    /**
     * Constructor por defecto.
     */
    public SelectorDeslizamiento() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("selector_deslizamiento.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        items = new ArrayList<>();
        selectedIndex = 0;
        previousButton.setOnAction((ActionEvent event) -> {
            setPrevious();
            fireEvent(event);
        });
        nextButton.setOnAction((ActionEvent event) -> {
            setNext();
            fireEvent(event);
        });
    }
    /**
     * Método setter para los ítems del selector.
     * @param items - ArrayList de ítems.
     */
    public void setItems(ArrayList<String> items) {
        this.items = items;
        selectFirst();
    }
    /**
     * Método setter para asignar el valor anterior.
     */
    public void setPrevious() {
        updateItem(selectedIndex - 1);
    }
    /**
     * Método setter para asignar el valor siguiente.
     */
    public void setNext() {
        updateItem(selectedIndex + 1);
    }
    /**
     * Método para asignar el primer valor.
     */
    public void selectFirst() {
        updateItem(0);
    }
    /**
     * Método para asignar el último valor.
     */
    private void selectLast() {
        updateItem(items.size() - 1);
    }
    /**
     * Método para actualizar el valor.
     */
    private void updateItem() {
        if (items.isEmpty()) {
            label.setText("Vacio");
        } else {
            if (selectedIndex < 0) {
                if (repetitive) {
                    selectedIndex = items.size() - 1;
                } else {
                    selectedIndex = 0;
                }
            }
            if (selectedIndex >= items.size()) {
                if (repetitive) {
                    selectedIndex = 0;
                } else {
                    selectedIndex = items.size() - 1;
                }
            }
            label.setText(items.get(selectedIndex));
        }
    }
    /**
     * Método para actualizar el valor con parámetro.
     * @param selectedIndex - Índice especificado. 
     */
    private void updateItem(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        updateItem();
    }
    /**
     * Método setter para asignar los ciclos con un booleano.
     * @param cyclesThrough - Booleano que especifica los ciclos.
     */
    public void setRepetitive(boolean cyclesThrough) {
        this.repetitive = cyclesThrough;
    }
    /**
     * Método que retorna la Property OnAction
     * @return OnAction
     */
    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return onAction;
    }
    /**
     * Método que asigna el handler al terminar la acción.
     * @param value - Se le pasa la acción que ejecutará.
     */
    public final void setOnAction(EventHandler<ActionEvent> value) {
        onActionProperty().set(value);
    }
    /**
     * Método para recuperar el método OnAction
     * @return - Retorna OnAction asignado.
     */
    public final EventHandler<ActionEvent> getOnAction() {
        return onActionProperty().get();
    }
    private ObjectProperty<EventHandler<ActionEvent>> onAction = new ObjectPropertyBase<EventHandler<ActionEvent>>() {
        @Override
        protected void invalidated() {
            setEventHandler(ActionEvent.ACTION, get());
        }

        @Override
        public Object getBean() {
            return SelectorDeslizamiento.this;
        }

        @Override
        public String getName() {
            return "onAction";
        }
    };

}
