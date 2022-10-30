/**
 * File: MatrixLogic.java
 * Project: Matrix Calculator
 * Date: October 2022
 * @author Neti-G
 */

/** 
 * The class where calculations are done with matrices.
*/
public class MatrixLogic {

    /**
     * Calculates the sum of two matrices.
     * 
     * @param matrixA first matrix.
     * @param matrixB second matrix.
     * @return the matrix formed by the sum of matrixA and matrixB.
     */
    public double[][] matrixSum(double[][] matrixA, double[][] matrixB) {
        double[][] resultMatrix = new double[matrixA.length][matrixA[0].length];
        for (int row = 0; row < matrixA.length; row++) {
            for (int column = 0; column < matrixA[0].length; column++) {
                resultMatrix[row][column] = matrixA[row][column] + matrixB[row][column];
            }
        }
        return resultMatrix;
    }

    /**
     * Calculates the difference of two matrices.
     * 
     * @param matrixA first matrix.
     * @param matrixB second matrix.
     * @return the matrix formed by the difference of matrixA with matrixB.
     */
    public double[][] matrixDifference(double[][] matrixA, double[][] matrixB) {
        double[][] resultMatrix = new double[matrixA.length][matrixA[0].length];
        for (int row = 0; row < matrixA.length; row++) {
            for (int column = 0; column < matrixA[0].length; column++) {
                resultMatrix[row][column] = matrixA[row][column] - matrixB[row][column];
            }
        }
        return resultMatrix;
    }

    /**
     * Calculates the product of two matrices.
     * 
     * @param matrixA first matrix.
     * @param matrixB second matrix.
     * @return the matrix formed by the product of matrixA with matrixB.
     */
    public double[][] matrixMultiplication(double[][] matrixA, double[][] matrixB) {
        double[][] returnMatrix = new double[matrixA.length][matrixB[0].length];
        for (int rowA = 0; rowA < matrixA.length; rowA++) {
            for (int columnB = 0; columnB < matrixB[0].length; columnB++) {
                double element = 0;
                for (int columnA = 0; columnA < matrixA[0].length; columnA++)
                    element += matrixA[rowA][columnA] * matrixB[columnA][columnB];
                returnMatrix[rowA][columnB] = element;
            }
        }
        return returnMatrix;
    }

    /**
     * Calculates the determinant of a matrix using Chio's method.
     * 
     * @param matrix the matrix.
     * @return the determinant of the matrix.
     */
    public double matrixDeterminant(double[][] matrix) {
        if(matrix.length == 1)
            return matrix[0][0];
        if (matrix.length == 2)
            return (matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1]);

        double[][] matrixCopy = new double[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++)
            for (int column = 0; column < matrix[0].length; column++)
                matrixCopy[row][column] = matrix[row][column];

        // Swaps the first row with another row if the first element is 0.
        int signChange = 1;
        if (matrixCopy[0][0] == 0) {
            for (int row = 1; row < matrixCopy.length; row++) {
                if (matrixCopy[row][0] == 0)
                    continue;

                double tempElement;
                for (int element = 0; element < matrixCopy[0].length; element++) {
                    tempElement = matrixCopy[0][element];
                    matrixCopy[0][element] = matrixCopy[row][element];
                    matrixCopy[row][element] = tempElement;
                }
                signChange = -1;
                break;
            }
            // If the matrix has a column of all zeros then the determinant is 0.
            if (matrixCopy[0][0] == 0)
                return 0;
        }

        double[][] returnMatrix = new double[matrixCopy.length - 1][matrixCopy[0].length - 1];
        for (int row = 1; row < matrixCopy.length; row++)
            for (int column = 1; column < matrixCopy[0].length; column++)
                returnMatrix[row - 1][column - 1] = (matrixCopy[0][0] * matrixCopy[row][column] - matrixCopy[row][0] * matrixCopy[0][column]);

        return signChange * 1 / Math.pow(matrixCopy[0][0], matrixCopy.length - 2) * matrixDeterminant(returnMatrix);
    }

    /**
     * Calculates the inverse of a matrix.
     * 
     * @param matrix the matrix.
     * @return the inverse of the given matrix.
     */
    public double[][] matrixInverse(double[][] matrix) {
        
        double[][] returnMatrix = new double[matrix.length][matrix.length];
        if(matrix.length==1){
            returnMatrix[0][0] = 1/matrix[0][0];
            return returnMatrix;
        }
        double matrixDeterminant = matrixDeterminant(matrix);

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {

                // Calculate the minor for the element with indexes row, column.
                double[][] minor = new double[matrix.length - 1][matrix.length - 1];
                int rowIncrement = 0;
                for (int tempRow = 0; tempRow < matrix.length; tempRow++) {
                    int columnIncrement = 0;
                    for (int tempColumn = 0; tempColumn < matrix[0].length; tempColumn++) {
                        if (row == tempRow) {
                            rowIncrement = 1;
                            continue;
                        }
                        if (column == tempColumn) {
                            columnIncrement = 1;
                            continue;
                        }
                        minor[tempRow - rowIncrement][tempColumn - columnIncrement] = matrix[tempRow][tempColumn];
                    }
                }
                
                returnMatrix[column][row] = (1 / matrixDeterminant) * Math.pow(-1, row + column) * matrixDeterminant(minor);
            }
        }
        return returnMatrix;
    }
}