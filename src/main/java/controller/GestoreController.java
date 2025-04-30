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
            int codiceEs = GestoreView.getCodiceEsercizio();

            String nome = GestoreView.getNomeMacchinario();
            String descrizione = GestoreView.getDescrizioneMacchinario();

            Esercizio esercizio = new Esercizio(codiceEs);

            Macchinario macchinario = new Macchinario(esercizio, nome, descrizione);
            MacchinarioDAO macchinarioDAO = new MacchinarioDAO();
            macchinarioDAO.aggiungiMacchinario(macchinario);

            GestoreView.showOutput("Macchinario aggiunto correttamente!");
        } catch (DAOException e) {
            GestoreView.showOutput("Errore nell'aggiungere il macchinario: " + e.getMessage());
        }
    }

    private void eliminaMacchinario() {
        try {
            String nomeMacchinario = GestoreView.getNomeMacchinario();

            MacchinarioDAO macchinarioDAO = new MacchinarioDAO();

            macchinarioDAO.eliminaMacchinario(nomeMacchinario);

            GestoreView.showOutput("Macchinario eliminato correttamente!");

        } catch (DAOException e) {
            GestoreView.showOutput("Errore nell'eliminare il macchinario: " + e.getMessage());
        }
    }
}
