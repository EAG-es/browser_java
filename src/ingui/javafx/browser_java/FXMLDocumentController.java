/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.javafx.browser_java;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://www.iespuertodelaCruz.es");    
    }       

}
