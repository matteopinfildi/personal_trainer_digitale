import controller.StartingController;
import exception.DAOException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, DAOException {
        StartingController controller = new StartingController();
        controller.start();
    }
}
