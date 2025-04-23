package controller;

import exception.DAOException;
import model.domain.Credentials;

import java.io.IOException;
import java.sql.SQLException;

public class StartingController implements Controller {
    private Credentials credentials;

    @Override
    public void start() throws IOException, DAOException {
        LoginController loginController = new LoginController();
        loginController.start();

        this.credentials = loginController.getCredentials();

        try {
            switch (this.credentials.getRole()) {
                case ATLETA:
                    AtletaController atletaController = new AtletaController();
                    atletaController.start();
                    break;
                case PERSONAL:
                    PersonalTrainerController personalTrainerController = new PersonalTrainerController();
                    personalTrainerController.start();
                    break;
                case GESTORE:
                    GestoreController gestoreController= new GestoreController();
                    gestoreController.start();
                    break;
                default:
                    System.out.println("Ruolo non valido");
                    break;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database: " + e.getMessage());
        }

    }
}
