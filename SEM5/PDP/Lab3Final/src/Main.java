import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static Integer m1_rows, m2_columns, m1_columns, m2_rows;
    private static String approach;
    private static String task;
    private static Integer numberOfTasks;
    private static Matrix matrix1;
    private static Matrix matrix2;

    public static void initStuff() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("rows matrix1: ");
        m1_rows = scanner.nextInt();
        System.out.print("columns matrix1: ");
        m1_columns = scanner.nextInt();
        System.out.print("rows matrix2: ");
        m2_rows = scanner.nextInt();
        System.out.print("columns matrix2: ");
        m2_columns = scanner.nextInt();
        System.out.print("approach (thread/threadpool): ");
        approach = scanner.next();
        System.out.print("task (row/column/kthrow): ");
        task = scanner.next();
        System.out.print("number of tasks: ");
        numberOfTasks = scanner.nextInt();
        getRandomMatrices();
    }

    public static void getRandomMatrices() {
        matrix1 = new Matrix(m1_rows, m1_columns);
        matrix2 = new Matrix(m2_rows, m2_columns);
        matrix1.print();
        matrix2.print();
    }

    public static void main(String[] args) throws InterruptedException {
        boolean keepRunning = true;
        Utils u = new Utils();
        Approach a = new Approach();
        while (keepRunning) {
            initStuff();
            if(Objects.equals(m1_columns, m2_rows)) {
                Matrix resMatrixNormal = u.getProductNoThreads(matrix1, matrix2);
                Matrix resMatrixThreads;

                long startTime = System.nanoTime();

                if (approach.equals("thread"))
                    resMatrixThreads = a.threadApproach(matrix1, matrix2, m1_rows, m2_columns, numberOfTasks, task);
                else
                    resMatrixThreads = a.threadpoolApproach(matrix1, matrix2, m1_rows, m2_columns, numberOfTasks, task);
                //resMatrixNormal.print();
                //resMatrixThreads.print();

                if (resMatrixNormal.equals(resMatrixThreads))
                    System.out.println("Works!");
                else
                    System.out.println("Error!");

                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;
                System.out.println("Execution Time: " + executionTime/1e9 + " seconds");


            } else {
                System.out.println("Error! Number of columns in matrix1 must be equal to number of rows in matrix2");
            }
            System.out.print("Do you want to continue (yes/no)? ");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.next();
            if ("no".equalsIgnoreCase(userInput)) {
                keepRunning = false;
            }
        }
    }

}