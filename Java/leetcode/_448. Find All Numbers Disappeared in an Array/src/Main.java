import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int nums[] = {4,3,2,7,8,2,3,1};
        List<Integer> res = sol.findDisappearedNumbers(nums);
        for (Integer el:
             res) {
            System.out.println(el);
        }
    }
} //

//важно смотреть в таких задачах оставляется ли бит знака как лишний
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i])-1] > 0) {
                nums[Math.abs(nums[i])-1] = -nums[Math.abs(nums[i])-1];
            }
        }
        List<Integer> res = new ArrayList<>();//+1
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>0){
                res.add(i+1);
            }
        }

    return res;
    }

}
