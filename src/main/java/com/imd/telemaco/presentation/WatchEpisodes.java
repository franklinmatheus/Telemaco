package com.imd.telemaco.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.data.EpisodeDAO;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.data.UserEpisodeDAO;
import com.imd.telemaco.entity.Episode;
import com.imd.telemaco.entity.Season;
import com.imd.telemaco.entity.Serie;
import com.imd.telemaco.entity.User;
import com.imd.telemaco.entity.UserEpisode;

public class WatchEpisodes extends HttpServlet {
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF8");
        
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("logged");
        
        try {
            SerieDAO dao = new SerieDAO();
            ArrayList<Serie> series = dao.selectAllSeries();
            for (Serie serie : series) {
            	ArrayList<Season> seasons = serie.getSeasons();
            	if (seasons == null) break;
            	for (Season season : seasons) {
            		ArrayList<Episode> episodes = season.getEpisodes();
            		if (episodes == null) break;
            		for (Episode ep : episodes) {
            			boolean epWasSeen = request.getParameter(ep.getName()) != null;            			

        				UserEpisodeDAO ueDAO = new UserEpisodeDAO();
            			ArrayList<Episode> episodesSeen = ueDAO.selectAllEpisodes(user.getId());
            			
            			if (epWasSeen && !existEpisode(episodesSeen, ep)) {
//            				System.out.println(user);
//            				System.out.println(user.getId()); // ERROR is here
//            				System.out.println(ep.getId());

            				UserEpisode userEpisode = new UserEpisode(user.getId(), ep.getId());
            				
            				ueDAO.insert(userEpisode);
            			}
            			
            			if (!epWasSeen && existEpisode(episodesSeen, ep)) {
            				UserEpisode userEpisode = new UserEpisode(user.getId(), ep.getId());
            				ueDAO.delete(userEpisode);
            			}
            		}
            	}
            }
            
            request.setAttribute("mensagem", "Episódios assistidos cadastrados com sucesso!"); // FIXME 
            response.sendRedirect("SelectAllSeries");
        } catch (DatabaseException | CloseConnectionException e) {
        	e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }
    
    private boolean existEpisode (ArrayList<Episode> episodes, Episode ep) {
    	for (Episode e : episodes) {
    		if (e.compareTo(ep) == 0) 
    			return true;
    	}
    	return false;
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