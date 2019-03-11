package ingui.javafx.browser_java;

import ingui.html.browser_java.IndexControlador;
import ingui.html.browser_java.IndexControladorChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author daw
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private WebView webView;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boolean ret = true;
        String [] error = { "" };
        WebEngine webEngine = webView.getEngine();
        ret = poner_escuchador_de_url(error);
        String texto = IndexControlador.iniciar_contenido(this.getClass(), error);
        if (texto != null) {
            webEngine.loadContent(texto);    
        } else {
            poner_error(error[0]);
        }
    }    
    
    public boolean poner_escuchador_de_url(String [] error)
    {
        boolean ret = true;
        WebEngine webEngine = this.webView.getEngine();
        ReadOnlyStringProperty readOnlyStringProperty = webEngine.locationProperty();
        IndexControladorChangeListener indexControladorChangeListener
                = new IndexControladorChangeListener();
        indexControladorChangeListener.fXMLDocumentController = this;
        readOnlyStringProperty.addListener(indexControladorChangeListener);
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
