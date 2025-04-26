package model.view;

import model.domain.Report;
import model.domain.SchedaAllenamento;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import static model.view.LoginView.reader;

public class PersonalTrainerView {


    private static final Scanner input = new Scanner(System.in);

    private PersonalTrainerView() {
    }

    public static int getOp() throws IOException {
        System.out.println("**************************");
        System.out.println("*  PERSONAL TRAINER  *");
        System.out.println("**************************\n");
        System.out.println("Puoi eseguire queste operazioni:");
        System.out.println("1) Assegna Personal Trainer");
        System.out.println("2) Crea Scheda Attiva");
        System.out.println("3) Associa Esercizio a Scheda");
        System.out.println("4) Archivia Scheda Attiva");
        System.out.println("5) Genera Report");
        System.out.println("6) Esci");

        int op;

        while (true) {
            System.out.print("Scegli una operazione: ");
            try {
                op = Integer.parseInt(reader.readLine());
                if (op >= 1 && op <= 7) break;
                System.out.println("Il numero che hai inserito non corrisponde ad alcuna operazione.\n");
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido.\n");
            }
        }
        return op;
    }

    public static String getCfAtleta() {
        System.out.print("Inserisci il codice fiscale dell'atleta: ");
        return input.nextLine();
    }

    public static String getCfPersonal() {
        System.out.print("Inserisci il codice fiscale del Personal Trainer: ");
        return input.nextLine();
    }

    public static SchedaAllenamento getScheda() {
        System.out.print("Codice fiscale del personal trainer: ");
        String cfPersonal = input.nextLine();

        System.out.print("Codice fiscale dell'atleta: ");
        String cfAtleta = input.nextLine();

        System.out.print("Descrizione della scheda: ");
        String descrizione = input.nextLine();


        return new SchedaAllenamento(0, cfPersonal, cfAtleta, descrizione, true, null);
    }

    public static int getCodiceEsercizio() {
        System.out.print("Inserisci il codice dell'esercizio: ");
        return Integer.parseInt(input.nextLine());
    }

    public static int getIdScheda() {
        System.out.print("Inserisci l'id della scheda: ");
        return Integer.parseInt(input.nextLine());
    }

    public static int getIdSchedaDaArchiviare() {
        System.out.print("Inserisci l'ID della scheda da archiviare: ");
        return Integer.parseInt(input.nextLine());
    }

    public static void showOutput(String message) {
        // Stampa il messaggio passato come argomento
        System.out.println(message);
    }

    public static Date getData(String tipo) {
        System.out.print("Inserisci " + tipo + " (YYYY-MM-DD): ");
        return Date.valueOf(input.nextLine());
    }


    public static void showReport(List<Report> report) {
        if (report.isEmpty()) {
            System.out.println("Nessun dato trovato per l'intervallo specificato.");
            return;
        }

        for (Report r : report) {
            System.out.println("Atleta: " + r.getNomeAtleta() + " (" + r.getCfAtleta() + ")");
            System.out.println("Data allenamento: " + r.getDataAllenamento());
            System.out.println("Durata: " + r.getDurata() + " minuti");
            System.out.println("Scheda [" + r.getIdScheda() + "]: " + r.getDescrizioneScheda());
            System.out.println("Esercizi totali: " + r.getTotaleEsercizi());
            System.out.println("Completati: " + r.getEserciziCompletati() + ", Saltati: " + r.getEserciziSaltati());
            System.out.println("Percentuale completamento: " + r.getPercentualeCompletamento() + "%");
            System.out.println("--------------------------------------");
        }
    }

    public static String getDataInizio() throws IOException {
        System.out.print("Inserisci la data di inizio (YYYY-MM-DD): ");
        return reader.readLine();
    }

    public static String getDataFine() throws IOException {
        System.out.print("Inserisci la data di fine (YYYY-MM-DD): ");
        return reader.readLine();
    }

}



