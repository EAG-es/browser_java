package ingui.javafx.browser_java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    public void initialize(URL url, ResourceBundle rb) {
        boolean ret = true;
        String [] error = { "" };
        WebEngine webEngine = webView.getEngine();
        ret = poner_escuchador_de_url(error);
        String archivo = "/ingui/html/browser_java/recursos/index.html";
        String texto = null;
        texto = leer_archivo_texto(archivo, error);
        if (texto != null) {
            webEngine.loadContent(texto);    
        }
    }       
   
    public String leer_archivo_texto(String nombre, String [] error)
    {
        // Leer bytes: InputStream / OutputStream
        // Leer caracteres: Reader / Writer
        String resultado = "";
        String texto = null;
        try {
            Class clase = this.getClass();
            InputStream inputStream = clase.getResourceAsStream(nombre);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {
                texto = bufferedReader.readLine();
                if (texto == null) {
                    break;
                }
                resultado = resultado + texto;
            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = "";
            }
            error[0] = "Error en leer_archivo_texto. " + error[0];
            resultado = null;
        }
        return resultado;
    }
    
    public boolean poner_escuchador_de_url(String [] error)
    {
        boolean ret = true;
        WebEngine webEngine = this.webView.getEngine();
        ReadOnlyStringProperty readOnlyStringProperty = webEngine.locationProperty();
        readOnlyStringProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                boolean ret = true;
                String [] error = { "" };
                String url = newValue;
                try {
                } catch (Exception e) {
                    error [0] = e.getMessage();
                    if (error[0] == null) {
                        error[0] = "";
                    }
                    error[0] = "Error al analizar el cambio de URL. ";
                    ret = false;
                }
//                if (ret == false) {
//                    poner_error(error[0]);
//                }
            }           
        });
        return ret;
    }
}
