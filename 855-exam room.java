/*
In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.

 

Example 1:

Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]
Explanation:
ExamRoom(10) -> null
seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student​​​​​​​ sits at the last seat number 5.
​​​​​​​

Note:

1 <= N <= 10^9
ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.

Author: Mindy927*/

//heap of empty slots based on max dist to neighbors
//fill 0, n-1 first 
class ExamRoom {
    class Interval{
        int x, y; //two adjacent people x, y
        int dist; //if seat in current inverval, max dist to nearest neighbours
        public Interval(int x, int y){ 
            this.x = x;
            this.y = y;
            if (x == -1) this.dist = y;
            else if ( y == N ) this.dist = N - x - 1;
            else this.dist = Math.abs(x-y)/2;
        }
    }
    
    int N;
    PriorityQueue<Interval> pq;
    public ExamRoom(int N) {
        this.N = N;
        pq = new PriorityQueue<Interval>(new Comparator<Interval>(){
           public int compare(Interval a, Interval b){
               if (a.dist != b.dist) return b.dist - a.dist; //max heap of distance to nearest neighbors
               else return a.x- b.x; //mid heap of index
           } 
        });
        pq.offer(new Interval(-1, N));
    }
    
    public int seat() {
        Interval cur = pq.poll();
        int mid = 0;
        if (cur.x == -1) mid = 0; //deal with 0,n-1 
        else if (cur.y == N) mid = N - 1;
        else mid = (cur.x + cur.y) / 2; 
        
        pq.offer(new Interval(cur.x, mid));
        pq.offer(new Interval(mid, cur.y));
        return mid;
    }
    
    //O(n) find intervals before/after p, remove and merge
    public void leave(int p) {
        Interval left = null, right = null;
        List<Interval> list = new ArrayList<>(pq);
        for (Interval cur:list){
            if (cur.y == p) left = cur;
            if (cur.x == p) right = cur;
        }
        
        pq.remove(left);
        pq.remove(right);
        
        //merge
        pq.offer(new Interval(left.x, right.y));
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */