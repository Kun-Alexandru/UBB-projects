import org.apache.commons.io.output.TeeOutputStream;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Redirect output to a file
        try {
            OutputStream fileOutputStream = new FileOutputStream("output.txt");
            OutputStream consoleOutputStream = System.out;

            // Use TeeOutputStream to write to both file and console
            OutputStream outputStream = new TeeOutputStream(consoleOutputStream, fileOutputStream);
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Cannot create the output file.");
            e.printStackTrace();
            return;
        }

        long startTime = System.currentTimeMillis();

        Bank bank = new Bank();
        bank.work();
        bank.verify();

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        double elapsedSeconds = elapsedTime / 1000.0;
        System.out.println("Total execution time: " + elapsedSeconds + " seconds");

        System.out.close();
    }
}
