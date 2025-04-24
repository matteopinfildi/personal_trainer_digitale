package model.dao;

import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssegnaPersonalDAO {
    public void assegnaPersonal(String cfAtleta, String cfPersonal) throws DAOException {
        // Crea una connessione al database
        try (Connection conn = ConnectionFactory.getConnection()) {
            // SQL per assegnare il Personal Trainer
            String sql = "UPDATE personal_trainer_digitale.atleta " +
                    "SET cf_personal = ? " +
                    "WHERE cf_atleta = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Imposta i parametri
                pstmt.setString(1, cfPersonal);
                pstmt.setString(2, cfAtleta);

                // Esegui l'update
                int affectedRows = pstmt.executeUpdate();

                // Se non sono stati aggiornati record, l'atleta o il personal trainer non sono validi
                if (affectedRows == 0) {
                    throw new DAOException("Atleta o Personal Trainer non trovati.");
                }
            }
        } catch (SQLException e) {
            // Gestione degli errori del database
            throw new DAOException("Errore durante l'assegnazione del Personal Trainer: " + e.getMessage());
        }
    }
}
