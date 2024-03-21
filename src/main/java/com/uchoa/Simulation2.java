package com.uchoa;

import com.uchoa.model.Job;
import com.uchoa.model.RoundRobin;

import java.util.Arrays;
import java.util.List;

public class Simulation2 {
    public static void main(String[] args) {
        // Criação de processos
        Job job1 = new Job("Processo 1", 10, 0);
        Job job2 = new Job("Processo 2", 5, 0);
        Job job3 = new Job("Processo 3", 8, 0);
        Job job4 = new Job("Processo 4", 6, 0);

        // Lista de processos
        List<Job> jobs = Arrays.asList(job1, job2, job3, job4);

        // Criação do Round Robin com tempo de troca de contexto 1
        RoundRobin roundRobin = new RoundRobin(1);

        // Adicionando processos ao Round Robin
        for (Job job : jobs) {
            roundRobin.addProcess(job);
        }

        // Executando o Round Robin com diferentes valores de quantum
        roundRobin.execute(6);
    }
}
