package com.imd.telemaco.presentation;

import java.io.IOException;


import java.io.PrintWriter;
<<<<<<< HEAD
=======
import java.sql.SQLException;
import java.util.ArrayList;
>>>>>>> shirley
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imd.telemaco.business.ValidateEpisodeServices;
import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.business.exception.EpisodeExistsException;
import com.imd.telemaco.business.exception.EpisodeInvalidException;
import com.imd.telemaco.data.SeasonDAO;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.entity.Episode;
import com.imd.telemaco.entity.Season;
import com.imd.telemaco.entity.Serie;
import com.imd.telemaco.entity.User;

public class RegisterEpisode extends HttpServlet {
<<<<<<< HEAD
=======
	private static final long serialVersionUID = 1L;
	ArrayList<Serie> series = new ArrayList<>();
	ArrayList<Season> seasons = new ArrayList<>();
	
	public RegisterEpisode () { }
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF8");
		PrintWriter out = response.getWriter();		
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("logged");
		
		try {
			Episode episode = new Episode();
			String serieName    = request.getParameter("serieName");
			String seasonNumber = request.getParameter("seasonNumber");
			String name         = request.getParameter("epName");
			String number       = request.getParameter("epNumber");
			String synopsis     = request.getParameter("epSynopsis");
			String time         = request.getParameter("epTime");
			
			SerieDAO serieDAO = new SerieDAO();
			/*ArrayList<Serie> series = serieDAO.selectAllSeries();
			HttpSession session = request.getSession(true);
            session.setAttribute("series", series);
            session = request.getSession(false);
            response.sendRedirect("RegisterEpisode.jsp");*/
			Serie serie = serieDAO.select(serieName);
			
            /*SeasonDAO seasonDAO = new SeasonDAO();
            seasons = seasonDAO.selectAllSeasons(serie.getId());
            session = request.getSession(true);
            session.setAttribute("seasons", seasons);
            response.sendRedirect("RegisterEpisode.jsp");
            FIXME fazer com que o usuário selecione uma season a partir de uma serie
            */
            
            int seasonNumberInt = Integer.parseInt(seasonNumber);
			int numberInt = Integer.parseInt(number);
			int timeInt = Integer.parseInt(time);
            
			SeasonDAO seasonDAO = new SeasonDAO();
            Season season = seasonDAO.select(seasonNumberInt, serie.getId());
            
            episode = new Episode (name, numberInt, timeInt, synopsis, season.getId());

    		try {
    			ValidateEpisodeServices validate = new ValidateEpisodeServices();
    			validate.validEpisodeInsert(episode);
    			response.sendRedirect("Logged.jsp");
    			
    		} catch (EpisodeInvalidException e) {
    			e.printStackTrace();
    		} catch (EpisodeExistsException e) {
    			e.printStackTrace();
    		}
		} catch (SQLException e) { //FIXME
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
>>>>>>> shirley

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Episode episode = new Episode();

        String serieName = request.getParameter("serieName");
        String seasonNumber = request.getParameter("season");
        String name = request.getParameter("epName");
        String number = request.getParameter("epNumber");
        String synopsis = request.getParameter("epSynopsis");
        String time = request.getParameter("epTime");
        int seasonNumberInt = Integer.parseInt(seasonNumber);
        int numberInt = Integer.parseInt(number);
        int timeInt = Integer.parseInt(time);
        
        try {
            SeasonDAO seasonDAO = new SeasonDAO();
            SerieDAO serieDAO = new SerieDAO();
            Serie serie = serieDAO.select(serieName);
            Season season = seasonDAO.select(seasonNumberInt, serie.getId());
            episode = new Episode(name, numberInt, timeInt, synopsis, season.getId());
        
            ValidateEpisodeServices validate = new ValidateEpisodeServices();
            validate.validEpisodeInsert(episode);
        } catch (EpisodeInvalidException | EpisodeExistsException | DatabaseException | CloseConnectionException e) {
            response.sendRedirect("Error.jsp");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (IOException e) {
            Logger.getLogger(RegisterEpisode.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
