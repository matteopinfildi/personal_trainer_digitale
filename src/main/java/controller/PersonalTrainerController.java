package controller;

import exception.DAOException;
import model.dao.*;
import model.domain.Report;
import model.domain.SchedaAllenamento;
import model.view.PersonalTrainerView;
import model.domain.Role;

import java.io.IOException;
import java.util.List;

public class PersonalTrainerController implements Controller {

    @Override
    public void start() throws IOException {
        ConnectionFactory.changeRole(Role.PERSONAL);
        int op;

        while (true) {
            op = PersonalTrainerView.getOp();
            switch (op) {
                case 1 -> assegnazionePersonalTrainer();
                case 2 -> creazioneSchedaAttiva();
                case 3 -> associaEsercizioScheda();
                case 4 -> archiviaSchedaAttiva();
                case 5 -> generaReport();
                case 6 -> System.exit(0);
            }
        }
    }

    private void assegnazionePersonalTrainer() {
        try {
            // Prendi i dati dal view
            String cfAtleta = PersonalTrainerView.getCfAtleta();
            String cfPersonal = PersonalTrainerView.getCfPersonal();

            // Aggiungi la logica per l'assegnazione nel DAO
            AssegnaPersonalDAO dao = new AssegnaPersonalDAO();
            dao.assegnaPersonal(cfAtleta, cfPersonal);

            // Risultato operazione
            PersonalTrainerView.showOutput("Personal Trainer assegnato con successo!");
        } catch (DAOException e) {
            PersonalTrainerView.showOutput("Errore: " + e.getMessage());
        }
    }

    private void creazioneSchedaAttiva() {
        try {
            SchedaAllenamento scheda = PersonalTrainerView.getScheda(); // input da utente
            SchedaAllenamentoDAO dao = new SchedaAllenamentoDAO();
            dao.creazioneSchedaAttiva(scheda);
            PersonalTrainerView.showOutput("Scheda attiva creata con successo!");
        } catch (DAOException e) {
            PersonalTrainerView.showOutput(e.getMessage());
        }
    }

    private void associaEsercizioScheda() {
        try {
            // Richiesta dei dati all'utente
            int codiceEs = PersonalTrainerView.getCodiceEsercizio();
            int idScheda = PersonalTrainerView.getIdScheda();

            // Creazione del DAO e chiamata del metodo
            AssociaEsercizioSchedaDAO dao = new AssociaEsercizioSchedaDAO();
            dao.associaEsercizioScheda(codiceEs, idScheda);

            // Output successivo
            PersonalTrainerView.showOutput("Esercizio associato alla scheda con successo!");
        } catch (DAOException e) {
            PersonalTrainerView.showOutput(e.getMessage());
        }
    }

    private void archiviaSchedaAttiva() {
        try {
            int idScheda = PersonalTrainerView.getIdSchedaDaArchiviare();
            new SchedaAllenamentoDAO().archiviaSchedaAttiva(idScheda);
            PersonalTrainerView.showOutput("Scheda archiviata con successo!");
        } catch (DAOException e) {
            PersonalTrainerView.showOutput(e.getMessage());
        }
    }

    private void generaReport() {
        try {
            // Chiedi all'utente le date per il report
            String dataInizioStr = PersonalTrainerView.getDataInizio();
            String dataFineStr = PersonalTrainerView.getDataFine();
            String cfPersonal = PersonalTrainerView.getCfPersonal();

            // Converti le stringhe in oggetti java.sql.Date
            java.sql.Date dataInizio = java.sql.Date.valueOf(dataInizioStr);
            java.sql.Date dataFine = java.sql.Date.valueOf(dataFineStr);

            // Crea una connessione al database per generare il report
            ReportDAO dao = new ReportDAO();
            List<Report> report = dao.generaReport(dataInizio, dataFine, cfPersonal);

            // Mostra il report all'utente
            PersonalTrainerView.showReport(report);
        } catch (DAOException e) {
            PersonalTrainerView.showOutput(e.getMessage());
        } catch (IllegalArgumentException e) {
            PersonalTrainerView.showOutput("Formato data non valido. Usa il formato YYYY-MM-DD.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
