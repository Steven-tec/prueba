package com.steven.prueba.prueba;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;

//anotacion para mapear este servlet a la url conexion /Servlet"
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Método para verificar si un número es primo
    private boolean EsPrimo(int numero) {
        if (numero <= 1) return false;// el 0 y el 1 no van a ser primos

        // vericamos si el numero tiene divisores tal cual seria 1 y si mismo
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false; // no es primo
        }
        return true;//y en caso de si serlo retornaria el numero primo en la pagina
    }

    // Método que maneja solicitudes POST (cuando se envía el formulario)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //obtiene los parametros de dicho formulario como enteros
        int inicio = Integer.parseInt(request.getParameter("inicio"));
        int fin = Integer.parseInt(request.getParameter("fin"));


        //generamos un array o una lista tambien llamado para guardar los numeros q sean primos
        ArrayList<Integer> primos = new ArrayList<>();


        // Recorre el rango dado es decir el numero inicial y el numero final
        for (int i = inicio; i <= fin; i++) {
            if (EsPrimo(i)) { //en este caso se toma el metodo ya creado y lo verifica
                primos.add(i);// en caso de q el metodo determine q es primo se agrega a la lista
            }
        }

        // preparamos la respuesta en el html
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Resultados</title></head><body>");
        out.println("<h2>Resultados para el rango: " + inicio + " - " + fin + "</h2>");

        //si no encontramos primos se mandara este mensaje
        if (primos.isEmpty()) {
            out.println("<p>No se encontraron números primos en este rango.</p>");
        } else {
            // en tal caso q si si se encuentran numeros primos
            out.println("<p>Números primos encontrados:</p><ul>");
            for (int primo : primos) {
                out.println("<li>" + primo + "</li>");
            }
            out.println("</ul>");
            out.println("<p>Total de números primos encontrados: <strong>" + primos.size() + "</strong></p>");
        }
        // enlace para volver al formulario como tal
        out.println("<br><a href='index.html'>Volver</a>");
        out.println("</body></html>");
    }
}
