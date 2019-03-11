/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.html.browser_java;

import innui.archivos.Archivos;
import innui.http.Url_operaciones;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author daw
 */
public class IndexControlador {
    public static int k_nota_un_punto = 1;
    public static double k_nota_penalizacion = 0.5;

    public static String iniciar_contenido(Class clase, String [] error)
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
        
    public static String procesar(String url_texto, String[] error) {
        String retorno = null;
        boolean ret = true;
        String respuesta;
        String solucion;
        double punto = 0.0;
        String comentario = "";
        String calificacion;
        int preguntas_num = -1;
        String preguntas_num_texto;
        try {
            Map<String, String> query_mapa = new HashMap();
            URL url = new URL(url_texto);
            ret = Url_operaciones.extraer_parametros_query(url, query_mapa, error);
            preguntas_num_texto = query_mapa.get("preguntas_num");
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
                    respuesta = query_mapa.get("input_pregunta_" + i + "_" + j);
                    if (respuesta != null) {
                        respuesta = respuesta.trim();
                        respuesta = respuesta.replaceAll("\\s\\s+", " ");
                        respuesta = respuesta.toLowerCase();
                        solucion = query_mapa.get("input_solucion_" + i + "_" + j);
                        solucion = solucion.trim();
                        solucion = solucion.replaceAll("\\s+", " ");
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
                calificacion = "<span style='color:red'>" + punto + "</span> ";
                comentario = "<span style='color:purple'>Tú contribuyes a tu futuro. Pero... puede ser que no esté aquí, el camino que te lleva a él.</span> ";
            } else {
                calificacion = "<span style='color:green'>" + punto + "</span> ";
                comentario = "<span style='color:olive'>Vas por buen camino. Todo esfuerzo que hagas será en tu propio beneficio.</span> ";
            }
            String archivo = Archivos.leer_archivo_texto(
                    "/ingui/html/browser_java/recursos/calificacion.html", error);
            if (archivo != null) {
                archivo = archivo.replace("${nota}", calificacion);
                archivo = archivo.replace("${comentario}", comentario);
                retorno = archivo;
            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = "";
            }
            error[0] = "Error en procesar. " + error[0];
            ret = false;
            retorno = null;
        }
        return retorno;
    }
}
