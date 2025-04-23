package model.view;

import java.util.Scanner;

public class GestoreView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getOp() {
        System.out.println("**************************");
        System.out.println("*  GESTORE  *");
        System.out.println("**************************\n");
        System.out.println("Puoi eseguire queste operazioni:");
        System.out.println("1) Aggiornare esercizio");
        System.out.println("2) Aggiornare macchinario");
        System.out.println("3) Eliminare esercizio");
        System.out.println("4) Eliminare macchinario");
        System.out.println("5) Esci");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int getCodiceEsercizio() {
        System.out.print("Inserisci codice esercizio: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static String getNomeEsercizio() {
        System.out.print("Inserisci nome esercizio: ");
        return scanner.nextLine();
    }

    public static String getDescrizioneEsercizio() {
        System.out.print("Inserisci descrizione esercizio: ");
        return scanner.nextLine();
    }

    public static int getNumSerie() {
        System.out.print("Inserisci il numero di serie per l'esercizio: ");
        return Integer.parseInt(scanner.nextLine());
    }
    public static int getRipetizioni() {
        System.out.print("Inserisci il numero di ripetizioni per l'esercizio: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static String getNomeMacchinario() {
        System.out.print("Inserisci nome macchinario: ");
        return scanner.nextLine();
    }

    public static String getDescrizioneMacchinario() {
        System.out.print("Inserisci descrizione macchinario: ");
        return scanner.nextLine();
    }
}
