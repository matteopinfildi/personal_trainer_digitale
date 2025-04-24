package controller;

import exception.DAOException;
import model.dao.*;
import model.domain.*;
import model.view.AtletaView;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AtletaController implements Controller {

    @Override
    public void start() throws IOException {
        ConnectionFactory.changeRole(Role.ATLETA);
        int op;

        while (true) {
            op = AtletaView.getOp();
            switch (op) {
                case 1 -> esercizioCompletato();
                case 2 -> esercizioSaltato();
                case 3 -> visualizzaSchedaAttiva();
                case 4 -> visualizzaSchedaArchiviata();
                case 5 -> registrazioneAllenamento();
                case 6 -> stampaEsercizio();
                case 7 -> System.exit(0); // placeholder per le prossime operazioni
            }
        }
    }

    private void esercizioCompletato() {
        try {
            Interagisce interazione = AtletaView.getDatiCompletamento();
            InterazioneDAO dao = new InterazioneDAO();
            dao.completaEsercizio(interazione);
            AtletaView.showOutput("Esercizio completato con successo!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void esercizioSaltato() {
        try {
            // Ottieni i dati per l'esercizio saltato dalla view
            Interagisce interazione = AtletaView.getDatiSaltato();

            // Crea un'istanza della DAO e invoca la procedura
            InterazioneDAO dao = new InterazioneDAO();
            dao.esercizioSaltato(interazione);

            // Mostra il risultato
            AtletaView.showOutput("Esercizio saltato con successo!");

        } catch (DAOException e) {
            AtletaView.showOutput("Errore: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private SchedaAllenamentoDAO schedaDAO = new SchedaAllenamentoDAO();

    public void visualizzaSchedaAttiva() {
        try {
            String cfAtleta = AtletaView.getCfAtleta();
            SchedaAllenamento scheda = schedaDAO.visualizzaSchedaAttiva(cfAtleta);
            AtletaView.showOutput("Scheda attiva: " + scheda.getDescrizione() +
                    " - Personal Trainer: " + scheda.getCfPersonal());
        } catch (DAOException e) {
            AtletaView.showOutput("Errore: " + e.getMessage());
        } catch (SQLException e) {
            AtletaView.showOutput("Errore nel database: " + e.getMessage());
        }
    }

    public void visualizzaSchedaArchiviata() {
        try {
            // Ottieni il codice fiscale dell'atleta dalla view
            String cfAtleta = AtletaView.getCfAtleta(); // Aggiunta la chiamata per ottenere il codice fiscale

            // Recupera la scheda archiviata per l'atleta
            SchedaAllenamento scheda = schedaDAO.visualizzaSchedaArchiviata(cfAtleta);

            // Mostra la scheda archiviata
            AtletaView.showOutput("Scheda archiviata: " + scheda.getDescrizione() +
                    " - Data Archiviazione: " + scheda.getDataArchiviazione());
        } catch (DAOException e) {
            AtletaView.showOutput("Errore: " + e.getMessage());
        } catch (SQLException e) {
            AtletaView.showOutput("Errore nel database: " + e.getMessage());
        }
    }

    public void registrazioneAllenamento() {
        try {
            String cfAtleta = AtletaView.getCfAtleta(); // Ottieni il codice fiscale dell'atleta
            LocalDate dataAllenamento = AtletaView.getDataAllenamento(); // Ottieni la data dell'allenamento
            int durata = AtletaView.getDurataAllenamento(); // Ottieni la durata dell'allenamento

            // Crea un oggetto SessioneAllenamento
            SessioneAllenamento sessione = new SessioneAllenamento(new Atleta(cfAtleta), dataAllenamento, durata);

            // Crea un'istanza della DAO e invoca la procedura
            SessioneAllenamentoDAO dao = new SessioneAllenamentoDAO();
            dao.registrazioneAllenamento(sessione);

            // Mostra il risultato
            AtletaView.showOutput("Sessione di allenamento registrata con successo!");

        } catch (DAOException e) {
            AtletaView.showOutput("Errore: " + e.getMessage());
        }
    }

    public static void stampaEsercizio() {
        // Chiediamo il codice dell'esercizio all'utente
        int codiceEsercizio = AtletaView.getCodiceEsercizio();

        // Passiamo il codice esercizio alla DAO per ottenere i dettagli
        EserciziDAO dao = new EserciziDAO();
        String result = dao.getDettagliEsercizio(codiceEsercizio);

        // Mostriamo il risultato ottenuto
        AtletaView.showOutput(result);
    }
}
