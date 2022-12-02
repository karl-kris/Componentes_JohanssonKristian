/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import javafx.scene.control.TextField;

/**
 *  Contenido de la clase CampoTextoNumerico.
 * @author Kristian Johansson Dougal
 */
public class CampoTextoNumerico extends TextField {
    /**
     * Constructor por defecto.
     */
    
    public CampoTextoNumerico(){
        super();   
    }
    
    /**
     * Método que especifica al campo de texto que solo se admiten caracteres alfabéticos.
     * @param start - Principio del string
     * @param end - Final del string
     * @param text - Texto introducido.
     */
    @Override
    public void replaceText(int start, int end, String text) {
        if (!text.matches("[a-z, A-Z]")) {
            super.replaceText(start, end, text);
        }
    }
        
    /**
     * Método para reemplazar el texto.
     * @param text - Texto a reemplazar.
     */
        @Override
    public void replaceSelection(String text) {
        if (!text.matches("[a-z, A-Z]")) {
            super.replaceSelection(text);
        }
    }
    
}
