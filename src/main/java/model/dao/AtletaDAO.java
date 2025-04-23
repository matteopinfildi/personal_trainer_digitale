package model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtletaDAO {
    private final Connection conn;

    public AtletaDAO(Connection conn) {
        this.conn = conn;
    }

    public void esercizioCompletato(String cfAtleta, String dataAllenamento, int codiceEsercizio) throws SQLException {
        CallableStatement stmt = conn.prepareCall("{CALL esercizio_completato(?, ?, ?)}");
        stmt.setString(1, cfAtleta);
        stmt.setString(2, dataAllenamento);
        stmt.setInt(3, codiceEsercizio);
        stmt.execute();
    }

    public void esercizioSaltato(String cfAtleta, String dataAllenamento, int codiceEsercizio) throws SQLException {
        CallableStatement stmt = conn.prepareCall("{CALL esercizio_saltato(?, ?, ?)}");
        stmt.setString(1, cfAtleta);
        stmt.setString(2, dataAllenamento);
        stmt.setInt(3, codiceEsercizio);
        stmt.execute();
    }

    public ResultSet visualizzaSchedaAttiva(String cfAtleta) throws SQLException {
        CallableStatement stmt = conn.prepareCall("{CALL visualizza_scheda_attiva(?)}");
        stmt.setString(1, cfAtleta);
        return stmt.executeQuery();
    }

    public ResultSet visualizzaSchedaArchiviata(String cfAtleta) throws SQLException {
        CallableStatement stmt = conn.prepareCall("{CALL visualizza_scheda_archiviata(?)}");
        stmt.setString(1, cfAtleta);
        return stmt.executeQuery();
    }

    public void registrazioneAllenamento(String cfAtleta, String data, int durata) throws SQLException{
        CallableStatement stmt = conn.prepareCall("{CALL registrazione_allenamento(?, ?, ?)}");
        stmt.setString(1, cfAtleta);
        stmt.setString(2, data);
        stmt.setInt(3, durata);
        stmt.execute();
    }

    public ResultSet stampaEsercizio(int codiceEsercizio) throws SQLException {
        CallableStatement stmt = conn.prepareCall("{CALL stampa_esercizio(?)}");
        stmt.setInt(1, codiceEsercizio);
        return stmt.executeQuery();
    }
}
