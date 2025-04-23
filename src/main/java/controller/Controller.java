package controller;

import exception.DAOException;

import java.io.IOException;

public interface Controller {
    void start() throws IOException, DAOException;
}
