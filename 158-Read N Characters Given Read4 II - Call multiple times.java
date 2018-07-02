/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

Example 1: 

Given buf = "abc"
read("abc", 1) // returns "a"
read("abc", 2); // returns "bc"
read("abc", 1); // returns ""
Example 2: 

Given buf = "abc"
read("abc", 4) // returns "abc"
read("abc", 1); // returns ""

Author: Mindy927 */


/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

//Corner Case: n<4
//Only read4 when current buf4 has done copying to buf
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */

    //Start pointer to read from buf4
    int offset4 = 0;  
    //Last avaliable char in buf4 (exlusive)
    int cnt4 = 0;   
    char[] buf4 = new char[4];
    public int read(char[] buf, int n) {
        int cnt = 0;
        while (cnt < n){
            if (offset4 == cnt4) { //Done reading all chars in buf4
                cnt4 = read4(buf4);   
                offset4 = 0;
            }
            if (cnt4==0) break; 
            while (cnt < n && offset4 < cnt4){
                buf[cnt++] = buf4[offset4++];
            }
        }
        return cnt;
    }
}