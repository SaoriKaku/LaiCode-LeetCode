
/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

// method 1: minHeap
// time complexity: O(n + (n - k)logk), space complexity: O(n)
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        /*
        1. HashMap<Key = integer in input array, Value = count of integer in input array>
        2. use minHeap<Map.entrySet>
        */
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int curr: nums) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> (e1.getValue() - e2.getValue()));
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(minHeap.size() < k) {
                minHeap.offer(entry);
                continue;
            }
            if(entry.getValue() > minHeap.peek().getValue()) {
                if(minHeap.size() == k) {
                    minHeap.poll();
                }
                minHeap.offer(entry);
            }
        }
        //System.out.println(minHeap.size());
        while(!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        //System.out.println(result.size());
        return result;
    }
}


// method 2: maxHeap
// time complexity: O(n + nlogn), space complexity: O(n)
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        /*
        1. HashMap<Key = integer in input array, Value = count of integer in input array>
        2. use minHeap<Map.entrySet>
        */
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int curr: nums) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((e1, e2) -> (e2.getValue() - e1.getValue()));
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            maxHeap.offer(entry);
        }
        //System.out.println(minHeap.size());
        for(int i = 0; i < k; i++) {
            result.add(maxHeap.poll().getKey());
        }
        //System.out.println(result.size());
        return result;
    }
}


// method 3: optimized maxHeap
// time complexity: O(n + nlogn), space complexity: O(n)
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(); // key: nums[i]; value: count
        for(int curr: nums) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        for(int curr: map.keySet()) {
            maxHeap.offer(curr);
        }
        while(k > 0) {
            result.add(maxHeap.poll());
            k--;
        }
        return result;
    }
}


// method 4: bucket Sort
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        // key: nums, value: count
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;
        for(int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            maxCount = Math.max(maxCount, map.get(num));
        }
        // index: count, value: list of num
        List<Integer>[] counts = new List[maxCount + 1];
        for(int key: map.keySet()) {
            int index = map.get(key);
            if(counts[index] == null) {
                counts[index] = new ArrayList<>();
            }
            counts[index].add(key);
        }
        List<Integer> result = new ArrayList<>();
        for(int i = maxCount; i >= 0; i--) {
            if(counts[i] != null) {
                for(int j = 0; j < counts[i].size(); j++) {
                    result.add(counts[i].get(j));
                }
            }
            if(result.size() == k) {
                break;
            }
        }
        int[] solu = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            solu[i] = result.get(i);
        }
        return solu;
    }
}




