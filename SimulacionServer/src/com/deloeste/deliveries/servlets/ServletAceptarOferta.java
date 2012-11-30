package com.deloeste.deliveries.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deloeste.deliveries.data.TruequeAndes;

public class ServletAceptarOferta extends ServletTemplate
{
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * serial
     */
    private static final long serialVersionUID = 1L;

    /**
     * Escribe el contenido de la página
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    public void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        // Saca el Printer
        PrintWriter respuesta = response.getWriter( );
        try
        {
            // Creamos objeto de oferta.
            String oferta = request.getParameter( "idOferta" );
            // Mundo
            TruequeAndes mundo = TruequeAndes.darInstancia( );
            System.out.println( "Oferta a aceptar: " + oferta );
            mundo.aceptarOferta( oferta );

            // Cambiar los parámetros para imprimir la información deseada
            respuesta.write( "<body>\r\n" );
            respuesta.write( "<div id=\"wrapper\" class=\"container_12\">   \r\n" );
            respuesta.write( "\r\n" );
            respuesta.write( "<header id=\"header\" class=\"grid_12\"> \r\n" );
            respuesta.write( "      <a href=\"index.html\"><img src=\"img/header.jpg\" width=\"948\" height=\"255\" alt=\"header\"></a>\r\n" );
            respuesta.write( "</header> <!-- end header -->\r\n" );
            respuesta.write( "    \r\n" );
            respuesta.write( "<div id=\"content\"><!-- Show a \"Please Upgrade\" box to both IE7 and IE6 users (Edit to IE 6 if you just want to show it to IE6 users) - jQuery will load the content from js/ie.html into the div -->       \r\n" );
            respuesta.write( "        <!--[if lte IE 7]>\r\n" );
            respuesta.write( "          <div class=\"ie grid_7\"></div>\r\n" );
            respuesta.write( "        <![endif]-->  \r\n" );
            respuesta.write( "  <div id=\"main\" class=\"grid_8 alpha\">\r\n" );
            respuesta.write( "       <article class=\"post\">\r\n" );
            imprimirMensajeOk( respuesta, "La oferta ha sido aceptada correctamente" );
            respuesta.write( "    </article><!-- end post 1 --></form>  \r\n" );
            respuesta.write( "  </div> <!-- end main -->\r\n" );
            respuesta.write( "    \r\n" );
            imprimirContenidoConstante( respuesta );
        }
        catch( Exception e )
        {
            // Imprime el mensaje de la excepción
            respuesta.write( "<body>\r\n" );
            respuesta.write( "<div id=\"wrapper\" class=\"container_12\">   \r\n" );
            respuesta.write( "\r\n" );
            respuesta.write( "<header id=\"header\" class=\"grid_12\"> \r\n" );
            respuesta.write( "      <a href=\"index.html\"><img src=\"img/header.jpg\" width=\"948\" height=\"255\" alt=\"header\"></a>\r\n" );
            respuesta.write( "</header> <!-- end header -->\r\n" );
            respuesta.write( "    \r\n" );
            respuesta.write( "<div id=\"content\"><!-- Show a \"Please Upgrade\" box to both IE7 and IE6 users (Edit to IE 6 if you just want to show it to IE6 users) - jQuery will load the content from js/ie.html into the div -->       \r\n" );
            respuesta.write( "        <!--[if lte IE 7]>\r\n" );
            respuesta.write( "          <div class=\"ie grid_7\"></div>\r\n" );
            respuesta.write( "        <![endif]-->  \r\n" );
            respuesta.write( "  <div id=\"main\" class=\"grid_8 alpha\">\r\n" );
            respuesta.write( "       <article class=\"post\">\r\n" );
            imprimirMensajeError( respuesta, e );
            respuesta.write( "    </article><!-- end post 1 --></form>  \r\n" );
            respuesta.write( "  </div> <!-- end main -->\r\n" );
            respuesta.write( "    \r\n" );
            imprimirContenidoConstante( respuesta );
        }
    }

    public void imprimirContenidoConstante( PrintWriter respuesta )
    {
        respuesta.write( "  <div id=\"sidebar\" class=\"grid_4 omega\">\r\n" );
        respuesta.write( "        <form method=\"post\"><aside class=\"widget\">\r\n" );
        respuesta.write( "            <h3>Opciones</h3>          \r\n" );
        respuesta.write( "            <ul><li><input type=\"submit\" formaction=\"servletMain.htm\" value=\"Perfil\"></li>\r\n" );
        respuesta.write( "                <li><input type=\"submit\" formaction=\"servletZonaOfertas.htm\" value=\"Ofertas\"></li>\r\n" );
        respuesta.write( "                <li><input type=\"submit\" formaction=\"servletZonaProductos.htm\" value=\"Productos\"></li>\r\n" );
        respuesta.write( "                <li><input type=\"submit\" formaction=\"servletZonaBusquedas.htm\" value=\"Busquedas\"></li>\r\n" );
        respuesta.write( "                <li><input type=\"submit\" formaction=\"servletZonaCerrarSesion.htm\" value=\"Cerrar Sesion\"></li></ul>\r\n" );
        respuesta.write( "        </aside> <!-- end widget --></form> \r\n" );
        respuesta.write( "        <aside class=\"widget\">\r\n" );
        respuesta.write( "        <form method\"post\">\r\n" );
        respuesta.write( "             <h3>Opciones de ofertas</h3>\r\n" );
        respuesta.write( "          <p>Seleccione el tipo de ofertas que desea visualizar</p><p>\r\n" );
        respuesta.write( "                 <input type=\"submit\" value=\"Ver solo ofertados\" formaction=\"\"/>\r\n" );
        respuesta.write( "        </p>\r\n" );
        respuesta.write( "          <p>\r\n" );
        respuesta.write( "            <input type=\"submit\" value=\"Ver solo demandados\" formaction=\"\"/>\r\n" );
        respuesta.write( "          </p>\r\n" );
        respuesta.write( "        </form> \r\n" );
        respuesta.write( "        </aside> <!-- end widget -->\r\n" );
        respuesta.write( "        <aside class=\"widget\">\r\n" );
        respuesta.write( "            <form action=\"\">\r\n" );
        respuesta.write( "             <h3>B&uacute;squeda R&aacute;pida</h3>\r\n" );
        respuesta.write( "               <p>Introduzca una palabra clave para realizar un busqueda r&aacute;pida.</p>\r\n" );
        respuesta.write( "                <input type=\"search\" results=\"10\" placeholder=\"Search...\" />\r\n" );
        respuesta.write( "                <input type=\"submit\" value=\"Search...\" />\r\n" );
        respuesta.write( "            </form>       \r\n" );
        respuesta.write( "           </aside> <!-- end widget -->\r\n" );
        respuesta.write( "  </div> <!-- end sidebar -->\r\n" );
        respuesta.write( "</div> <!-- end content -->\r\n" );
    }
}
