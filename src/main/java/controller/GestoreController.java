package controller;

import exception.DAOException;
import model.dao.ConnectionFactory;
import model.dao.EserciziDAO;
import model.dao.MacchinarioDAO;
import model.domain.Esercizio;
import model.domain.Macchinario;
import model.view.GestoreView;
import model.domain.Role;

import java.io.IOException;

public class GestoreController implements Controller {

    @Override
    public void start() throws IOException {
        ConnectionFactory.changeRole(Role.GESTORE);
        int op;

        while (true) {
            op = GestoreView.getOp();
            switch (op) {
                case 1 -> inserisciEsercizio();
                case 2 -> eliminaEsercizio();
                case 3 -> aggiungiMacchinario();
                case 4 -> eliminaMacchinario();
                case 5 -> System.exit(0);
            }
        }
    }

    private void inserisciEsercizio() {
        try {
            Esercizio esercizio = GestoreView.getEsercizio();
            EserciziDAO dao = new EserciziDAO();
            dao.aggiungiEsercizio(esercizio);
            GestoreView.showOutput("Esercizio inserito con successo!");
        } catch (DAOException e) {
            GestoreView.showOutput("Errore: " + e.getMessage());
        }
    }

    private void eliminaEsercizio() {
        try {
            int codiceEs = GestoreView.getCodiceEsercizio();
            EserciziDAO dao = new EserciziDAO();
            dao.eliminaEsercizio(codiceEs);
            GestoreView.showOutput("Esercizio eliminato correttamente!");
        } catch (DAOException e) {
            GestoreView.showOutput("Errore: " + e.getMessage());
        }
    }

    private void aggiungiMacchinario() {
        try {
            // Ottieni il codice dell'esercizio dalla view
            int codiceEs = GestoreView.getCodiceEsercizio();
            EserciziDAO eserciziDAO = new EserciziDAO();
            // Recupera l'esercizio dal database
            Esercizio esercizio = eserciziDAO.getEsercizioByCodice(codiceEs);

            // Ottieni le informazioni sul macchinario dalla view
            String nome = GestoreView.getNomeMacchinario();
            String descrizione = GestoreView.getDescrizioneMacchinario();

            // Crea un oggetto Macchinario
            Macchinario macchinario = new Macchinario(esercizio, nome, descrizione);

            // Crea un'istanza della DAO e inserisci il macchinario
            MacchinarioDAO macchinarioDAO = new MacchinarioDAO();
            macchinarioDAO.aggiungiMacchinario(macchinario);

            // Mostra messaggio di successo
            GestoreView.showOutput("Macchinario aggiunto correttamente!");

        } catch (DAOException e) {
            GestoreView.showOutput("Errore nell'aggiungere il macchinario: " + e.getMessage());
        }
    }

    private void eliminaMacchinario() {
        try {
            // Ottieni il nome del macchinario dalla view
            String nomeMacchinario = GestoreView.getNomeMacchinario();

            // Crea un'istanza della DAO per eliminare il macchinario
            MacchinarioDAO macchinarioDAO = new MacchinarioDAO();

            // Esegui l'eliminazione del macchinario tramite la DAO
            macchinarioDAO.eliminaMacchinario(nomeMacchinario);

            // Mostra il messaggio di successo
            GestoreView.showOutput("Macchinario eliminato correttamente!");

        } catch (DAOException e) {
            GestoreView.showOutput("Errore nell'eliminare il macchinario: " + e.getMessage());
        }
    }
}
