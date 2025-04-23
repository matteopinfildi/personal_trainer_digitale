package model.view;

import java.io.IOException;

public class PersonalTrainerView {

    private PersonalTrainerView(){}

    public static int getOp() throws IOException {
        System.out.println("**************************");
        System.out.println("*  PERSONAL TRAINER  *");
        System.out.println("**************************\n");
        System.out.println("Puoi eseguire queste operazioni:");
        System.out.println("1) Assegna Personal Trainer");
        System.out.println("2) Crea Scheda Attiva");
        System.out.println("3) Associa Esercizio a Scheda");
        System.out.println("4) Archivia Scheda Attiva");
        System.out.println("5) Modifica Dati");
        System.out.println("6) Genera Report");
        System.out.println("7) Esci");

        int op;

        while (true) {
            System.out.print("Scegli una operazione: ");
            op = Integer.parseInt(LoginView.reader.readLine());

            if (op >= 1 && op <= 7) break;
            System.out.println("Il numero che hai inserito non corrisponde ad alcuna operazione.\n");
        }
        return op;
    }

    // Metodi di input analoghi a quelli usati per l'atleta
    public static String getCFAtleta() throws IOException {
        System.out.print("Inserisci codice fiscale dell'atleta: ");
        return LoginView.reader.readLine();
    }

    public static String getCFPersonal() throws IOException {
        System.out.print("Inserisci il tuo codice fiscale (personal trainer): ");
        return LoginView.reader.readLine();
    }


    public static int getIDEsercizio() throws IOException {
        System.out.print("Inserisci ID dell'esercizio: ");
        return Integer.parseInt(LoginView.reader.readLine());
    }

    public static int getIDScheda() throws IOException {
        System.out.print("Inserisci ID della scheda: ");
        return Integer.parseInt(LoginView.reader.readLine());
    }

    public static String getDescrizioneScheda() throws IOException {
        System.out.print("Inserisci la descrzione della scheda: ");
        return LoginView.reader.readLine();
    }

}


