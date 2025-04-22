package model.domain;

public class Contenuto {

    private Esercizio esercizio;
    private SchedaAllenamento schedaAllenamento;

    public Contenuto(Esercizio esercizio, SchedaAllenamento schedaAllenamento) {
        this.esercizio = esercizio;
        this.schedaAllenamento = schedaAllenamento;
    }

    public Esercizio getEsercizio() {
        return esercizio;
    }

    public SchedaAllenamento getSchedaAllenamento() {
        return schedaAllenamento;
    }

    @Override
    public String toString() {
        return "Contenuto{" +
                "esercizio=" + esercizio.getCodiceEs() +
                ", scheda=" + schedaAllenamento.getIdScheda() +
                '}';
    }
}
