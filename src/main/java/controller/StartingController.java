package controller;

import model.domain.Credentials;

import java.io.IOException;

public class StartingController implements Controller{

    private Credentials credentials;
    @Override
    public void start() throws IOException {
        LoginController loginController = new LoginController();
        loginController.start();

        this.credentials = loginController.getCredentials();

        switch(this.credentials.getRole()){
            case PERSONAL -> {
                PersonalTrainerController controller = new PersonalTrainerController();
                controller.start();
            }
            case ATLETA -> {
                AtletaController controller = new AtletaController();
                controller.start();
            }
            case GESTORE -> {
                GestoreController controller = new GestoreController();
                controller.start();
            }
            default -> throw new RuntimeException("Invalid credentials");
        }
    }
}
