/*
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].
Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true
UPDATE (2017/1/8):
The seqs parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the code definition to get the latest changes.


Author: Mindy927*/

/*
topo sort with indegree map and preReq map(preReq:neighbor)
for seqs, add adjacent pair to preReq map
*/
lass Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int n = org.length;
        int[] indegree = new int[n+1];
        Map<Integer, Set<Integer>> map = new HashMap<>(); //pre-req: all neighbors
        
        //build indegree array and preReq map
        for(List<Integer> seq:seqs){
            if (seq.size()==0) continue;
            if (n==1 && seq.size() == 1 && seq.get(0) == 1) indegree[1] = 1;
            if (n==1 && seq.size() > 1) return false;
            if (seq.get(0) <=0 || seq.get(0)>n) return false;
            for(int i=0; i<seq.size()-1; i++){
                int preReq = seq.get(i);
                int nei = seq.get(i+1);
                if (preReq <=0 || preReq > n || nei <=0 || nei > n) return false;
                if (!map.containsKey(preReq)) map.put(preReq, new HashSet<>());
                if (map.get(preReq).contains(nei)) continue;//duplicate
                map.get(preReq).add(nei);
                indegree[nei]++;
            }
        }
        
        if (n == 1) return indegree[1] == 1;
        
        Queue<Integer> q = new LinkedList<>();
        for (int i=1; i<=n; i++){
            if (indegree[i] == 0) q.offer(i);
        }
        
        int[] res = new int[n];
        int idx = 0;
        while (!q.isEmpty()){
            if (q.size() > 1 || idx == n) return false; //multiple choice in this round -> doesnt exist unique order
            int cur = q.poll();
            res[idx++] = cur;
            if (!map.containsKey(cur)) continue;
            for(int nei:map.get(cur)){
               indegree[nei]--;
                if (indegree[nei] == 0) q.offer(nei);
            }
        }
        
        //check indegree are all zero
        if (!Arrays.equals(indegree, new int[n+1])) return false;
        
        return Arrays.equals(res, org);
    }
}