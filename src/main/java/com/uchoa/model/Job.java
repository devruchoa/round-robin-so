package com.uchoa.model;

public class Job {
    private String name;
    private double burstTime;
    private double arrivalTime;
    private double waitingTime;
    private double turnAroundTime;

    public Job(String name, double burstTime, double arrivalTime) {
        this(name, burstTime, arrivalTime, 0, 0);
    }

    public Job(String name, double burstTime, double arrivalTime, double waitingTime, double turnAroundTime) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
