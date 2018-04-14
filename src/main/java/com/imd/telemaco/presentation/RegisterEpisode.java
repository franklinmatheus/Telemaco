package com.imd.telemaco.presentation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imd.telemaco.entity.Episode;

public class RegisterEpisode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		try {
			String serieName = request.getParameter("serieName");
			String seasonNumber = request.getParameter("season");
			String name = request.getParameter("epName");
			String number = request.getParameter("epNumber");
			String synopsis = request.getParameter("epSynopsis");
			String time = request.getParameter("epTime");
			
			int seasonNumberInt = Integer.parseInt(seasonNumber);
			
			Episode episode = new Episode ();
			
		} catch () {
			
		}

	}

}
