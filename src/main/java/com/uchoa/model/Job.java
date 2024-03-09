package com.uchoa.model;

public class Job {
    public int burstTime;
    public int arrivalTime;
    int waitingTime;
    int turnAroundTime;

    public Job(int burstTime, int arrivalTime) {
        this(burstTime, arrivalTime, 0, 0);
    }

    public Job(int burstTime, int arrivalTime, int waitingTime, int turnAroundTime) {
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.waitingTime = waitingTime;
        this.turnAroundTime = turnAroundTime;
    }
}
