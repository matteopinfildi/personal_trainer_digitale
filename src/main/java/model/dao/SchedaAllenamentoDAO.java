package model.dao;

import exception.DAOException;
import model.domain.SchedaAllenamento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class SchedaAllenamentoDAO {
    public void creazioneSchedaAttiva(SchedaAllenamento scheda) throws DAOException {
        String sql = "{CALL crea_scheda_attiva(?, ?, ?, ?, ?)}";  // Ho aggiunto 1 parametro in più, se necessario
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, scheda.getCfAtleta());  // cfAtleta
            stmt.setString(2, scheda.getDescrizione());  // descrizione
            stmt.setDate(3, java.sql.Date.valueOf(scheda.getDataArchiviazione()));  // dataArchiviazione
            stmt.setString(4, scheda.getCfPersonal());  // cfPersonal
            stmt.setBoolean(5, scheda.isStato());  // stato (booleano, gestito con setBoolean)

            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Errore nella creazione della scheda: " + e.getMessage());
        }
    }

    public void archiviaSchedaAttiva(int idScheda) throws DAOException {
        String query = "{CALL archivia_scheda_attiva(?)}";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setInt(1, idScheda);
            stmt.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore durante l'archiviazione della scheda attiva: " + e.getMessage());
        }
    }
}
