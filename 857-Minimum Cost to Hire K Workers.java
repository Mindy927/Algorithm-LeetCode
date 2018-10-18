/*
There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.

 

Example 1:

Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
 

Note:

1 <= K <= N <= 10000, where N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10^-5 of the correct answer will be considered correct.

Author: Mindy927*/

class Solution {
    // wage = totalQuality of K workers * max(wage/quality with k workers)
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        //worker[i][0]: wage/quality
        //worker[i][1]: quality
        double[][] workers = new double[n][2];
        for (int i=0; i<n; i++){
            workers[i] = new double[]{(double)wage[i]/quality[i], (double)quality[i]};
        }
        //1. sort workers based on wage/quality 
        Arrays.sort(workers, (a,b) -> Double.compare(a[0], b[0]));
        
        //2. max heap of quality for current k workers
        PriorityQueue<Double> pq = new PriorityQueue<>( (a,b) -> Double.compare(b,a));
        double totalQ = 0; //current total quality
        double res = Integer.MAX_VALUE;
        for (double[] cur:workers){
            totalQ += cur[1];
            pq.offer(cur[1]);
            if (pq.size() > K) totalQ -= pq.poll(); //poll max quality
            if (pq.size() == K) res = Math.min(res, totalQ * cur[0]); //all workers wage/quality is bounded by cur[0] since its the largest, and we need to meet min wage for cur
        }
        
        return res;
    }
}