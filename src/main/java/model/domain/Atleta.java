package model.domain;

import java.time.LocalDate;

public class Atleta {
    private String cfAtleta;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String cfPersonal; // può essere null

    public Atleta(String cfAtleta, String nome, String cognome, LocalDate dataNascita, String cfPersonal) {
        this.cfAtleta = cfAtleta;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.cfPersonal = cfPersonal;
    }

    // Overload costruttore senza personal trainer
    public Atleta(String cfAtleta, String nome, String cognome, LocalDate dataNascita) {
        this(cfAtleta, nome, cognome, dataNascita, null);
    }

    // Getter
    public String getCfAtleta() {
        return cfAtleta;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public String getCfPersonal() {
        return cfPersonal;
    }

    // Setter
    public void setCfAtleta(String cfAtleta) {
        this.cfAtleta = cfAtleta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setCfPersonal(String cfPersonal) {
        this.cfPersonal = cfPersonal;
    }

    @Override
    public String toString() {
        return "Atleta{" +
                "cfAtleta='" + cfAtleta + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", cfPersonal=" + (cfPersonal != null ? cfPersonal : "Nessun personal trainer") +
                '}';
    }
}
