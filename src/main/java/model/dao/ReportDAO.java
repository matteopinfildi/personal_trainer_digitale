package model.dao;

import exception.DAOException;
import model.domain.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    public List<Report> generaReport(Date dataInizio, Date dataFine, String cfPersonal) throws DAOException {
        List<Report> risultati = new ArrayList<>();
        String query = "{CALL genera_report(?, ?, ?)}";

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setDate(1, dataInizio);
            stmt.setDate(2, dataFine);
            stmt.setString(3, cfPersonal);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getString("cf_atleta"),
                        rs.getString("nome_atleta"),
                        rs.getDate("data_allenamento").toLocalDate(),
                        rs.getInt("durata"),
                        rs.getInt("id_scheda"),
                        rs.getString("descrizione_scheda"),
                        rs.getInt("totale_esercizi"),
                        rs.getInt("esercizi_completati"),
                        rs.getInt("esercizi_saltati"),
                        rs.getDouble("percentuale_completamento")
                );
                risultati.add(report);
            }

        } catch (SQLException e) {
            throw new DAOException("Errore nella generazione del report: " + e.getMessage());
        }

        return risultati;
    }
}
