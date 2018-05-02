/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.presentation;

import com.imd.telemaco.business.ValidateSerieServices;
import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.data.UserEpisodeDAO;
import com.imd.telemaco.entity.Episode;
import com.imd.telemaco.entity.Rating;
import com.imd.telemaco.entity.Serie;
import com.imd.telemaco.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author franklin
 */
public class SelectSerie extends HttpServlet {

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
        try {
            HttpSession session = request.getSession(true);
            int id = Integer.parseInt(request.getParameter("id"));

            SerieDAO dao = SerieDAO.getInstance();
            Serie serie = dao.select(id);

            ValidateSerieServices validate = new ValidateSerieServices();
            ArrayList<Rating> ratings = validate.getRatings(id);
            
            User user = (User) (session.getAttribute("logged"));
            UserEpisodeDAO ueDAO = new UserEpisodeDAO();

            ArrayList<Episode> episodesSeen = ueDAO.selectAllEpisodes(user.getId());
            session.setAttribute("episodesSeen", episodesSeen);
            
            session.setAttribute("serie", serie);
            session.setAttribute("ratings", ratings);
            response.sendRedirect("Serie.jsp");

        } catch (DatabaseException | CloseConnectionException e) {
            response.sendRedirect("Error.jsp");
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
