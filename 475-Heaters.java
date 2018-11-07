/*
Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.
Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
Author: Mindy927 */

//two pointers, O(nlgn)
//for each house i, find two closest heaters, house i+1 start searching from left heater for house i
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        Arrays.sort(houses);
        int j = 0, res = 0;
        
        for (int i=0; i<houses.length; i++){
            //heaters[j+1] >= houses[i] while heaters[j]< houses[i]
            while (j+1 < heaters.length && heaters[j+1] < houses[i]) j++;
            int cur = heaters[j]; // j is the last heater <= house[i]
            int next = j+1<heaters.length? heaters[j+1]: heaters[j]; //last heater
            res = Math.max(res, Math.min(Math.abs(houses[i]-cur), Math.abs(next-houses[i])));
        }
        
        return  res;
    }
}

//binary search, O(nlgn)
//for each house, binary search for closest heaters
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        
        int res = 0;
        for (int h:houses){
            int idx = binarySearch(heaters, h);
            int leftDist = idx==-1? Integer.MAX_VALUE: h - heaters[idx];
            int rightDist = idx+1<heaters.length? heaters[idx+1]-h: Integer.MAX_VALUE;
            res = Math.max(res, Math.min(leftDist, rightDist));
        }
        
        return res;
    }
    
    //return index of closest heater on the left
    public int binarySearch(int[] heaters, int pos){
        int left = 0, right = heaters.length-1;
        while ( left+1 < right ){
            int mid = (left + right)/2;
            if (heaters[mid] > pos) right = mid;
            else left = mid;
        }
        
        return heaters[right] <= pos? right:heaters[left]<=pos? left:-1;
    }
}
