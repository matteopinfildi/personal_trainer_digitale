package model.domain;

import java.time.LocalDate;

public class SchedaAllenamento {

    private final int idScheda;
    private final String cfAtleta;
    private final String descrizione;
    private final boolean stato;
    private final LocalDate dataArchiviazione;
    private final String cfPersonal;

    public SchedaAllenamento(int idScheda,  String cfPersonal, String cfAtleta, String descrizione, boolean stato, LocalDate dataArchiviazione) {
        this.idScheda = idScheda;
        this.cfAtleta = cfAtleta;
        this.descrizione = descrizione;
        this.stato = stato;
        this.dataArchiviazione = dataArchiviazione;
        this.cfPersonal = cfPersonal;
    }


    public int getIdScheda() {
        return idScheda;
    }

    public String getCfAtleta() {
        return cfAtleta;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public boolean isStato() {
        return stato;
    }

    public LocalDate getDataArchiviazione() {
        return dataArchiviazione;
    }

    public String getCfPersonal() {
        return cfPersonal;
    }

    @Override
    public String toString() {
        return "SchedaAllenamento{" +
                "idScheda=" + idScheda +
                ", cfAtleta='" + cfAtleta + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", stato=" + stato +
                ", dataArchiviazione=" + dataArchiviazione +
                ", cfPersonal='" + cfPersonal + '\'' +
                '}';
    }
}

