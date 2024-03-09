package com.uchoa.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

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
    }

    public void execute(int quantum) {
        while (!queue.isEmpty()) {
            Job process = queue.poll();
            if (process.burstTime > quantum) {
                process.burstTime -= quantum;
                currentTime += quantum;
                for (Job job : queue) {
                    job.waitingTime += quantum;
                }
                queue.add(new Job(process.burstTime, process.arrivalTime, process.waitingTime, process.turnAroundTime));
            } else {
                currentTime += process.burstTime;
                for (Job job : queue) {
                    job.waitingTime += process.burstTime;
                }
                process.turnAroundTime = currentTime - process.arrivalTime;
                process.burstTime = 0;
                finishedJobs.add(process);
            }
            currentTime += contextSwitchTime;
            if (!queue.isEmpty()) {
                for (Job job : queue) {
                    job.waitingTime += contextSwitchTime;
                }
            }
        }
    }

    public double averageWaitingTime() {
        return finishedJobs.stream().mapToInt(p -> p.waitingTime).average().orElse(0);
    }

    public double averageTurnaroundTime() {
        return finishedJobs.stream().mapToInt(p -> p.turnAroundTime).average().orElse(0);
    }

    public double throughput() {
        return (double) finishedJobs.size() / currentTime;
    }
}
