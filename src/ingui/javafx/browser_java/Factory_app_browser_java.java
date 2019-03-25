/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.javafx.browser_java;

/**
 * Clase de fabricación de objetos App_broser_java
 * @author daw
 * @since 25/marzo/2019 
 */
public class Factory_app_browser_java {
    /**
     * Atributo que  hace refenencia al elemento producido por el {@literal <factory>}
     */
    public static App_browser_java app_browser_java = null;

    public static App_browser_java getApp_browser_java() {
        return app_browser_java;
    }

    public static void setApp_browser_java(App_browser_java app_browser_java) {
        Factory_app_browser_java.app_browser_java = app_browser_java;
    }
    
    
}
