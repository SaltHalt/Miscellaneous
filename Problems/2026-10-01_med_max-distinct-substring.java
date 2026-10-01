class Result {

    /*
     * Complete the 'maxDistinctSubstringLengthInSessions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING sessionString as parameter.
     */

    public static int maxDistinctSubstringLengthInSessions(String sessionString) {
        int largestWindow = 0;
        int left = 0;
        Map<Character, Integer> lastSeen = new HashMap<>();
        for (int right = 0; right < sessionString.length(); right++){
            char newChar = sessionString.charAt(right);
            if(newChar == '*'){
                left = right + 1;
            } else {
                //Check if [left:right+1] is a valid window. We know [left:right] is valid via induction so we then check if arr[right] is within the window
                boolean isCharNew = !lastSeen.containsKey(newChar) || (lastSeen.get(newChar) < left);
                if (isCharNew){
                    largestWindow = Math.max(largestWindow, right-left+1);
                    lastSeen.put(newChar, right);
                } else {
                    left = lastSeen.get(newChar) + 1;
                    lastSeen.put(newChar, right);
                }
            }
        }
        return largestWindow;
    }

}
