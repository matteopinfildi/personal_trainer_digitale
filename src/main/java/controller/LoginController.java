package controller;

import exception.DAOException;
import model.dao.ConnectionFactory;
import model.dao.LoginProcedureDAO;
import model.domain.Credentials;
import model.domain.Role;
import model.view.LoginView;

import java.io.IOException;

public class LoginController implements Controller{
    private Credentials credentials;
    @Override
    public void start(){
        ConnectionFactory.changeRole(Role.LOGIN);
        try{
            this.credentials = LoginView.getCredentials();
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        try{
            this.credentials = new LoginProcedureDAO().logIn(this.credentials.getUsername(), this.credentials.getPassword());
        } catch(DAOException e){
            System.out.println(e.getMessage());
        }
    }

    public Credentials getCredentials(){
        return this.credentials;
    }
}
