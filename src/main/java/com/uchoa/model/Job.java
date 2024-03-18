package com.uchoa.model;

public class Job {
    private double burstTime;
    private double arrivalTime;
    private double waitingTime;
    private double turnAroundTime;

    public Job(double burstTime, double arrivalTime) {
        this(burstTime, arrivalTime, 0, 0);
    }

    public Job(double burstTime, double arrivalTime, double waitingTime, double turnAroundTime) {
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.waitingTime = waitingTime;
        this.turnAroundTime = turnAroundTime;
    }

    public double getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(double burstTime) {
        this.burstTime = burstTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public double getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(double turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }
}
