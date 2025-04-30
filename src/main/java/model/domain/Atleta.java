package model.domain;

import java.time.LocalDate;

public class Atleta {
    private String cfAtleta;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private PersonalTrainer personalTrainer;

    public Atleta(String cfAtleta, String nome, String cognome, LocalDate dataNascita, PersonalTrainer personalTrainer) {
        this.cfAtleta = cfAtleta;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.personalTrainer = personalTrainer;
    }

    public Atleta(String cfAtleta){this.cfAtleta = cfAtleta;}


    public String getCfAtleta() {
        return cfAtleta;
    }



    @Override
    public String toString() {
        return "Atleta{" +
                "cfAtleta='" + cfAtleta + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", personalTrainer=" + (personalTrainer != null ? personalTrainer.getCfPersonal() : "Nessun PT") +
                '}';
    }
}
