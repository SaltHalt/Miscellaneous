import com.google.common.collect.Multiset;
import com.google.common.collect.ImmutableMultiset;


class Result {

    /*
     * Complete the 'findZeroSumTripletsInWindow' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY readings
     *  2. INTEGER windowSize
     */
     
    private static void addIndex(List<Integer> readings, int index, Map<Integer, ArrayList<Integer>> pool){
        int val = readings.get(index);
        if (pool.containsKey(val)){
            pool.get(val).add(index);
        } else {
            pool.put(val, new ArrayList<>(List.of(index)));
        }
    }
    private static void dropIndex(List<Integer> readings, int index, Map<Integer, ArrayList<Integer>> pool){
        int val = readings.get(index);
        ArrayList<Integer> list = pool.get(val);
        list.remove((Integer) index);
        if (list.isEmpty()){
            pool.remove(val);
        }
    }

    public static List<List<Integer>> findZeroSumTripletsInWindow(List<Integer> readings, int windowSize) {
        Map<Integer, ArrayList<Integer>> pool = new HashMap<>(windowSize);
        Set<Multiset<Integer>> triplets = new HashSet<>();
        for (int k = 0; k < readings.size(); k++){
            if (k - windowSize >= 0){
                dropIndex(readings, k - windowSize, pool);
            }
            
            int arr_k = readings.get(k);
            for (int i = Math.max(k-windowSize+1, 0); i < k - 1; i++){
                int arr_i = readings.get(i);
                int arr_j = 0 - arr_i - arr_k;
                final int i_immutable = i;
                if (pool.containsKey(arr_j)){
                    ArrayList<Integer> indices = pool.get(arr_j);
                    indices.stream().filter(j -> (j > i_immutable)).forEach(j -> triplets.add(ImmutableMultiset.of(arr_i, arr_j, arr_k)));
                }
            }
            addIndex(readings, k, pool);
        }
        List<List<Integer>> tripletsList = new ArrayList<>(triplets.size());
        for (Multiset<Integer> triplet : triplets){
            tripletsList.add(List.copyOf(triplet));
            // System.out.println(triplet);
        } 
        return tripletsList;
    }

}
