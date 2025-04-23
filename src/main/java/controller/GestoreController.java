package controller;

import controller.Controller;
import model.dao.ConnectionFactory;
import model.dao.GestoreDAO;
import model.domain.Esercizio;
import model.domain.Macchinario;
import model.view.GestoreView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class GestoreController implements Controller {

    private final GestoreDAO gestoreDAO;

    public GestoreController() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        this.gestoreDAO = new GestoreDAO(connection);
    }

    @Override
    public void start() throws IOException {
        int op;
        while (true) {
            op = GestoreView.getOp();
            switch (op) {
                case 1:
                    this.aggiornaEsercizio();
                    break;
                case 2:
                    this.aggiornaMacchinario();
                    break;
                case 3:
                    this.eliminaEsercizio();
                    break;
                case 4:
                    this.eliminaMacchinario();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Operazione non valida");
                    break;
            }
        }
    }

    private void aggiornaEsercizio() throws IOException {
        // Creazione dell'oggetto Esercizio
        int codiceEsercizio = GestoreView.getCodiceEsercizio();
        String nome = GestoreView.getNomeEsercizio();
        String descrizione = GestoreView.getDescrizioneEsercizio();
        int numSerie = GestoreView.getNumSerie();
        int ripetizioni = GestoreView.getRipetizioni();

        // Creazione dell'oggetto Esercizio da passare al DAO
        Esercizio esercizio = new Esercizio(codiceEsercizio, nome, descrizione, numSerie, ripetizioni);

        try {
            gestoreDAO.aggiornaEsercizio(esercizio);
            System.out.println("Esercizio aggiornato.");
        } catch (SQLException e) {
            System.err.println("Errore nell'aggiornamento dell'esercizio: " + e.getMessage());
        }
    }

    private void aggiornaMacchinario() throws IOException {
        // Creazione dell'oggetto Macchinario
        int codiceEsercizio = GestoreView.getCodiceEsercizio();
        String nomeEs = GestoreView.getNomeEsercizio();
        String descrizioneEs = GestoreView.getDescrizioneEsercizio();
        String nome = GestoreView.getNomeMacchinario();
        String descrizione = GestoreView.getDescrizioneMacchinario();
        int numSerie = GestoreView.getNumSerie();
        int ripetizioni = GestoreView.getRipetizioni();

        // Creazione dell'oggetto Esercizio
        Esercizio esercizio = new Esercizio(codiceEsercizio, nomeEs, descrizioneEs, numSerie, ripetizioni);
        Macchinario macchinario = new Macchinario(esercizio, nome, descrizione);

        try {
            gestoreDAO.aggiornaMacchinario(macchinario);
            System.out.println("Macchinario aggiornato.");
        } catch (SQLException e) {
            System.err.println("Errore nell'aggiornamento del macchinario: " + e.getMessage());
        }
    }

    private void eliminaEsercizio() throws IOException {
        // Creazione dell'oggetto Esercizio
        int codiceEsercizio = GestoreView.getCodiceEsercizio();
        String nome = GestoreView.getNomeEsercizio();
        String descrizione = GestoreView.getDescrizioneEsercizio();
        int numSerie = GestoreView.getNumSerie();
        int ripetizioni = GestoreView.getRipetizioni();
        Esercizio esercizio = new Esercizio(codiceEsercizio, nome, descrizione, numSerie, ripetizioni);

        try {
            gestoreDAO.eliminaEsercizio(esercizio);
            System.out.println("Esercizio eliminato.");
        } catch (SQLException e) {
            System.err.println("Errore nell'eliminazione dell'esercizio: " + e.getMessage());
        }
    }

    private void eliminaMacchinario() throws IOException {
        // Creazione dell'oggetto Macchinario
        int codiceEsercizio = GestoreView.getCodiceEsercizio();
        String nome = GestoreView.getNomeEsercizio();
        String descrizione = GestoreView.getDescrizioneEsercizio();
        int numSerie = GestoreView.getNumSerie();
        int ripetizioni = GestoreView.getRipetizioni();
        String nomeM = GestoreView.getNomeMacchinario();
        String descrizioneM = GestoreView.getDescrizioneMacchinario();
        Macchinario macchinario = new Macchinario(new Esercizio(codiceEsercizio, nome, descrizione, numSerie, ripetizioni), nomeM, descrizioneM);

        try {
            gestoreDAO.eliminaMacchinario(macchinario);
            System.out.println("Macchinario eliminato.");
        } catch (SQLException e) {
            System.err.println("Errore nell'eliminazione del macchinario: " + e.getMessage());
        }
    }
}
