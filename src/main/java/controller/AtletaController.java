package controller;

import exception.DAOException;
import model.dao.*;
import model.domain.*;
import model.view.AtletaView;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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
                case 7 -> System.exit(0);
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
            Interagisce interazione = AtletaView.getDatiSaltato();

            InterazioneDAO dao = new InterazioneDAO();
            dao.esercizioSaltato(interazione);

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
            StringBuilder output = new StringBuilder();
            output.append("=== SCHEDA ATTIVA ===\n")
                    .append("Descrizione: ").append(scheda.getDescrizione()).append("\n")
                    .append("Personal Trainer: ").append(scheda.getCfPersonal()).append("\n")
                    .append("Esercizi:\n");

            for (Esercizio esercizio : scheda.getEsercizi()) {
                output.append("- ").append(esercizio.getNome())
                        .append(": ").append(esercizio.getNumSerie())
                        .append(" serie x ").append(esercizio.getRipetizioni())
                        .append(" ripetizioni\n");
            }

            AtletaView.showOutput(output.toString());

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


            List<SchedaAllenamento> schede = schedaDAO.visualizzaSchedaArchiviata(cfAtleta);


            if (schede.isEmpty()) {
                AtletaView.showOutput("Nessuna scheda archiviata trovata");
                return;
            }

            StringBuilder output = new StringBuilder();

            for (SchedaAllenamento scheda : schede) {
                output.append("\n=== SCHEDA ARCHIVIATA ===\n")
                        .append("Descrizione: ").append(scheda.getDescrizione()).append("\n")
                        .append("Data archiviazione: ").append(scheda.getDataArchiviazione()).append("\n")
                        .append("Esercizi:\n");

                for (Esercizio esercizio : scheda.getEsercizi()) {
                    output.append("- ").append(esercizio.getNome()).append(":\n")
                            .append("  Serie: ").append(esercizio.getNumSerie()).append(" x ")
                            .append(esercizio.getRipetizioni()).append(" ripetizioni\n")
                            .append("  Descrizione: ").append(esercizio.getDescrizione()).append("\n\n");
                }
            }

            AtletaView.showOutput(output.toString());

        } catch (DAOException e) {
            AtletaView.showOutput("Errore: " + e.getMessage());
        } catch (SQLException e) {
            AtletaView.showOutput("Errore nel database: " + e.getMessage());
        }
    }

    public void registrazioneAllenamento() {
        try {
            String cfAtleta = AtletaView.getCfAtleta();
            LocalDate dataAllenamento = AtletaView.getDataAllenamento();
            int durata = AtletaView.getDurataAllenamento();

            SessioneAllenamento sessione = new SessioneAllenamento(new Atleta(cfAtleta), dataAllenamento, durata);

            SessioneAllenamentoDAO dao = new SessioneAllenamentoDAO();
            dao.registrazioneAllenamento(sessione);

            AtletaView.showOutput("Sessione di allenamento registrata con successo!");

        } catch (DAOException e) {
            AtletaView.showOutput("Errore: " + e.getMessage());
        }
    }

    public static void stampaEsercizio() {
        int codiceEsercizio = AtletaView.getCodiceEsercizio();

        EserciziDAO dao = new EserciziDAO();
        String result = dao.getDettagliEsercizio(codiceEsercizio);

        AtletaView.showOutput(result);
    }
}
