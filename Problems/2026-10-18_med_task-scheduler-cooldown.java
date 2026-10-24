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
    
    static class Task{
        final int type;
        final int quantity;
        final int coolTime;
        Task(int type, int quantity){
            this.type = type;
            this.quantity = quantity;
            this.coolTime = -1; 
        }
        Task(int type, int quantity, int coolTime){
            this.type = type;
            this.quantity = quantity;
            this.coolTime = coolTime; 
        }
    }
    
    
    private static PriorityQueue<Task> createTaskQueue(List<Integer> tasks){
        Map<Integer, Integer> freq = new HashMap<>(tasks.size());
        for (Integer t : tasks) freq.merge(t, 1, Integer::sum);
        
        PriorityQueue<Task> queue = new PriorityQueue<>((a,b) -> b.quantity - a.quantity);
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()){
            Task task = new Task(entry.getKey(), entry.getValue());
            queue.add(task);
        }
        return queue;
    }
    
    private static void releaseCooledTasks(PriorityQueue<Task> runnableTasks, Queue<Task> coolingTasks, int time){
        while(!coolingTasks.isEmpty() && coolingTasks.element().coolTime <= time){
            Task cooledTask = coolingTasks.remove();
            runnableTasks.add(cooledTask);
        }
    }
    
    private static void dispatchTask(PriorityQueue<Task> runnableTasks, Queue<Task> coolingTasks, int time, int machines, int cooldown){
        Task task = runnableTasks.remove();
        if (task.quantity > machines){
            coolingTasks.add(new Task(task.type, task.quantity - machines, time + cooldown));
        } else {
            int work = task.quantity;
            while (!runnableTasks.isEmpty() && work < machines){
                task = runnableTasks.remove();
                work += task.quantity;
            }
            int remainingWork = work - machines;
            if (remainingWork > 0){
                // coolingTasks.add(new Task(task.type, remainingWork, time + cooldown));
                runnableTasks.add(new Task(task.type, remainingWork));
            }
        }
    }

    public static int naive(List<Integer> tasks, int m, int k) {
        return tasks.size() / m + 1;
    }

    public static int calculateMinimumTimeUnits(List<Integer> tasks, int m, int k) {
        PriorityQueue<Task> runnableTasks = createTaskQueue(tasks);
        Queue<Task> coolingTasks = new ArrayDeque<>();
        int completedIterations = 0;

        while(!runnableTasks.isEmpty() || !coolingTasks.isEmpty()){
            // if (runnableTasks.isEmpty()){
            //     completedIterations = coolingTasks.element().coolTime;
            // }
            releaseCooledTasks(runnableTasks, coolingTasks, completedIterations);
            if(!runnableTasks.isEmpty()){
                dispatchTask(runnableTasks, coolingTasks, completedIterations, m, k);
            }
            
            completedIterations++;
        }
        // return naive(tasks, m, k);
        return completedIterations;
    }

}
