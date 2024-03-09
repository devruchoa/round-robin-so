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
        int maxQuantum = 100;
        int simulationsPerQuantum = 10;

        List<Job> jobs = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int burstTime = (int) (50 + 10 * random.nextGaussian());
            burstTime = Math.max(burstTime, 1);
            jobs.add(new Job(burstTime, 0));
        }

        XYSeries seriesWaitingTime = new XYSeries("Average Waiting Time");
        XYSeries seriesTurnaroundTime = new XYSeries("Average Turnaround Time");
        XYSeries seriesThroughput = new XYSeries("Throughput");

        for (int quantum = 1; quantum <= maxQuantum; quantum++) {
            double totalWaitingTime = 0;
            double totalTurnaroundTime = 0;
            double totalThroughput = 0;

            for (int i = 0; i < simulationsPerQuantum; i++) {
                RoundRobin roundRobin = new RoundRobin(1);
                for (Job job : jobs) {
                    roundRobin.addProcess(new Job(job.burstTime, job.arrivalTime));
                }
                roundRobin.execute(quantum);
                totalWaitingTime += roundRobin.averageWaitingTime();
                totalTurnaroundTime += roundRobin.averageTurnaroundTime();
                totalThroughput += roundRobin.throughput();
            }

            double averageWaitingTime = totalWaitingTime / simulationsPerQuantum;
            double averageTurnaroundTime = totalTurnaroundTime / simulationsPerQuantum;
            double averageThroughput = totalThroughput / simulationsPerQuantum;

            seriesWaitingTime.add(quantum, averageWaitingTime);
            seriesTurnaroundTime.add(quantum, averageTurnaroundTime);
            seriesThroughput.add(quantum, averageThroughput);

            System.out.println("Quantum: " + quantum);
            System.out.println("Average Waiting Time: " + averageWaitingTime);
            System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
            System.out.println("Throughput: " + averageThroughput);
            System.out.println();
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesWaitingTime);
        dataset.addSeries(seriesTurnaroundTime);
        dataset.addSeries(seriesThroughput);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Metrics by Quantum",
                "Quantum",
                "Metrics",
                dataset
        );

        ChartUtils.saveChartAsPNG(new File("chart.png"), chart, 800, 600);
    }
}
