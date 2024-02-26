/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.wilczynskimikolaj.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import pl.polsl.wilczynskimikolaj.model.DatabaseHandler;

/**
 * Servlet to initialize database connection once for the run of application.
 * 
 * @author Mikołaj
 * @version 1.0
 */
@WebListener
public class AppInitializer implements ServletContextListener {

    /**
     * Function to initialize database connection.
     * 
     * @param sce servlet context
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        DatabaseHandler dbHandler = new DatabaseHandler();

        dbHandler.connect();
        dbHandler.createTableIfNotExists();
        ctx.setAttribute("DbHandler", dbHandler);
    }

    /**
     * Function disconnect from database.
     * @param sce servlet context
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        DatabaseHandler dbHandler = (DatabaseHandler) ctx.getAttribute("DbHandler");

        if (dbHandler != null) {
            dbHandler.disconnect();
        }
    }
}
