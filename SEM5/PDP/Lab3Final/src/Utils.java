public class Utils {

    public Matrix getProductNoThreads(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Matrix dimensions are incompatible for multiplication.");
        }

        int resultRowCount = matrix1.getRowCount();
        int resultColumnCount = matrix2.getColumnCount();
        Integer[][] resultMatrix = new Integer[resultRowCount][resultColumnCount];

        for (int i = 0; i < resultRowCount; i++) {
            for (int j = 0; j < resultColumnCount; j++) {
                int sum = 0;
                for (int k = 0; k < matrix1.getColumnCount(); k++) {
                    sum += matrix1.getCell(i,k) * matrix2.getCell(k,j);
                }
                resultMatrix[i][j] = sum;
            }
        }

        return new Matrix(resultMatrix);
    }

    public int computeSingleElementMatrix(Matrix matrix1, Matrix matrix2, int row, int col) {
        if (row < 0 || row >= matrix1.getRowCount() || col < 0 || col >= matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Invalid row or column index.");
        }

        int sum = 0;
        for (int k = 0; k < matrix1.getColumnCount(); k++) {
            sum += matrix1.getCell(row, k) * matrix2.getCell(k, col);
        }
        return sum;
    }

}
