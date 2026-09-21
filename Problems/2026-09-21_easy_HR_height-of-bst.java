import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'getBinarySearchTreeHeight' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY values
     *  2. INTEGER_ARRAY leftChild
     *  3. INTEGER_ARRAY rightChild
     */

    public static int getBinarySearchTreeHeight(List<Integer> values, List<Integer> leftChild, List<Integer> rightChild) {
        //Iteratively check each layer of tree
        Collection<Integer> currentLayer = new ArrayList<>();
        Collection<Integer> nextLayer = new ArrayList<>();
        int layersTraversed = 0;
        if (!values.isEmpty()){
            currentLayer.add(0);
        }
        while (!currentLayer.isEmpty()) {
            for (Integer parent : currentLayer) {
                Integer left = leftChild.get(parent);
                Integer right = rightChild.get(parent);
                if (left != -1) nextLayer.add(left);
                if (right != -1) nextLayer.add(right);
            }
            layersTraversed++;
            currentLayer = nextLayer;
            nextLayer = new ArrayList<>();
        }
        return layersTraversed;   
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int valuesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> values = IntStream.range(0, valuesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int leftChildCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> leftChild = IntStream.range(0, leftChildCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int rightChildCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> rightChild = IntStream.range(0, rightChildCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.getBinarySearchTreeHeight(values, leftChild, rightChild);

        System.out.println(result);

        bufferedReader.close();
    }
}

