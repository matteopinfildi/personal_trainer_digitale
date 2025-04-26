package model.dao;

import exception.DAOException;
import model.domain.SchedaAllenamento;

import java.sql.*;
import java.time.LocalDate;

public class SchedaAllenamentoDAO {
    public void creazioneSchedaAttiva(SchedaAllenamento scheda) throws DAOException {
        String sql = "{CALL creazione_scheda_attiva(?, ?, ?)}";  // Ho aggiunto 1 parametro in più, se necessario
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, scheda.getCfPersonal());
            stmt.setString(2, scheda.getCfAtleta());  // cfAtleta
            stmt.setString(3, scheda.getDescrizione());  // descrizione
//            stmt.setNull(3, java.sql.Types.DATE);  // dataArchiviazione
            // cfPersonal
//            stmt.setBoolean(5, scheda.isStato());  // stato (booleano, gestito con setBoolean)

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
        String query = "SELECT id_scheda, cf_atleta, descrizione, stato, data_archiviazione, cf_personal " +
                "FROM personal_trainer_digitale.scheda_allenamento " +
                "WHERE cf_atleta = ? AND stato = 1 LIMIT 1";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, cfAtleta);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idScheda = rs.getInt("id_scheda");
                    String cfAtletaResult = rs.getString("cf_atleta");
                    String descrizione = rs.getString("descrizione");
                    boolean stato = rs.getInt("stato") == 1;  // Convertiamo 1/0 in boolean
                    String cfPersonal = rs.getString("cf_personal");

                    return new SchedaAllenamento(idScheda, cfPersonal, cfAtletaResult, descrizione, stato, null);
                } else {
                    throw new DAOException("Nessuna scheda attiva trovata per l'atleta");
                }
            }
        }
    }

    public SchedaAllenamento visualizzaSchedaArchiviata(String cfAtleta) throws DAOException, SQLException {
        String sql = "SELECT id_scheda, descrizione, data_archiviazione, cf_atleta " +
                "FROM scheda_allenamento " +
                "WHERE cf_atleta = ? AND stato = 0";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cfAtleta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new SchedaAllenamento(
                        rs.getInt("id_scheda"),
                        null,
                        rs.getString("cf_atleta"),
                        rs.getString("descrizione"),
                        false, // stato archiviato è 0
                        rs.getDate("data_archiviazione").toLocalDate()
                );
            } else {
                throw new DAOException("Nessuna scheda archiviata trovata per l'atleta con CF: " + cfAtleta);
            }
        } catch (SQLException e) {
            throw new SQLException("Errore durante la visualizzazione della scheda archiviata", e);
        }
    }
}
