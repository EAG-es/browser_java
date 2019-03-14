/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.html.browser_java;

import ingui.javafx.browser_java.App_browser_java;
import ingui.javafx.browser_java.FXMLDocumentController;
import innui.archivos.Archivos;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author daw
 */
public class Examen_banderas implements App_browser_java {
    public FXMLDocumentController fXMLDocumentController = null;

    @Override
    public FXMLDocumentController getfXMLDocumentController() {
        return fXMLDocumentController;
    }

    @Override
    public void setfXMLDocumentController(FXMLDocumentController fXMLDocumentController) {
        this.fXMLDocumentController = fXMLDocumentController;
    }

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

    @Override
    public String iniciar_contenido(Class clase, String [] error)
    {
        String archivo = "/ingui/html/browser_java/recursos/index.html";
        String texto = null;
        String ruta = Archivos.leer_ruta_base(clase, error);
        if (ruta != null) {
            texto = Archivos.leer_archivo_texto(archivo, error);
        }
        if (texto != null) {
            texto = texto.replaceAll("\\$\\{\\s*browser_java_ruta\\s*\\}", ruta);
        }
        return texto;
    }    
}
