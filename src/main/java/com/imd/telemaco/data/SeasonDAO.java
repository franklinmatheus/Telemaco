package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.imd.telemaco.entity.Season;
import com.imd.telemaco.entity.Episode;

/**
 * Class that represent the table season of the database telemaco
 *
 * @author Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 11 de abr de 2018 | 23:29:36
 */
public class SeasonDAO implements DAO<Season>, DAOSeasonSpecialOperations {

    private Connection connection;
    private static SeasonDAO seasonDAO = null;

    /**
     * Default constructor
     *
     * @throws com.imd.telemaco.business.exception.DatabaseException
     */
    public SeasonDAO() throws DatabaseException {
        this.connection = ConnectionFactory.getConnection();
    }

    /**
     * @return seasonDAO
     * @throws com.imd.telemaco.business.exception.DatabaseException
     */
    public static synchronized SeasonDAO getInstance() throws DatabaseException {
        if (seasonDAO == null) {
            seasonDAO = new SeasonDAO();
        }
        return seasonDAO;
    }
    
    private void startsConnection() throws DatabaseException {
        try {
            if(this.connection.isClosed())
                this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new DatabaseException();
        }   
    }

    @Override
    public void insert(Season season) throws DatabaseException, CloseConnectionException {
        String sql = "INSERT INTO telemaco.season (number, idfkserie, amountep) VALUES (?, ?, ?)";
        try {
            this.startsConnection();
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, season.getNumber());
            statement.setInt(2, season.getIdSerie());
            statement.setInt(3, season.getEpAmount());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new CloseConnectionException();
            }
        }
    }

    @Override
    public Season select(int id) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.season WHERE id = '" + id + "'";
        Season season = new Season();

        try {
            this.startsConnection();
            
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            boolean isThere = result.next();

            if (isThere) {
                int number = result.getInt("number");
                EpisodeDAO epDAO = new EpisodeDAO();
                ArrayList<Episode> episodes = epDAO.selectAllEpisodes(id);
                int idFkSerie = result.getInt("idfkserie");

                season = new Season(id, number, episodes, idFkSerie);
            } else {
                season = null;
            }

            return season;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new CloseConnectionException();
            }
        }
    }

    @Override
    public Season select(int number, int idSerie) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.season WHERE idfkserie='" + idSerie + "' AND number='" + number + "'";
        Season season = new Season();

        try {
            this.startsConnection();
            
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            if (result.next()) {
                int id = result.getInt("id");
                season = select(id);
            } else {
                season = null;
            }

            return season;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }

    @Override
    public ArrayList<Season> selectAllSeasons(int idSerie) throws DatabaseException, CloseConnectionException {
        ArrayList<Season> seasons = new ArrayList<Season>();
        String sql = "SELECT * FROM telemaco.season WHERE idfkserie='" + idSerie +"'";

        try {
            this.startsConnection();
            
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            while (result.next()) {
                int idSeason = result.getInt("id");
                Season s = select(idSeason);
                seasons.add(s);
            }
            
            return seasons;
        } catch (SQLException e) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new CloseConnectionException();
            }
        }
    }

    @Override
    public void delete(Season season) throws DatabaseException, CloseConnectionException {
        String sql = "DELETE FROM telemaco.season WHERE id ='" + season.getId() + "'";

        try {
            this.startsConnection();
            
            Statement stm = connection.createStatement();
            stm.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new CloseConnectionException();
            }
        }
    }

    @Override
    public void update(Season season) throws DatabaseException, CloseConnectionException {
        String sql = "UPDATE FROM telemaco.season "
                + "number=?, "
                + "amountep=?, "
                + "idfkseason=? "
                + "WHERE id=?";

        try {
            this.startsConnection();
            
            PreparedStatement pStm = connection.prepareStatement(sql);//
            pStm.setInt(1, season.getNumber());
            pStm.setInt(2, season.getEpAmount());
            pStm.setInt(3, season.getIdSerie());
            pStm.setInt(4, season.getId());
            pStm.execute();

        } catch (SQLException e) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new CloseConnectionException();
            }
        }
    }
}
