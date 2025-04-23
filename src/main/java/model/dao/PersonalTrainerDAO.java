package model.dao;

import exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonalTrainerDAO {

    private final Connection conn;

    public PersonalTrainerDAO(Connection conn) {
        this.conn = conn;
    }

    public void assegnaPersonal(String cfAtleta, String cfPersonal) throws SQLException {
        String sql = "{ call assegna_personal(?, ?) }";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, cfAtleta);
            stmt.setString(2, cfPersonal);
            stmt.execute();
        }
    }


    public void creazioneSchedaAttiva(String cfAtleta, String descrizioneScheda) throws DAOException {
        try (CallableStatement stmt = conn.prepareCall("{call creazione_scheda_attiva(?, ?)}")) {
            stmt.setString(1, cfAtleta);
            stmt.setString(2, descrizioneScheda);
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Errore nella creazione della scheda: " + e.getMessage());
        }
    }

    public void associaEsercizioScheda(int idScheda, int idEsercizio) throws DAOException {
        try (CallableStatement stmt = conn.prepareCall("{call associa_esercizio_scheda(?, ?)}")) {
            stmt.setInt(1, idScheda);
            stmt.setInt(2, idEsercizio);
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Errore nell'associazione dell'esercizio: " + e.getMessage());
        }
    }

    public void archiviaSchedaAttiva(String cfAtleta) throws SQLException {
        String sql = "UPDATE schede_attive SET stato = 'archiviato' WHERE cf_atleta = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cfAtleta);
            stmt.executeUpdate();
        }
    }


    public void modificaPersonal(String nuovoCognome) throws DAOException {
        try (CallableStatement stmt = conn.prepareCall("{call modifica_personal(?)}")) {
            stmt.setString(1, nuovoCognome);
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Errore nella modifica del personal: " + e.getMessage());
        }
    }

    public void generaReport(String cfAtleta) throws DAOException {
        try (CallableStatement stmt = conn.prepareCall("{call genera_report(?)}")) {
            stmt.setString(1, cfAtleta);
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Errore nella generazione del report: " + e.getMessage());
        }
    }
}

