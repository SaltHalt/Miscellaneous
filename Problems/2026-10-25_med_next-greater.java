public static List<List<Integer>> findNextGreaterElementsWithDistance(List<Integer> readings) {
	Deque<int[]> unmatched = new ArrayDeque<>();
	List<List<Integer>> result = new ArrayList<>(Collections.nCopies(readings.size(), null));
	
	for (int i = 0; i < readings.size(); i++){
		int current = readings.get(i);
		while (!unmatched.isEmpty() && unmatched.element()[0] < current){
			int[] prior = unmatched.pop();
			result.set(prior[1], List.of(current, i-prior[1]));
		}
		unmatched.push(new int[]{current, i});
	}
	
	while (!unmatched.isEmpty()){
		int[] item = unmatched.pop();
		result.set(item[1], List.of(-1, -1));
	}
	return result;
}
