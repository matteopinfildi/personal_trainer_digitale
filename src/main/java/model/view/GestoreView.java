package model.view;

import model.domain.Atleta;
import model.domain.Esercizio;
import model.domain.PersonalTrainer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        System.out.println("5) Inserisci nuovo atleta");
        System.out.println("6) Inserisci nuovo personal trainer");
        System.out.println("7) Esci");

        int op;

        while (true) {
            System.out.print("Scegli una operazione: ");
            try {
                op = Integer.parseInt(LoginView.reader.readLine());
                if (op >= 1 && op <= 7) break;
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

    public static Atleta getAtleta() throws IOException {
        System.out.print("Codice Fiscale: ");
        String cf = input.nextLine().trim();

        System.out.print("Nome: ");
        String nome = input.nextLine().trim();

        System.out.print("Cognome: ");
        String cognome = input.nextLine().trim();

        LocalDate dataNascita = null;
        while (dataNascita == null) {
            System.out.print("Data di nascita (YYYY-MM-DD): ");
            String dataStr = input.nextLine().trim();
            try {
                dataNascita = LocalDate.parse(dataStr);
            } catch (DateTimeParseException e) {
                System.out.println("Formato data non valido, riprova.");
            }
        }

        return new Atleta(cf, nome, cognome, dataNascita, null);
    }

    public static PersonalTrainer getPersonalTrainer() {
        System.out.print("Codice Fiscale PT: ");
        String cf = input.nextLine().trim();

        System.out.print("Nome PT: ");
        String nome = input.nextLine().trim();

        System.out.print("Cognome PT: ");
        String cognome = input.nextLine().trim();

        return new PersonalTrainer(cf, nome, cognome);
    }

    public static void showOutput(String message) {
        System.out.println(message);
    }
}

