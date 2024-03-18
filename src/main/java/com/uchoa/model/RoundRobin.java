package com.uchoa.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin {
    private final Queue<Job> queue = new LinkedList<>();
    private final List<Job> finishedJobs = new ArrayList<>();
    private final double contextSwitchTime;
    private double currentTime = 0;

    public RoundRobin(double contextSwitchTime) {
        this.contextSwitchTime = contextSwitchTime;
    }

    public void addProcess(Job process) {
        queue.add(new Job(process.getBurstTime(), process.getArrivalTime()));
        System.out.println("Adicionado processo: " + process);
    }

    public void execute(int quantum) {
        while (!queue.isEmpty()) {
            Job process = queue.poll();
            if (process.getBurstTime() > quantum) {
                process.setBurstTime(process.getBurstTime() - quantum);
                currentTime += quantum;
                for (Job job : queue) {
                    job.setWaitingTime(job.getWaitingTime() + quantum);
                }
                queue.add(process);
                System.out.println("Processo " + process + " executou por " + quantum + " unidades de tempo");
            } else {
                currentTime += process.getBurstTime();
                for (Job job : queue) {
                    job.setWaitingTime(job.getWaitingTime() + process.getBurstTime());
                }
                process.setTurnAroundTime(currentTime - process.getArrivalTime());
                process.setBurstTime(0);
                finishedJobs.add(process);
                System.out.println("Processo " + process + " completou a execução");
            }
            currentTime += contextSwitchTime;
            System.out.println("Tempo de troca de contexto: " + contextSwitchTime);
            if (!queue.isEmpty()) {
                for (Job job : queue) {
                    job.setWaitingTime(job.getWaitingTime() + contextSwitchTime);
                }
            }
        }
    }

    public double averageWaitingTime() {
        double average = finishedJobs.stream().mapToDouble(Job::getWaitingTime).average().orElse(0);
        System.out.println("Tempo médio de espera: " + average);
        return average;
    }

    public double averageTurnaroundTime() {
        double average = finishedJobs.stream().mapToDouble(Job::getTurnAroundTime).average().orElse(0);
        System.out.println("Tempo médio de retorno: " + average);
        return average;
    }

    public double throughput() {
        double throughput = (double) finishedJobs.size() / currentTime;
        System.out.println("Vazão: " + throughput);
        return throughput;
    }
}
