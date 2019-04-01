/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.html.browser_java;

import innui.archivos.Archivos;
import innui.http.Url_operaciones;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author daw
 */
public class IndexControlador {
    public static int k_nota_un_punto = 1;
    public static double k_nota_penalizacion = 0.5;
    
    public static String cambiar_nombre_archivo(String archivo, String [] error)
    {
        String retorno = null;
        Locale locale = Locale.getDefault();
        String codigo_idioma = locale.getLanguage();
        File file = new File (archivo);
        String path = file.getParent();
        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos >= 0) {
            name = name.substring(0, pos) + "_" + codigo_idioma + name.substring(pos);
        } else {
            name = name + "_" + codigo_idioma;
        }
        String nuevo_archivo = path + File.separatorChar + name;
        URL url = IndexControlador.class.getResource(nuevo_archivo);
        if (url == null) {
            url = IndexControlador.class.getResource(archivo);
            if (url != null) {
                retorno = archivo;
            } else {
                error[0] = "No existen los archivos que cambiar. ";
            }
        } else {
            retorno = nuevo_archivo;
        }
        return retorno;
    }
       
    public static String procesar(String url_texto, String[] error) {
        String retorno = null;
        boolean ret = true;
        String respuesta;
        String solucion;
        double punto = 0.0;
        String comentario = ""; //NOI18N
        String calificacion;
        int preguntas_num = -1;
        String preguntas_num_texto;
        try {
            Map<String, String> query_mapa = new HashMap();
            URL url = new URL(url_texto);
            ret = Url_operaciones.extraer_parametros_query(url, query_mapa, error);
            preguntas_num_texto = query_mapa.get("preguntas_num"); //NOI18N
            if (preguntas_num_texto != null) { 
                preguntas_num = Integer.valueOf(
                        preguntas_num_texto);
            }            
            int i = 1;
            int j = 1;
            boolean sin_respuesta = true;
            while (true) {
                sin_respuesta = true;
                while (true) {
                    if (j > 2) {
                        break;
                    }
                    respuesta = query_mapa.get("input_pregunta_" + i + "_" + j); //NOI18N
                    if (respuesta != null) {
                        respuesta = respuesta.trim();
                        respuesta = respuesta.replaceAll("\\s\\s+", " "); //NOI18N
                        respuesta = respuesta.toLowerCase();
                        solucion = query_mapa.get("input_solucion_" + i + "_" + j); //NOI18N
                        solucion = solucion.trim();
                        solucion = solucion.replaceAll("\\s+", " "); //NOI18N
                        solucion = solucion.toLowerCase();
                        if (solucion.equals(respuesta)) {
                            punto = punto + k_nota_un_punto;
                        } else {
                            punto = punto - k_nota_penalizacion;
                        }
                        sin_respuesta = false;
                    }
                    j = j + 1;
                }
                if (preguntas_num == -1) {
                    if (sin_respuesta) {
                        break;
                    }
                } else {
                    if (i > preguntas_num) {
                        break;
                    }
                }
                j = 1;
                i = i + 1;
            }
            if (punto <= 0) {
                calificacion = "<span style='color:red'>" + punto + "</span> "; //NOI18N
                comentario = java.util.ResourceBundle.getBundle("ingui/html/browser_java/recursos/int").getString("<SPAN STYLE='COLOR:PURPLE'>TÚ CONTRIBUYES A TU FUTURO. PERO... PUEDE SER QUE NO ESTÉ AQUÍ, EL CAMINO QUE TE LLEVA A ÉL.</SPAN> ");
            } else {
                calificacion = "<span style='color:green'>" + punto + "</span> "; //NOI18N
                comentario = java.util.ResourceBundle.getBundle("ingui/html/browser_java/recursos/int").getString("<SPAN STYLE='COLOR:OLIVE'>VAS POR BUEN CAMINO. TODO ESFUERZO QUE HAGAS SERÁ EN TU PROPIO BENEFICIO.</SPAN> ");
            }
            String archivo_html = "/ingui/html/browser_java/recursos/calificacion.html";
            archivo_html = cambiar_nombre_archivo(archivo_html, error);
            if (archivo_html != null) {
                String archivo = Archivos.leer_archivo_texto(
                        archivo_html , error); //NOI18N
                if (archivo != null) {
                    archivo = archivo.replace("${nota}", calificacion); //NOI18N
                    archivo = archivo.replace("${comentario}", comentario); //NOI18N
                    retorno = archivo;
                }
            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ingui/html/browser_java/recursos/int").getString("ERROR EN PROCESAR. {0}"), new Object[] {error[0]});
            ret = false;
            retorno = null;
        }
        return retorno;
    }
}
