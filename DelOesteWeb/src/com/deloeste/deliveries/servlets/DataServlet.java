package com.deloeste.deliveries.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deloeste.deliveries.data.DataManager;
import com.deloeste.deliveries.dto.Delivery;
import com.deloeste.deliveries.dto.GsonDelivery;
import com.google.gson.Gson;

public class DataServlet extends HttpServlet
{
    private static final long serialVersionUID = -8163160721777677381L;
    
    private static final String GENERATE_DATA = "generate";

    private static final int MINUTE_MILLIS = 60000;
    private static final int HOUR_MILLIS = 3600000;

    private DataManager dm = new DataManager( );

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

    @SuppressWarnings("deprecation")
    public void procesar( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // response.setContentType( "text/plain" );
        response.setContentType( "application/json" );
        PrintWriter out = response.getWriter( );

        String method = request.getParameter( "method" );
        if( method.equals( GENERATE_DATA ) )
        {
            Date date1 = new Date( );
            Date date2 = new Date( date1.getYear( ), date1.getMonth( ) != 11 ? date1.getMonth( ) : 0, date1.getDay( ) );
            List<Delivery> deliveries = dm.generateDeliveries( date1, date2 );
            Gson gson = new Gson( );
            List<GsonDelivery> resp = new ArrayList<GsonDelivery>( );
            for( Delivery delivery : deliveries )
            {
                Date datetime = delivery.getDatetime( );
                int day = 1;
                switch( datetime.getDay( ) )
                {
                    case 5:
                        day = 2;
                        break;
                    case 6:
                        day = 3;
                        break;
                    case 0:
                        day = 4;
                        break;
                    default:
                        day = 1;
                        break;
                }
                int time = datetime.getHours( ) * HOUR_MILLIS + datetime.getMinutes( ) * MINUTE_MILLIS;
                resp.add( new GsonDelivery( day, time, delivery.getSector( ) ) );
            }
            Object[] ans = resp.toArray( );
            String json = gson.toJson( ans );
            out.println( json );
        }

        out.close( );
    }
    public void destroy( )
    {

    }
}
