package model.dao;

import exception.DAOException;
import model.domain.PersonalTrainer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class PersonalTrainerDAO {

    private static final String CALL_INSERT = "{ call inserisci_personal_trainer(?, ?, ?) }";

    public void inserisciPersonalTrainer(PersonalTrainer pt) throws DAOException {
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(CALL_INSERT)) {
            stmt.setString(1, pt.getCfPersonal());
            stmt.setString(2, pt.getNome());
            stmt.setString(3, pt.getCognome());


            stmt.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore inserimento personal trainer: " + e.getMessage());
        }
    }
}