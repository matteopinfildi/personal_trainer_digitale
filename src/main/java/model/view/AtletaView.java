package model.view;

import java.io.IOException;

public class AtletaView {

    private AtletaView(){}

    public static int getOp() throws IOException {
        System.out.println("**************************");
        System.out.println("*       ATLETA       *");
        System.out.println("**************************\n");
        System.out.println("Puoi eseguire queste operazioni:");
        System.out.println("1) Segna esercizio come completato");
        System.out.println("2) Segna esercizio come saltato");
        System.out.println("3) Visualizza scheda di allenamento attiva");
        System.out.println("4) Visualizza scheda di allenamento archiviata");
        System.out.println("5) Registra una nuova sessione di allenamento");
        System.out.println("6) Visualizza esercizio");
        System.out.println("7) Esci");

        int op;

        while (true) {
            System.out.print("Scegli una operazione: ");

            op = Integer.parseInt(LoginView.reader.readLine());

            if (op >= 1 && op <= 12) break;
            System.out.println("Il numero che hai inserito non corrisponde ad alcuna operazione.\n");
        }
        return op;
    }
}
