/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.wilczynskimikolaj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.polsl.wilczynskimikolaj.model.DatabaseHandler;
import pl.polsl.wilczynskimikolaj.model.KeyGenerationException;
import pl.polsl.wilczynskimikolaj.model.Model;
import pl.polsl.wilczynskimikolaj.model.Operation;

/**
 * Decoding servlet.
 * 
 * @author Mikołaj
 * @version 1.0
 */
@WebServlet(name = "DecodeServlet", urlPatterns = {"/DecodeServlet"})
public class DecodeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String key = request.getParameter("key");
        String text = request.getParameter("text");

        if (key == null || key.isEmpty() || text == null || text.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid key or text provided");
            return;
        }

        Model model = new Model();
        String decodedText = "";
        try {
            decodedText = model.decode(text, key);
            Operation operation = new Operation("Decode", decodedText, text, key);
            DatabaseHandler dbHandler = (DatabaseHandler) getServletContext().getAttribute("DbHandler");
            dbHandler.insertOperation(operation);
        } catch (KeyGenerationException ex) {;
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during decoding");
            return;
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EncodeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Result: " + decodedText + "</h1>");
            int count = 0;
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("visitCount")) {
                        count = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
            }

            count++;
            out.println("Number of times you Decoded:" + String.valueOf(count));
            out.println("</body>");
            out.println("</html>");

            Cookie visitCookie = new Cookie("visitCount", String.valueOf(count));
            response.addCookie(visitCookie);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
