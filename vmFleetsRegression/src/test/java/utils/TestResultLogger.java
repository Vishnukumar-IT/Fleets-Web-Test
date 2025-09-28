package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class TestResultLogger {
    public static void logResult(int passed, int failed, int skipped) {
        String date = LocalDate.now().toString();
        String line = date + "," + passed + "," + failed + "," + skipped + "\n";

        try (FileWriter writer = new FileWriter("test-output/results.csv", true)) {
            writer.write(line);
            System.out.println("✅ Logged results for " + date);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

