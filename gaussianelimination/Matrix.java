/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaussianelimination;

import java.util.Arrays;

/**
 *
 * @author Quack <Quack.a@school.horse>
 */
public class Matrix {
    final private int HEIGHT;
    final private int LENGTH;
    double[][] matrix;
    /*n x m matrix is a [n][m] matrix*/
    public Matrix(double[][] matrix){
        this.matrix = matrix;
        HEIGHT = matrix.length;
        LENGTH = matrix[0].length;
    }
    public double[][] getMatrix(){
        return matrix;
    }
    public double[] getRow(int row){
        return matrix[row];
    }
    public double getElement(int row, int column){
        return matrix[row][column];
    }
    public void swapRows(int row1, int row2){
        double[] rowHolder = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = rowHolder;
    }
    public double[] scaleArray(double[] array, double scalar){
        for(int i = 0; i < array.length; i++){
            array[i] = array[i]*scalar;
        }
        return array;
    }
    public double[] subtractArrays(double[] array, double[] arrayToSubtract){
        for(int i = 0; i<array.length;i++){
            array[i]=array[i]-arrayToSubtract[i];
        }
        return array;
    }
    public void subtractRow(int row, double[] arrayToSubtract){
        matrix[row] = subtractArrays(getRow(row),arrayToSubtract);
    }
    public void subtractRowUsingFactor(int row, int rowToSubtract, int column){
        double[] subtractingArray = getRow(rowToSubtract).clone();
        double factor = matrix[row][column]/matrix[rowToSubtract][column];
        subtractingArray = scaleArray(subtractingArray,factor);
        subtractRow(row,subtractingArray);
    }
    public void subtractRowFromAllRows(int rowToSubtract, int column){
        for(int i = 0; i < HEIGHT; i++){
            if(i!=rowToSubtract){
                subtractRowUsingFactor(i,rowToSubtract, column);
            }
        }
    }
    public int getNonzeroRow(int startRow, int column){
        int nonZeroRow = -1;
        for(int i = startRow; i < HEIGHT; i++){
            if(matrix[i][column] != 0){
                nonZeroRow = i;
                break;
            }
        }
        return nonZeroRow;
    }
    /**This destroys the object*/
    public void performGaussianElimination(){
        for (int i = 0; i < HEIGHT; i++){
            int nonZeroRow = getNonzeroRow(i,i);
            if(nonZeroRow==-1){
                System.out.println("Error: Determinant zero");
                break;
            }
            swapRows(i,nonZeroRow);
            subtractRowFromAllRows(i,i);
            matrix[i]=scaleArray(matrix[i].clone(),1/matrix[i][i]);
        }
    }
    public double[][] getSquareMatrix(){
        double[][] output = new double[HEIGHT][HEIGHT];
        for(int i = 0; i < HEIGHT; i++){
            output[i] = Arrays.copyOfRange(matrix[i],0,HEIGHT);
        }
        return output;
    }
    public double[][] getExtraMatrix(){
        double[][] output = new double[HEIGHT][];
        for(int i = 0; i < HEIGHT; i++){
            output[i] = Arrays.copyOfRange(matrix[i],HEIGHT,LENGTH);
        }
        return output;
    }
}
