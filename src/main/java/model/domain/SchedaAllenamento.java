package model.domain;

import java.time.LocalDate;

public class SchedaAllenamento {
    private int idScheda;
    private Atleta atleta;
    private String descrizione;
    private boolean stato;
    private LocalDate dataArchiviazione;
    private PersonalTrainer personalTrainer;

    public SchedaAllenamento(int idScheda, Atleta atleta, String descrizione, boolean stato, LocalDate dataArchiviazione, PersonalTrainer personalTrainer) {
        this.idScheda = idScheda;
        this.atleta = atleta;
        this.descrizione = descrizione;
        this.stato = stato;
        this.dataArchiviazione = dataArchiviazione;
        this.personalTrainer = personalTrainer;
    }

    public int getIdScheda() {
        return idScheda;
    }

    public Atleta getAtleta() {
        return atleta;
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

    public PersonalTrainer getPersonalTrainer() {
        return personalTrainer;
    }

    @Override
    public String toString() {
        return "SchedaAllenamento{" +
                "idScheda=" + idScheda +
                ", atleta=" + atleta.getCfAtleta() +
                ", descrizione='" + descrizione + '\'' +
                ", stato=" + stato +
                ", dataArchiviazione=" + (dataArchiviazione != null ? dataArchiviazione : "N/A") +
                ", personalTrainer=" + personalTrainer.getCfPersonal() +
                '}';
    }
}
