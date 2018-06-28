/*


Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:
Input: 
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.

Author:Mindy927*/

class Solution {
    public int findLonelyPixel(char[][] picture) {
        int m = picture.length;
        int n = picture[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        int count = 0;
        
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (picture[i][j] == 'B') {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (picture[i][j] == 'B' && row[i]==1 && col[j] == 1 ) {
                    count++;
                }
            }
        }
        
        return count;
    }
}