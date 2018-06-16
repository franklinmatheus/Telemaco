package com.imd.telemaco.presentation;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imd.telemaco.data.SeasonDAO;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.entity.Season;
import com.imd.telemaco.entity.Serie;

/**
 * Servlet implementation class SelectSeasonAtSerie
 */
@WebServlet("/SelectSeasonAtSerie")
public class SelectSeasonsAtSerie extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String serieName = request.getParameter("serieName");
            SerieDAO serieDAO = new SerieDAO();
            Serie serie = serieDAO.select(serieName);

            SeasonDAO seasonDAO = new SeasonDAO();
            ArrayList<Season> seasons = seasonDAO.selectAllSeasons(serie.getId());

            HttpSession session = request.getSession(true);
            session.setAttribute("seasons", seasons);
//            response.sendRedirect("RegisterEpisode.jsp");
        } catch (DatabaseException | CloseConnectionException e) {
            response.sendRedirect("Error.jsp");
        }
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
