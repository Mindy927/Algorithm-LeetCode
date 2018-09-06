/*
A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx

  Author: Mindy927 */

/*
There are five kinds of valid byte type: 0**, 10**, 110**,1110** and 11110**
if a byte belongs to one of them:
1 : if it is type 0, continue
2 : if it is type 2 or 3 or 4, check whether the following 1, 2, and 3 byte(s) are of type 1 or not
                if not, return false;
*/

class Solution {
    public boolean validUtf8(int[] data) {
       int required = 0; //number of 10XXXXXX required
        
       for (int i=0;i<data.length; i++) {
           //0XXXXXXX
            if ( (data[i] >> 7) == 0 ) continue;  
          
           //10XXXXXX
            if ( (data[i] >> 6) == 2 ) {  
                if (required > 0) required--;
                else return false;  
            } 
           //110XXXXX, 1110XXX, 11110XXX
           else{ 
                if ( required!=0 ) return false;
                int zeroIdx = 2;
                while (((data[i] >> (7-zeroIdx)) & 1) != 0) zeroIdx++;
                if (zeroIdx > 4) return false;
                required = zeroIdx-1;
            }
        } 
    
     return required == 0;
    }
}