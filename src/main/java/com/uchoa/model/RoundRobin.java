package com.uchoa.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin {
    Queue<Job> queue = new LinkedList<>();
    List<Job> finishedJobs = new ArrayList<>();
    int contextSwitchTime;
    int currentTime = 0;

    public RoundRobin(int contextSwitchTime) {
        this.contextSwitchTime = contextSwitchTime;
    }

    public void addProcess(Job process) {
        queue.add(new Job(process.burstTime, process.arrivalTime));
        System.out.println("Adicionado processo: " + process);
    }

    public void execute(int quantum) {
        while (!queue.isEmpty()) {
            Job process = queue.poll();
            System.out.println("Iniciando execução do processo: " + process);
            if (process.burstTime > quantum) {
                process.burstTime -= quantum;
                currentTime += quantum;
                System.out.println("Processo " + process + " executou por " + quantum + " unidades de tempo");
                for (Job job : queue) {
                    job.waitingTime += quantum;
                }
                queue.add(new Job(process.burstTime, process.arrivalTime, process.waitingTime, process.turnAroundTime));
            } else {
                currentTime += process.burstTime;
                System.out.println("Processo " + process + " completou a execução");
                for (Job job : queue) {
                    job.waitingTime += process.burstTime;
                }
                process.turnAroundTime = currentTime - process.arrivalTime;
                process.burstTime = 0;
                finishedJobs.add(process);
            }
            currentTime += contextSwitchTime;
            System.out.println("Tempo de troca de contexto: " + contextSwitchTime);
            if (!queue.isEmpty()) {
                for (Job job : queue) {
                    job.waitingTime += contextSwitchTime;
                }
            }
        }
    }

    public double averageWaitingTime() {
        double average = finishedJobs.stream().mapToInt(p -> p.waitingTime).average().orElse(0);
        System.out.println("Tempo médio de espera: " + average);
        return average;
    }

    public double averageTurnaroundTime() {
        double average = finishedJobs.stream().mapToInt(p -> p.turnAroundTime).average().orElse(0);
        System.out.println("Tempo médio de retorno: " + average);
        return average;
    }

    public double throughput() {
        double throughput = (double) finishedJobs.size() / currentTime;
        System.out.println("Vazão: " + throughput);
        return throughput;
    }
}
