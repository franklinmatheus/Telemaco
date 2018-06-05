package com.imd.telemaco.presentation;

import com.imd.telemaco.business.ValidateSerieServices;
import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.business.exception.SerieExistsException;
import com.imd.telemaco.business.exception.SerieInvalidException;
import com.imd.telemaco.entity.Serie;
import com.imd.telemaco.entity.enums.Classification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.postgresql.util.MD5Digest;

/**
 *
 * @author valmir
 * @author Shirley Ohara (shirleyohara@ufrn.edu.br)
 */
public class RegisterSerie extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();

        try {
            Serie serie = new Serie();

            String name = request.getParameter("name");
            String year = request.getParameter("year");
            String status = request.getParameter("status");
            String creator = request.getParameter("creator");
            String classif = request.getParameter("classification");
            String genre = request.getParameter("genre");
            String synopsis = request.getParameter("synopsis");
            String imageName = request.getParameter("image");
            int yearInt = Integer.parseInt(year);
            Classification classification = serie.stringToClassif(classif);
            
            serie = new Serie(name, yearInt, status, creator, classification, genre, synopsis, imageName.toLowerCase());

            if ((name == null || name.isEmpty())) {
                response.sendRedirect("RegisterSerie.jsp");
            } else {
                HttpSession session = request.getSession();
                session.getAttribute("logged");
               
                ValidateSerieServices validate = new ValidateSerieServices();
                validate.validSerieInsert(serie);
                //TODO mensagem que foi insedira com sucesso
                response.sendRedirect("Logged.jsp");
            }
        } catch (SerieExistsException | SerieInvalidException | DatabaseException | CloseConnectionException e) {
            e.getMessage();
            response.sendRedirect("Error.jsp");
        } finally {
            out.close();
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
