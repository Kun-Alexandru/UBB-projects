public class RowCalc implements Runnable {
    private final Integer[][] resMatrix;
    private final int startIndex;
    private final int endIndex;

    private final Matrix matrix1;
    private final Matrix matrix2;

    RowCalc(Integer[][] resMatrix, int startIndex, int endIndex, Matrix matrix1, Matrix matrix2) {
        this.resMatrix = resMatrix;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
    }

    @Override
    public void run() {
        Utils u = new Utils();
        int matrix2_columns = matrix2.getColumnCount();
        int rowIndex = startIndex / matrix2_columns;
        int columnIndex = startIndex % matrix2_columns;
        int numberElementCompute = endIndex - startIndex;

        for (int index = 0; index < numberElementCompute; index++) {
            this.resMatrix[rowIndex][columnIndex] = u.computeSingleElementMatrix(matrix1, matrix2, rowIndex, columnIndex);
            columnIndex = columnIndex + 1;
            if (columnIndex == matrix2_columns) {
                columnIndex = 0;
                rowIndex = rowIndex + 1;
            }
        }
    }
}
