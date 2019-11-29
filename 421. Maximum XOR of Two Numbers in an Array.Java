
/*
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
*/


// method 1: x ^ y = max, x ^ max = y, 在set中能找到x ^ max(也就是y)，说明有x y 能够形成max
// time complexity: O(30 * 2n) = O(n), space complexity: O(n)
class Solution {
    /*               
    25 = 16 + 8 + 1 11001
    10 = 8 + 2      01010
    8 = 8           01000
    5 = 4 + 1       00101
    3 = 2 + 1       00011
    2 = 2           00010
    a ^ max = b, 对每个a在set里面找到a ^ max是否存在，这个数字也就是b
                     11001 01010 01000 00101 00011 00010
    set:  10000&num: 10000 00000 00000 00000 00000 00000               
    find prev^10000: 00000 10000 10000 10000 10000 10000 如果有一个true, max=10000
                     set有 
    set:  11000&num: 11000 01000 01000 00000 00000 00000
    find prev^11000: 00000 10000 10000 11000 11000 11000 如果有一个true, max=11000
                     set有  
    set:  11100&num: 11000 01000 01000 00100 00000 00000
    find prev^11100: 00100 10100 10100 11000 11100 11100 如果有一个true, max=11100
                     set有
    set:  11110&num: 11000 01010 01000 00100 00010 00010
    find prev^11110: 00110 10100 10110 11010 11100 11100 如果有一个true, max=11100
                     set无  set无 set无 set无  set无 set无
    set:  11111&num: 11001 01010 01000 00101 00011 00010
    find prev^11111: 00110 10101 10111 11010 11100 11101 如果有一个true, max=11100
                     set无  set无 set无 set无  set无 set无
    */         
    public int findMaximumXOR(int[] nums) {
        int mask = 0;
        int max = 0;
        for(int i = 30; i >= 0; i--) {
            // 先设置最左边的bit为1，其余为0
            // 然后设置左边第二位为1，其余为0...
            int iBitLeft = 1 << i;
            // 让mask = 10000, 11000, 11100, 11110, 11111
            mask = mask | iBitLeft;
            Set<Integer> set = new HashSet<>();
            // set中保存的是num & mask,只保留前几位1
            for(int num: nums) {
                set.add(mask & num);
            }
            for(int num: set) {
                // max | iBitLeft为这个尝试的max更大的结果
                // 如果num ^ max可以在原set里面找到num1，说明num ^ num1 = max
                if(set.contains(num ^ (max | iBitLeft))) {
                    // max又有一位可以设为1
                    max = max | iBitLeft;
                    break;
                }
            }
        }
        return max;
    }
}


// method 2: Trie, left == 0, right == 1; 
// dfs: priority: (left + right) > (left + left, right + right), you have to try all possibility
// time complexity: l = the maximum bit length of num = 32, build trie = O(32n + 2^32), 
// space complexity: O(2^32)

class Solution {
    public class TrieNode {
        TrieNode left;
        TrieNode right;
    }
    
    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        for(int num: nums) {
            TrieNode curr = root;
            for(int i = 31; i >= 0; i--) {
                // ith bit == 0
                if((num & (1 << i)) == 0) {
                    if(curr.left == null) {
                        curr.left = new TrieNode();
                    }
                    curr = curr.left;
                }
                else {
                    if(curr.right == null) {
                        curr.right = new TrieNode();
                    }
                    curr = curr.right;
                }
            }
        }
        int[] result = new int[1];
        dfs(root, root, 31, 0, result);
        return result[0];
    }
    public void dfs(TrieNode node1, TrieNode node2, int layer, int value, int[] result) {
        result[0] = Math.max(result[0], value);
        if(layer == -1) {
            return;
        }
        boolean flag = true;
        if(node1.left != null && node2.right != null) {
            //System.out.println("left, right");
            flag = false;
            dfs(node1.left, node2.right, layer - 1, value | (1 << layer), result);
        }
        if(node1.right != null && node2.left != null) {
            //System.out.println("right, left");
            flag = false;
            dfs(node1.right, node2.left, layer - 1, value | (1 << layer), result);
        }
        if(flag && node1.left != null && node2.left != null) {
            //System.out.println("left, left");
            dfs(node1.left, node2.left, layer - 1, value, result);
        }
        if(flag && node1.right != null && node2.right != null) {
            //System.out.println("right, right");
            dfs(node1.right, node2.right, layer - 1, value, result);
        }
    } 
}