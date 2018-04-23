/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.presentation;

import com.imd.telemaco.business.ValidateSerieServices;
import com.imd.telemaco.business.exception.SerieExistsException;
import com.imd.telemaco.business.exception.SerieInvalidException;
import com.imd.telemaco.entity.Serie;
import com.imd.telemaco.entity.User;
import com.imd.telemaco.entity.enums.Classification;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author valmir
 * @author Shirley Ohara (shirleyohara@ufrn.edu.br)
 */
public class RegisterSerie extends HttpServlet {
    
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
        	
            String name     = request.getParameter("name");
            String year     = request.getParameter("year");
            String status   = request.getParameter("status");
            String creator  = request.getParameter("creator");
            String classif  = request.getParameter("classification");
            String genre    = request.getParameter("genre");
            String synopsis = request.getParameter("synopsis");
            String image 	= request.getParameter("image");
            int yearInt     = Integer.parseInt(year);
            Classification classification = serie.stringToClassif(classif);
            
            serie = new Serie (name, yearInt, status, creator, classification, genre, synopsis, image);
            
            if( ( name == null || name.isEmpty() ) ) {
                response.sendRedirect("RegisterSerie.jsp");
            } else {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("logged");
                
                try {
	                ValidateSerieServices validate = new ValidateSerieServices();
	                validate.validSerieRegister(serie);
                    //TODO mensagem que foi insedira com sucesso
                    response.sendRedirect("Logged.jsp");
                } catch (SerieExistsException e) {
                	response.sendRedirect("Error.jsp");
                } catch (SerieInvalidException e) {
                	response.sendRedirect("Error.jsp");
				}
            } 
        } catch(Exception e) {
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

