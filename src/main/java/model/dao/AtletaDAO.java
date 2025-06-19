package model.dao;

import exception.DAOException;
import model.domain.Atleta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;

public class AtletaDAO {

    private static final String CALL_INSERT = "{ call inserisci_atleta(?, ?, ?, ?) }";

    public void inserisciAtleta(Atleta atleta) throws DAOException {
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(CALL_INSERT)) {
            stmt.setString(1, atleta.getCfAtleta());
            stmt.setString(2, atleta.getNome());
            stmt.setString(3, atleta.getCognome());
            stmt.setDate(4, Date.valueOf(atleta.getDataNascita()));

            stmt.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore inserimento atleta: " + e.getMessage());
        }
    }
}

