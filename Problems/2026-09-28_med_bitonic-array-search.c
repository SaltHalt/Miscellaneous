/*
 * Complete the 'findPeakIndex' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY counts as parameter.
 */

/**
I also considered a more convoluted solution that splits the array into 3 segments rather than two. It was worse because in the worst case you may still be left with 2/3 of the array. Additionally, it would have introduced more cases to consider.
*/
int findPeakIndex(int counts_count, int* counts) {
    int left = 0;
    int right = counts_count -1;
    while (left < right){
        int mid = (left + right)/2;
        bool isLeftIncreasing = (mid == 0)? true : counts[mid - 1] < counts[mid];
        bool isRightIncreasing = (mid == counts_count -1)? false : counts[mid] < counts[mid + 1];
        if (isLeftIncreasing && !isRightIncreasing){
            return mid;
        } else if (isRightIncreasing){
            left = mid+1;
        } else if (!isLeftIncreasing){
            if (left == mid){
                return left;
            } else {
                right = mid-1;
            } 
        }
    }
    return left;
}

