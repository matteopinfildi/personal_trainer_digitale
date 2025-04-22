package model.domain;

public class Macchinario {

    private Esercizio esercizio;
    private String nome;
    private String descrizione;

    public Macchinario(Esercizio esercizio, String nome, String descrizione) {
        this.esercizio = esercizio;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public Esercizio getEsercizio() {
        return esercizio;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return "Macchinario{" +
                "esercizio=" + esercizio.getCodiceEs() +
                ", nome='" + nome + '\'' +
                ", descrizione='" + (descrizione != null ? descrizione : "N/A") + '\'' +
                '}';
    }
}
