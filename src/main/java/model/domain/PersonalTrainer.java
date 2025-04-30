package model.domain;

public class PersonalTrainer {
    private String cfPersonal;
    private String nome;
    private String cognome;

    public PersonalTrainer(String cfPersonal, String nome, String cognome) {
        this.cfPersonal = cfPersonal;
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getCfPersonal() {
        return cfPersonal;
    }



    @Override
    public String toString() {
        return "PersonalTrainer{" +
                "cfPersonal='" + cfPersonal + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                '}';
    }
}
