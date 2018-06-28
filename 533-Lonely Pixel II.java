/*
Given a picture consisting of black and white pixels, and a positive integer N, find the number of black pixels located at some specific row R and column C that align with all the following rules:

Row R and column C both contain exactly N black pixels.
For all rows that have a black pixel at column C, they should be exactly the same as row R
The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

Example:
Input:                                            
[['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'W', 'B', 'W', 'B', 'W']] 

N = 3
Output: 6
Explanation: All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
        0    1    2    3    4    5         column index                                            
0    [['W', 'B', 'W', 'B', 'B', 'W'],    
1     ['W', 'B', 'W', 'B', 'B', 'W'],    
2     ['W', 'B', 'W', 'B', 'B', 'W'],    
3     ['W', 'W', 'B', 'W', 'B', 'W']]    
row index

Take 'B' at row R = 0 and column C = 1 as an example:
Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.

Author: Mind927 */


class Solution {
    public int findBlackPixel(char[][] picture, int N) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // col j:list of rows has B at col j
        int m = picture.length;
        int n = picture[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        int count=0;
            
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (picture[i][j] == 'B') {
                    row[i]++;
                    col[j]++;
                    map.putIfAbsent(j, new ArrayList<>());
                    map.get(j).add(i);
                }
            }
        }
        
        for (int i=0; i<m; i++){
            if (row[i]!=N) continue; //check ealier 
            for (int j=0; j<n; j++){
                if (col[j]!=N) continue;
                if (picture[i][j] == 'B'){
                    boolean valid = true; // break jump out of current loop, count++ anyway, Need flag!!
                    for (int k:map.get(j)){
                        if (!Arrays.equals(picture[i],picture[k])){
                            valid = false; break; 
                        }
                    }
                    if (valid) count++;
                }
            }
        }
        
        return count;
    }
}