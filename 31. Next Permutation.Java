
/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/


// time compleixty: O(n), space complexity: O(1)
class Solution {
    /*
    字典序：123，132，213，231，312，321
    中间的规则是从小到大，再从大到小，从后往前，找到峰值的那个点
    firstSmall = 这个点后面往前都是从小到大，所以这个点的值是从后面开始第一个比它前面的值大的;
    firstLarge = 从峰值后面的数字开始找，找到closest larget than firstSmall;
    交换firstSmall，firstLarge，然后把峰值后面的数字排列;
    */
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return;
        }
        int firstSmall = -1;
        for(int i = nums.length - 1; i >= 1; i--) {
            if(nums[i] > nums[i - 1]) {
               firstSmall = i - 1;
                break;
            }
        }
        if(firstSmall == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int firstLarge = -1;
        for(int i = nums.length - 1; i > firstSmall; i--) {
            if(nums[i] > nums[firstSmall]) {
                firstLarge = i;
                break;
            }
        }
        swap(nums, firstSmall, firstLarge);
        reverse(nums, firstSmall + 1, nums.length - 1);
        return;
    }
    public void reverse(int[] array, int i, int j) {
        while(i < j) {
            swap(array, i++, j--);
        }
    }
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


