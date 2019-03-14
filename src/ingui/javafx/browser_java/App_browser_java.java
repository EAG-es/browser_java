/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.javafx.browser_java;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author daw
 */
public interface App_browser_java extends ChangeListener<String> {
    
    void changed(ObservableValue<? extends String> observable, String oldValue, String newValue);
    
    String iniciar_contenido(Class clase, String [] error);

    FXMLDocumentController getfXMLDocumentController();

    void setfXMLDocumentController(FXMLDocumentController fXMLDocumentController);
    
    
}
