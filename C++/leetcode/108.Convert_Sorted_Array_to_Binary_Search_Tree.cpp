#include <iostream>
#include <vector>

// если кажется что нужно делать отдельное разделение на контейнеры из одного
// контейнера, то иногда кажется и если есть возможность, то лучше по индексам

// если функция неудобна, но можно сделать свою и запустить в неудобной свою,
// конечно это применимо только в контексте зааний на литкоде

// с деревьями делать выборки с большим количеством данных, >5 хотя бы

struct TreeNode {
  int val;
  TreeNode *left;
  TreeNode *right;
  TreeNode() : val(0), left(nullptr), right(nullptr) {}
  TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
  TreeNode(int x, TreeNode *left, TreeNode *right)
      : val(x), left(left), right(right) {}
};

class Solution {
public:
  TreeNode *sortedArrayToBST(std::vector<int> &nums) {
    return createBST(nums, 0, nums.size() - 1);
  }

  TreeNode *createBST(std::vector<int> &nums, int leftIndex, int rightIndex) {
    int diff = rightIndex - leftIndex;
    if (diff == 0) {
      return new TreeNode(nums[leftIndex]);
    } else if (diff == 1) {
      return new TreeNode(nums[rightIndex], new TreeNode(nums[leftIndex]),
                          nullptr);
    }
    int midIndex = leftIndex + diff / 2;
    return new TreeNode(nums[midIndex],
                        createBST(nums, leftIndex, midIndex - 1),
                        createBST(nums, midIndex + 1, rightIndex));
  }
};

int main() {
  Solution solution;
  std::vector<int> nums = {0, 1, 2, 3, 4, 5};

  solution.sortedArrayToBST(nums);
}