package model.dao;

import exception.DAOException;
import model.domain.Atleta;
import model.domain.Esercizio;
import model.domain.Interagisce;
import model.domain.SessioneAllenamento;

import java.sql.*;

public class InterazioneDAO {
    private final Connection connection;


    public InterazioneDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void completaEsercizio(Interagisce interazione) throws SQLException {
        String sql = "{CALL esercizio_completato(?, ?, ?)}";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            Atleta atleta = interazione.getAtleta();
            Esercizio esercizio = interazione.getEsercizio();
            SessioneAllenamento sessione = interazione.getSessioneAllenamento();

            stmt.setString(1, atleta.getCfAtleta());
            stmt.setInt(2, esercizio.getCodiceEs());
            stmt.setDate(3, Date.valueOf(sessione.getDataAllenamento()));

            stmt.execute();
        }
    }

    public void esercizioSaltato(Interagisce interazione) throws DAOException {
        CallableStatement statement = null;

        try {
            statement = connection.prepareCall("{CALL esercizio_saltato(?, ?, ?)}");

            statement.setString(1, interazione.getAtleta().getCfAtleta());
            statement.setInt(2, interazione.getEsercizio().getCodiceEs());
            statement.setDate(3, java.sql.Date.valueOf(interazione.getSessioneAllenamento().getDataAllenamento()));

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Errore nell'esecuzione della procedura esercizio_saltato" + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Errore nella chiusura dello statement");
            }
        }
    }
}
