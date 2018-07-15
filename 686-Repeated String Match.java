/*
Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

Note:
The length of A and B will be between 1 and 10000.

Authour: Mindy927*/

class Solution {
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder();
        int min = B.length()/A.length(); //min times A need to repreated to get B
        for (int i=0; i<min; i++){
            sb.append(A);
        }
        
        for (int i=0; i<3; i++){ //case: B.len/A.len = 0 while result = 2
            if(sb.toString().indexOf(B)!=-1) return min+i;
            sb.append(A);
        }
        
        return -1;
    }
}