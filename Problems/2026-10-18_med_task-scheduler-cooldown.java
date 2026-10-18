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
    static class Machine {
        private Set<Integer> historySet; 
        private Queue<Integer> historyQueue;
        private int memory;
        private final int EMPTY_TASK = -1;
        
        Machine(int k) {
            memory = k;
            historySet = new HashSet<>(k);
            historyQueue = new ArrayDeque<>(k);
        }
        private void executeTask(int task_id){
            if (memory > 0){
                // System.out.println(task_id);
                // System.out.println(historyQueue.toString());
                if (historyQueue.size() > memory){
                    throw new Error("History queue contains more than k tasks.");
                } else if (historyQueue.size() == memory){
                    Integer old_task = historyQueue.remove();
                    historySet.remove(old_task);
                }
                historyQueue.add(task_id);
                historySet.add(task_id);
            }
            
        }
        
        void takeTask(PriorityQueue<int[]> queue){
            Optional<int[]> task = queue.stream().filter((arr) -> !historySet.contains(arr[0])).findFirst();
            if (task.isEmpty()){
                executeTask(EMPTY_TASK);
            } else {
                int[] arr = task.get();
                queue.remove(arr);
                executeTask(arr[0]);
                if (arr[1] > 1){
                    int[] newTask = {arr[0], arr[1]-1};
                    queue.add(newTask);
                }
            } 
        }
    }
    private static PriorityQueue<int[]> createTaskQueue(List<Integer> tasks){
        Map<Integer, Integer> freq = new HashMap<>(tasks.size());
        for (Integer t : tasks) freq.merge(t, 1, Integer::sum);
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()){
            int[] pair = {entry.getKey(), entry.getValue()};
            queue.add(pair);
        }
        return queue;
    }
    
    public static int calculateMinimumTimeUnits(List<Integer> tasks, int m, int k) {
        PriorityQueue<int[]> taskQueue = createTaskQueue(tasks);
        List<Machine> machines = new ArrayList<>(m);
        for (int i = 0; i < m; i++) machines.add(new Machine(k));

        int iterationCount = 0;        
        while (!taskQueue.isEmpty()){
            for (Machine machine : machines) machine.takeTask(taskQueue);
            iterationCount++;
        }
                
        return iterationCount;
    }

}
