package model.dao;

import model.domain.Esercizio;
import model.domain.Macchinario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class GestoreDAO {
    private final Connection connection;

    public GestoreDAO(Connection connection) {
        this.connection = connection;
    }

    // ✅ Usando oggetto Esercizio
    public void aggiornaEsercizio(Esercizio esercizio) throws SQLException {
        try (CallableStatement statement = connection.prepareCall("{CALL aggiorna_esercizi(?, ?, ?)}")) {
            statement.setInt(1, esercizio.getCodiceEs());
            statement.setString(2, esercizio.getNome());
            statement.setString(3, esercizio.getDescrizione());
            statement.execute();
        }
    }

    // ✅ Usando oggetto Macchinario
    public void aggiornaMacchinario(Macchinario macchinario) throws SQLException {
        try (CallableStatement statement = connection.prepareCall("{CALL aggiorna_macchinari(?, ?, ?)}")) {
            statement.setInt(1, macchinario.getEsercizio().getCodiceEs());
            statement.setString(2, macchinario.getNome());
            statement.setString(3, macchinario.getDescrizione());
            statement.execute();
        }
    }

    // ✅ Elimina Esercizio con entità
    public void eliminaEsercizio(Esercizio esercizio) throws SQLException {
        try (CallableStatement statement = connection.prepareCall("{CALL elimina_esercizio(?)}")) {
            statement.setInt(1, esercizio.getCodiceEs());
            statement.execute();
        }
    }

    // ✅ Elimina Macchinario con entità
    public void eliminaMacchinario(Macchinario macchinario) throws SQLException {
        try (CallableStatement statement = connection.prepareCall("{CALL elimina_macchinario(?)}")) {
            statement.setInt(1, macchinario.getEsercizio().getCodiceEs());
            statement.execute();
        }
    }
}

