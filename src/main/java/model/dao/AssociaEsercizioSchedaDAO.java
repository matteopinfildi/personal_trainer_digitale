package model.dao;

import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssociaEsercizioSchedaDAO {

    public void associaEsercizioScheda(int codiceEs, int idScheda) throws DAOException {
        // Crea una connessione al database
        try (Connection conn = ConnectionFactory.getConnection()) {
            // Verifica se l'esercizio esiste
            String sqlEs = "SELECT COUNT(*) FROM personal_trainer_digitale.esercizi WHERE codice_es = ?";
            try (PreparedStatement pstmtEs = conn.prepareStatement(sqlEs)) {
                pstmtEs.setInt(1, codiceEs);
                ResultSet rs = pstmtEs.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new DAOException("Esercizio non trovato");
                }
            }

            // Verifica se la scheda esiste e ha stato attivo
            String sqlScheda = "SELECT COUNT(*) FROM personal_trainer_digitale.scheda_allenamento WHERE id_scheda = ? AND stato = 1";
            try (PreparedStatement pstmtScheda = conn.prepareStatement(sqlScheda)) {
                pstmtScheda.setInt(1, idScheda);
                ResultSet rsScheda = pstmtScheda.executeQuery();
                if (rsScheda.next() && rsScheda.getInt(1) == 0) {
                    throw new DAOException("Scheda di allenamento non trovata");
                }
            }

            // Inserisce l'associazione nella tabella contenuto
            String sqlInsert = "INSERT INTO personal_trainer_digitale.contenuto (codice_es, id_scheda) VALUES (?, ?)";
            try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {
                pstmtInsert.setInt(1, codiceEs);
                pstmtInsert.setInt(2, idScheda);
                pstmtInsert.executeUpdate();
            }

        } catch (SQLException e) {
            // Gestione degli errori del database
            throw new DAOException("Errore durante l'associazione esercizio e scheda: " + e.getMessage());
        }
    }
}
