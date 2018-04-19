package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.Episode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class that represent the table episode of the database telemaco
 *
 * @author Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 10 de abr de 2018 | 22:36:15
 */
public class EpisodeDAO implements DAOEpisodeSpecialOperations {

    private Connection connection;
    private static EpisodeDAO epDAO = null;

    /**
     * Default constructor
     *
     * @throws com.imd.telemaco.business.exception.DatabaseException
     */
    public EpisodeDAO() throws DatabaseException {
        this.connection = ConnectionFactory.getConnection();
    }

    /**
     * @return epDAO
     * @throws com.imd.telemaco.business.exception.DatabaseException
     */
    public static synchronized EpisodeDAO getInstance() throws DatabaseException {
        if (epDAO == null) {
            epDAO = new EpisodeDAO();
        }
        return epDAO;
    }

    private void startsConnection() throws DatabaseException {
        try {
            if (this.connection.isClosed()) {
                this.connection = ConnectionFactory.getConnection();
            }
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    @Override
    public void insert(Episode episode) throws DatabaseException, CloseConnectionException {
        String sql = "INSERT INTO epsiode (id, name, number, time, synopsis, idfkseason) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            this.startsConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(2, episode.getName());
            statement.setInt(3, episode.getNumber());
            statement.setInt(4, episode.getTime());
            statement.setString(5, episode.getSynopsis());
            statement.setInt(6, episode.getIdSeason());

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
    public Episode select(int id) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.episode WHERE id='" + id + "'";
        Episode episode = null;

        try {
            this.startsConnection();

            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            if (result.next()) {
                String name = result.getString("name");
                int number = result.getInt("number");
                int time = result.getInt("time");
                String synopsis = result.getString("synopsis");
                int idFkSeason = result.getInt("idfkseason");

                episode = new Episode(id, name, number, time, synopsis, idFkSeason);
            }

            return episode;
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
    public Episode select(String name, int idSeason) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.episode WHERE name='" + name + "' AND idfkseason='" + idSeason + "'";
        Episode episode = new Episode();

        try {
            this.startsConnection();

            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            if (result.next()) {
                int id = result.getInt("id");
                episode = select(id);
            } else {
                episode = null;
            }

            return episode;
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
    public Episode select(int number, int idSeason) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.episode WHERE number='" + number + "' AND idfkseason='" + idSeason + "'";
        Episode episode = new Episode();

        try {
            this.startsConnection();

            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            if (result.next()) {
                int id = result.getInt("id");
                episode = select(id);
            } else {
                episode = null;
            }

            return episode;
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
    public ArrayList<Episode> selectAllEpisodes(int idSeason) throws DatabaseException, CloseConnectionException {
        String sql = "SELECT * FROM telemaco.episode WHERE idfkseason='" + idSeason + "'";

        try {
            this.startsConnection();

            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            ArrayList<Episode> episodes = new ArrayList<Episode>();

            while (result.next()) {
                int idEp = result.getInt("id");
                Episode e = select(idEp);
                episodes.add(e);
            }

            return episodes;
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
    public void delete(Episode episode) throws DatabaseException, CloseConnectionException {
        String sql = "DELETE * FROM telemaco.episode WHERE id='" + episode.getId() + "'";

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
    public void update(Episode episode) throws DatabaseException, CloseConnectionException {
        String sql = "UPDATE telemaco.episode SET "
                + "name=?, "
                + "number=?, "
                + "time=?, "
                + "synopsis=?, "
                + "idfkseason=? "
                + "WHERE id=?";

        try {
            this.startsConnection();

            PreparedStatement pStm = connection.prepareStatement(sql);
            pStm.setString(1, episode.getName());
            pStm.setInt(2, episode.getNumber());
            pStm.setInt(3, episode.getTime());
            pStm.setString(4, episode.getSynopsis());
            pStm.setInt(5, episode.getIdSeason());
            pStm.setInt(6, episode.getId());

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
