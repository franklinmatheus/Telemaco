package com.imd.telemaco.presentation;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

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

public class RegisterEpisode extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ArrayList<Serie> series = new ArrayList<>();
    ArrayList<Season> seasons = new ArrayList<>();

    public RegisterEpisode() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        session.getAttribute("logged");

        try {
            Episode episode = new Episode();
            String serieName = request.getParameter("serieName");
            String seasonNumber = request.getParameter("seasonNumber");
            String name = request.getParameter("epName");
            String number = request.getParameter("epNumber");
            String synopsis = request.getParameter("epSynopsis");
            String time = request.getParameter("epTime");

            Serie serie = (Serie) session.getAttribute("serie");

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

            episode = new Episode(name, numberInt, timeInt, synopsis, season.getId());

            try {
                ValidateEpisodeServices validate = new ValidateEpisodeServices();
                validate.validEpisodeInsert(episode);
                
                SerieDAO dao = SerieDAO.getInstance();
                Serie updateSerie = dao.select(serie.getId());
                session.setAttribute("serie", updateSerie);
                
                response.sendRedirect("Serie.jsp");

            } catch (EpisodeInvalidException e) {
                e.printStackTrace();
            } catch (EpisodeExistsException e) {
                e.printStackTrace();
            }
        } catch (DatabaseException | CloseConnectionException e) {
            response.sendRedirect("Error.jsp");
        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
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
