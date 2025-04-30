package model.dao;

import exception.DAOException;
import model.domain.SessioneAllenamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SessioneAllenamentoDAO {

    public void registrazioneAllenamento(SessioneAllenamento sessione) throws DAOException {
        String sql = "CALL registrazione_allenamento(?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sessione.getAtleta().getCfAtleta());
            ps.setDate(2, java.sql.Date.valueOf(sessione.getDataAllenamento()));
            ps.setInt(3, sessione.getDurata());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Errore durante la registrazione della sessione di allenamento");
        }
    }
}
