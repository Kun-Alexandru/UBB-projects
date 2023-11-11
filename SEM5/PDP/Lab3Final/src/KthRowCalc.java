public class KthRowCalc implements Runnable {
    private final Integer[][] resMatrix;
    private final int threadNumber;
    private final int step;
    private final Matrix matrix1;
    private final Matrix matrix2;

    KthRowCalc(Integer[][] resMatrix, int threadNumber, int step, Matrix matrix1, Matrix matrix2) {
        this.resMatrix = resMatrix;
        this.threadNumber = threadNumber;
        this.step = step;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
    }

    @Override
    public void run() {
        Utils u = new Utils();
        int matrix1_rows = matrix1.getRowCount();
        int matrix2_columns = matrix2.getColumnCount();
        int rowIndex = 0;
        int columnIndex = threadNumber;
        boolean isRunning = true;

        while (isRunning) {
            int buffer = columnIndex / matrix2_columns;
            rowIndex += buffer;
            columnIndex = columnIndex - (buffer * matrix2_columns);
            if (rowIndex >= matrix1_rows)
                isRunning = false;
            if(isRunning) {
                resMatrix[rowIndex][columnIndex] = u.computeSingleElementMatrix(matrix1, matrix2, rowIndex, columnIndex);
                columnIndex += step;
            }
        }
    }
}
