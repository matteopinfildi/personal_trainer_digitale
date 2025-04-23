package controller;

import exception.DAOException;
import model.dao.ConnectionFactory;
import model.dao.PersonalTrainerDAO;
import model.domain.Role;
import model.view.PersonalTrainerView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PersonalTrainerController implements Controller {
    private final PersonalTrainerDAO personalDAO;

    public PersonalTrainerController() throws SQLException {
        ConnectionFactory.changeRole(Role.PERSONAL);
        Connection connection = ConnectionFactory.getConnection();
        this.personalDAO = new PersonalTrainerDAO(connection);
    }

    @Override
    public void start() throws IOException, DAOException {
        ConnectionFactory.changeRole(Role.PERSONAL);

        int op;

        while (true) {
            op = PersonalTrainerView.getOp();
            switch (op) {
                case 1:
                    this.assignToAthlete();
                    break;
                case 2:
                    this.createTrainingCard();
                    break;
                case 3:
                    this.addExerciseToCard();
                    break;
                case 4:
                    this.archiveTrainingCard();
                    break;
                case 5:
                    this.modifyPersonal();
                    break;
                case 6:
                    this.generateReport();
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

    private void assignToAthlete() throws IOException {
        String cfAtleta = PersonalTrainerView.getCFAtleta();
        String cfPersonal = PersonalTrainerView.getCFPersonal();
        try {
            personalDAO.assegnaPersonal(cfAtleta, cfPersonal);
            System.out.println("Personal trainer assegnato.");
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }

    private void createTrainingCard() throws IOException, DAOException {
        String cfAtleta = PersonalTrainerView.getCFAtleta();
        String descrizione = PersonalTrainerView.getDescrizioneScheda();
        personalDAO.creazioneSchedaAttiva(cfAtleta, descrizione);
        System.out.println("Scheda attiva creata.");
    }

    private void addExerciseToCard() throws IOException,DAOException {
        int idScheda = PersonalTrainerView.getIDScheda();
        int codiceEsercizio = PersonalTrainerView.getIDEsercizio();
        personalDAO.associaEsercizioScheda(idScheda, codiceEsercizio);
        System.out.println("Esercizio associato alla scheda.");
    }

    private void archiveTrainingCard() throws IOException, DAOException {
        String cfAtleta = PersonalTrainerView.getCFAtleta();
        try {
            personalDAO.archiviaSchedaAttiva(cfAtleta);
            System.out.println("Scheda attiva archiviata.");
        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }

    private void modifyPersonal() throws IOException, DAOException {
        String cfPersonal = PersonalTrainerView.getCFPersonal();
        personalDAO.modificaPersonal(cfPersonal);
        System.out.println("Recapito modificato.");
    }

    private void generateReport() throws IOException, DAOException {
        String cfAtleta = PersonalTrainerView.getCFAtleta();
        personalDAO.generaReport(cfAtleta);
        System.out.println("Report generato.");
    }
}
