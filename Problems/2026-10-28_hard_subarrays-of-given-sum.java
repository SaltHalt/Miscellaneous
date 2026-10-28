public static long countSubarraysWithSumAndMaxAtMost(List<Integer> nums, long k, long M) {
    long subArrayCount = 0;
    long runningSum = 0;
    Map<Long, Integer> prefixSumCounts = new HashMap<>();
    for (int num : nums){
        if (num > M){
            runningSum = 0;
            prefixSumCounts = new HashMap<>();  
        } else {
            prefixSumCounts.merge(runningSum, 1, Integer::sum);
            runningSum += num;
            subArrayCount += prefixSumCounts.getOrDefault(runningSum-k, 0);
        }
    }
    return subArrayCount;
}
