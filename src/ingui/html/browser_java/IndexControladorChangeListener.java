/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.html.browser_java;

import ingui.javafx.browser_java.FXMLDocumentController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author daw
 */
public class IndexControladorChangeListener implements ChangeListener<String> {
    public FXMLDocumentController fXMLDocumentController = null;

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        boolean ret = true;
        String contenido;
        String[] error = {""};
        String url = newValue;
        try {
            if (url.startsWith("http://browser_java/index")) {
                contenido = IndexControlador.procesar(url, error);
                if (contenido != null) {
                    ret = fXMLDocumentController.cargar_contenido(contenido, "text/html", error);
                } else {
                    ret = false;
                }
            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = "";
            }
            error[0] = "Error al analizar el cambio de URL. ";
            ret = false;
        }
        if (ret == false) {
            fXMLDocumentController.poner_error(error[0]);
        }
    }
}
