package model.dao;

import exception.DAOException;
import model.domain.Esercizio;
import model.domain.SchedaAllenamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchedaAllenamentoDAO {
    public void creazioneSchedaAttiva(SchedaAllenamento scheda) throws DAOException {
        String sql = "{CALL creazione_scheda_attiva(?, ?, ?)}";  // Ho aggiunto 1 parametro in più, se necessario
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, scheda.getCfPersonal());
            stmt.setString(2, scheda.getCfAtleta());  // cfAtleta
            stmt.setString(3, scheda.getDescrizione());  // descrizione

            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Errore nella creazione della scheda: " + e.getMessage());
        }
    }

    public void archiviaSchedaAttiva(int idScheda) throws DAOException {
        String query = "{CALL archivia_scheda_attiva(?)}";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setInt(1, idScheda);
            stmt.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore durante l'archiviazione della scheda attiva: " + e.getMessage());
        }
    }

    public SchedaAllenamento visualizzaSchedaAttiva(String cfAtleta) throws DAOException, SQLException {
        String query = "{CALL visualizza_scheda_attiva(?)}";
        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setString(1, cfAtleta);
            ResultSet rs = stmt.executeQuery();

            SchedaAllenamento scheda = null;
            List<Esercizio> esercizi = new ArrayList<>();

            while (rs.next()) {
                if (scheda == null) {
                    scheda = new SchedaAllenamento(
                            rs.getInt("id_scheda"),
                            rs.getString("cf_personal"),
                            cfAtleta,
                            rs.getString("descrizione_scheda"),
                            true,
                            null,
                            new ArrayList<>()
                    );
                }

                esercizi.add(new Esercizio(
                        rs.getInt("codice_es"),
                        rs.getString("nome_esercizio"),
                        null,
                        rs.getInt("num_serie"),
                        rs.getInt("ripetizioni")
                ));
            }
            if (scheda == null) {
                throw new DAOException("Nessuna scheda attiva trovata per l'atleta");
            }

            return new SchedaAllenamento(
                    scheda.getIdScheda(),
                    scheda.getCfPersonal(),
                    scheda.getCfAtleta(),
                    scheda.getDescrizione(),
                    scheda.isStato(),
                    scheda.getDataArchiviazione(),
                    esercizi
            );
        }
    }

    public List<SchedaAllenamento> visualizzaSchedaArchiviata(String cfAtleta) throws DAOException, SQLException {
        String query = "{CALL visualizza_scheda_archiviata(?)}";
        List<SchedaAllenamento> schedeArchiviate = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement stmt = connection.prepareCall(query)) {

            stmt.setString(1, cfAtleta);
            ResultSet rs = stmt.executeQuery();

            SchedaAllenamento currentScheda = null;
            List<Esercizio> currentEsercizi = new ArrayList<>();
            int currentIdScheda = -1;

            while (rs.next()) {
                int idScheda = rs.getInt("id_scheda");

                if (idScheda != currentIdScheda) {
                    if (currentScheda != null) {
                        schedeArchiviate.add(new SchedaAllenamento(
                                currentScheda.getIdScheda(),
                                null,
                                currentScheda.getCfAtleta(),
                                currentScheda.getDescrizione(),
                                false,
                                currentScheda.getDataArchiviazione(),
                                currentEsercizi
                        ));
                        currentEsercizi = new ArrayList<>();
                    }

                    currentScheda = new SchedaAllenamento(
                            idScheda,
                            null,
                            cfAtleta,
                            rs.getString("descrizione_scheda"),
                            false,
                            rs.getDate("data_archiviazione").toLocalDate(),
                            null
                    );
                    currentIdScheda = idScheda;
                }

                currentEsercizi.add(new Esercizio(
                        rs.getInt("codice_es"),
                        rs.getString("nome_esercizio"),
                        rs.getString("descrizione_esercizio"),
                        rs.getInt("num_serie"),
                        rs.getInt("ripetizioni")
                ));
            }

            if (currentScheda != null) {
                schedeArchiviate.add(new SchedaAllenamento(
                        currentScheda.getIdScheda(),
                        null,
                        currentScheda.getCfAtleta(),
                        currentScheda.getDescrizione(),
                        false,
                        currentScheda.getDataArchiviazione(),
                        currentEsercizi
                ));
            }

            if (schedeArchiviate.isEmpty()) {
                throw new DAOException("Nessuna scheda archiviata trovata per l'atleta con CF: " + cfAtleta);
            }
            return schedeArchiviate;
        }
    }
}
