package com.uchoa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilaProcesso {

    private Processo head;
    private int quanto;
    private int numeroProcessos;
    private int contadorQuanto;
    private int totalVazao;

    private FileWriter arquivo;
    private PrintWriter escreve;

    List<Processo> executados = new ArrayList<>();

    private Scanner input = new Scanner(System.in);

    FilaProcesso() {
        System.out.println("infórme o número de processos.");
        this.numeroProcessos = input.nextInt();

        System.out.println("Infórme o valor do QUANTO.");
        this.quanto = input.nextInt();

        geraFilaProcessos();
        escalonamento();
        dobraquanto();
        escreve.close();
    }

    public void geraFilaProcessos() {
        Processo newProcesso = new Processo("Processo1");
        Processo corrente = newProcesso;
        setHead(newProcesso);

        try {
            arquivo = new FileWriter("Dados.txt");
            escreve = new PrintWriter(arquivo);

            escreve.println("Dados iniciais dos processos!");
            escreve.println(newProcesso.toString());

            executados.add(newProcesso.clone());

            for (int i = 2; i <= this.numeroProcessos; i++) {
                newProcesso = new Processo("processo" + i);

                executados.add(newProcesso.clone());

                corrente.setNext(newProcesso);
                corrente = newProcesso;
                escreve.println(newProcesso.toString());
            }
            corrente.setNext(getHead());
        } catch (IOException e) {
            System.out.println("Erro na criação do arquyivo.");
        }
    }

    public void escalonamento() {
        Processo current = getHead();
        Processo prev = getHead();
        int count = getNumeroProcessos();

        escreve.println("Dados Escalonamento com o Quanto = " + getQuanto());

        while (count > 0) {
            current.setTempoProcesso(current.getTempoProcesso() - getQuanto());
            this.contadorQuanto++;

            if (current.getTempoProcesso() <= 0) {
                current.setVazao(getTotalVazao());
                this.totalVazao++;
                current.setTempoExecucao(
                        (getContadorQuanto() * getQuanto()) + getContadorQuanto() + current.getTempoProcesso());
                escreve.println(current.toString());
                current = current.getNext();
                prev.setNext(current);
                count--;
            } else {
                prev = prev.getNext();
                current = prev.getNext();
            }
        }
    }

    public void dobraquanto() {
        this.quanto *= 2;
        this.contadorQuanto = 0;
        this.totalVazao = 0;

        setHead(executados.get(0));
        Processo current = getHead();

        for (int i = 1; i < executados.size(); i++) {
            current.setNext(executados.get(i));
            current = current.getNext();
        }

        current.setNext(getHead());
        escalonamento();
    }

    public Processo getHead() {
        return head;
    }

    public void setHead(Processo head) {
        this.head = head;
    }

    public int getQuanto() {
        return quanto;
    }

    public void setQuanto(int quanto) {
        this.quanto = quanto;
    }

    public int getNumeroProcessos() {
        return numeroProcessos;
    }

    public void setNumeroProcessos(int numeroProcessos) {
        this.numeroProcessos = numeroProcessos;
    }

    public int getContadorQuanto() {
        return contadorQuanto;
    }

    public void setContadorQuanto(int contadorQuanto) {
        this.contadorQuanto = contadorQuanto;
    }

    public int getTotalVazao() {
        return totalVazao;
    }

    public void setTotalVazao(int totalVazao) {
        this.totalVazao = totalVazao;
    }

    @Override
    public String toString() {
        return "FilaProcesso [quanto=" + quanto + ", numeroProcessos=" + numeroProcessos + ", contadorQuanto="
                + contadorQuanto + ", totalVazao=" + totalVazao + "]";
    }

}
