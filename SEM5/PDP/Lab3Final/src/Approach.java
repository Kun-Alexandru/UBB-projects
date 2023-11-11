import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Approach {

    public Matrix threadApproach(Matrix matrix1, Matrix matrix2, Integer r1, Integer c2, Integer numberOfTasks, String task) throws InterruptedException {
        System.out.println("Product computed using Threads");
        Integer[][] resMatrix = new Integer[r1][c2];
        List<Thread> threads = new ArrayList<>();
        int taskOrder = r1 * c2 / numberOfTasks;
        for (int i = 0; i < numberOfTasks; i++) {
            int startIndex = i * taskOrder;
            int endIndex;
            if( i != numberOfTasks - 1){
                endIndex = (i+1) * taskOrder;
            } else {
                endIndex = r1 * c2;
            }
            if (task.equals("row")) {
                //System.out.println("Product computed using row");
                System.out.println("Thread Info: " + " startIndex=" + startIndex + " endIndex=" + endIndex);
                threads.add(new Thread(new RowCalc(resMatrix, startIndex, endIndex, matrix1, matrix2)));
            }
            else if (task.equals("column")) {
                //System.out.println("Product computed using column");
                System.out.println("Thread Info: " + " startIndex=" + startIndex + " endIndex=" + endIndex);
                threads.add(new Thread(new ColumnCalc(resMatrix,matrix1,matrix2, startIndex, endIndex)));
            }
            else {
                //System.out.println("Product computed using kthrow");
                threads.add(new Thread(new KthRowCalc(resMatrix, i, numberOfTasks, matrix1, matrix2)));
            }
        }
        for (Thread thread : threads)
            thread.start();
        for (Thread thread : threads)
            thread.join();
        return new Matrix(resMatrix);
    }

    public Matrix threadpoolApproach(Matrix matrix1, Matrix matrix2, Integer r1, Integer c2, Integer numberOfTasks, String task) throws InterruptedException {
        System.out.println("Product computed using Thread Pool");
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfTasks);
        Integer[][] resMatrix = new Integer[r1][c2];
        List<Runnable> tasks = new ArrayList<>();
        int taskOrder = r1 * c2 / numberOfTasks;
        for (int i = 0; i < numberOfTasks; i++) {
            int startIndex = i * taskOrder;
            int endIndex;
            if( i != numberOfTasks - 1){
                endIndex = (i+1) * taskOrder;
            } else {
                endIndex = r1 * c2;
            }
            if (task.equals("row")) {
                //System.out.println("Product computed using row");
                System.out.println("Thread Info: " + " startIndex=" + startIndex + " endIndex=" + endIndex);
                tasks.add(new Thread(new RowCalc(resMatrix, startIndex, endIndex, matrix1, matrix2)));
            }
            else if (task.equals("column")) {
                //System.out.println("Product computed using column");
                System.out.println("Thread Info: " + " startIndex=" + startIndex + " endIndex=" + endIndex);
                tasks.add(new Thread(new ColumnCalc(resMatrix, matrix1, matrix2,startIndex, endIndex)));
            }
            else {
                //System.out.println("Product computed using kthrow");
                tasks.add(new Thread(new KthRowCalc(resMatrix, i, numberOfTasks, matrix1, matrix2)));
            }
        }
        for (Runnable runTask : tasks)
            executor.execute(runTask);
        executor.shutdown();
        executor.awaitTermination(100, TimeUnit.DAYS);
        return new Matrix(resMatrix);
    }

}
