package Entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Pessoa implements Serializable {
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private Candidato candidato;

    public Pessoa() {
    }

    public Pessoa(String nome, LocalDate dataNascimento, String cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", candidato=" + candidato +
                '}';
    }
}
