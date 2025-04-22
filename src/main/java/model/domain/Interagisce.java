package model.domain;

public class Interagisce {

    private Atleta atleta;
    private SessioneAllenamento sessioneAllenamento;
    private Esercizio esercizio;
    private boolean saltato;
    private boolean contrassegnato;

    public Interagisce(Atleta atleta, SessioneAllenamento sessioneAllenamento, Esercizio esercizio, boolean saltato, boolean contrassegnato) {
        this.atleta = atleta;
        this.sessioneAllenamento = sessioneAllenamento;
        this.esercizio = esercizio;
        this.saltato = saltato;
        this.contrassegnato = contrassegnato;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public SessioneAllenamento getSessioneAllenamento() {
        return sessioneAllenamento;
    }

    public Esercizio getEsercizio() {
        return esercizio;
    }

    public boolean isSaltato() {
        return saltato;
    }

    public boolean isContrassegnato() {
        return contrassegnato;
    }

    @Override
    public String toString() {
        return "Interagisce{" +
                "atleta=" + atleta.getCfAtleta() +
                ", sessioneAllenamento=" + sessioneAllenamento.getDataAllenamento() +
                ", esercizio=" + esercizio.getCodiceEs() +
                ", saltato=" + saltato +
                ", contrassegnato=" + contrassegnato +
                '}';
    }
}
