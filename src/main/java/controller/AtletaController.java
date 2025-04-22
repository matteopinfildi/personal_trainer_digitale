package controller;

import model.dao.ConnectionFactory;
import model.domain.Role;

import java.io.IOException;

public class AtletaController implements Controller {
    @Override
    public void start() throws IOException {
        ConnectionFactory.changeRole(Role.ATLETA);

        int op ;

//        while(true) {
//            op = AtletaView.getOp();
//            switch (op){
//                case 1 -> this.exerciseCompleted();
//                case 2 -> this.exerciseSkipped();
//                case 3 -> this.viewActiveTrainingCard();
//                case 4 -> this.viewArchivedTrainingCard();
//                case 5 -> this.recordTraining();
//                case 6 -> this.viewExercise();
//                case 7 -> System.exit(0);
//            }
//        }
    }
}
