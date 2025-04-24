package model.domain;

import java.time.LocalDate;

public class Report {
    private final String cfAtleta;
    private final String nomeAtleta;
    private final LocalDate dataAllenamento;
    private final int durata;
    private final int idScheda;
    private final String descrizioneScheda;
    private final int totaleEsercizi;
    private final int eserciziCompletati;
    private final int eserciziSaltati;
    private final double percentualeCompletamento;

    public Report(String cfAtleta, String nomeAtleta, LocalDate dataAllenamento, int durata,
                             int idScheda, String descrizioneScheda, int totaleEsercizi,
                             int eserciziCompletati, int eserciziSaltati, double percentualeCompletamento) {
        this.cfAtleta = cfAtleta;
        this.nomeAtleta = nomeAtleta;
        this.dataAllenamento = dataAllenamento;
        this.durata = durata;
        this.idScheda = idScheda;
        this.descrizioneScheda = descrizioneScheda;
        this.totaleEsercizi = totaleEsercizi;
        this.eserciziCompletati = eserciziCompletati;
        this.eserciziSaltati = eserciziSaltati;
        this.percentualeCompletamento = percentualeCompletamento;
    }

    public String getCfAtleta() {
        return cfAtleta;
    }

    public String getNomeAtleta() {
        return nomeAtleta;
    }

    public LocalDate getDataAllenamento() {
        return dataAllenamento;
    }

    public int getDurata() {
        return durata;
    }

    public int getIdScheda() {
        return idScheda;
    }

    public String getDescrizioneScheda() {
        return descrizioneScheda;
    }

    public int getTotaleEsercizi() {
        return totaleEsercizi;
    }

    public int getEserciziCompletati() {
        return eserciziCompletati;
    }

    public int getEserciziSaltati() {
        return eserciziSaltati;
    }

    public double getPercentualeCompletamento() {
        return percentualeCompletamento;
    }
}
