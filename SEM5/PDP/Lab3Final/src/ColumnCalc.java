public class ColumnCalc implements Runnable {
    private final Integer[][] resMatrix;
    private final Matrix matrix1;
    private final Matrix matrix2;
    private final int startIndex;
    private final int endIndex;

    ColumnCalc(Integer[][] resMatrix, Matrix matrix1, Matrix matrix2, int startIndex, int endIndex) {
        this.resMatrix = resMatrix;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        Utils u = new Utils();
        int matrix1_rows = matrix1.getRowCount();
        int rowIndex = startIndex % matrix1_rows;
        int columnIndex = startIndex / matrix1_rows;
        int numberElementCompute = endIndex - startIndex;

        for (int index = 0; index < numberElementCompute; index++) {
            this.resMatrix[rowIndex][columnIndex] = u.computeSingleElementMatrix(matrix1, matrix2, rowIndex, columnIndex);
            rowIndex = rowIndex + 1;
            if (rowIndex == matrix1_rows) {
                rowIndex = 0;
                columnIndex = columnIndex + 1;
            }
        }
    }
}
