package utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.io.*;
import java.util.*;

public class TestChartGenerator {

    public static void generateBarChart(int passed, int failed, int skipped) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(passed, "Tests", "Passed");
        dataset.addValue(failed, "Tests", "Failed");
        dataset.addValue(skipped, "Tests", "Skipped");

        JFreeChart barChart = ChartFactory.createBarChart(
                "Test Execution Summary",
                "Status",
                "Count",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        try {
            ChartUtils.saveChartAsPNG(new File("test-output/TestSummaryChart.png"), barChart, 800, 600);
            System.out.println("✅ Bar chart saved: TestSummaryChart.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generatePieChart(int passed, int failed, int skipped) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Passed", passed);
        dataset.setValue("Failed", failed);
        dataset.setValue("Skipped", skipped);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Test Result Distribution",
                dataset,
                true, true, false
        );

        try {
            ChartUtils.saveChartAsPNG(new File("test-output/TestPieChart.png"), pieChart, 600, 400);
            System.out.println("✅ Pie chart saved: TestPieChart.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateTrendChart(Map<String, Integer> runData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : runData.entrySet()) {
            dataset.addValue(entry.getValue(), "Passed Tests", entry.getKey());
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Test Execution Trend",
                "Run Date",
                "Passed Count",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        try {
            ChartUtils.saveChartAsPNG(new File("test-output/TestTrendChart.png"), lineChart, 800, 600);
            System.out.println("✅ Trend chart saved: TestTrendChart.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> readTrendData() {
        Map<String, Integer> runData = new LinkedHashMap<>();

        File file = new File("test-output/results.csv");
        if (!file.exists()) {
            System.out.println("⚠️ No results.csv file found for trend chart.");
            return runData;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String date = parts[0];
                    int passed = Integer.parseInt(parts[1]);
                    runData.put(date, passed);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return runData;
    }
}
