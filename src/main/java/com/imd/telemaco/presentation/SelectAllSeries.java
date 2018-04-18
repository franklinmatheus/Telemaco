package com.imd.telemaco.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.entity.Serie;

/**
 * Servlet implementation class SelectAllSeries
 */
@WebServlet("/SelectAllSeries")
public class SelectAllSeries extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public SelectAllSeries () {
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SerieDAO sDAO = new SerieDAO ();
			ArrayList<Serie> series = sDAO.selectAllSeries();
			
			HttpSession session = request.getSession(true);
			session.setAttribute("series", series);
			response.sendRedirect("Series.jsp");
		} catch (SQLException e) {
			throw new RuntimeException (e);
		}
	}

}
