package model.view;

import model.domain.Credentials;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginView {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Credentials getCredentials() throws IOException {
        System.out.print("Insert username: ");
        String username = reader.readLine();
        System.out.print("Insert password: ");
        String password = reader.readLine();
        return new Credentials(username, password, null);
    }
}
