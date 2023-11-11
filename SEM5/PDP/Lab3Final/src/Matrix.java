import java.util.Random;

public class Matrix {
    private final Integer[][] matrix;
    private final Integer rowCount, columnCount;

    Matrix(Integer[][] matrix) {
        this.matrix = matrix;
        this.rowCount = matrix.length;
        this.columnCount = matrix[0].length;
    }

    public Matrix(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.matrix = new Integer[rowCount][columnCount];
        Random random = new Random();
        int minValue = 3;
        int maxValue = 50;
        int range = maxValue - minValue;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                int randomValue = random.nextInt(range) + minValue;
                matrix[i][j] = randomValue;
            }
        }
    }

    public int getCell(int i, int j) {
        return matrix[i][j];
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void print() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Matrix otherMatrix = (Matrix) o;

        if (this.getRowCount() != otherMatrix.getRowCount() || this.getColumnCount() != otherMatrix.getColumnCount()) {
            return false;
        }

        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                if (!this.matrix[i][j].equals(otherMatrix.getCell(i, j))) {
                    return false;
                }
            }
        }

        return true;
    }
}