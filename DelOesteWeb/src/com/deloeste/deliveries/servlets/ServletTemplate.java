/**
 * 
 */
package com.deloeste.deliveries.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Abstracto principal con el template del diseño de la página
 */
public abstract class ServletTemplate extends HttpServlet
{
    /**
     * SerialVersion
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Maneja un pedido GET de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        procesarPedido( request, response );
    }

    /**
     * Maneja un pedido POST de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        procesarPedido( request, response );
    }

    /**
     * Procesa el pedido de igual manera para todos
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    private void procesarPedido( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        //
        // Comienza con el Header del template y los Estilos
        imprimirEstilos( request, response );
        //
        // Escribe el contenido de la página
        escribirContenido( request, response );
        //
        // Termina con el footer del template
        imprimirFooter( response );

    }

    /**
     * Imprime el Header del diseño de la página
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción al imprimir en el resultado
     */
    private void imprimirEstilos( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        //
        // Saca el printer de la repuesta
        PrintWriter respuesta = response.getWriter( );

        // Imprime los estilos
        respuesta.write( "<!DOCTYPE html>\r\n" );
        respuesta.write( "<html lang=\"es\">\r\n" );
        respuesta.write( "<head>\r\n" );
        respuesta.write( "    <meta charset=\"iso-8859-1\" />\r\n" );
        respuesta.write( "    <!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame -->\r\n" );
        respuesta.write( "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />   \r\n" );
        respuesta.write( "    <title>Trueque Alpes</title>    \r\n" );
        respuesta.write( "    <meta name=\"description\" content=\"\" />\r\n" );
        respuesta.write( "    <link rel=\"shortcut icon\" href=\"favicon.ico\">" );
        respuesta.write( "<!--                                     -->\r\n" );
        respuesta.write( "<!-- CSS Styles                          --> \r\n" );

        respuesta.write( "<!--                                     -->\r\n" );
        respuesta.write( "    <link href=\"css/960.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n" );
        respuesta.write( "    <link href=\"css/mainStyle.css\" rel=\"stylesheet\" type=\"text/css\" />   \r\n" );
        respuesta.write( "<link href=\"SpryAssets/SpryValidationTextField.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" );
        respuesta.write( "<link href=\"SpryAssets/SpryValidationPassword.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" );
        respuesta.write( "<link href=\"SpryAssets/SpryValidationConfirm.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" );
        //TODO CSS faltante
        respuesta.write( "<!--                                     -->\r\n" );
        respuesta.write( "<!-- Scripts                           --> \r\n" );
        respuesta.write( "<!--                                     -->\r\n" );
        respuesta.write( "<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js\"></script>\r\n" );
        respuesta.write( "<script type=\"text/javascript\" src=\"scripts/utilidades.js\"></script>   \r\n" );
        respuesta.write( "<script type=\"text/javascript\" src=\"scripts/indexScripts.js\"></script>\r\n" );
        respuesta.write( "<script type=\"text/javascript\" src=\"SpryAssets/SpryValidationTextField.js\"></script>   \r\n" );
        respuesta.write( "    <!--[if lt IE 9]>\r\n" );
        respuesta.write( "<script src=\"SpryAssets/SpryValidationPassword.js\" type=\"text/javascript\"></script>\r\n" );
        respuesta.write( "<script src=\"SpryAssets/SpryValidationConfirm.js\" type=\"text/javascript\"></script>\r\n" );
        respuesta.write( "      <script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\" type=\"text/javascript\"></script>\r\n" );
        respuesta.write( "    <![endif]-->\r\n" );
        respuesta.write( "</head>\r\n" );
    }
    /**
     * Imprime el Footer del diseño de la página
     * @param response Respuesta
     * @throws IOException Excepción al escribir en la respuesta
     */
    private void imprimirFooter( HttpServletResponse response ) throws IOException
    {
        //
        // Saca el writer de la respuesta
        PrintWriter respuesta = response.getWriter( );
        //
        // Imprime el footer
        respuesta.write( "<footer id=\"footer\" class=\"grid_12\">\r\n" );
        respuesta.write( " <!-- You're free to remove the credit link in the footer, but please, please leave it there. -->\r\n" );
        respuesta
                .write( "<p><a rel=\"license\" href=\"http://creativecommons.org/licenses/by-nc-sa/3.0/\"><img alt=\"Creative Commons License\" style=\"border-width:0\" src=\"http://i.creativecommons.org/l/by-nc-sa/3.0/80x15.png\" /></a><a href=\"#\">Truque de los Alpes</a> - Design by <a href=\"about.html\" title=\"Sobre Trueque Alpes\">David Cano &amp; Juan Manuel Moreno</a></p>\r\n" );
        respuesta.write( "</footer> <!-- end footer -->    \r\n" );
        respuesta.write( "<div class=\"clear\"></div>\r\n" );
        respuesta.write( "</div> <!-- end wrapper -->\r\n" );
        respuesta.write( "</body>\r\n" );
        respuesta.write( "</html>\r\n" );

    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo Título del error
     * @param mensaje Mensaje del error
     */
    protected void imprimirMensajeError( PrintWriter respuesta, String mensaje )
    {
        // Mensaje error básico
        respuesta.write( "            <h2>Error</h2>\r\n" );
        respuesta.write( "            <p>" + mensaje + "</p>\r\n" );
    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param exception Excepción de error
     */
    protected void imprimirMensajeError( PrintWriter respuesta, Exception exception )
    {
        // Mensaje error con Excepcion

        respuesta.write( "            <h2>Error</h2>\r\n" );
        respuesta.write( "            <p>" + exception.getMessage( ) + "</p>\r\n" );

    }

    /**
     * Imprime un mensaje de éxito
     * @param respuesta Respuesta al cliente
     * @param titulo Título del mensaje
     * @param mensaje Contenido del mensaje
     */
    protected void imprimirMensajeOk( PrintWriter respuesta, String mensaje )
    {
        // Mensaje OK
        respuesta.write( "         <div class=\"contenidoBody\" id=\"divContenido\">\r\n" );
        respuesta.write( "  <h2>Proceso completado correctamente</h2>\r\n" );
    }

    // -----------------------------------------------------------------
    // Métodos Abstractos
    // -----------------------------------------------------------------

    /**
     * Escribe el contenido de la página
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepción de error al escribir la respuesta
     */
    public abstract void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException;

}