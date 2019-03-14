/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.javafx.browser_java;

/**
 *
 * @author daw
 */
public class Factory_app_browser_java {
    
    public static App_browser_java app_browser_java = null;

    public static App_browser_java getApp_browser_java() {
        return app_browser_java;
    }

    public static void setApp_browser_java(App_browser_java app_browser_java) {
        Factory_app_browser_java.app_browser_java = app_browser_java;
    }
    
    
}
