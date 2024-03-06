package com.uchoa;

import java.util.Random;

public class Processo {
    private String nome;
    private int tempoProcesso;
    private Processo next;
    private int vazao;
    private int tempoExecucao;

    Random random = new Random();

    Processo(String nome) {
        this.nome = nome;
        this.tempoProcesso = Math.abs(random.nextInt(100) + 1);
        this.next = null;
    }

    public Processo clone() {
        Processo clone = new Processo(getNome());
        clone.setTempoProcesso(getTempoProcesso());
        return clone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoProcesso() {
        return tempoProcesso;
    }

    public void setTempoProcesso(int tempoProcesso) {
        this.tempoProcesso = tempoProcesso;
    }

    public Processo getNext() {
        return next;
    }

    public void setNext(Processo next) {
        this.next = next;
    }

    public int getVazao() {
        return vazao;
    }

    public void setVazao(int vazao) {
        this.vazao = vazao;
    }

    public int getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(int tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    @Override
    public String toString() {
        return "Processo [nome=" + nome + ", tempoProcesso=" + tempoProcesso + ", vazao=" + vazao + ", tempoExecucao="
                + tempoExecucao + "]";
    }

}
