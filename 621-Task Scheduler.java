/*
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

*/

/*
Method 1: Round Roubin + PriorityQueue  (beats 16%)
(1) HashMap <char, cnt>
     maxHeap (a,b) -> (map.get(b) - map.get(a)
(2) Always pop() top n frequent element from pq(if not empty)
(3) cnt += n+1 is not last round
     cnt += # pop() in last round
Remark: Each round pop n+1 instead of n
*/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>(); //char:cnt
        int cnt = 0;
        
        for (char c:tasks){
            map.put(c, map.containsKey(c)? map.get(c)+1:1);
        }
        
        PriorityQueue<Character> pq = new PriorityQueue<>( (a,b) -> (map.get(b) - map.get(a))); //max heap
        
        for (char c:map.keySet()){
            pq.offer(c);
        }
        
        while (!pq.isEmpty()){ //Aways pop() top n frequent chars in each round
            Set<Character> set = new HashSet<>();
            int cntTemp = 0;
            for(int i=0; i<n+1; i++){
                if (!pq.isEmpty()){
                    char c = pq.poll();
                    set.add(c);
                    cntTemp++;
                    map.put(c, map.get(c)-1);
                }
            }
            
            //put back chars to pq if cnt>1
            for (Character c:set){
                if (map.get(c) > 0) pq.offer(c);
            }
            
            cnt+= !pq.isEmpty()? n+1:cntTemp; //cntTemp for lastRound
        }
        
        return cnt;
    }
}


/*
Method 2: Math
(1) Case 1: no idle: tasks.length()
(2) (n+1 ) * (cnt[25] -1)+ # chars in last round/share maxCnt
     n+1: size for each round
    cnt[25] -1: # of rounds will be full
*/

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        for(char c:tasks) cnt[c -'A']++;
        
        Arrays.sort(cnt);
        int maxFreqCnt = 1; //# of chars share max frequency, i.e, will appear in the last round
        int i=24;
        while (i>=0 && cnt[i]==cnt[25]){
            i--; maxFreqCnt++;
        }
        
        return Math.max(tasks.length, (n+1) * (cnt[25]-1) + maxFreqCnt);
    }
}
