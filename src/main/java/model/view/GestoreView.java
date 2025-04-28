package model.view;

import model.domain.Esercizio;

import java.io.IOException;
import java.util.Scanner;

public class GestoreView {

    private static final Scanner input = new Scanner(System.in);

    private GestoreView() {
    }

    public static int getOp() throws IOException {
        System.out.println("**************************");
        System.out.println("*        GESTORE         *");
        System.out.println("**************************\n");
        System.out.println("1) Inserisci un nuovo esercizio");
        System.out.println("2) Elimina un esercizio esistente");
        System.out.println("3) Inserisci un nuovo macchinario");
        System.out.println("4) Elimina un macchinario esistente");
        System.out.println("5) Esci");

        int op;

        while (true) {
            System.out.print("Scegli una operazione: ");
            try {
                op = Integer.parseInt(LoginView.reader.readLine());
                if (op >= 1 && op <= 5) break;
                System.out.println("Numero non valido.");
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido.");
            }
        }

        return op;
    }

    public static Esercizio getEsercizio() {
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Descrizione: ");
        String descrizione = input.nextLine();
        System.out.print("Numero serie: ");
        int numSerie = Integer.parseInt(input.nextLine());
        System.out.print("Ripetizioni: ");
        int ripetizioni = Integer.parseInt(input.nextLine());

        return new Esercizio(0, nome, descrizione, numSerie, ripetizioni);
    }

    public static int getCodiceEsercizio() {
        System.out.print("Inserisci il codice dell'esercizio: ");
        return Integer.parseInt(input.nextLine());
    }

    public static String getNomeMacchinario() {
        System.out.print("Inserisci il nome del macchinario: ");
        return input.nextLine();
    }

    public static String getDescrizioneMacchinario() {
        System.out.print("Inserisci la descrizione del macchinario: ");
        return input.nextLine();
    }

    public static void showOutput(String message) {
        System.out.println(message);
    }
}

