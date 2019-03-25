package ingui.javafx.browser_java;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Cklase generada al genera el controlador del archivo FXMLDocument.fxml
 * @author daw
 * @since 25/marzo/2019
 */
public class FXMLDocumentController implements Initializable {
    /**
     * Atributo que representa un visor HTML de JafaFX.
     */
    @FXML
    private WebView webView;
    /**
     * Receptor de la factoria: Factory_app_browser_java
     */
    public App_browser_java app_browser_java = null;
    
    /**
     * Método llamado mediante la interface Initializable.
     * @param url URL de inicio
     * @param resourceBundle Conjunto de recursos disponibles
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boolean ret = true;       
        String [] error = { "" };
        // Recuperar el objeto de la factoria
        app_browser_java = Factory_app_browser_java.getApp_browser_java();
        WebEngine webEngine = webView.getEngine();
        ret = poner_escuchador_de_url(error);
        if (ret) {
            String texto = app_browser_java.iniciar_contenido(this.getClass(), error);
            if (texto != null) {
                webEngine.loadContent(texto);    
            } else {
                ret = false;
            }
        } 
        if (ret == false) {
            poner_error(error[0]);
        }
    }    
    /**
     * Pone el escuchador de cambio de URL en el objeto webView
     * @param error Mensaje de error en la posición 0.
     * @return true si todo es correcto, false si hay algún error.
     */
    public boolean poner_escuchador_de_url(String [] error)
    {
        boolean ret = true;
        WebEngine webEngine = this.webView.getEngine();
        ReadOnlyStringProperty readOnlyStringProperty = webEngine.locationProperty();
//        Examen_banderas indexControladorChangeListener
//                = new Examen_banderas();
        app_browser_java.setfXMLDocumentController(this);
        readOnlyStringProperty.addListener(app_browser_java);
        return ret;
    }

    public boolean poner_error(String mensaje) {
        String [] error = { "" };
        return cargar_contenido(mensaje, "text/html", error);
    }
    
    public boolean cargar_contenido(String contenido, String tipo_contenido, String [] error) {
        boolean ret = true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
               WebEngine webEngine =  webView.getEngine();
               webEngine.loadContent(contenido, tipo_contenido);
            }
            
        });
        return ret;
    }
    
}
