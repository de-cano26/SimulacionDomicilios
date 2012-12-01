package com.deloeste.deliveries.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deloeste.deliveries.data.DataManager;
import com.deloeste.deliveries.dto.GsonDelivery;

public class DataServlet extends HttpServlet
{
    private static final long serialVersionUID = -8163160721777677381L;

    private static final String GENERATE_DATA = "generate";

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        procesar( request, response );
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        procesar( request, response );
    }

    public void procesar( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // response.setContentType( "text/plain" );
        //response.setContentType( "application/json" );
        PrintWriter out = response.getWriter( );

        String method = request.getParameter( "method" );
        System.out.println( "Metodo por ver" );
        if( method.equals( GENERATE_DATA ) )
        {
            DataManager dm = new DataManager( );
            List<GsonDelivery> deliveries = dm.generateDeliveries( );
            String json = "[";
            for( GsonDelivery delivery : deliveries )
            {
                json += delivery.toString( ) + ",";
            }
            json = json.substring( 0, json.length( ) - 1 );
            json += "]";
            // Gson gson = new Gson( );
            // Object[] ans = resp.toArray( );
            // String json = gson.toJson( ans );
            out.println( json );
        }

        out.close( );
    }
}
