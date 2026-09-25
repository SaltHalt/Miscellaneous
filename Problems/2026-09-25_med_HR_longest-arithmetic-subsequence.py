#!/bin/python3

import math
import os
import random
import re
import sys



#
# Complete the 'findLongestArithmeticProgression' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER_ARRAY arr
#  2. INTEGER k
#
# [-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

def findLongestArithmeticProgression(arr, k):
    # Write your code here
    largest = 0 
    subSeqLens = {}
    subSeqLatest = {}
    for x in sorted(arr):
        residue = x % k
        if residue not in subSeqLatest:
            subSeqLatest[residue] = x
            subSeqLens[residue] = 1
        else:
            last = subSeqLatest[residue] 
            if (last == x):
                pass
            elif(last + k == x):
                subSeqLatest[residue] = x
                subSeqLens[residue] += 1
            else: #previous sequence got lost
                largest = max(largest, subSeqLens[residue])
                subSeqLatest[residue] = x
                subSeqLens[residue] = 1
                
    return max([largest] + [*subSeqLens.values()])
                
if __name__ == '__main__':
    arr_count = int(input().strip())

    arr = []

    for _ in range(arr_count):
        arr_item = int(input().strip())
        arr.append(arr_item)

    k = int(input().strip())

    result = findLongestArithmeticProgression(arr, k)

    print(result)

