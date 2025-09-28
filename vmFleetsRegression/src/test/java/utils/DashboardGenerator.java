package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardGenerator {
	
	    public static void generateDashboard(int passed, int failed, int skipped) {
	        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

	        String html = "<!DOCTYPE html>\n<html>\n<head>\n<title>Test Dashboard</title>\n" +
	                "<style>\nbody { font-family: Arial; margin: 20px; }\n" +
	                "h1 { color: #2c3e50; }\n.summary { margin-bottom: 20px; }\n" +
	                "img { max-width: 600px; margin: 10px 0; border: 1px solid #ccc; }\n" +
	                "a { color: #2980b9; text-decoration: none; }\n</style>\n</head>\n<body>\n" +
	                "<h1>Test Execution Dashboard</h1>\n" +
	                "<p><strong>Run Time:</strong> " + timestamp + "</p>\n" +
	                "<div class='summary'>\n" +
	                "<p><strong>Passed:</strong> " + passed + "</p>\n" +
	                "<p><strong>Failed:</strong> " + failed + "</p>\n" +
	                "<p><strong>Skipped:</strong> " + skipped + "</p>\n" +
	                "</div>\n" +
	                "<h2>Extent Report</h2>\n" +
	                "<p><a href='ExtentReport.html' target='_blank'>View Detailed Extent Report</a></p>\n" +
	                "<h2>Pie Chart</h2>\n<img src='TestPieChart.png' alt='Pie Chart'>\n" +
	                "<h2>Bar Chart</h2>\n<img src='TestSummaryChart.png' alt='Bar Chart'>\n" +
	                "<h2>Trend Chart</h2>\n<img src='TestTrendChart.png' alt='Trend Chart'>\n";

	        // Optional: Embed screenshots if available
	        File screenshotDir = new File("test-output/screenshots");
	        if (screenshotDir.exists() && screenshotDir.isDirectory()) {
	            File[] files = screenshotDir.listFiles((dir, name) -> name.endsWith(".png"));
	            if (files != null && files.length > 0) {
	                html += "<h2>Failure Screenshots</h2>\n";
	                for (File file : files) {
	                    html += "<img src='screenshots/" + file.getName() + "' alt='" + file.getName() + "'>\n";
	                }
	            }
	        }

	        html += "</body>\n</html>";

	        try (FileWriter writer = new FileWriter("test-output/TestDashboard.html")) {
	            writer.write(html);
	            System.out.println("✅ Dashboard generated: TestDashboard.html");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
