package controller;

import model.dao.ConnectionFactory;
import model.domain.Role;
import model.dao.AtletaDAO;
import model.view.AtletaView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AtletaController implements Controller {
    private final AtletaDAO atletaDAO;

    public AtletaController() throws SQLException {
        ConnectionFactory.changeRole(Role.ATLETA);
        Connection connection = ConnectionFactory.getConnection();
        this.atletaDAO = new AtletaDAO(connection);
    }

    @Override
    public void start() throws IOException {
        ConnectionFactory.changeRole(Role.ATLETA);

        int op;

        while (true) {
            op = AtletaView.getOp();
            switch (op) {
                case 1:
                    this.exerciseCompleted();
                    break;
                case 2:
                    this.exerciseSkipped();
                    break;
                case 3:
                    this.viewActiveTrainingCard();
                    break;
                case 4:
                    this.viewArchivedTrainingCard();
                    break;
                case 5:
                    this.recordTraining();
                    break;
                case 6:
                    this.viewExercise();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Operazione non valida");
                    break;
            }
        }
    }

    private void exerciseCompleted() throws IOException {
        String cfAtleta = AtletaView.getCFAtleta();
        String data = AtletaView.getData();
        int codiceEsercizio = AtletaView.getCodiceEsercizio();
        try {
            atletaDAO.esercizioCompletato(cfAtleta, data, codiceEsercizio);
            System.out.println("Esercizio completato.");
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }

    private void exerciseSkipped() throws IOException {
        String cfAtleta = AtletaView.getCFAtleta();
        String data = AtletaView.getData();
        int codiceEsercizio = AtletaView.getCodiceEsercizio();
        try {
            atletaDAO.esercizioSaltato(cfAtleta, data, codiceEsercizio);
            System.out.println("Esercizio saltato.");
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }

    private void viewActiveTrainingCard() throws IOException {
        String cfAtleta = AtletaView.getCFAtleta();
        try {
            atletaDAO.visualizzaSchedaAttiva(cfAtleta);
            System.out.println("Scheda attiva visualizzata");
        } catch (SQLException e) {
            System.err.println("Errore durante la visualizzazione della scheda attiva: " + e.getMessage());
        }
    }

    private void viewArchivedTrainingCard() throws IOException {
        String cfAtleta = AtletaView.getCFAtleta();

        try {
            atletaDAO.visualizzaSchedaArchiviata(cfAtleta);
            System.out.println("Scheda archiviata visualizzata");
        } catch (SQLException e) {
            System.err.println("Errore durante la visualizzazione della scheda archiviata: " + e.getMessage());
        }
    }

    private void recordTraining() throws IOException {
        String cfAtleta = AtletaView.getCFAtleta();
        String data = AtletaView.getData();
        int durata = AtletaView.getDurata();
        try {
            atletaDAO.registrazioneAllenamento(cfAtleta, data, durata);  // Chiamata corretta
            System.out.println("✅ Allenamento registrato.");
        } catch (SQLException e) {
            System.err.println("Errore durante la registrazione dell'allenamento: " + e.getMessage());
        }
    }

    private void viewExercise() throws IOException {
        int codiceEsercizio = AtletaView.getCodiceEsercizio();
        try {
            atletaDAO.stampaEsercizio(codiceEsercizio);
            System.out.println("Esercizio visualizzato");
        } catch (SQLException e) {
            System.err.println("Errore durante la visualizzazione dell'esercizio': " + e.getMessage());
        }
    }
}
