package model.domain;

public class Esercizio {
    private int codiceEs;
    private String nome;
    private String descrizione;
    private int numSerie;
    private int ripetizioni;

    public Esercizio(int codiceEs, String nome, String descrizione, int numSerie, int ripetizioni) {
        this.codiceEs = codiceEs;
        this.nome = nome;
        this.descrizione = descrizione;
        this.numSerie = numSerie;
        this.ripetizioni = ripetizioni;
    }

    public Esercizio (int codiceEs){this.codiceEs = codiceEs;}

    public int getCodiceEs() {
        return codiceEs;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getNumSerie() {
        return numSerie;
    }

    public int getRipetizioni() {
        return ripetizioni;
    }

    @Override
    public String toString() {
        return "Esercizio{" +
                "codiceEs=" + codiceEs +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", numSerie=" + numSerie +
                ", ripetizioni=" + ripetizioni +
                '}';
    }
}
