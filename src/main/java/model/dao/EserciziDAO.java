package model.dao;

import exception.DAOException;
import model.domain.Esercizio;

import java.sql.*;

public class EserciziDAO {

    public Esercizio getEsercizioByCodice(int codiceEs) throws DAOException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM esercizi WHERE codice_es = ?")) {

            stmt.setInt(1, codiceEs);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Esercizio(
                        rs.getInt("codice_es"),
                        rs.getString("nome"),
                        rs.getString("descrizione"),
                        rs.getInt("num_serie"),
                        rs.getInt("ripetizioni")
                );
            } else {
                throw new DAOException("Esercizio non trovato.");
            }
        } catch (SQLException e) {
            throw new DAOException("Errore nel recupero dell'esercizio: " + e.getMessage());
        }
    }


    public void aggiungiEsercizio(Esercizio esercizio) throws DAOException {
        String sql = "{CALL aggiorna_esercizi(?, ?, ?, ?)}";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, esercizio.getNome());
            stmt.setString(2, esercizio.getDescrizione());
            stmt.setInt(3, esercizio.getNumSerie());
            stmt.setInt(4, esercizio.getRipetizioni());

            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Errore durante l'aggiunta dell'esercizio: " + e.getMessage());
        }
    }

    public void eliminaEsercizio(int codiceEsercizio) throws DAOException {
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall("{call elimina_esercizio(?)}")) {

            stmt.setInt(1, codiceEsercizio);
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Errore durante l'eliminazione dell'esercizio: " + e.getMessage());
        }
    }

    public String getDettagliEsercizio(int codiceEs) {
        String esercizioInfo = "";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("CALL stampa_esercizio(?)")) {

            ps.setInt(1, codiceEs);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nomeEsercizio = rs.getString("nome_esercizio");
                    int numSerie = rs.getInt("num_serie");
                    int ripetizioni = rs.getInt("ripetizioni");
                    String nomeMacchinario = rs.getString("nome_macchinario");
                    String descrizioneMacchinario = rs.getString("descrizione_macchinario");

                    esercizioInfo = "Nome esercizio: " + nomeEsercizio + "\n" +
                            "Serie: " + numSerie + "\n" +
                            "Ripetizioni: " + ripetizioni + "\n" +
                            "Macchinario: " + nomeMacchinario + "\n" +
                            "Descrizione macchinario: " + descrizioneMacchinario;

                } else {
                    esercizioInfo = "Esercizio non trovato!";
                }
            }
        } catch (SQLException e) {
            esercizioInfo = "Errore nel recupero dell'esercizio: " + e.getMessage();
        }

        return esercizioInfo;
    }


}