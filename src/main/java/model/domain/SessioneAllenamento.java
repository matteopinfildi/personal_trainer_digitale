package model.domain;

import java.time.LocalDate;

public class SessioneAllenamento {

    private Atleta atleta;
    private LocalDate dataAllenamento;
    private int durata;

    public SessioneAllenamento(Atleta atleta, LocalDate dataAllenamento, int durata) {
        this.atleta = atleta;
        this.dataAllenamento = dataAllenamento;
        this.durata = durata;
    }

    // Getter
    public Atleta getAtleta() {
        return atleta;
    }

    public LocalDate getDataAllenamento() {
        return dataAllenamento;
    }

    public int getDurata() {
        return durata;
    }

    @Override
    public String toString() {
        return "SessioneAllenamento{" +
                "atleta=" + atleta.getCfAtleta() +
                ", dataAllenamento=" + dataAllenamento +
                ", durata=" + durata + " minuti" +
                '}';
    }

}
