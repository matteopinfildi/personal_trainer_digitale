package model.dao;

import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssegnaPersonalDAO {
    public void assegnaPersonal(String cfAtleta, String cfPersonal) throws DAOException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE personal_trainer_digitale.atleta " +
                    "SET cf_personal = ? " +
                    "WHERE cf_atleta = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cfPersonal);
                pstmt.setString(2, cfAtleta);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows == 0) {
                    throw new DAOException("Atleta o Personal Trainer non trovati.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore durante l'assegnazione del Personal Trainer: " + e.getMessage());
        }
    }
}
