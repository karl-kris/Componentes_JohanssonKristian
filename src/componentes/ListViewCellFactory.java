/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *Contenido de la clase ListViewCellFactory.
 * @author Kristian Johansson Dougal
 */
public class ListViewCellFactory  extends ListCell<String> {
    
    /**
     * Constructor por defecto.
     */
    public ListViewCellFactory(){
        super();
    }
    /**
     * Método que actualiza el valor de la celda.
     * @param item - Ítem con el que se va a actualizar la celda
     * @param empty - Booleano que comprueba si está vacía la celda.
     */
    @Override 
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        Circle circ = new Circle(20);
        if (item != null) {
            circ.setFill(Color.web(item));
            setGraphic(circ);
        } else {
            setGraphic(null);
        }
    }
    
}
