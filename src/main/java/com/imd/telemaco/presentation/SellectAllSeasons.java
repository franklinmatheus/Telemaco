package com.imd.telemaco.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.data.SeasonDAO;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.entity.Season;
import com.imd.telemaco.entity.Serie;
import com.imd.telemaco.entity.User;

public class SellectAllSeasons extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Season> seasons = new ArrayList<>();
	
	public SellectAllSeasons () { }
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("logged");
        
		try {
			String serieName = request.getParameter("serieName");

			SerieDAO serieDAO = new SerieDAO();
			Serie serie = serieDAO.select(serieName);
			SeasonDAO seasonDAO = new SeasonDAO();
            ArrayList<Season> seasons = seasonDAO.selectAllSeasons(serie.getId());
            
            session = request.getSession(true);
            session.setAttribute("seasons", seasons);
            response.sendRedirect("RegisterEpisode.jsp");

		} catch (DatabaseException | CloseConnectionException e) {
			e.printStackTrace();
			response.sendRedirect("Error.jsp");
		}  finally {
			out.close();
		}
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	public String getServletInfo () {
		return "Short description";
	}
}