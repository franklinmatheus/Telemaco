package com.imd.telemaco.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imd.telemaco.business.ValidateSeasonServices;
import com.imd.telemaco.business.exception.SeasonExistsException;
import com.imd.telemaco.business.exception.SeasonIncompleteException;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.entity.Season;
import com.imd.telemaco.entity.Serie;
import com.imd.telemaco.entity.User;

/**
 * Servlet implementation class ResgisterSeason
 */
@WebServlet("/ResgisterSeason")
public class ResgisterSeason extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Season season = new Season();
			
			String number    = request.getParameter("number");
			String serieName = request.getParameter("serieName");
			
			SerieDAO serieDAO = new SerieDAO();
			Serie serie = serieDAO.select(serieName);
			
			int numberInt = Integer.parseInt(number);
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("logged");

			season = new Season (numberInt, serie.getId());
			
			try {
				ValidateSeasonServices validate = new ValidateSeasonServices();
				validate.validSeasonInsert(season);
				response.sendRedirect("Logged.jsp");
				
			} catch (SeasonExistsException e) {
				System.out.println(e.getMessage()); //FIXME exibir msg p usuário
			} catch (SeasonIncompleteException e) {
				System.out.println(e.getMessage()); //FIXME exibir msg p usuário
			} 
			
		} catch (SQLException e) {
			new RuntimeException (e); //FIXME
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
