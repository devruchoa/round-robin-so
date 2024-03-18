package com.uchoa;

import com.uchoa.model.Job;
import com.uchoa.model.RoundRobin;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    public static void main(String[] args) throws Exception {
        List<Job> jobs = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            double burstTime = 1 + random.nextDouble() * 10;
            double arrivalTime = 0;
            jobs.add(new Job(burstTime, arrivalTime));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Average Waiting Time");
        XYSeries series2 = new XYSeries("Average Turnaround Time");
        XYSeries series3 = new XYSeries("Throughput");

        for (int quantum = 1; quantum <= 10; quantum++) {
            RoundRobin rr = new RoundRobin(1);
            for (Job job : jobs) {
                rr.addProcess(job);
            }
            rr.execute(quantum);

            double avgWaitingTime = rr.averageWaitingTime();
            double avgTurnaroundTime = rr.averageTurnaroundTime();
            double throughput = rr.throughput();

            series1.add(quantum, avgWaitingTime);
            series2.add(quantum, avgTurnaroundTime);
            series3.add(quantum, throughput);
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Round Robin Scheduling Metrics",
                "Quantum",
                "Value",
                dataset
        );

        ChartUtils.saveChartAsPNG(new File("chart.png"), chart, 800, 600);
    }
}
