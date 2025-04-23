package model.dao;

import exception.DAOException;
import model.domain.Credentials;
import model.domain.Role;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginProcedureDAO {
    public Credentials logIn(String username, String password) throws DAOException {
        String codiceFiscale;
        int role;

        try{
            Connection connection = ConnectionFactory.getConnection();
            CallableStatement cs = connection.prepareCall("{call login(?, ?, ?)}");
            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.execute();
            role = cs.getInt(3);
        } catch(SQLException e){
            throw new DAOException("Login error: " + e.getMessage());
        }

        return new Credentials(username, password, Role.fromInt(role));
    }
}
