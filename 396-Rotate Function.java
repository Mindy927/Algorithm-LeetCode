
/*
Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

Calculate the maximum value of F(0), F(1), ..., F(n-1).
Author Mindy927*/


class Solution {
    public int maxRotateFunction(int[] A) {
        if (A.length==0) return 0;
        int sum = 0;
        int temp = 0;
        int n = A.length;
        
        for (int i=0; i<n; i++){
            sum += A[i];
            temp += i*A[i];
        }
        
        int max = temp;
        for (int i=1; i<n; i++){
            temp = temp + sum - n *A[n-i];
            max = Math.max(max, temp);
        }
        
        return max;
    }
}