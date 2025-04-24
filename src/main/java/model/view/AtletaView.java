package model.view;

import model.domain.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AtletaView {

    private static final Scanner scanner = new Scanner(System.in);

    private AtletaView() {}

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

            if (op >= 1 && op <= 7) break;
            System.out.println("Il numero che hai inserito non corrisponde ad alcuna operazione.\n");
        }
        return op;
    }

    public static Interagisce getDatiCompletamento() {
        System.out.print("Inserisci CF Atleta: ");
        String cfAtleta = scanner.nextLine();

        System.out.print("Inserisci codice esercizio: ");
        int codiceEs = Integer.parseInt(scanner.nextLine());

        System.out.print("Inserisci data allenamento (YYYY-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());

        Atleta atleta = new Atleta(cfAtleta);
        Esercizio esercizio = new Esercizio(codiceEs);
        SessioneAllenamento sessione = new SessioneAllenamento(atleta, data);

        return new Interagisce(atleta, sessione, esercizio, false, true);
    }

    public static Interagisce getDatiSaltato() {
        System.out.print("Inserisci il codice fiscale dell'atleta: ");
        String cfAtleta = scanner.next();

        System.out.print("Inserisci il codice dell'esercizio: ");
        int codiceEs = scanner.nextInt();

        System.out.print("Inserisci la data dell'allenamento (yyyy-mm-dd): ");
        String dataAll = scanner.next();


        LocalDate dataAllenamento = LocalDate.parse(dataAll, DateTimeFormatter.ISO_LOCAL_DATE);

        // Creazione di oggetti per SessioneAllenamento, Atleta, Esercizio (usati nell'esempio)
        Atleta atleta = new Atleta(cfAtleta);
        SessioneAllenamento sessione = new SessioneAllenamento(atleta, dataAllenamento);
        Esercizio esercizio = new Esercizio(codiceEs);

        return new Interagisce(atleta, sessione, esercizio, true, false);  // Saltato=true, Contrassegnato=false
    }

    public static String getCfAtleta() {
        System.out.print("Inserisci il codice fiscale dell'atleta: ");
        return scanner.next();
    }

    public static LocalDate getDataAllenamento() {
        System.out.print("Inserisci la data dell'allenamento (yyyy-mm-dd): ");
        String dataAll = scanner.next();
        return LocalDate.parse(dataAll, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static int getDurataAllenamento() {
        System.out.print("Inserisci la durata dell'allenamento (in minuti): ");
        return scanner.nextInt();
    }

    public static int getCodiceEsercizio() {
        System.out.print("Inserisci il codice dell'esercizio: ");
        return scanner.nextInt();
    }


    public static void showOutput(String msg) {
        System.out.println(msg);
    }

}
