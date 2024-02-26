/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.wilczynskimikolaj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.wilczynskimikolaj.model.DatabaseHandler;
import pl.polsl.wilczynskimikolaj.model.Operation;

/**
 * Class responsible for displaying history. 
 * 
 * @author Mikołaj
 * @version 1.0
 */
@WebServlet(name = "History", urlPatterns = {"/History"})
public class History extends HttpServlet {

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

        // Final 5 HISTORY
        DatabaseHandler dbHandler = (DatabaseHandler) getServletContext().getAttribute("DbHandler");
        List<Operation> operations = dbHandler.getAllOperations();

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet History</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Type of Change</th>");
            out.println("<th>Changed Text</th>");
            out.println("<th>Original Text</th>");
            out.println("<th>Key</th>");
            out.println("</tr>");

            for (Operation element : operations) {
                out.println("<tr>");
                out.println("<td>" + element.getTypeOfChange() + "</td>");
                out.println("<td>" + element.getChangedText() + "</td>");
                out.println("<td>" + element.getOriginalText() + "</td>");
                out.println("<td>" + element.getKey() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");
        }

        // FINAL 4 HISTORY
//        ArrayList<ArrayList<String>> history = new ArrayList<>();
//
//        String TypeOfOperation = (String) request.getAttribute("Type");
//        String OriginalText = (String) request.getAttribute("Original text");
//        String Key = (String) request.getAttribute("Key");
//        String Text = (String) request.getAttribute("Text");
//
//        ArrayList<String> Element = new ArrayList<>();
//        Element.add(TypeOfOperation);
//        Element.add(OriginalText);
//        Element.add(Key);
//        Element.add(Text);
//        history.add(Element);
//
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet History</title>");
//            out.println("</head>");
//            out.println("<body>");
//            for (ArrayList<String> element : history) {
//                for (String value : element) {
//                    out.println(value);
//                }
//            }
//            out.println("</body>");
//            out.println("</html>");
//        }
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
