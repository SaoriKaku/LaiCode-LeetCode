
/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:
Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4

Example 2:
Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/


// method 1: Math, slope + intercept, y = ax + b
// time complexity: O(n^2), space complexity: O(n)
class Solution {
    /*
    private int generateGCD(int a,int b) {
        if (b==0) return a;
        return generateGCD(b,a%b);
    }
    (2, 6) -> (6, 2) -> (2, 0)
    (3, 4) -> (4, 3) -> (3, 1) -> (1, 0)
    */
    class Pair {
        int y;
        int x;
        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;
            if(!(obj instanceof Pair)) return false;
            Pair a = (Pair)obj;
            return this.x == a.x && this.y == a.y;
        }
        @Override
        public int hashCode() {
            return 31 * this.x + this.y;
        }
    }
    public int maxPoints(int[][] points) {
        if(points == null || points.length < 1) {
            return 0;
        }
        int result = 0; 
        for(int i = 0; i < points.length; i++) {
            // map<key: pair<y1-y2, x1-x2>; value = count of this pair whose k = key>
            Map<Pair, Integer> map = new HashMap<>();
            int x1 = points[i][0];
            int y1 = points[i][1];
            int dupPoint = 1;
            int currMax = 0;
            for(int j = i + 1; j < points.length; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                if(x1 == x2 && y1 == y2) {
                    dupPoint += 1;
                }
                else {
                    Pair currSlope = getSlope(x1, y1, x2, y2);
                    if(map.containsKey(currSlope)) {
                        map.put(currSlope, map.get(currSlope) + 1);
                    }
                    else {
                        map.put(currSlope, 1);
                    }
                    //map.put(currSlope, map.getOrDefault(map.get(currSlope), 0) + 1);
                    currMax = Math.max(currMax, map.get(currSlope));
                }
            }
            result = Math.max(result, currMax + dupPoint);
            //System.out.println("map: " + map);
        }
        return result;
    }
    public Pair getSlope(int x1, int y1, int x2, int y2) {
        int y = y1 - y2;
        int x = x1 - x2;
        if(y == 0) {
            return new Pair(y1, 0);
        }
        if(x == 0) {
            return new Pair(0, x1);
        }
        int k = getGCF(y, x);
        return new Pair(y / k, x / k);
    }
    // get great common factor/least common multiple
    public int getGCF(int a, int b) {
        if(b == 0) {
            return a;
        }
        return getGCF(b, a % b);
    }
}


