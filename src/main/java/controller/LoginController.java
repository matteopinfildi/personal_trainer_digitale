package controller;

import model.domain.Credentials;
import model.domain.Role;

import java.io.IOException;

public class LoginController implements Controller{
    private Credentials credentials;
    @Override
    public void start(){
//        ConnectionFactory.changeRole(Role.LOGIN);
//        try{
//            this.credentials = LogInView.getCredentials();
//        } catch(IOException e){
//            throw new RuntimeException(e);
//        }
//
//        try{
//            this.credentials = new LogInProcedureDAO().logIn(this.credentials.getUsername(), this.credentials.getPassword());
//        } catch(DAOException e){
//            System.out.println(e.getMessage());
//        }
    }

    public Credentials getCredentials(){
        return this.credentials;
    }
}
