package com.imd.telemaco.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imd.telemaco.business.ValidateEpisodeServices;
import com.imd.telemaco.business.exception.EpisodeExistsException;
import com.imd.telemaco.business.exception.EpisodeInvalidException;
import com.imd.telemaco.data.SeasonDAO;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.entity.Episode;
import com.imd.telemaco.entity.Season;
import com.imd.telemaco.entity.Serie;

public class RegisterEpisode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Episode episode = new Episode();
		
		try {
			String serieName = request.getParameter("serieName");
			String seasonNumber = request.getParameter("season");
			String name = request.getParameter("epName");
			String number = request.getParameter("epNumber");
			String synopsis = request.getParameter("epSynopsis");
			String time = request.getParameter("epTime");
			
			int seasonNumberInt = Integer.parseInt(seasonNumber);
			int numberInt = Integer.parseInt(number);
			int timeInt = Integer.parseInt(time);
			
			SeasonDAO seasonDAO = new SeasonDAO();
			SerieDAO serieDAO = new SerieDAO();
			Serie serie = serieDAO.select(serieName);
			
			Season season = seasonDAO.select(seasonNumberInt, serie.getId());
			
			episode = new Episode (name, numberInt, timeInt, synopsis, season.getId());
		} catch (SQLException e) {
			response.sendRedirect("...");
		}		
		
		try {
			ValidateEpisodeServices validate = new ValidateEpisodeServices();
			validate.validEpisodeInsert(episode);
		} catch (EpisodeInvalidException e) {
			e.printStackTrace();
		} catch (EpisodeExistsException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) {
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
	
	public String getServletInfo () {
		return "Short description";
	}
}
