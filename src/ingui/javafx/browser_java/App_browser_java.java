/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.javafx.browser_java;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Interfaz con los métodos que se necesitan para crear una aplicación "browser_java"
 * @author daw
 */
public interface App_browser_java extends ChangeListener<String> {
    /**
     * Método changed para que el listener de WebView escuche los cambios de URL
     * @param observable Especiacialización para String de un valor "escuchable".
     * @param oldValue Contiene la URL antigua
     * @param newValue Contiene la URL nueva
     */
    void changed(ObservableValue<? extends String> observable, String oldValue, String newValue);
    /**
     * Método llamado al inicio de la aplicación, y solo entonces.
     * @param clase Parámetro con la información de la clase generada por el archivo fxml: FXMLDocumentController.
     * @param error Mensaje de error en la posición 0, si lo hay
     * @return true si todo es correcto, false, si hay error.
     */
    String iniciar_contenido(Class clase, String [] error);
    /**
     * Método para acceder a la información del FXMLDocumentController.
     * @return null si hay error, o el valor solicitado.
     */
    FXMLDocumentController getfXMLDocumentController();
    /**
     * Método para establecer la información del FXMLDocumentController.
     * @param fXMLDocumentController dato con la información del FXMLDocumentController
     */
    void setfXMLDocumentController(FXMLDocumentController fXMLDocumentController);
    
    
}
