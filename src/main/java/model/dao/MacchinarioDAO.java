package model.dao;

import exception.DAOException;
import model.domain.Macchinario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MacchinarioDAO {
    private Connection conn;

    public void aggiungiMacchinario(Macchinario macchinario) throws DAOException {
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall("{call aggiorna_macchinari(?, ?, ?)}")) {

            stmt.setInt(1, macchinario.getEsercizio().getCodiceEs());
            stmt.setString(2, macchinario.getNome());
            stmt.setString(3, macchinario.getDescrizione());

            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Errore nell'inserimento del macchinario: " + e.getMessage());
        }
    }

    public void eliminaMacchinario(String nomeMacchinario) throws DAOException {
        String sql = "CALL elimina_macchinario(?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nomeMacchinario);

            // Esegui la chiamata alla stored procedure
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Errore durante l'eliminazione del macchinario");
        }
    }
}
