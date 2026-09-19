/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaussianelimination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author Quack <Quack.a@school.horse>
 */
public class GaussianElimination {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        print("Enter height of array");
        int height = Integer.parseInt(inputString());
        double[][] matrix = new double[height][];
        for(int i = 0; i < height; i++){
            print("Enter array row, delimiting values using ;");
            String inputRow = inputString();
            double[] row = extractRowFromString(inputRow);
            matrix[i] = row;
        }
        
        double[][] reducedMatrix = getGaussianElimination(matrix);
        print("Row reduced echelon form:");
        printArray(reducedMatrix);
    }
    public static double[] extractRowFromString(String s){
        String[] s_array = s.split(";");
        double[] output = new double[s_array.length];
        for(int i = 0; i < s_array.length; i++){
            output[i] = Double.parseDouble(s_array[i]);
        }
        return output;
    }
    public static String inputString(){
        BufferedReader BufferedReader_Name = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try{
             input = BufferedReader_Name.readLine();
        }catch(Exception ex){};
        return input;
    }
    public static void print(String s){
        System.out.println(s);
    }
    public static void printArray(double[][] array){
        for(int i = 0; i < array.length;i++){
            String output = Arrays.toString(array[i]);
            output = output.replace(", ", "\t") + "\n";
            print(output);
        }
    }
    public static double[][] getGaussianElimination(double[][] matrix){
        Matrix m = new Matrix(matrix);
        m.performGaussianElimination();
        return m.getMatrix();
    }
}
