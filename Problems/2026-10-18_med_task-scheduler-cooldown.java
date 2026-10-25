class Result {

    /*
     * Complete the 'calculateMinimumTimeUnits' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY tasks  
     *  2. INTEGER m
     *  3. INTEGER k
     */
    
    public static int calculateMinimumTimeUnits(List<Integer> tasks, int m, int k) {
        if (tasks.isEmpty()){
            return 0;
        }
        Map<Integer, Integer> freq = new HashMap<>();
        for (Integer t : tasks) freq.merge(t, 1, Integer::sum);
        
        int saturatedTimetable = (int) Math.ceil((double) tasks.size() / m);
        
        int highestFreq = Collections.max(freq.values());
        int columnHeight = (int) Math.ceil((double) highestFreq / m) - 1;
        int excessTasks = freq.values().stream().filter((f) -> f > columnHeight * m).map(f -> f - columnHeight * m).reduce(0, Integer::sum);
        int bottomRowLength = (int) Math.ceil((double) excessTasks / m);
        
        return Math.max(saturatedTimetable, (k+1) * columnHeight + bottomRowLength);
    }

}
