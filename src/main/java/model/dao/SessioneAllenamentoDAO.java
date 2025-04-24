package model.dao;

import exception.DAOException;
import model.domain.SessioneAllenamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SessioneAllenamentoDAO {

    // Metodo per registrare una sessione di allenamento
    public void registrazioneAllenamento(SessioneAllenamento sessione) throws DAOException {
        String sql = "CALL registrazione_allenamento(?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sessione.getAtleta().getCfAtleta()); // Codice fiscale atleta
            ps.setDate(2, java.sql.Date.valueOf(sessione.getDataAllenamento())); // Data allenamento
            ps.setInt(3, sessione.getDurata()); // Durata allenamento

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Errore durante la registrazione della sessione di allenamento");
        }
    }
}
