package com.deloeste.deliveries.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataServlet extends HttpServlet
{

    // TODO To initialize send locals to simulate

    // TODO To run simulation, send deliveries data and time span

    @Override
    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    /*
    * Get the value of parameters
    */
    String name = request.getParameter("name");

    /*
    * Set the content type(MIME Type) of the response.
    */
    response.setContentType("text/plain");
    response.setContentType("application/json");
     
    PrintWriter out = response.getWriter();
    /*
    * Write the JSON answers
    */
    out.println("<html>");
    out.println("<head>");
    out.println("<title> A very simple servlet example</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<a href=Click here to go back to input page "+"</a>");
    out.println("</body>");
    out.println("</html>");
    out.close();
     
    }
    public void destroy( )
    {

    }
}
